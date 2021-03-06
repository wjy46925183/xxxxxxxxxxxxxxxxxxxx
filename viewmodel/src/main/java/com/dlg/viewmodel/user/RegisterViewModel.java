package com.dlg.viewmodel.user;

import android.content.Context;

import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.server.UserServer;
import com.dlg.viewmodel.user.presenter.RegisterPresenter;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：小明
 * 主要功能：
 * 创建时间：2017/7/14 0014 15:50
 */
public class RegisterViewModel extends BaseViewModel<JsonResponse<List<String>>> {
    private BasePresenter basePresenter;
    private final UserServer mServer;
    private RegisterPresenter registerPresenter;

    public RegisterViewModel(Context context, BasePresenter basePresenter, RegisterPresenter presenter) {
        this.basePresenter = basePresenter;
        mServer = new UserServer(context);
        this.registerPresenter=presenter;
    }

    public void getRegister(String phone,String psd,String userType,String codeText,String deviceId, String channel,String appVersion) {
        HashMap<String,String> map = new HashMap<>();
        map.put("source","register");
        map.put("username","");
        map.put("email","");
        map.put("weChat","");
        map.put("oicq","");
        map.put("personalizedSignature","");
        map.put("location","");
        map.put("parentUserId","");
        map.put("isLogin","0");
        map.put("source","register");
        map.put("activity","");
        map.put("ws","");
        map.put("client","ANDROID");
        map.put("phone",phone);
        map.put("password",psd);
        map.put("userType",userType);
        map.put("vaildCode",codeText);
        map.put("imei",deviceId);
        map.put("channel",channel);
        map.put("appVersion",appVersion);

        mSubscriber=getRegist();
        mServer.getRegistResult(mSubscriber,map);
    }
    private Subscriber<JsonResponse<List<String>>> getRegist(){
        return new RXSubscriber<JsonResponse<List<String>>, List<String>>(basePresenter) {
            @Override
            public void requestNext(List<String> result) {
                registerPresenter.getRegister(result);
            }
        };
    }
}
