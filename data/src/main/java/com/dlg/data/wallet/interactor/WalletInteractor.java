package com.dlg.data.wallet.interactor;

import com.dlg.data.wallet.model.BillDetailBean;
import com.dlg.data.wallet.model.BillDetailListBean;
import com.dlg.data.wallet.model.BillHistoryBean;
import com.dlg.data.wallet.model.InvoiceInformationRestVoBean;
import com.dlg.data.wallet.model.InvoiceListBean;
import com.dlg.data.wallet.model.WalletBean;
import com.dlg.data.wallet.model.WalletListBean;
import com.dlg.data.wallet.model.WalletListDetailBean;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Observable;

/**
 * 作者：曹伟
 * 主要功能：零钱接口
 * 创建时间：2017/7/6 17:53
 */

public interface WalletInteractor {
    Observable<JsonResponse<List<WalletBean>>> getWalletBalance(HashMap<String, String> map);

	/**
	 * 获得消费列表
	 * @param map
	 * @return
	 */
	Observable<JsonResponse<List<InvoiceListBean>>> getInvoiceBalance(HashMap<String, String> map);
	/*
		 *获得发票列表
		 */
	Observable<JsonResponse<List<BillHistoryBean>>> getInvoiceHistory(HashMap<String, String> map);

	/**
	 * 发票详情
	 * @param map
	 * @return
	 */
	Observable<JsonResponse<List<BillDetailListBean>>> getInvoiceHistoryDetail(HashMap<String, String> map);

	/**
	 * 发票模板
	 * @param map
	 * @return
	 */
	Observable<JsonResponse<List<InvoiceInformationRestVoBean>>> getOrderBillTemplape(HashMap<String, String> map);

	/**
	 * 新建发票
	 * @param map
	 * @return
	 */
	Observable<JsonResponse<List<String>>> getOrderBillNew(HashMap<String, String> map);

	/**
	 * 得到钱包详情列表
	 * @param map
	 * @return
	 */
	Observable<JsonResponse<List<WalletListBean>>> getWalletList(HashMap<String, String> map);

	/**
	 * 得到钱包详情列表 单个详情
	 * @param map
	 * @return
	 */
	Observable<JsonResponse<List<WalletListDetailBean>>> getWalletListDetail(HashMap<String, String> map);

	/**
	 * c重置密码校验验证码
	 * @param map
	 * @return
	 */
	Observable<JsonResponse<List<String>>> getResetPwdCheck(HashMap<String, String> map);

	/**
	 * 重置密码
	 * @param map
	 * @return
	 */
	Observable<JsonResponse<List<String>>> getResetPwd(HashMap<String, String> map);

	/**
	 * 订单支付
	 * @param map
	 * @return
	 */
	Observable<JsonResponse<List<String>>> getPayment(HashMap<String, String> map);
}
