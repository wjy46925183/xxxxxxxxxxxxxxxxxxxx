package com.dlg.viewmodel.user;

import android.content.Context;

import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.server.UserServer;
import com.dlg.viewmodel.user.presenter.UpUserInfoMainPresenter;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：xiaoming
 * 主要功能：
 * 创建时间：2017/7/24 13:13
 */
public class UpUserInfoMainViewModel extends BaseViewModel<JsonResponse<String>> {
    private BasePresenter basePresenter;
    private final UserServer mServer;
    private UpUserInfoMainPresenter upUserInfoMainPresenter;

    public UpUserInfoMainViewModel(Context context, BasePresenter basePresenter, UpUserInfoMainPresenter presenter) {
        this.basePresenter = basePresenter;
        mServer = new UserServer(context);
        this.upUserInfoMainPresenter=presenter;
    }

    public void upUserInfoMain(String username,String email,String oicq,String id) {
        HashMap<String,String> map = new HashMap<>();
        map.put("username",username);
        map.put("email",email);
        map.put("oicq",oicq);
        map.put("id",id);



        mSubscriber=upUserInfoMain();
        mServer.upUserInfoMain(mSubscriber,map);
    }
    private Subscriber<JsonResponse<String>> upUserInfoMain(){
        return new RXSubscriber<JsonResponse<String>, String>(basePresenter) {
            @Override
            public void requestNext(String result) {
                upUserInfoMainPresenter.upUserInfoMain(result);
            }
        };
    }
}