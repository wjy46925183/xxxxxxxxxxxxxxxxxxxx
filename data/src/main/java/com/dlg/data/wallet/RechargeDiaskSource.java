package com.dlg.data.wallet;

import com.alibaba.fastjson.JSON;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.wallet.interactor.RechargeInteractor;
import com.dlg.data.wallet.model.RechargeBean;
import com.dlg.data.wallet.url.WalletUrl;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Observable;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/11 10:39
 */
public class RechargeDiaskSource implements RechargeInteractor{
    private final ObjectCache objectCache;

    public RechargeDiaskSource(ObjectCache objectCache) {
        this.objectCache = objectCache;
    }

    @Override
    public Observable<JsonResponse<List<RechargeBean>>> getPay(HashMap<String, String> map) {
        return this.objectCache.getList(WalletUrl.WALLET_PAY + JSON.toJSONString(map), JsonResponse.class,RechargeBean.class);
    }

    @Override
    public Observable<JsonResponse<List<String>>> rechargeError(HashMap<String, String> map) {
        return this.objectCache.getList(WalletUrl.RECHARGE_ERROE + JSON.toJSONString(map), JsonResponse.class,String.class);

    }
}