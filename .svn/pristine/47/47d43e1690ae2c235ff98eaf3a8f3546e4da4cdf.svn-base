package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.oddjob.model.OddHirerBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.oddjob.presenter.OddMapInfoPresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：wangdakuan
 * 主要功能：雇主零工地图详情
 * 创建时间：2017/7/12 14:26
 */
public class OddMapInfoViewModel extends BaseViewModel<JsonResponse<List<OddHirerBean>>> {

    private BasePresenter basePresenter;
    private OddMapInfoPresenter mOddMapInfoPresenter;
    private OddServer mOddServer;

    public OddMapInfoViewModel(Context context, BasePresenter basePresenter, OddMapInfoPresenter presenter) {
        this.mOddMapInfoPresenter = presenter;
        this.basePresenter = basePresenter;
        mOddServer = new OddServer(context);
        this.mContext = context;
    }

    public void getJobtaskInfo(String taskId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("taskId", taskId);
        hashMap.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        mSubscriber = getJobtaskInfoSubscriber();
        mOddServer.getJobtaskInfo(mSubscriber, hashMap);
    }

    private Subscriber<JsonResponse<List<OddHirerBean>>> getJobtaskInfoSubscriber() {
        return new RXSubscriber<JsonResponse<List<OddHirerBean>>, List<OddHirerBean>>(basePresenter) {
            @Override
            public void requestNext(List<OddHirerBean> result) {
                if (null != mOddMapInfoPresenter && null != result && result.size() > 0) {
                    mOddMapInfoPresenter.oddMapInfoData(result.get(0));
                }
            }
        };
    }

}
