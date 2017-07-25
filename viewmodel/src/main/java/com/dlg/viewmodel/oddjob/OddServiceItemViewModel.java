package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.dlg.data.oddjob.model.OddServiceItemBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.oddjob.presenter.OddServiceItemPresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：wangdakuan
 * 主要功能：零工服务列表ViewModel
 * 创建时间：2017/7/13 13:24
 */
public class OddServiceItemViewModel extends BaseViewModel<JsonResponse<List<OddServiceItemBean>>> {

    private OddServiceItemPresenter mServiceItemPresenter;
    private OddServer mOddServer;

    public OddServiceItemViewModel(Context context, OddServiceItemPresenter presenter) {
        this.mServiceItemPresenter = presenter;
        mOddServer = new OddServer(context);
        this.mContext = context;
    }
    /**
     * 查询零工服务
     * @param userId
     */
    public void getJobServiceList(String userId, int pageIndex) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        hashMap.put("pageIndex", 0 + "");
        hashMap.put("pageSize", "100");
        mSubscriber = getJobServiceListSubscriber();
        mOddServer.getJobServiceList(mSubscriber, hashMap);
    }

    private Subscriber<JsonResponse<List<OddServiceItemBean>>> getJobServiceListSubscriber() {
        return new RXSubscriber<JsonResponse<List<OddServiceItemBean>>, List<OddServiceItemBean>>(null) {
            @Override
            public void requestNext(List<OddServiceItemBean> result) {
                if (null != mServiceItemPresenter && null != result) {
                    mServiceItemPresenter.onOddServiceData(result);
                }
            }
        };
    }

}
