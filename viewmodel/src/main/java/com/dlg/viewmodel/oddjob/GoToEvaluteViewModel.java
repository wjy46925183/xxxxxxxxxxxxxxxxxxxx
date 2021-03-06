package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.oddjob.presenter.GoToEvaluatePresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：雇主和雇员评价
 * 创建时间：2017/7/12 14:07
 */

public class GoToEvaluteViewModel extends BaseViewModel {
    private GoToEvaluatePresenter mGoToEvaluatePresenter;
    private OddServer mOddServer;
    private BasePresenter basePresenter;

    public GoToEvaluteViewModel(Context context, BasePresenter basePresenter, GoToEvaluatePresenter presenter) {
        mGoToEvaluatePresenter = presenter;
        mOddServer = new OddServer(context);
        mContext = context;
        this.basePresenter = basePresenter;
    }

    /**
     * @param businessNumber  订单编号
     * @param accepterUserId  被评价人
     * @param appraiserUserId 评价人
     * @param desc            评价描述
     * @param tabs            评价标签
     * @param level           评价级别
     */
    public void goToEvaluate(String businessNumber, String accepterUserId
            , String appraiserUserId, String desc, String tabs, String level) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        map.put("orderBusinessNumber", businessNumber);//订单编号
        map.put("accepterUserId", accepterUserId);//被评价人id
        map.put("appraiserUserId", appraiserUserId);//评价人id

        map.put("evaluateContent", desc);//其他说明
        map.put("evaluateTag", tabs);//所选评价标签
        map.put("evaluateLevel", level);//评价等级
        map.put("format", "json");

        mSubscriber = getSub();
        mOddServer.evaluateClick(mSubscriber,map);
    }

    private Subscriber<JsonResponse<Object>> getSub() {
        return new RXSubscriber<JsonResponse<Object>, Object>(basePresenter) {
            @Override
            public void requestNext(Object s) {
                mGoToEvaluatePresenter.gotoEvaluate(s.toString());
            }
        };
    }
}
