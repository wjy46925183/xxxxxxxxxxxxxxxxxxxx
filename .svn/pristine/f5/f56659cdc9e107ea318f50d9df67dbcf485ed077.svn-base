package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.common.string.LogUtils;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.PayViewPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.PayViewServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：关蕤
 * 主要功能：查询用户是否设置过支付密码
 * 创建时间：2017/7/14 09:59
 */
public class PayViewModel extends BaseViewModel<JsonResponse<List<Boolean>>> {
    private PayViewPresenter presenter;
    private final PayViewServer server;

    public PayViewModel(Context context, PayViewPresenter presenter){
        server = new PayViewServer(context);
        this.presenter = presenter;
        mContext = context ;
    }

    /**
     * 判断用户是否设置过支付密码
     * code = 0  设置过
     * code = 1  未设置
     */
    public void judgePwd() {
        HashMap map = new HashMap();
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        mSubscriber = hasPwd();
        server.judgePwd(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<Boolean>>> hasPwd() {
        return new RXSubscriber<JsonResponse<List<Boolean>>, List<Boolean>>(null) {
            @Override
            public void requestNext(List<Boolean> booleen) {
                super.requestNext(booleen);
                if(null != booleen){
                    presenter.getHasPwd(booleen.get(0));
                }else{
                    presenter.getHasPwd(false);
                }
            }

        };
    }
}
