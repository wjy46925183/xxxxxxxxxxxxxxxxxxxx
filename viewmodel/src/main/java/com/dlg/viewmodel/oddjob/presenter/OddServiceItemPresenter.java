package com.dlg.viewmodel.oddjob.presenter;

import com.dlg.data.oddjob.model.OddServiceItemBean;

import java.util.List;

/**
 * 作者：wangdakuan
 * 主要功能：零工服务列表数据接口
 * 创建时间：2017/7/13 13:24
 */
public interface OddServiceItemPresenter {

    void onOddServiceData(List<OddServiceItemBean> beanList);

}
