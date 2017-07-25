package com.dlg.viewmodel.home;

import android.content.Context;
import android.text.TextUtils;

import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.home.presenter.AddJobConditionPresenter;
import com.dlg.viewmodel.server.HomeServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：小明
 * 主要功能：增加零工条件
 * 创建时间：2017/7/5 0005 10:50
 */
public class AddJobConditionViewModel extends BaseViewModel<JsonResponse<List<String>>> {
    private BasePresenter basePresenter;
    private final HomeServer mServer;
    private AddJobConditionPresenter addJobConditionPresenter;

    public AddJobConditionViewModel(Context context, BasePresenter basePresenter, AddJobConditionPresenter presenter) {
        this.basePresenter = basePresenter;
        mServer = new HomeServer(context);
        this.addJobConditionPresenter=presenter;
    }

    public void getDictionaryData(String id, String jobTypeName,String jobType, String postName, int demandType, String price, int jobMeterUnit) {
        HashMap<String,String> map = new HashMap<>();
        map.put("id",id);
        if(!TextUtils.isEmpty(jobType)){
            map.put("jobType", jobType);
            map.put("jobTypeName", jobTypeName);
        }
        map.put("postName",postName);
        map.put("demandType",demandType+"");
        map.put("price",price);
        map.put("jobMeterUnit",jobMeterUnit+"");

        mSubscriber=getAddJobSubscriber();
        mServer.getAddJobData(mSubscriber,map);
    }
    private Subscriber<JsonResponse<List<String>>> getAddJobSubscriber(){
        return new RXSubscriber<JsonResponse<List<String>>, List<String>>(basePresenter) {
            @Override
            public void requestNext(List<String> addJobBeen) {
                addJobConditionPresenter.getAddJobCondition(addJobBeen);
            }
        };
    }
}
