package com.dlg.viewmodel.home.presenter;

import com.dlg.data.home.model.ServiceShareBean;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/19 09:39
 */

public interface ShareServicePresenter {
    void shareService(List<ServiceShareBean> serviceShareBeen);
    void error(String errorMsg);
}
