package com.dlg.viewmodel.server;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dlg.data.wallet.factory.PayFactory;
import com.dlg.data.wallet.interactor.PayInteractor;
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
 * 创建时间：2017/7/14 10:03
 */
public class PayViewServer {
    PayFactory factory;

    public PayViewServer(Context appContext) {
        this(new PayFactory(appContext));
    }

    public PayViewServer(PayFactory factory) {
        this.factory = factory;
    }
    /**
     * 判断用户是否设置过支付密码
     * @param mSubscriber
     * @param map
     */
    public void judgePwd(Subscriber<JsonResponse<List<Boolean>>> mSubscriber, HashMap map) {
        PayInteractor interactor = factory.createHomeData(WalletUrl.HAS_SET_PAY_PWD + JSON.toJSONString(map), true);
        interactor.judgePwd(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }
}
