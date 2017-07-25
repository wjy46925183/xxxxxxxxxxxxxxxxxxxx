package com.dlg.viewmodel.user;

import android.content.Context;

import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.server.UserServer;
import com.dlg.viewmodel.user.presenter.UpUserInfoPresenter;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：xiaoming
 * 主要功能：
 * 创建时间：2017/7/24 13:13
 */
public class UpUserInfoViewModel extends BaseViewModel<JsonResponse<String>> {
    private BasePresenter basePresenter;
    private final UserServer mServer;
    private UpUserInfoPresenter upUserInfoPresenter;

    public UpUserInfoViewModel(Context context, BasePresenter basePresenter, UpUserInfoPresenter presenter) {
        this.basePresenter = basePresenter;
        mServer = new UserServer(context);
        this.upUserInfoPresenter=presenter;
    }

    public void upUserInfo(String height,String weight,String sign,String address,String personalizedSignature,String id) {
        HashMap<String,String> map = new HashMap<>();
        map.put("height",height+"");
        map.put("weight",weight+"");
        map.put("identity",sign+"");
        map.put("location",address+"");
        map.put("id",id+"");
        map.put("personalizedSignature",personalizedSignature+"");


        mSubscriber=upUserInfo();
        mServer.upUserInfo(mSubscriber,map);
    }
    private Subscriber<JsonResponse<String>> upUserInfo(){
        return new RXSubscriber<JsonResponse<String>, String>(basePresenter) {
            @Override
            public void requestNext(String result) {
                upUserInfoPresenter.upUserInfo(result);
            }
        };
    }
}