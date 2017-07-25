package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.dlg.data.oddjob.model.CancleBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.oddjob.presenter.CancleOrderPresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/13 16:34
 */

public class CancleOrderViewModel extends BaseViewModel<JsonResponse<Object>> {
    private CancleOrderPresenter presenter;
    private final OddServer oddServer;
    private BasePresenter basePresenter;

    public CancleOrderViewModel(Context context, BasePresenter basePresenter, CancleOrderPresenter presenter) {
        this.presenter = presenter;
        this.basePresenter = basePresenter;
        oddServer = new OddServer(context);
    }

    public void goToCancleOrder(String businessNumber, String dataCode
            , String payPassword, CancleBean cancleBean, boolean isWY) {
        HashMap<String, String> map = new HashMap<>();
        map.put("businessNumber", businessNumber);
        map.put("cancerCause", dataCode);
        map.put("format", "json");
        if (isWY) {//有违约金的
            map.put("reward", String.valueOf(cancleBean.getPrice()));
            map.put("cancerMoney", String.valueOf(cancleBean.getCompensatoryPayment()));
            map.put("payPassword", payPassword);
        }

        mSubscriber = getSub();
        oddServer.goToCancleOrder(mSubscriber, map);
    }

    private Subscriber<JsonResponse<Object>> getSub() {
        return new RXSubscriber<JsonResponse<Object>, Object>(basePresenter) {
            @Override
            public void requestNext(Object s) {
                presenter.cancleOrder("");
            }
        };
    }
}
