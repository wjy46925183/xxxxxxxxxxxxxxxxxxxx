package com.dlg.viewmodel.user;

import android.content.Context;

import com.dlg.data.user.model.UserIsBindingBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.server.UserServer;
import com.dlg.viewmodel.user.presenter.IsBindingPresenter;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 是否绑定三方
 * Created by xiaoming on 2017/7/18.
 */

public class IsBindingThirdViewModel extends BaseViewModel<JsonResponse<List<UserIsBindingBean>>> {
    private BasePresenter basePresenter;
    private final UserServer mServer;
    private IsBindingPresenter mIsBindingPresenter;

    public IsBindingThirdViewModel(Context context, BasePresenter basePresenter, IsBindingPresenter presenter) {
        this.basePresenter = basePresenter;
        mServer = new UserServer(context);
        this.mIsBindingPresenter=presenter;
    }

    public void isBinding(String openId,String loginType) {
        HashMap<String,String> map = new HashMap<>();
        map.put("type","1");
        map.put("openId",openId);
        map.put("loginType",loginType);


        mSubscriber=isBinding();
        mServer.isBindingThird(mSubscriber,map);
    }
    private Subscriber<JsonResponse<List<UserIsBindingBean>>> isBinding(){
        return new RXSubscriber<JsonResponse<List<UserIsBindingBean>>, List<UserIsBindingBean>>(basePresenter) {
            @Override
            public void requestNext(List<UserIsBindingBean> result) {
                mIsBindingPresenter.isBinding(result);
            }
        };
    }
}
