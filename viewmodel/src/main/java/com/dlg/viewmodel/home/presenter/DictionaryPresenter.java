package com.dlg.viewmodel.home.presenter;

import com.dlg.data.home.model.DictionaryBean;

import java.util.List;

/**
 * 作者：小明
 * 主要功能：词典P层
 * 创建时间：2017/6/29 0029 19:54
 */
public interface DictionaryPresenter {
    void getDictionary(List<DictionaryBean>dictionaryBean);
}
