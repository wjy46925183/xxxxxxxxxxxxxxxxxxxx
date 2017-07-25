package com.dlg.viewmodel.server;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dlg.data.wallet.factory.RechargeFactory;
import com.dlg.data.wallet.interactor.RechargeInteractor;
import com.dlg.data.wallet.model.RechargeBean;
import com.dlg.data.wallet.url.WalletUrl;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/11 10:29
 */
public class RechargeServer {
    RechargeFactory rechargeFactory;

    public RechargeServer(Context appContext) {
        this(new RechargeFactory(appContext));
    }

    public RechargeServer(RechargeFactory rechargeFactory) {
        this.rechargeFactory = rechargeFactory;
    }

    public void pay(Subscriber<JsonResponse<List<RechargeBean>>> mSubscriber, HashMap<String, String> map) {
        RechargeInteractor walletInteractor = rechargeFactory.getWalletBalance(WalletUrl.WALLET_PAY + JSON.toJSONString(map), true);
        walletInteractor.getPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }
    public void rechargeError(Subscriber<JsonResponse<List<String>>> mSubscriber, HashMap<String, String> map) {
        RechargeInteractor walletInteractor = rechargeFactory.getWalletBalance(WalletUrl.RECHARGE_ERROE + JSON.toJSONString(map), true);
        walletInteractor.rechargeError(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }
}
