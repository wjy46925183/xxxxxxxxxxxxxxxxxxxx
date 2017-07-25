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
 * 主要功能：解除绑定
 * 创建时间：2017/7/14 17:13
 */
public class UnBindViewModel extends BaseViewModel<JsonResponse<Object>> {
    private SuccessObjectPresenter butPresenter; //与页面交互的接口
    private CommonServer mCommonServer;
    private BasePresenter basePresenter;

    public UnBindViewModel(Context context, BasePresenter basePresenter, SuccessObjectPresenter presenter) {
        this.butPresenter = presenter;
        this.basePresenter = basePresenter;
        mCommonServer = new CommonServer(context);
        mContext = context;
    }

    public void unBind(String bindId) {
        HashMap map = new HashMap();
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        map.put("id", bindId);
        mSubscriber = getUnBind();
        mCommonServer.unBind(mSubscriber, map);
    }

    private Subscriber<JsonResponse<Object>> getUnBind() {
        return new RXSubscriber<JsonResponse<Object>, Object>(basePresenter) {
            @Override
            public void requestNext(Object strings) {
                butPresenter.onSuccess(true);
            }
        };
    }
}
