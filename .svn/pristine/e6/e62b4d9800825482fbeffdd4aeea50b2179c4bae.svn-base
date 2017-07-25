package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.common.utils.DateUtils;
import com.dlg.data.wallet.model.InvoiceListBean;
import com.dlg.data.wallet.model.WalletListBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.InvoicePresenter;
import com.dlg.viewmodel.Wallet.presenter.WalletDetailPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.WalletServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：邹前坤
 * 主要功能：发票列表 model
 * 创建时间：2017/7/11 09:20
 */

public class WalletDetailViewModel extends BaseViewModel<JsonResponse<List<WalletListBean>>>{
    private BasePresenter basePresenter;
    private final WalletServer mServer;
    private WalletDetailPresenter walletDetailPresenter;

    public WalletDetailViewModel(Context context, BasePresenter basePresenter, WalletDetailPresenter walletDetailPresenter) {
        this.basePresenter = basePresenter;
        mContext = context;
        mServer = new WalletServer(context);
        this.walletDetailPresenter=walletDetailPresenter;
    }

    /**
     * 根据userid获取当前用户消费列表
     */
    public void getInvoiceBalance(int currentPage,String billStatus) {
        HashMap<String,String> map = new HashMap<>();
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        map.put("pageSize", "10");
        map.put("minId", "0");
        map.put("status", billStatus);
        map.put("isPossessInvoice", "");
        map.put("pageNumber", currentPage + "");

        mSubscriber = getMapSubscriber();
        mServer.getWalletList(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<WalletListBean>>> getMapSubscriber(){
        return new RXSubscriber<JsonResponse<List<WalletListBean>>,List<WalletListBean>>(basePresenter) {
            @Override
            public void requestNext(List<WalletListBean> walletBeans) {
                if(null != walletBeans && walletBeans.size() > 0){

                }
                walletDetailPresenter.getDataList(walletBeans);
            }
        };
    }
}
