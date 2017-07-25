package com.dlg.data.wallet;

import com.alibaba.fastjson.JSON;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.wallet.interactor.WithDrawalInteractor;
import com.dlg.data.wallet.model.BankBean;
import com.dlg.data.wallet.url.WalletUrl;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Observable;

/**
 * 作者：曹伟
 * 主要功能：缓存实现接口
 * 创建时间：2017/7/6 17:54
 */

public class WithDrawalDiaskSource implements WithDrawalInteractor{
    private final ObjectCache objectCache;

    public WithDrawalDiaskSource(ObjectCache objectCache) {
        this.objectCache = objectCache;
    }

    @Override
    public Observable<JsonResponse<List<String>>> getFrequency(HashMap<String, String> map) {
        return this.objectCache.getList(WalletUrl.FREQUENCY_NUM + JSON.toJSONString(map), JsonResponse.class,String.class);
    }

    @Override
    public Observable<JsonResponse<List<String>>> getBindWeChat(HashMap map) {
        return this.objectCache.getList(WalletUrl.PAY_BIND_ADD + JSON.toJSONString(map), JsonResponse.class,String.class);
    }

    @Override
    public Observable<JsonResponse<List<String>>> getIncBindWeChat(HashMap map) {
        return this.objectCache.getList(WalletUrl.INC_PAY_BIND_ADD + JSON.toJSONString(map), JsonResponse.class,String.class);

    }

    @Override
    public Observable<JsonResponse<List<String>>> exchange(HashMap map) {
        return this.objectCache.getList(WalletUrl.EXCHANGE + JSON.toJSONString(map), JsonResponse.class,String.class);
    }

    @Override
    public Observable<JsonResponse<List<String>>> setPayPwd(HashMap map) {
        return this.objectCache.getList(WalletUrl.SET_PAY_PWD + JSON.toJSONString(map), JsonResponse.class,String.class);
    }

    @Override
    public Observable<JsonResponse<List<BankBean>>> getBank(HashMap map) {
        return this.objectCache.getList(WalletUrl.GET_BANK + JSON.toJSONString(map), JsonResponse.class,BankBean.class);

    }
}
