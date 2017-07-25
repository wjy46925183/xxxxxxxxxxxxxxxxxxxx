package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.dlg.data.oddjob.model.HirerMapBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.oddjob.presenter.HirerMapDetailPresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：雇主零工详情
 * 创建时间：2017/7/10 19:40
 */

public class OddHirerMapViewModel extends BaseViewModel {
    private HirerMapDetailPresenter mHirerMapDetailPresenter;
    private final OddServer mOddServer;

    public OddHirerMapViewModel(Context context, HirerMapDetailPresenter presenter){
        this.mHirerMapDetailPresenter = presenter;
        mOddServer = new OddServer(context);
    }

    public void getMapHirerList(String businessNumber){
        HashMap<String,String> map = new HashMap<>();
        map.put("businessNumber",businessNumber);
        map.put("format","json");
        mSubscriber = getSub();
        mOddServer.getOddHirerMapList(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<HirerMapBean>>> getSub(){

        return new RXSubscriber<JsonResponse<List<HirerMapBean>>, List<HirerMapBean>>(null) {
            @Override
            public void requestNext(List<HirerMapBean> beanList) {
                mHirerMapDetailPresenter.getOddMap(beanList);
            }
        };
    }

}
