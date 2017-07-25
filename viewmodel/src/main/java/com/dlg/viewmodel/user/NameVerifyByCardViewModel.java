package com.dlg.viewmodel.user;

import android.content.Context;

import com.dlg.data.user.model.UserNameBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.server.UserServer;
import com.dlg.viewmodel.user.presenter.NameVerifyByCardPresenter;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：xiaoming
 * 主要功能：
 * 创建时间：2017/7/20 15:42
 */
public class NameVerifyByCardViewModel extends BaseViewModel<JsonResponse<List<UserNameBean>>> {
    private BasePresenter basePresenter;
    private final UserServer mServer;
    private NameVerifyByCardPresenter verifyByCardPresenter;

    public NameVerifyByCardViewModel(Context context, BasePresenter basePresenter, NameVerifyByCardPresenter presenter) {
        this.basePresenter = basePresenter;
        mServer = new UserServer(context);
        this.verifyByCardPresenter=presenter;
    }

    public void getVerify(String img,String userId,String cardType) {
        HashMap<String,String> map = new HashMap<>();
        map.put("img",img);
        map.put("userId",userId);
        map.put("cardType",cardType);



        mSubscriber=getVerify();
        mServer.getVerify(mSubscriber,map);
    }
    private Subscriber<JsonResponse<List<UserNameBean>>> getVerify(){
        return new RXSubscriber<JsonResponse<List<UserNameBean>>, List<UserNameBean>>(basePresenter) {
            @Override
            public void requestNext(List<UserNameBean> result) {
                verifyByCardPresenter.getName(result);
            }
        };
    }
}
