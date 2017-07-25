package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.wallet.model.InvoiceInformationRestVoBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.OrderBillPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.WalletServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：邹前坤
 * 主要功能：新开发票
 * 创建时间：2017/7/12 09:20
 */

public class OrderBillNewViewModel extends BaseViewModel<JsonResponse<List<String>>>{
    private BasePresenter basePresenter;
    private final WalletServer mServer;
    private OrderBillPresenter invoicePresenter;

    public OrderBillNewViewModel(Context context, BasePresenter basePresenter, OrderBillPresenter invoicePresenter) {
        this.basePresenter = basePresenter;
        mContext = context;
        mServer = new WalletServer(context);
        this.invoicePresenter=invoicePresenter;
    }

    /**
     * 根据userid获取当前用户消费列表
     * @param map
     */
    public void getOrderBIllNew(HashMap<String, String> map) {
        mSubscriber = getMapSubscriber();
        mServer.getOrderBillNew(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<String>>> getMapSubscriber(){
        return new RXSubscriber<JsonResponse<List<String>>,List<String>>(basePresenter) {
            @Override
            public void requestNext(List<String> walletBeans) {
                if(null != walletBeans && walletBeans.size() > 0){
                    invoicePresenter.getOkOrErroe(walletBeans.get(0));
                }
            }
        };
    }
}
