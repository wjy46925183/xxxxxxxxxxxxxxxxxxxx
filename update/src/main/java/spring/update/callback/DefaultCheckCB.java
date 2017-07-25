package spring.update.callback;

import android.app.Activity;
import android.app.Dialog;

import java.lang.ref.WeakReference;

import spring.update.UpdateBuilder;
import spring.update.Updater;
import spring.update.creator.DialogCreator;
import spring.update.model.Update;
import spring.update.util.Recycler;
import spring.update.util.SafeDialogOper;

/**
 * default check callback to receive update event send by {@link org.lzh.framework.updatepluginlib.business.UpdateWorker}
 */
public class DefaultCheckCB implements UpdateCheckCB,Recycler.Recycleable {

    private WeakReference<Activity> actRef = null;
    private UpdateBuilder builder;
    private UpdateCheckCB checkCB;

    public DefaultCheckCB(Activity context) {
        this.actRef = new WeakReference<>(context);
    }

    public void setBuilder (UpdateBuilder builder) {
        this.builder = builder;
        this.checkCB = builder.getCheckCB();
    }

    /**
     * Receive and pass has_update event send by {@link org.lzh.framework.updatepluginlib.business.UpdateWorker#sendHasUpdate(Update)}<br>
     * Create update dialog if should be shown according to {@link org.lzh.framework.updatepluginlib.strategy.UpdateStrategy#isShowUpdateDialog(Update)}<br>
     */
    @Override
    public void hasUpdate(Update update) {
        if (checkCB != null) {
            checkCB.hasUpdate(update);
        }

        if (!builder.getStrategy().isShowUpdateDialog(update)) {
            Updater.getInstance().downUpdate(actRef.get(),update,builder);
            return;
        }

        // replace activity when necessary
        Activity current = actRef.get();
        if (builder.getReplaceCB() != null) {
            current = builder.getReplaceCB().replace(current);
        }

        DialogCreator creator = builder.getUpdateDialogCreator();
        creator.setBuilder(builder);
        creator.setCheckCB(builder.getCheckCB());
        Dialog dialog = creator.create(update,current);
        SafeDialogOper.safeShowDialog(dialog);

        Recycler.release(this);
    }

    /**
     * Receive and pass no_update event send by {@link UpdateWorker#sendNoUpdate()}
     */
    @Override
    public void noUpdate() {
        if (checkCB != null) {
            checkCB.noUpdate();
        }

        Recycler.release(this);
    }

    /**
     * Receive and pass check_error event send by {@link UpdateWorker#sendOnErrorMsg(int, String)}
     */
    @Override
    public void onCheckError(int code, String errorMsg) {
        if (checkCB != null) {
            checkCB.onCheckError(code,errorMsg);
        }

        Recycler.release(this);
    }

    /**
     * will be never invoke
     */
    @Override
    public void onUserCancel() {
        if (checkCB != null) {
            checkCB.onUserCancel();
        }

        Recycler.release(this);
    }

    @Override
    public void onCheckIgnore(Update update) {
        if (checkCB != null) {
            checkCB.onCheckIgnore(update);
        }

        Recycler.release(this);
    }

    @Override
    public void release() {
        this.actRef = null;
        this.builder = null;
        this.checkCB = null;
    }
}
