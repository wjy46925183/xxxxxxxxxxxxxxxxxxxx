package com.dlg.data.wallet;

import com.alibaba.fastjson.JSON;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.wallet.interactor.CashInteractor;
import com.dlg.data.wallet.model.BindBean;
import com.dlg.data.wallet.url.WalletUrl;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Observable;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/12 13:29
 */
public class CashDiskSource implements CashInteractor {
    private final ObjectCache objectCache;

    public CashDiskSource(ObjectCache objectCache) {
        this.objectCache = objectCache;
    }

    @Override
    public Observable<JsonResponse<List<BindBean>>> getBindList(HashMap<String, String> map) {
        return this.objectCache.getList(WalletUrl.PAY_BIND_LIST + JSON.toJSONString(map), JsonResponse.class,BindBean.class);
    }
}
