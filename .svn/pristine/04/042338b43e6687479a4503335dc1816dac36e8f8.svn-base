package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.wallet.model.RechargeBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.RechargeErrorPresenter;
import com.dlg.viewmodel.Wallet.presenter.RechargePresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.RechargeServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：邹前坤
 * 主要功能：充值 失败 退出 等异常的viewmodle
 * 创建时间：2017/7/17 17:24
 */
public class RechargeErrorViewModel extends BaseViewModel<JsonResponse<List<String>>> {
    private final RechargeServer mServer;
    private RechargeErrorPresenter rechargeErrorPresenter;
    public RechargeErrorViewModel(Context context, RechargeErrorPresenter rechargeErrorPresenter) {
        mContext = context;
        mServer = new RechargeServer(context);
        this.rechargeErrorPresenter=rechargeErrorPresenter;
    }


    public void rechargeError(String businessNumber) {
        HashMap<String,String> map = new HashMap<>();
        map.put("businessNumber",businessNumber);
        mSubscriber = getMapSubscriber();
        mServer.rechargeError(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<String>>> getMapSubscriber(){
        return new RXSubscriber<JsonResponse<List<String>>,List<String>>(null) {
            @Override
            public void requestNext(List<String> rechargeBeens) {
                if(null != rechargeBeens && rechargeBeens.size() > 0){
                    rechargeErrorPresenter.rechargeError(rechargeBeens.get(0));
                }
            }
        };
    }
}
