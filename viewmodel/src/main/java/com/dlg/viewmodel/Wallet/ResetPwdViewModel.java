package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.wallet.model.InvoiceListBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.InvoicePresenter;
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

public class ResetPwdViewModel extends BaseViewModel<JsonResponse<List<String>>>{
    private BasePresenter basePresenter;
    private final WalletServer mServer;
    private ResetPwdPresenter invoicePresenter;

    public ResetPwdViewModel(Context context, BasePresenter basePresenter, ResetPwdPresenter invoicePresenter) {
        this.basePresenter = basePresenter;
        mContext = context;
        mServer = new WalletServer(context);
        this.invoicePresenter=invoicePresenter;
    }

    /**
     * 重置密码 验证验证码 是否有效
     */
    public void getVerifyVerificationCode(String mobile,String verifyCode) {
        HashMap<String,String> map = new HashMap<>();
        map.put("phone", mobile);
        map.put("verifyCode", verifyCode);
        mSubscriber = getMapSubscriber();
        mServer.VerifyVerificationCode(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<String>>> getMapSubscriber(){
        return new RXSubscriber<JsonResponse<List<String>>,List<String>>(basePresenter) {
            @Override
            public void requestNext(List<String> walletBeans) {

                invoicePresenter.geterifyCodeOk(true);
            }
        };
    }
}
