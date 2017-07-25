package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.common.utils.Base64Utils;
import com.common.utils.MD5Utils;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.ResetPwdInputPresenter;
import com.dlg.viewmodel.Wallet.presenter.ResetPwdPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.WalletServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：邹前坤
 * 主要功能：重置密码
 * 创建时间：2017/7/17 09:20
 */

public class ResetPwdInputViewModel extends BaseViewModel<JsonResponse<List<String>>>{
    private BasePresenter basePresenter;
    private final WalletServer mServer;
    private ResetPwdInputPresenter invoicePresenter;

    public ResetPwdInputViewModel(Context context, BasePresenter basePresenter, ResetPwdInputPresenter invoicePresenter) {
        this.basePresenter = basePresenter;
        mContext = context;
        mServer = new WalletServer(context);
        this.invoicePresenter=invoicePresenter;
    }

    /**
     * 重置密码
     */
    public void getResetPwd(String passWord,String identifyingCode) {
        HashMap<String,String> map = new HashMap<>();

        map.put("password",  Base64Utils.getBase64(MD5Utils.MD5Encode(passWord) + "/."));
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        //重置密码
        map.put("code", identifyingCode);

        mSubscriber = getMapSubscriber();
        mServer.getResetPwd(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<String>>> getMapSubscriber(){
        return new RXSubscriber<JsonResponse<List<String>>,List<String>>(basePresenter) {
            @Override
            public void requestNext(List<String> walletBeans) {

                invoicePresenter.getResetCodeOk(true);
            }
        };
    }
}
