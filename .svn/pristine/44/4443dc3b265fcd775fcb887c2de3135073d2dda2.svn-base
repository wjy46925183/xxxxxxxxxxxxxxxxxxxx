package com.dlg.personal.oddjob.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlg.data.oddjob.model.OddServiceItemBean;
import com.dlg.personal.R;

import java.util.List;

/**
 * 作者：wangdakuan
 * 主要功能：服务数据展示
 * 创建时间：2017/7/13 15:18
 */
public class OddServiceAdapter extends RecyclerView.Adapter<OddServiceAdapter.ViewHolder> {

    private List<OddServiceItemBean> mBeanList; //零工服务数据

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_odd_service, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final OddServiceItemBean itemBean = mBeanList.get(position);
        int serviceMeterUnit = itemBean.getServiceMeterUnit();
        String unitName = "";
        if (serviceMeterUnit == 1) {
            unitName = "/天";
        } else if (serviceMeterUnit == 2) {
            unitName = "/时";
        } else if (serviceMeterUnit == 3) {
            unitName = "/单";
        }
        StringBuffer buffer = new StringBuffer(itemBean.getServiceName());
        buffer.append("  ").append(itemBean.getPrice()).append("元").append(unitName);
        holder.mTvName.setText(buffer.toString());
        if (position >= getItemCount() - 1) {
            holder.mTvLine.setVisibility(View.GONE);
        } else {
            holder.mTvLine.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(null != oddServiceItemClick){
                    oddServiceItemClick.onOddServiceItem(itemBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null != mBeanList ? mBeanList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName; //名称
        private TextView mTvLine; //分割线

        public ViewHolder(View view) {
            super(view);
            mTvName = (TextView) view.findViewById(R.id.tv_name);
            mTvLine = (TextView) view.findViewById(R.id.tv_line);
        }
    }

    public void setBeanList(List<OddServiceItemBean> mBeanList) {
        this.mBeanList = mBeanList;
        notifyDataSetChanged();
    }
    private onOddServiceItemClick oddServiceItemClick;

    public void setOddServiceItemClick(onOddServiceItemClick oddServiceItemClick) {
        this.oddServiceItemClick = oddServiceItemClick;
    }

    public interface onOddServiceItemClick{
        void onOddServiceItem(OddServiceItemBean itemBean);
    }
}
