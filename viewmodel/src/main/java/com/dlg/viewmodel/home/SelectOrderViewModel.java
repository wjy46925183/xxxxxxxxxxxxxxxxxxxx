package com.dlg.viewmodel.home;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.home.presenter.SelectOrderPrensenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.HomeServer;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：选择雇佣
 * 创建时间：2017/7/18 13:40
 */

public class SelectOrderViewModel extends BaseViewModel {
    private SelectOrderPrensenter mSelectOrderPrensenter;
    private final HomeServer mHomeServer;

    public SelectOrderViewModel(Context context, SelectOrderPrensenter selectOrderPrensenter){
        mContext = context;
        this.mSelectOrderPrensenter = selectOrderPrensenter;
        mHomeServer = new HomeServer(mContext);
    }

    public void selectOrder(String employeeParamId,String taskId){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        hashMap.put("employeeId", employeeParamId);
        hashMap.put("taskId", taskId);
        mSubscriber =getSub();
        mHomeServer.selectOrder(mSubscriber,hashMap);
    }

    public Subscriber<JsonResponse<String>> getSub() {
        return new RXSubscriber<JsonResponse<String>,String>(null){
            @Override
            public void requestNext(String s) {
                mSelectOrderPrensenter.selectHandleOrder(s);
            }

            @Override
            public void onError(Throwable e) {
                mSelectOrderPrensenter.errorMsg(e.getMessage());
            }
        };
    }
}