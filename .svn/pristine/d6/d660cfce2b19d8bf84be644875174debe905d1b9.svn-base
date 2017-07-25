package com.dlg.viewmodel.home;

import android.content.Context;

import com.dlg.data.home.model.HistoryBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.home.presenter.HistoryPresenter;
import com.dlg.viewmodel.server.HomeServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：小明
 * 主要功能：搜索记录Model
 * 创建时间：2017/7/4 0004 19:14
 */
public class HistoryViewModel extends BaseViewModel<JsonResponse<List<HistoryBean>>> {
    private HistoryPresenter mHistoryPresenter;
    private final HomeServer mHomeServer;

    public HistoryViewModel(Context context, HistoryPresenter presenter){
        mHomeServer = new HomeServer(context);
        this.mHistoryPresenter = presenter;
    }
    public void getHistoryData(String taskId){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("id",taskId);
        mSubscriber = getHistory();
        mHomeServer.getHistory(mSubscriber,hashMap);
    }

    private Subscriber<JsonResponse<List<HistoryBean>>> getHistory(){
        return new RXSubscriber<JsonResponse<List<HistoryBean>>, List<HistoryBean>>(null) {
            @Override
            public void requestNext(List<HistoryBean> historyBean) {
                mHistoryPresenter.getHistoryList(historyBean);
            }
        };
    }
}
