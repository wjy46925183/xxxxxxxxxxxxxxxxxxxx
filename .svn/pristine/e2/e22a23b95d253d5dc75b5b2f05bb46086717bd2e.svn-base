package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.dlg.data.oddjob.model.CancleBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.oddjob.presenter.CancleTruskPresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能： 获取违约金
 * 创建时间：2017/7/12 14:07
 */

public class CancleTrucskViewModel extends BaseViewModel {
    private CancleTruskPresenter mPresenter;
    private OddServer mOddServer;
    private BasePresenter basePresenter;

    public CancleTrucskViewModel(Context context, BasePresenter basePresenter, CancleTruskPresenter presenter) {
        mPresenter = presenter;
        mOddServer = new OddServer(context);
        mContext = context;
        this.basePresenter = basePresenter;
    }

    public void getTruskAndMoney(String businessNumber) {
        HashMap<String, String> map = new HashMap<>();
        map.put("businessNumber", businessNumber);
        map.put("format", "json");
        mSubscriber = getSub();
        mOddServer.getTruskAndMoney(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<CancleBean>>> getSub() {
        return new RXSubscriber<JsonResponse<List<CancleBean>>, List<CancleBean>>(basePresenter) {
            @Override
            public void requestNext(List<CancleBean> cancleBeen) {
                mPresenter.getTruskAndMoney(cancleBeen);
            }
        };
    }
}
