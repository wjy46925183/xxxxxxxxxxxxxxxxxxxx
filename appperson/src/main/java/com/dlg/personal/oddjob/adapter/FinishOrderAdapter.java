package com.dlg.personal.oddjob.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.oddjob.model.ListBean;
import com.dlg.personal.R;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.oddjob.activity.EvaluateHirerActivity;
import com.dlg.personal.oddjob.view.HirerPublicView;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：已完成
 * 创建时间：2017/7/10 13:38
 */

public class FinishOrderAdapter extends BaseLoadMoreHeaderAdapter<ListBean> {
    public FinishOrderAdapter(Context mContext, RecyclerView recyclerView, List<ListBean> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
    }

    @Override
    public void convert(final Context mContext, RecyclerView.ViewHolder holder, int position, final ListBean listBean) {
        if (holder instanceof BaseViewHolder) {
            HirerPublicView view = ((BaseViewHolder) holder).getView(R.id.public_view);
            view.setContent(listBean);
            view.getCheckBox().setVisibility(View.GONE);
            TextView tvStatus = ((BaseViewHolder) holder).getView(R.id.status_text);
            TextView tvPrice = ((BaseViewHolder) holder).getView(R.id.tv_price);
            TextView tvPing = ((BaseViewHolder) holder).getView(R.id.ping_text);
            RatingBar ratingBar = ((BaseViewHolder) holder).getView(R.id.fen_ratingBar);
            TextView tvNoPing = ((BaseViewHolder) holder).getView(R.id.tv_no_ping);

            tvPing.setVisibility(View.GONE);
            ratingBar.setVisibility(View.GONE);
            if(listBean.isEvaluate.equals("0")){
                tvNoPing.setVisibility(View.VISIBLE);
                tvNoPing.setText("未评价");
                tvPing.setText("评价");
                tvPing.setVisibility(View.VISIBLE);
                tvPing.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("DetailBean",listBean);
                        ActivityNavigator.navigator().navigateTo(EvaluateHirerActivity.class,bundle);
                    }
                });
            }else{
                tvNoPing.setVisibility(View.VISIBLE);
                tvPing.setVisibility(View.GONE);
                tvNoPing.setText("已评价");
                ratingBar.setRating(Float.parseFloat(listBean.getScoreCount()));
                ratingBar.setVisibility(View.VISIBLE);
            }
            tvStatus.setText("已支付");
            tvPrice.setText(listBean.getPrice()+"");
        }
    }


}
