package com.dlg.viewmodel.home;

import android.content.Context;

import com.dlg.data.home.model.ServiceShareBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.home.presenter.ShareServicePresenter;
import com.dlg.viewmodel.server.HomeServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：服务分享
 * 创建时间：2017/7/19 09:40
 */

public class ServiceShareViewModel extends BaseViewModel {
    private ShareServicePresenter mShareServicePresenter;
    private final HomeServer mHomeServer;

    public ServiceShareViewModel(Context context, ShareServicePresenter shareServicePresenter) {
        mContext = context;
        this.mShareServicePresenter = shareServicePresenter;
        mHomeServer = new HomeServer(mContext);
    }

    public void shareService(String serviceId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("serviceId", serviceId);
        mSubscriber = getSub();
        mHomeServer.shareService(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<ServiceShareBean>>> getSub() {
        return new RXSubscriber<JsonResponse<List<ServiceShareBean>>, List<ServiceShareBean>>(null) {
            @Override
            public void requestNext(List<ServiceShareBean> serviceShareBeen) {
                if(mShareServicePresenter != null&&serviceShareBeen.size() >0){
                    mShareServicePresenter.shareService(serviceShareBeen);
                }
            }

            @Override
            public void onError(Throwable e) {
                if(mShareServicePresenter != null){
                    mShareServicePresenter.error(e.getMessage());
                }
            }
        };
    }
}
