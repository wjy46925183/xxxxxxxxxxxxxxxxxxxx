package com.dlg.viewmodel.common;

import android.content.Context;

import com.common.cache.ACache;
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
 * 主要功能：提现
 * 创建时间：2017/7/14 17:11
 */
public class RechargePriceViewModel extends BaseViewModel<JsonResponse<Object>> {
    private SuccessObjectPresenter butPresenter; //与页面交互的接口
    private CommonServer mCommonServer;
    private BasePresenter basePresenter;

    public RechargePriceViewModel(Context context, BasePresenter basePresenter, SuccessObjectPresenter presenter) {
        this.butPresenter = presenter;
        this.basePresenter = basePresenter;
        mCommonServer = new CommonServer(context);
        mContext = context;
    }

    public void recharge(String type, String mCode, String mCount, String userName, String bindId, String pwd) {
        HashMap map = new HashMap();
        map.put("amount", mCount);
        map.put("mobile", ACache.get(mContext).getAsString(AppKey.CacheKey.USER_PHONE));
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        map.put("vaildCode", mCode);
        map.put("payPassword", pwd);
        map.put("payName", userName);
        map.put("payType", type);
        map.put("bindId", bindId);
        mSubscriber = getRecharge();
        mCommonServer.recharge(mSubscriber, map);
    }

    private Subscriber<JsonResponse<Object>> getRecharge() {
        return new RXSubscriber<JsonResponse<Object>, Object>(basePresenter) {
            @Override
            public void requestNext(Object strings) {
                butPresenter.onSuccess(false);
            }
        };
    }
}