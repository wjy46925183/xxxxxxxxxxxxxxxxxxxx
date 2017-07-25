package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.wallet.model.WalletBean;
import com.dlg.data.wallet.model.WalletListDetailBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.WalletDetailDetailPresenter;
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

public class WalletDetailDetailViewModel extends BaseViewModel<JsonResponse<List<WalletListDetailBean>>>{
    private BasePresenter basePresenter;
    private final WalletServer mServer;
    private WalletDetailDetailPresenter walletPresenter;

    public WalletDetailDetailViewModel(Context context, BasePresenter basePresenter, WalletDetailDetailPresenter walletPresenter) {
        this.basePresenter = basePresenter;
        mContext = context;
        mServer = new WalletServer(context);
        this.walletPresenter=walletPresenter;
    }

    /**
     * 根据userid获取当前用户零钱信息
     */
    public void getWalletDetaildetail(String billid) {
        HashMap<String,String> map = new HashMap<>();
        map.put("subBillBusinessNumber", billid);
        mSubscriber = getMapSubscriber();
        mServer.getWalletDetailDetailBean(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<WalletListDetailBean>>> getMapSubscriber(){
        return new RXSubscriber<JsonResponse<List<WalletListDetailBean>>,List<WalletListDetailBean>>(basePresenter) {
            @Override
            public void requestNext(List<WalletListDetailBean> walletBeans) {
                if(null != walletBeans && walletBeans.size() > 0){
                    walletPresenter.getWalletDetailDetailBean(walletBeans.get(0));
                }
            }
        };
    }
}
