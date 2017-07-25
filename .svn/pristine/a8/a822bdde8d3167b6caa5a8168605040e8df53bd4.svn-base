package com.dlg.viewmodel.user;

import android.content.Context;

import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.server.UserServer;
import com.dlg.viewmodel.user.presenter.ResetPsdPresenter;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 重置密码
 * Created by xiaoming on 2017/7/18.
 */

public class ResetPsdViewModel extends BaseViewModel<JsonResponse<String>> {
    private BasePresenter basePresenter;
    private final UserServer mServer;
    private ResetPsdPresenter resetPsdPresenter;

    public ResetPsdViewModel(Context context, BasePresenter basePresenter, ResetPsdPresenter presenter) {
        this.basePresenter = basePresenter;
        mServer = new UserServer(context);
        this.resetPsdPresenter=presenter;
    }

    public void getReset(String phone,String psd,String code) {
        HashMap<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("password",psd);
        map.put("code",code);
        map.put("type", "1");
        map.put("isLogin","1");

        mSubscriber=getReset();
        mServer.getResetResult(mSubscriber,map);
    }
    private Subscriber<JsonResponse<String>> getReset(){
        return new RXSubscriber<JsonResponse<String>, String>(basePresenter) {
            @Override
            public void requestNext(String result) {
                resetPsdPresenter.resetPsd(result);
            }
        };
    }
}
