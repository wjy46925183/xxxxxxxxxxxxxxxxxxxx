package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.wallet.model.WalletBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.WalletPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.WalletServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：曹伟
 * 主要功能：钱包model
 * 创建时间：2017/7/6 16:37
 */

public class WalletViewModel extends BaseViewModel<JsonResponse<List<WalletBean>>>{
    private BasePresenter basePresenter;
    private final WalletServer mServer;
    private WalletPresenter walletPresenter;

    public WalletViewModel(Context context, BasePresenter basePresenter, WalletPresenter walletPresenter) {
        this.basePresenter = basePresenter;
        mContext = context;
        mServer = new WalletServer(context);
        this.walletPresenter=walletPresenter;
    }

    /**
     * 根据userid获取当前用户零钱信息
     */
    public void getWalletBalance() {
        HashMap<String,String> map = new HashMap<>();
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        mSubscriber = getMapSubscriber();
        mServer.getWalletBalance(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<WalletBean>>> getMapSubscriber(){
        return new RXSubscriber<JsonResponse<List<WalletBean>>,List<WalletBean>>(basePresenter) {
            @Override
            public void requestNext(List<WalletBean> walletBeans) {
                if(null != walletBeans && walletBeans.size() > 0){
                    walletPresenter.toMap(walletBeans.get(0));
                }
            }
        };
    }
}
