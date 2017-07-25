package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.wallet.model.BillHistoryBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.InvoiceHistoryPresenter;
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

public class InvoiceHistoryViewModel extends BaseViewModel<JsonResponse<List<BillHistoryBean>>>{
    private BasePresenter basePresenter;
    private final WalletServer mServer;
    private InvoiceHistoryPresenter invoicePresenter;

    public InvoiceHistoryViewModel(Context context, BasePresenter basePresenter, InvoiceHistoryPresenter invoicePresenter) {
        this.basePresenter = basePresenter;
        mContext = context;
        mServer = new WalletServer(context);
        this.invoicePresenter=invoicePresenter;
    }

    /**
     * 根据userid获取当前用户消费列表
     */
    public void getInvoiceHistory(int currentPage) {
        HashMap<String,String> map = new HashMap<>();
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        map.put("size", "10");
        map.put("minId", "0");
        map.put("pageNumber", currentPage + "");
        mSubscriber = getMapSubscriber();
        mServer.getInvoicHistory(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<BillHistoryBean>>> getMapSubscriber(){
        return new RXSubscriber<JsonResponse<List<BillHistoryBean>>,List<BillHistoryBean>>(basePresenter) {
            @Override
            public void requestNext(List<BillHistoryBean> walletBeans) {
                invoicePresenter.getDataList(walletBeans);
            }
        };
    }
}
