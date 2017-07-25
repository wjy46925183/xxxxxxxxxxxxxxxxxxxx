package com.dlg.viewmodel.home;

import android.content.Context;

import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.home.presenter.DeletHistoryPresenter;
import com.dlg.viewmodel.server.HomeServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：小明
 * 主要功能：删除搜索历史
 * 创建时间：2017/7/7 0007 10:18
 */
public class DeletHistoryViewModel extends BaseViewModel<JsonResponse<List<String>>> {
    private BasePresenter basePresenter;
    private final HomeServer mServer;
    private DeletHistoryPresenter deletHistoryPresenter;

    public DeletHistoryViewModel(Context context, BasePresenter basePresenter, DeletHistoryPresenter presenter) {
        this.basePresenter = basePresenter;
        mServer = new HomeServer(context);
        this.deletHistoryPresenter=presenter;
    }

    public void deletHistory(String id,String userId) {
        HashMap<String,String> map = new HashMap<>();
        map.put("id",id);
        map.put("userId",userId);

        mSubscriber=deletHistory();
        mServer.deletHistory(mSubscriber,map);
    }
    private Subscriber<JsonResponse<List<String>>> deletHistory(){
        return new RXSubscriber<JsonResponse<List<String>>, List<String>>(basePresenter) {
            @Override
            public void requestNext(List<String> delethistory) {
                deletHistoryPresenter.delethistory(delethistory);
            }
        };
    }
}
