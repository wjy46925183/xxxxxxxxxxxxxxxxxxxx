package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.dlg.data.oddjob.model.OrderStatisticalBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.oddjob.presenter.OrderStatisticalPresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：wangdakuan
 * 主要功能：订单统计ViewModel
 * 创建时间：2017/7/13 13:24
 */
public class OrderStatisticalViewModel extends BaseViewModel<JsonResponse<List<OrderStatisticalBean>>> {

    private OrderStatisticalPresenter mStatisticalPresenter;
    private OddServer mOddServer;

    public OrderStatisticalViewModel(Context context, OrderStatisticalPresenter presenter) {
        this.mStatisticalPresenter = presenter;
        mOddServer = new OddServer(context);
        this.mContext = context;
    }

    /**
     * 查询统计数据
     * @param userId
     */
    public void queryReleaseJoinData(String userId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        mSubscriber = queryReleaseJoinDataSubscriber();
        mOddServer.queryReleaseJoinData(mSubscriber, hashMap);
    }

    private Subscriber<JsonResponse<List<OrderStatisticalBean>>> queryReleaseJoinDataSubscriber() {
        return new RXSubscriber<JsonResponse<List<OrderStatisticalBean>>, List<OrderStatisticalBean>>(null) {
            @Override
            public void requestNext(List<OrderStatisticalBean> result) {
                if (null != mStatisticalPresenter && null != result && result.size() > 0) {
                    mStatisticalPresenter.onOrderStatisticalData(result.get(0));
                }
            }
        };
    }

}
