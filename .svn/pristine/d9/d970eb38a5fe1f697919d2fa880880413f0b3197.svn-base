package com.dlg.viewmodel.home;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.home.model.DataBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.home.presenter.HirerDeskPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.HomeServer;

import java.util.HashMap;
import java.util.List;

import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：雇主正在进行中订单
 * 创建时间：2017/7/5 09:34
 */

public class HirerDeskViewModel extends BaseViewModel{
    private HirerDeskPresenter mDeskPresenter;
    private final HomeServer mHomeServer;
    private Context mContext;

    public HirerDeskViewModel(Context context, HirerDeskPresenter mDeskPresenter){
        this.mDeskPresenter = mDeskPresenter;
        mHomeServer = new HomeServer(context);
        this.mContext = context;
    }

    public void getHirerData(){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        hashMap.put("format","json");
        mSubscriber = getSub();
        mHomeServer.getBossTaskingList(mSubscriber,hashMap);
    }

    private Subscriber<List<DataBean>> getSub(){
        return new RXSubscriber<List<DataBean>,List<DataBean>>(null) {
            @Override
            public void requestNext(List<DataBean> dataBeen) {
                mDeskPresenter.getHirerDesk(dataBeen);
            }
        };
    }
}
