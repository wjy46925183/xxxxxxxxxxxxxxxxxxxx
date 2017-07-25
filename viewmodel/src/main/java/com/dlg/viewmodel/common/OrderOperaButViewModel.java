package com.dlg.viewmodel.common;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.common.model.ButtonBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.common.presenter.SuccessObjectPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.CommonServer;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：wangdakuan
 * 主要功能：订单按钮操作viewModel
 * 创建时间：2017/7/6 09:32
 */
public class OrderOperaButViewModel extends BaseViewModel<JsonResponse<Object>> {
    private SuccessObjectPresenter butPresenter; //与页面交互的接口
    private CommonServer mCommonServer;
    private BasePresenter basePresenter;

    public OrderOperaButViewModel(Context context, BasePresenter basePresenter, SuccessObjectPresenter presenter) {
        this.butPresenter = presenter;
        this.basePresenter = basePresenter;
        mCommonServer = new CommonServer(context);
        mContext = context;
    }

    public void updateOrderOperaStatus(ButtonBean buttonBean, String businessNumber) {
        HashMap<String, String> map = new HashMap<>();
        map.put("operaStatus", buttonBean.getOperateStatusCode() + "");
        map.put("nextStatusCode", buttonBean.getNextStatusCode() + "");
        map.put("businessNumber", businessNumber);
        map.put("userVo", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        mSubscriber = updateOrderOperaStatusSubscriber();
        mCommonServer.updateOrderOperaStatus(mSubscriber, map);
    }

    private Subscriber<JsonResponse<Object>> updateOrderOperaStatusSubscriber() {
        return new RXSubscriber<JsonResponse<Object>, Object>(basePresenter) {
            @Override
            public void requestNext(Object buttonsBean) {
                butPresenter.onSuccess(true);
            }
        };
    }
}