package com.dlg.viewmodel.home;

import android.content.Context;

import com.dlg.data.oddjob.model.ServiceListDataBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.home.presenter.ServiceDetailPersenter;
import com.dlg.viewmodel.server.HomeServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/18 17:57
 */

public class ServiceDetailViewModel extends BaseViewModel {
    private ServiceDetailPersenter mServiceDetailPersenter;
    private final HomeServer mHomeServer;

    public ServiceDetailViewModel(Context context, ServiceDetailPersenter serviceDetailPersenter){
        this.mServiceDetailPersenter = serviceDetailPersenter;
        mContext = context;
        mHomeServer = new HomeServer(mContext);
    }

    public void serviceDetail(String id){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id",id);
        mSubscriber = getSub();
        mHomeServer.serviceDetail(mSubscriber,hashMap);
    }

    public Subscriber<JsonResponse<List<ServiceListDataBean>>> getSub() {
        return new RXSubscriber<JsonResponse<List<ServiceListDataBean>>,List<ServiceListDataBean>>(null){
            @Override
            public void requestNext(List<ServiceListDataBean> serviceListDataBeen) {
                if(serviceListDataBeen != null)
                mServiceDetailPersenter.serviceDetail(serviceListDataBeen);
            }

            @Override
            public void onError(Throwable e) {
                if(mServiceDetailPersenter != null){
                    mServiceDetailPersenter.error(e.getMessage());
                }
            }
        };
    }
}
