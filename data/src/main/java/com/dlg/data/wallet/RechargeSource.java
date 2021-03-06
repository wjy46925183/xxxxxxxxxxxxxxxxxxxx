package com.dlg.data.wallet;

import com.alibaba.fastjson.JSON;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.wallet.interactor.RechargeInteractor;
import com.dlg.data.wallet.model.RechargeBean;
import com.dlg.data.wallet.url.WalletUrl;
import com.http.okgo.OkGo;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonConvert;
import okhttp.rx.JsonResponse;
import okhttp.rx.RxAdapter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/11 10:41
 */
public class RechargeSource implements RechargeInteractor{
    private final ObjectCache objectCache;

    /**
     */
    public RechargeSource(ObjectCache objectCache) {
        this.objectCache = objectCache;
    }

    private Action1<Object> saveToCacheAction(final String key) {
        return new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o != null) {
                    RechargeSource.this.objectCache.put(o, key);
                }
            }
        };
    }

    @Override
    public Observable<JsonResponse<List<RechargeBean>>> getPay(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.WALLET_PAY)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<RechargeBean>>>() {
                }, RxAdapter.<JsonResponse<List<RechargeBean>>>create())
                .doOnNext(saveToCacheAction(WalletUrl.WALLET_PAY + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<String>>> rechargeError(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.RECHARGE_ERROE)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<String>>>() {
                }, RxAdapter.<JsonResponse<List<String>>>create())
               // .doOnNext(saveToCacheAction(WalletUrl.WALLET_PAY + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }
}
