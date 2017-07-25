package com.dlg.data.wallet;

import com.dlg.data.cache.ObjectCache;
import com.dlg.data.wallet.interactor.PayInteractor;
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
 * 创建时间：2017/7/14 10:18
 */
public class PaySource implements PayInteractor {
    private final ObjectCache objectCache;

    /**
     */
    public PaySource(ObjectCache objectCache) {
        this.objectCache = objectCache;
    }

    private Action1<Object> saveToCacheAction(final String key) {
        return new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o != null) {
                    PaySource.this.objectCache.put(o, key);
                }
            }
        };
    }

    @Override
    public Observable<JsonResponse<List<Boolean>>> judgePwd(HashMap<String, String> hashMap) {
        return OkGo.post(WalletUrl.HAS_SET_PAY_PWD)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<Boolean>>>() {
                }, RxAdapter.<JsonResponse<List<Boolean>>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
