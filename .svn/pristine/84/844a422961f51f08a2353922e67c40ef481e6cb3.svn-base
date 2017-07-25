package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.oddjob.model.ServiceListDataBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.oddjob.presenter.ServicePresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：服务列表
 * 创建时间：2017/7/14 10:09
 */

public class ServiceListViewModel extends BaseViewModel {
    private ServicePresenter presenter;
    private final OddServer oddServer;

    public ServiceListViewModel(Context context, ServicePresenter presenter){
        this.presenter = presenter;
        mContext = context;
        oddServer = new OddServer(context);
    }
    public void httpServiceList(int page){
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        map.put("pageIndex", page + "");
        map.put("pageSize", "20");
        mSubscriber = getSub();
        oddServer.getServiceList(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<ServiceListDataBean>>> getSub(){
        return new RXSubscriber<JsonResponse<List<ServiceListDataBean>>,List<ServiceListDataBean>>(null) {
            @Override
            public void requestNext(List<ServiceListDataBean> serviceListDataBeen) {
                if(presenter != null){
                    presenter.getServiceList(serviceListDataBeen);
                }
            }
        };
    }
}
