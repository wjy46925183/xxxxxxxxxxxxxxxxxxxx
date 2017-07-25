package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.oddjob.presenter.GrabSingleOrderPresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：wangdakuan
 * 主要功能：抢单
 * 创建时间：2017/7/19 10:01
 */
public class GrabSingleOrderViewModel extends BaseViewModel<JsonResponse<List<String>>> {
    private GrabSingleOrderPresenter mPresenter;
    private OddServer mOddServer;
    private BasePresenter basePresenter;

    public GrabSingleOrderViewModel(Context context, BasePresenter basePresenter, GrabSingleOrderPresenter presenter) {
        mPresenter = presenter;
        mOddServer = new OddServer(context);
        mContext = context;
        this.basePresenter = basePresenter;
    }

    /**
     * 抢单
     *
     * @param jobId 任务ID
     */
    public void grabSingleOrder(String jobId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("jobId", jobId);
        map.put("employeeId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        mSubscriber = grabSingleOrderSub();
        mOddServer.grabSingleOrder(mSubscriber, map);
    }

    private Subscriber<JsonResponse<List<String>>> grabSingleOrderSub() {
        return new RXSubscriber<JsonResponse<List<String>>, List<String>>(basePresenter) {
            @Override
            public void requestNext(List<String> strings) {
                if (null != strings && strings.size() > 0) {
                    mPresenter.onGrabSingleOrderSuccess(true);
                } else {
                    mPresenter.onGrabSingleOrderSuccess(false);
                }

            }
        };
    }

}
