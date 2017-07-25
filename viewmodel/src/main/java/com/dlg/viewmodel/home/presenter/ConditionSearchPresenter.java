package com.dlg.viewmodel.home.presenter;

import com.dlg.data.home.model.ConditionSearchBean;

import java.util.List;

/**
 * 作者：小明
 * 主要功能：條件搜索
 * 创建时间：2017/7/6 0006 17:31
 */
public interface ConditionSearchPresenter {
    void getSearchResult(List<ConditionSearchBean> searchBeans);
}
