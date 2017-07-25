package com.dlg.data.common;

import com.alibaba.fastjson.JSON;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.common.interactor.CommonInteractor;
import com.dlg.data.common.model.ActionButtonsBean;
import com.dlg.data.common.model.ShareDataBean;
import com.dlg.data.wallet.url.WalletUrl;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Observable;

/**
 * 作者：wangdakuan
 * 主要功能：缓存实现接口
 * 创建时间：2017/6/23 10:34
 */
public class CommonDiaskSource implements CommonInteractor {

    private final ObjectCache objectCache;

    public CommonDiaskSource(ObjectCache objectCache) {
        this.objectCache = objectCache;
    }

    @Override
    public Observable<ActionButtonsBean> getActionButtons(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<ShareDataBean> getShareData(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<JsonResponse<Object>> updateOrderOperaStatus(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<JsonResponse<Object>> getSendMsg(HashMap map) {
        return this.objectCache.getList(WalletUrl.SEND_VERIFY_CODE + JSON.toJSONString(map), JsonResponse.class,String.class);
    }

    @Override
    public Observable<JsonResponse<Object>> recharge(HashMap map) {
        return this.objectCache.getList(WalletUrl.GET_CASH + JSON.toJSONString(map), JsonResponse.class,String.class);
    }

    @Override
    public Observable<JsonResponse<Object>> unBind(HashMap map) {
        return this.objectCache.getList(WalletUrl.UNBIND + JSON.toJSONString(map), JsonResponse.class,String.class);
    }
}
