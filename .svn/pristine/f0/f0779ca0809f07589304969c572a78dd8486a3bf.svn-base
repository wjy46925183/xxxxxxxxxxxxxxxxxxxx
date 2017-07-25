package com.dlg.viewmodel.home;

import android.content.Context;
import android.text.TextUtils;

import com.common.cache.ACache;
import com.dlg.data.home.model.EmployeeListBean;
import com.dlg.data.home.model.OddJobMarketBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.home.presenter.EmployeeListPresenter;
import com.dlg.viewmodel.home.presenter.OddJobMarketPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.HomeServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：邹前坤
 * 主要功能：代理商首页零工列表
 * 创建时间：2017/7/24 15:40
 */

public class OddJobMarketViewModel extends BaseViewModel<JsonResponse<List<OddJobMarketBean>>> {
    private BasePresenter basePresenter;
    private OddJobMarketPresenter oddJobMarketPresenter;
    private final HomeServer mServer;

    public OddJobMarketViewModel(Context context, BasePresenter presenter, OddJobMarketPresenter oddJobMarketPresenter) {
        this.basePresenter = presenter;
        mContext = context;
        this.oddJobMarketPresenter = oddJobMarketPresenter;
        mServer = new HomeServer(context);
    }

    public void getOddJobMarketList(int curntPage,String xCoordinate,String yCoordinate) {
        HashMap<String, String> map = new HashMap<>();
        /**
         * 当前登录用户
         "pageSize", "6"//每页数量
         "pageIndex", "0"//当前页，从0开始
         xCoordinate: （选填，需要计算距离时必填）
         yCoordinate: （选填，需要计算距离时必填）
         */
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        map.put("pageSize", "10");
        map.put("pageIndex", curntPage+"");
        map.put("xCoordinate", xCoordinate);
        map.put("yCoordinate", yCoordinate);
        mSubscriber = getListtext();
        mServer.getOddJobMarket(mSubscriber, map);
    }

    /**
     * JsonResponse<></> 不变 <请到的数据></>
     * @return
     */
    private Subscriber<JsonResponse<List<OddJobMarketBean>>> getListtext() {
        return new RXSubscriber<JsonResponse<List<OddJobMarketBean>>, List<OddJobMarketBean>>(basePresenter) {
            @Override
            public void requestNext(List<OddJobMarketBean> t) {
                oddJobMarketPresenter.getOddJobMarketList(t);
            }
        };
    }
}
