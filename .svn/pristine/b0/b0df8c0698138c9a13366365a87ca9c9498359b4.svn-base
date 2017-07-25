package com.dlg.personal.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;


import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.home.model.DictionaryBean;
import com.dlg.personal.R;

import java.util.List;

/**
 * 作者：小明
 * 主要功能：
 * 创建时间：2017/6/29 0029 11:37
 */
public class HotSearchAdapter extends BaseLoadMoreHeaderAdapter<DictionaryBean> {

    public HotSearchAdapter(Context context, RecyclerView recyclerView, List<DictionaryBean> mDatas, int mLayoutId){
        super(context, recyclerView, mDatas, mLayoutId);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, DictionaryBean dictionaryBean) {
        if(holder instanceof BaseViewHolder) {
            ((BaseViewHolder) holder).setText(R.id.text, dictionaryBean.getDataName());
        }
    }
}
