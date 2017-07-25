package com.dlg.data.wallet;

import com.dlg.data.cache.ObjectCache;
import com.dlg.data.wallet.interactor.PayInteractor;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Observable;


/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/14 10:18
 */
public class PayDiaskSource implements PayInteractor {
    private final ObjectCache objectCache;

    public PayDiaskSource(ObjectCache objectCache) {
        this.objectCache = objectCache;
    }

    @Override
    public Observable<JsonResponse<List<Boolean>>> judgePwd(HashMap<String, String> hashMap) {
        return null;
    }
}