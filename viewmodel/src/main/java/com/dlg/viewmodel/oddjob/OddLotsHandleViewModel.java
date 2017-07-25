package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.oddjob.presenter.OddLotsPresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：批量处理
 * 创建时间：2017/7/11 13:11
 */

public class OddLotsHandleViewModel extends BaseViewModel {
    private OddLotsPresenter mOddLotsPresenter;
    private final OddServer mOddServer;
    private BasePresenter mBasePresenter;

    public OddLotsHandleViewModel(Context context, BasePresenter basePresenter, OddLotsPresenter presenter){
        this.mOddLotsPresenter = presenter;
        mOddServer = new OddServer(context);
        this.mBasePresenter = basePresenter;
    }

    public void toHandleLots(String businessNumbers,String nextStatusCode,String operaStatus){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("businessNumber", businessNumbers);
        hashMap.put("nextStatusCode", nextStatusCode);
        hashMap.put("operaStatus", operaStatus);
        hashMap.put("format", "json");

        mSubscriber = getSub();
        mOddServer.getOddHirerLots(mSubscriber,hashMap);
    }

    private Subscriber<JsonResponse<String>> getSub(){
        return new RXSubscriber<JsonResponse<String>, String>(mBasePresenter) {
            @Override
            public void requestNext(String beanList) {
                mOddLotsPresenter.hanleLots(beanList);
            }
        };
    }
}
