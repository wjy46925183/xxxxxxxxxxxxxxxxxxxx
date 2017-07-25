package com.dlg.personal.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.home.model.HistoryBean;
import com.dlg.personal.R;

import java.util.List;

/**
 * 作者：小明
 * 主要功能：条件搜索adapter类
 * 创建时间：2017/7/5 0005 09:34
 */
public class ConditionHistoryAdapter extends BaseLoadMoreHeaderAdapter<HistoryBean> {

    public ConditionHistoryAdapter(Context mContext, RecyclerView recyclerView, List<HistoryBean> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
    }

    String name="";
    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, HistoryBean historyBean) {
        if(holder instanceof BaseViewHolder){
            ((BaseViewHolder) holder).setText(R.id.work_history_name,historyBean.getPostName());
            ((BaseViewHolder) holder).setText(R.id.work_history_type,historyBean.getJobTypeName());
            ((BaseViewHolder) holder).setText(R.id.work_history_price,historyBean.getPrice());

            if (historyBean.getDemandType().equals("1")){
                name="工作日";
            }if (historyBean.getDemandType().equals("2")){
                name="双休日";
            }if (historyBean.getDemandType().equals("3")){
                name="计件";
            }
            ((BaseViewHolder) holder).setText(R.id.work_history_time,name);
        }
    }
}
