package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.wallet.model.BindBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.CashPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.CashServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：关蕤
 * 主要功能：查询绑定列表
 * 创建时间：2017/7/12 13:13
 */
public class CashViewModel extends BaseViewModel<JsonResponse<List<BindBean>>> {
    private BasePresenter basePresenter;
    private final CashServer mServer;
    private CashPresenter presenter;

    public CashViewModel(Context context, BasePresenter basePresenter, CashPresenter presenter) {
        this.basePresenter = basePresenter;
        mContext = context;
        mServer = new CashServer(context);
        this.presenter=presenter;
    }

    /**
     * 查询绑定列表
     */
    public void getBindList() {
        HashMap<String,String> map = new HashMap<>();
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        map.put("minId", "0");
        map.put("size", "10");
        map.put("payType", "");
        mSubscriber = getMapSubscriber();
        mServer.getBindList(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<BindBean>>> getMapSubscriber() {
        return new RXSubscriber<JsonResponse<List<BindBean>>,List<BindBean>>(basePresenter) {
            @Override
            public void requestNext(List<BindBean> bindBeans) {
                if(bindBeans != null && bindBeans.size() > 0){
                    for (int i = 0 ; i < bindBeans.size() ; i++){
                        presenter.toMap(bindBeans.get(i));
                    }
                }
            }
        };
    }
}
