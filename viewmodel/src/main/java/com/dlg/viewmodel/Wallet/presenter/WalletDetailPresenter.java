package com.dlg.viewmodel.Wallet.presenter;

import com.dlg.data.wallet.model.WalletBean;
import com.dlg.data.wallet.model.WalletListBean;

import java.util.List;

/**
 * 作者：曹伟
 * 主要功能：
 * 创建时间：2017/7/7 09:42
 */

public interface WalletDetailPresenter {
    void getDataList(List<WalletListBean> bean);
}
