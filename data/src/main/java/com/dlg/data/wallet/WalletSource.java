package com.dlg.data.wallet;

import com.alibaba.fastjson.JSON;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.wallet.interactor.WalletInteractor;
import com.dlg.data.wallet.model.BillDetailBean;
import com.dlg.data.wallet.model.BillDetailListBean;
import com.dlg.data.wallet.model.BillHistoryBean;
import com.dlg.data.wallet.model.InvoiceInformationRestVoBean;
import com.dlg.data.wallet.model.InvoiceListBean;
import com.dlg.data.wallet.model.WalletBean;
import com.dlg.data.wallet.model.WalletListBean;
import com.dlg.data.wallet.model.WalletListDetailBean;
import com.dlg.data.wallet.url.WalletUrl;
import com.http.okgo.OkGo;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonConvert;
import okhttp.rx.JsonResponse;
import okhttp.rx.RxAdapter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 作者：曹伟
 * 主要功能：实现接口
 * 创建时间：2017/7/6 17:56
 */

public class WalletSource implements WalletInteractor{
    private final ObjectCache objectCache;

    /**
     */
    public WalletSource(ObjectCache objectCache) {
        this.objectCache = objectCache;
    }

    private Action1<Object> saveToCacheAction(final String key) {
        return new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o != null) {
                    WalletSource.this.objectCache.put(o, key);
                }
            }
        };
    }

    @Override
    public Observable<JsonResponse<List<WalletBean>>> getWalletBalance(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.WALLET_BALANCE)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<WalletBean>>>() {
                }, RxAdapter.<JsonResponse<List<WalletBean>>>create())
                .doOnNext(saveToCacheAction(WalletUrl.WALLET_BALANCE + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<InvoiceListBean>>> getInvoiceBalance(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.INVOICE_LIST_BALANCE)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<InvoiceListBean>>>() {
                }, RxAdapter.<JsonResponse<List<InvoiceListBean>>>create())
               // .doOnNext(saveToCacheAction(WalletUrl.INVOICE_LIST_BALANCE + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<BillHistoryBean>>> getInvoiceHistory(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.INVOICE_BILL_LIST)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<BillHistoryBean>>>() {
                }, RxAdapter.<JsonResponse<List<BillHistoryBean>>>create())
                // .doOnNext(saveToCacheAction(WalletUrl.INVOICE_LIST_BALANCE + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<BillDetailListBean>>> getInvoiceHistoryDetail(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.INVOICE_BILL_DETAILS)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<BillDetailListBean>>>() {
                }, RxAdapter.<JsonResponse<List<BillDetailListBean>>>create())
                // .doOnNext(saveToCacheAction(WalletUrl.INVOICE_LIST_BALANCE + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<InvoiceInformationRestVoBean>>> getOrderBillTemplape(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.ORDER_BILL_TEMPLATE)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<InvoiceInformationRestVoBean>>>() {
                }, RxAdapter.<JsonResponse<List<InvoiceInformationRestVoBean>>>create())
                // .doOnNext(saveToCacheAction(WalletUrl.INVOICE_LIST_BALANCE + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 新建发票
     * @param map
     * @return
     */
    @Override
    public Observable<JsonResponse<List<String>>> getOrderBillNew(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.ORDER_BILL_NEW)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<String>>>() {
                }, RxAdapter.<JsonResponse<List<String>>>create())
                // .doOnNext(saveToCacheAction(WalletUrl.INVOICE_LIST_BALANCE + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 钱包详情列表
     * @param map
     * @return
     */
    @Override
    public Observable<JsonResponse<List<WalletListBean>>> getWalletList(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.WALLET_DETAILS_LIST)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<WalletListBean>>>() {
                }, RxAdapter.<JsonResponse<List<WalletListBean>>>create())
                // .doOnNext(saveToCacheAction(WalletUrl.INVOICE_LIST_BALANCE + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<WalletListDetailBean>>> getWalletListDetail(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.WALLET_DETAILS_LIST_DETAIL)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<WalletListDetailBean>>>() {
                }, RxAdapter.<JsonResponse<List<WalletListDetailBean>>>create())
                // .doOnNext(saveToCacheAction(WalletUrl.INVOICE_LIST_BALANCE + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<String>>> getResetPwdCheck(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.RESET_PWD_CHECK_VERIFYCODE)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<String>>>() {
                }, RxAdapter.<JsonResponse<List<String>>>create())
                // .doOnNext(saveToCacheAction(WalletUrl.INVOICE_LIST_BALANCE + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<String>>> getResetPwd(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.RESET_PWD_UPDATE_PWD)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<String>>>() {
                }, RxAdapter.<JsonResponse<List<String>>>create())
                // .doOnNext(saveToCacheAction(WalletUrl.INVOICE_LIST_BALANCE + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<String>>> getPayment(HashMap<String, String> map) {
        return OkGo.post(WalletUrl.PAYMENT)//
                .params(map)
                .getCall(new JsonConvert<JsonResponse<List<String>>>() {
                }, RxAdapter.<JsonResponse<List<String>>>create())
                // .doOnNext(saveToCacheAction(WalletUrl.INVOICE_LIST_BALANCE + JSON.toJSONString(map)))
                .observeOn(AndroidSchedulers.mainThread());
    }
}
