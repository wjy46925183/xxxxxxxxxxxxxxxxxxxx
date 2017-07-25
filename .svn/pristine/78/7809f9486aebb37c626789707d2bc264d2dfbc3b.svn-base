package com.dlg.viewmodel.user;

import android.content.Context;

import com.dlg.data.user.model.UserUpHeadBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.server.UserServer;
import com.dlg.viewmodel.user.presenter.UpHeadPresenter;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：xiaoming
 * 主要功能：
 * 创建时间：2017/7/20 16:42
 */
public class UpHeadViewModel extends BaseViewModel<JsonResponse<List<UserUpHeadBean>>> {
    private BasePresenter basePresenter;
    private final UserServer mServer;
    private UpHeadPresenter upHeadPresenter;

    public UpHeadViewModel(Context context, BasePresenter basePresenter, UpHeadPresenter presenter) {
        this.basePresenter = basePresenter;
        mServer = new UserServer(context);
        this.upHeadPresenter=presenter;
    }

    public void upHead(String str,String userId) {
        HashMap<String,String> map = new HashMap<>();
        map.put("base64Str",str);
        map.put("userId",userId);

        mSubscriber=upHead();
        mServer.upLoadHead(mSubscriber,map);
    }
    private Subscriber<JsonResponse<List<UserUpHeadBean>>> upHead(){
        return new RXSubscriber<JsonResponse<List<UserUpHeadBean>>, List<UserUpHeadBean>>(basePresenter) {
            @Override
            public void requestNext(List<UserUpHeadBean> result) {
                upHeadPresenter.upUserHead(result);
            }
        };
    }
}
