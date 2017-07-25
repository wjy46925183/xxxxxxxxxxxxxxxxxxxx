package com.dlg.viewmodel.Wallet.presenter;

import com.dlg.data.wallet.model.BillHistoryBean;

import java.util.List;

/**
 * 作者：邹前坤
 * 主要功能： 传出去需要的数据
 * 创建时间：2017/7/11 17:21
 */

public interface InvoiceHistoryPresenter {
	void getDataList(List<BillHistoryBean> bean);
}
