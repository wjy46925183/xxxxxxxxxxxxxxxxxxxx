package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.wallet.model.BillDetailListBean;
import com.dlg.data.wallet.model.BillHistoryBean;
import com.dlg.data.wallet.model.InvoiceInformationRestVoBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.InvoiceHistoryDetailPresenter;
import com.dlg.viewmodel.Wallet.presenter.OrderBillPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.WalletServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：邹前坤
 * 主要功能：新开发票
 * 创建时间：2017/7/12 09:20
 */

public class OrderBillViewModel extends BaseViewModel<JsonResponse<List<InvoiceInformationRestVoBean>>>{
    private BasePresenter basePresenter;
    private final WalletServer mServer;
    private OrderBillPresenter invoicePresenter;

    public OrderBillViewModel(Context context, BasePresenter basePresenter, OrderBillPresenter invoicePresenter) {
        this.basePresenter = basePresenter;
        mContext = context;
        mServer = new WalletServer(context);
        this.invoicePresenter=invoicePresenter;
    }

    /**
     * 根据userid获取当前用户消费列表
     */
    public void getOrderBIlltemplate() {
        HashMap<String,String> map = new HashMap<>();
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));


        mSubscriber = getMapSubscriber();
        mServer.getOrderBillTemplate(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<InvoiceInformationRestVoBean>>> getMapSubscriber(){
        return new RXSubscriber<JsonResponse<List<InvoiceInformationRestVoBean>>,List<InvoiceInformationRestVoBean>>(basePresenter) {
            @Override
            public void requestNext(List<InvoiceInformationRestVoBean> walletBeans) {
                if(null != walletBeans && walletBeans.size() > 0){
                    invoicePresenter.getTemplateBean(walletBeans.get(0));
                }
            }
        };
    }
}
