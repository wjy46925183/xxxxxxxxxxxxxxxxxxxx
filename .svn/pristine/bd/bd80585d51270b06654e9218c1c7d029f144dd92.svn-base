package com.dlg.viewmodel.user;

import android.content.Context;

import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.server.UserServer;
import com.dlg.viewmodel.user.presenter.IsRegisterPresenter;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：小明
 * 主要功能：
 * 创建时间：2017/7/14 0014 13:34
 */
public class IsRegisterViewModel extends BaseViewModel {
    private final UserServer mServer;
    private IsRegisterPresenter mIsRegisterPresenter;

    public IsRegisterViewModel(Context context, IsRegisterPresenter presenter) {
        mServer = new UserServer(context);
        this.mIsRegisterPresenter=presenter;
    }

    public void IsRegister(String phone,String type) {
        HashMap<String,String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("type",type);
        mSubscriber=IsRegister();
        mServer.getRegist(mSubscriber,map);
    }
    private Subscriber<JsonResponse> IsRegister() {
        return new RXSubscriber<JsonResponse, JsonResponse>(null) {
            @Override
            public void requestNextCode(Object m) {
                super.requestNextCode(m);
                if("0".equals(m.toString())){
                    mIsRegisterPresenter.getIsRegister(true);
                }else{
                    mIsRegisterPresenter.getIsRegister(false);
                }
            }
        };
    }
}
