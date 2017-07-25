package com.dlg.viewmodel.oddjob.presenter;

import com.dlg.data.oddjob.model.EmployeeOrderItemBean;

import java.util.List;

/**
 * 作者：wangdakuan
 * 主要功能：雇员零工列表数据回调
 * 创建时间：2017/7/6 15:19
 */
public interface EmployeeOddPresenter {
    void getEmployeeOddList(List<EmployeeOrderItemBean> job);
}
