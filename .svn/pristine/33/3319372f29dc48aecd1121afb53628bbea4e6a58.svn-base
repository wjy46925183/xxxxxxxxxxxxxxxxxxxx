package com.dlg.viewmodel.server;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dlg.data.wallet.factory.CashFactory;
import com.dlg.data.wallet.interactor.CashInteractor;
import com.dlg.data.wallet.model.BindBean;
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
 * 创建时间：2017/7/12 13:23
 */
public class CashServer {
    CashFactory factory ;

    public CashServer(Context appContext) {
        this(new CashFactory(appContext));
    }

    public CashServer(CashFactory factory) {
        this.factory = factory;
    }

    public void getBindList(Subscriber<JsonResponse<List<BindBean>>> mSubscriber, HashMap<String, String> map) {
        CashInteractor interactor = factory.getBindList(WalletUrl.PAY_BIND_LIST + JSON.toJSONString(map), true);
        interactor.getBindList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }
}
