package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.dlg.data.oddjob.model.EvaluateItemsBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.oddjob.presenter.EvaluatePresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：雇员和雇主评价
 * 创建时间：2017/7/12 13:48
 */

public class EvaluateViewModel extends BaseViewModel {
    private EvaluatePresenter mEvaluatePresenter;
    private final OddServer mOddServer;

    public EvaluateViewModel(Context context,EvaluatePresenter presenter){
        this.mEvaluatePresenter = presenter;
        mOddServer = new OddServer(context);
    }

    public void getEvaluateItems(boolean isGuyuan){
        HashMap<String,String> map = new HashMap<>();
        if (isGuyuan)
            map.put("groupCode", "employee.evaluation");//雇员评价雇主
        else
            map.put("groupCode", "employer.evaluation");//雇主评价标签
        map.put("format", "json");
        mSubscriber = getSub();
        mOddServer.evaluateItems(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<EvaluateItemsBean>>> getSub(){
        return new RXSubscriber<JsonResponse<List<EvaluateItemsBean>>,List<EvaluateItemsBean>>(null){

            @Override
            public void requestNext(List<EvaluateItemsBean> evaluateItemsBeen) {
                mEvaluatePresenter.evaluateItems(evaluateItemsBeen);
            }
        };
    }
}
