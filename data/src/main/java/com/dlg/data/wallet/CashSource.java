package com.dlg.data.wallet;

import com.alibaba.fastjson.JSON;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.wallet.interactor.CashInteractor;
import com.dlg.data.wallet.model.BindBean;
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
 * 创建时间：2017/7/12 13:30
 */
public class CashSource implements CashInteractor {
    private final ObjectCache objectCache;

    /**
     */
    public CashSource(ObjectCache objectCache) {
        this.objectCache = objectCache;
    }

    private Action1<Object> saveToCacheAction(final String key) {
        return new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o != null) {
                    CashSource.this.objectCache.put(o, key);
                }
            }
        };
    }

    @Override
    public Observable getBindList(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.PAY_BIND_LIST)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<BindBean>>>() {
                }, RxAdapter.<JsonResponse<List<BindBean>>>create())
                .doOnNext(saveToCacheAction(WalletUrl.PAY_BIND_LIST + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }
}
