package com.dlg.viewmodel.user;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.user.model.UserAttributeInfoBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.UserServer;
import com.dlg.viewmodel.user.presenter.LogInPresenter;
import com.dlg.viewmodel.user.presenter.LogOutPresenter;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：wangdakuan
 * 主要功能：登录ViewModel
 * 创建时间：2017/7/4 16:16
 */
public class LogOutViewModel extends BaseViewModel<JsonResponse<Object>> {

    private LogOutPresenter mLogInPresenter;
    private BasePresenter mBasePresenter;
    private final UserServer mServer;

    public LogOutViewModel(Context context, BasePresenter basePresenter, LogOutPresenter presenter) {
        this.mLogInPresenter = presenter;
        mBasePresenter = basePresenter;
        mServer = new UserServer(context);
    }


    public void logOut(String userid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", userid);
        mSubscriber = logOutSubscriber();
        mServer.logOut(mSubscriber, map);
    }


    private Subscriber<JsonResponse<Object>> logOutSubscriber() {
        return new RXSubscriber<JsonResponse<Object>, Object>(mBasePresenter) {
            @Override
            public void requestNext(Object o) {
                if (null != mLogInPresenter) {
                    mLogInPresenter.logOut(o);
                }
            }

        };
    }
}
