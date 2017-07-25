package com.dlg.viewmodel.user;

import android.content.Context;

import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.server.UserServer;
import com.dlg.viewmodel.user.presenter.NameVerifyPresenter;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：xiaoming
 * 主要功能：
 * 创建时间：2017/7/20 13:12
 */
public class NameVerifyViewModel extends BaseViewModel<JsonResponse<List<String>>> {
    private BasePresenter basePresenter;
    private final UserServer mServer;
    private NameVerifyPresenter verifyPresenter;

    public NameVerifyViewModel(Context context, BasePresenter basePresenter, NameVerifyPresenter presenter) {
        this.basePresenter = basePresenter;
        mServer = new UserServer(context);
        this.verifyPresenter=presenter;
    }

    public void verifyName(String idName,String idNo,String cardType,String id) {
        HashMap<String,String> map = new HashMap<>();
        map.put("name", idName);
        map.put("idCard", idNo);
        map.put("cardType",cardType);
        map.put("sex","3");
        map.put("id",id);


        mSubscriber=verifyName();
        mServer.getVerifyResult(mSubscriber,map);
    }
    private Subscriber<JsonResponse<List<String>>> verifyName(){
        return new RXSubscriber<JsonResponse<List<String>>, List<String>>(basePresenter) {
            @Override
            public void requestNext(List<String> result) {
                verifyPresenter.verifyName(result);
            }
        };
    }
}
