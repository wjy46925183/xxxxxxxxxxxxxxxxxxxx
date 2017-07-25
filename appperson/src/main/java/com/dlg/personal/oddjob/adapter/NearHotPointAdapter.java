package com.dlg.personal.oddjob.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.amap.api.services.help.Tip;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.personal.R;

import java.util.List;

/**
 * 作者：小明
 * 主要功能：
 * 创建时间：2017/7/12 0012 08:59
 */
public class NearHotPointAdapter extends BaseLoadMoreHeaderAdapter<Tip> {
    public NearHotPointAdapter(Context mContext, RecyclerView recyclerView, List<Tip> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, Tip tip) {
        if(holder instanceof BaseViewHolder){
            ((BaseViewHolder) holder).setText(R.id.searcher_address_name,tip.getName());
            ((BaseViewHolder) holder).setText(R.id.searcher_address_address,tip.getAddress());

        }
    }
}
