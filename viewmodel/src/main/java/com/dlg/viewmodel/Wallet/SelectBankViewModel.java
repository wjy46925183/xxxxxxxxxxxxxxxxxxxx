package com.dlg.viewmodel.Wallet;

import android.content.Context;

import com.common.cache.ACache;
import com.common.utils.Base64Utils;
import com.common.utils.MD5Utils;
import com.dlg.data.wallet.model.BankBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.Wallet.presenter.SelectBankPresenter;
import com.dlg.viewmodel.Wallet.presenter.WithDrawalPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.server.WithDrawalServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：关蕤
 * 主要功能：查询可提现次数
 * 创建时间：2017/7/12 09:37
 */
public class SelectBankViewModel extends BaseViewModel<JsonResponse<List<BankBean>>> {

    private BasePresenter basePresenter;
    private final WithDrawalServer mServer;
    private SelectBankPresenter presenter;

    public SelectBankViewModel(Context context, BasePresenter basePresenter, SelectBankPresenter rechargePresenter) {
        this.basePresenter = basePresenter;
        mContext = context;
        mServer = new WithDrawalServer(context);
        this.presenter=rechargePresenter;
    }
    /**
     * 查询可免费提现次数
     */
    public void getBank(String name) {
        HashMap<String,String> map = new HashMap<>();
        map.put("bankName", name);
        mSubscriber = getMapSubscriber();
        mServer.getBank(mSubscriber,map);
    }

    private Subscriber<JsonResponse<List<BankBean>>> getMapSubscriber() {
        return new RXSubscriber<JsonResponse<List<BankBean>>,List<BankBean>>(basePresenter) {
            @Override
            public void requestNext(List<BankBean> bean) {
                    if(bean != null && bean.size() > 0){
                        presenter.getBank(bean);
                    }

            }
        };
    }

}
