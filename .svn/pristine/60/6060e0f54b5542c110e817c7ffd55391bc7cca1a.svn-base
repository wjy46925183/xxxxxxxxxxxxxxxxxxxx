package com.dlg.viewmodel.home;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.home.model.ConditionSearchBean;
import com.dlg.data.home.model.EmployeeListBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.home.presenter.ConditionSearchPresenter;
import com.dlg.viewmodel.home.presenter.EmployeeListPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.HomeServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：小明
 * 主要功能：條件搜索
 * 创建时间：2017/7/6 0006 17:34
 */
public class ConditionSearchViewModel extends BaseViewModel<JsonResponse<List<EmployeeListBean>>> {
    private BasePresenter basePresenter;
    private final HomeServer mServer;
    private EmployeeListPresenter employeeListPresenter;

    public ConditionSearchViewModel(Context context, BasePresenter basePresenter, EmployeeListPresenter employeeListPresenter) {
        this.basePresenter = basePresenter;
        mContext = context;
        mServer = new HomeServer(context);
        this.employeeListPresenter = employeeListPresenter;
    }

    public void getSearchData(String id,String xCoordinate,String yCoordinate,String pageIndex) {
        HashMap<String, String> map = new HashMap<>();
        map.put("xCoordinate", xCoordinate);
        map.put("yCoordinate", yCoordinate);
        map.put("conditionId", id);
        map.put("demandType", "1");
        map.put("pageSize", "10");
        map.put("pageIndex", pageIndex);
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        mSubscriber = getConditionSearchSubscriber();
        mServer.getSearchData(mSubscriber, map);
    }

    private Subscriber<JsonResponse<List<EmployeeListBean>>> getConditionSearchSubscriber() {
        return new RXSubscriber<JsonResponse<List<EmployeeListBean>>, List<EmployeeListBean>>(basePresenter) {
            @Override
            public void requestNext(List<EmployeeListBean> searchBean) {
                employeeListPresenter.getListData(searchBean);
            }
        };
    }
}
