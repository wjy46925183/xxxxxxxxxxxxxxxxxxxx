package com.dlg.viewmodel.server;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dlg.data.common.factory.CommonFactory;
import com.dlg.data.common.interactor.CommonInteractor;
import com.dlg.data.common.model.ActionButtonsBean;
import com.dlg.data.common.url.CommonUrl;
import com.dlg.data.wallet.url.WalletUrl;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：wangdakuan
 * 主要功能：公共接口类
 * 创建时间：2017/6/23 14:52
 */
public class CommonServer {


    CommonFactory commonFactory;

    public CommonServer(Context appContext) {
        this(new CommonFactory(appContext));
    }

    public CommonServer(CommonFactory commonFactory) {
        this.commonFactory = commonFactory;
    }

    /**
     * 订单操作按钮数据
     * @param subscriber
     * @param hashMap
     */
    public void getActionButtonsList(Subscriber subscriber, HashMap<String, String> hashMap) {
        CommonInteractor dataInteractor = commonFactory.createData(CommonUrl.GET_ACTION_BUTTONS + JSON.toJSONString(hashMap),true);
        dataInteractor.getActionButtons(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 订单操作按钮数据
     * @param hashMap
     */
    public Observable<ActionButtonsBean> getActionButtonsList(HashMap<String, String> hashMap) {
        CommonInteractor dataInteractor = commonFactory.createData(CommonUrl.GET_ACTION_BUTTONS + JSON.toJSONString(hashMap),true);
        return dataInteractor.getActionButtons(hashMap);
    }

    /**
     * 获取分享内容数据
     * @param subscriber
     * @param hashMap
     */
    public void getShareData(Subscriber subscriber, HashMap<String, String> hashMap) {
        CommonInteractor dataInteractor = commonFactory.createData(CommonUrl.SHARE_DATA + JSON.toJSONString(hashMap),true);
        dataInteractor.getShareData(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 订单操作按钮请求
     * @param subscriber
     * @param hashMap
     */
    public void updateOrderOperaStatus(Subscriber subscriber, HashMap<String, String> hashMap) {
        CommonInteractor dataInteractor = commonFactory.createData(CommonUrl.ORDER_OPERA_STATUS + JSON.toJSONString(hashMap),true);
        dataInteractor.updateOrderOperaStatus(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getSendMsg(Subscriber<JsonResponse<Object>> mSubscriber, HashMap map) {
        CommonInteractor dataInteractor = commonFactory.createData(WalletUrl.SEND_VERIFY_CODE + JSON.toJSONString(map),true);
        dataInteractor.getSendMsg(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }

    public void recharge(Subscriber<JsonResponse<Object>> mSubscriber, HashMap map) {
        CommonInteractor dataInteractor = commonFactory.createData(WalletUrl. GET_CASH+ JSON.toJSONString(map),true);
        dataInteractor.recharge(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }

    public void unBind(Subscriber<JsonResponse<Object>> mSubscriber, HashMap map) {
        CommonInteractor dataInteractor = commonFactory.createData(WalletUrl.UNBIND+ JSON.toJSONString(map),true);
        dataInteractor.unBind(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }
}
