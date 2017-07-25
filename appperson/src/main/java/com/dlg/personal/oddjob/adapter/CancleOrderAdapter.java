package com.dlg.personal.oddjob.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.oddjob.model.ListBean;
import com.dlg.personal.R;
import com.dlg.personal.oddjob.view.HirerPublicView;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：取消订单
 * 创建时间：2017/7/10 13:19
 */

public class CancleOrderAdapter extends BaseLoadMoreHeaderAdapter<ListBean> {

    public CancleOrderAdapter(Context mContext, RecyclerView recyclerView, List<ListBean> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, ListBean listBean) {
        if(holder instanceof BaseViewHolder){
            HirerPublicView view = ((BaseViewHolder) holder).getView(R.id.public_view);
            view.setContent(listBean);
            view.getCheckBox().setVisibility(View.GONE);

            TextView tvStatus = ((BaseViewHolder) holder).getView(R.id.status_text);
            TextView tvCancleReseason = ((BaseViewHolder) holder).getView(R.id.cancle_reseason_text);
            TextView btnCancle = ((BaseViewHolder) holder).getView(R.id.cancle_btn);
            TextView tvAgree = ((BaseViewHolder) holder).getView(R.id.agree_tv);

            tvCancleReseason.setVisibility(View.VISIBLE);
            btnCancle.setVisibility(View.GONE);
            tvAgree.setVisibility(View.GONE);

            tvStatus.setText("已取消");
            tvCancleReseason.setText(listBean.cancelCause);
        }
    }
}