package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.oddjob.model.OddHirerBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.oddjob.presenter.HirerOddPresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：雇主零工请求
 * 创建时间：2017/7/6 15:19
 */

public class OddHirerViewModel extends BaseViewModel {
    private HirerOddPresenter mHirerOddPresenter;
    private final OddServer mOddServer;
    private Context mContext;

    public OddHirerViewModel(Context context, HirerOddPresenter presenter){
        this.mHirerOddPresenter = presenter;
        mOddServer = new OddServer(context);
        this.mContext = context;
    }

    public void getOddHirerList(String status,int pageIndex){
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        hashMap.put("status", status);//全部
//        map.put("minId", minId);//分页的时候 放的是最后一个查询jobid
        hashMap.put("pageSize", 8 + "");
        hashMap.put("pageIndex", pageIndex + "");
        hashMap.put("format","json");

        mSubscriber = getSub();
        mOddServer.getOddHirerList(mSubscriber,hashMap);
    }

    private Subscriber<JsonResponse<List<OddHirerBean>>> getSub(){
        return new RXSubscriber<JsonResponse<List<OddHirerBean>>, List<OddHirerBean>>(null) {
            @Override
            public void requestNext(List<OddHirerBean> beanList) {
                mHirerOddPresenter.getHirerOddList(beanList);
            }
        };
    }
}
