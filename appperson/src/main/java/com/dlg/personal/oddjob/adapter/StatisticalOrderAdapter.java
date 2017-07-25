package com.dlg.personal.oddjob.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.utils.DateUtils;
import com.dlg.data.oddjob.model.OrderCancelBean;
import com.dlg.data.oddjob.model.OrderDispatchBean;
import com.dlg.data.oddjob.model.OrderOperateBean;
import com.dlg.data.oddjob.model.OrderTakingBean;
import com.dlg.personal.R;

import java.util.List;

/**
 * 作者：wangdakuan
 * 主要功能：数据统计展示适配器
 * 创建时间：2017/7/13 14:53
 */
public class StatisticalOrderAdapter extends RecyclerView.Adapter<StatisticalOrderAdapter.ViewHolder> {

    private int mOrderType = 0; //1 =发单；2=接单；3=取消；4=迟到

    private List<OrderCancelBean> orderCancelRestVos;//取消列表
    private List<OrderDispatchBean> orderDispatchRestVos;//发单列表
    private List<OrderOperateBean> orderOperateRestVos;//迟到列表
    private List<OrderTakingBean> orderTakingRestVos;//接单列表

    public StatisticalOrderAdapter(int mOrderType) {
        this.mOrderType = mOrderType;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public StatisticalOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistical_order, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(StatisticalOrderAdapter.ViewHolder holder, int position) {
        switch (mOrderType) {
            case 1:
                OrderDispatchBean dispatchBean = orderDispatchRestVos.get(position);
                setViewValue(holder, dispatchBean.getPostName(), dispatchBean.getPrice() + "", dispatchBean.getJobMeterUnitName(),
                        dispatchBean.getStartTime(), dispatchBean.getEndTime());
                break;
            case 2:
                OrderTakingBean takingBean = orderTakingRestVos.get(position);
                setViewValue(holder, takingBean.getPostName(), takingBean.getPrice() + "", takingBean.getMeterUnitName(),
                        takingBean.getStartDate(), takingBean.getEndDate());
                break;
            case 3:
                OrderCancelBean cancelBean = orderCancelRestVos.get(position);
                setViewValue(holder, cancelBean.getPostName(), cancelBean.getPrice() + "", cancelBean.getMeterUnitName(),
                        cancelBean.getStartDate() + "", cancelBean.getEndDate() + "");
                holder.mTvWhy.setText("取消原因：" + cancelBean.getCancelCause());
                holder.mTvWhy.setVisibility(View.VISIBLE);
                break;
            case 4:
                OrderOperateBean operateBean = orderOperateRestVos.get(position);
                setViewValue(holder, operateBean.getPostName(), operateBean.getPrice() + "", operateBean.getMeterUnitName(),
                        operateBean.getStartDate() + "", operateBean.getEndDate() + "");
                holder.mTvWhy.setText("迟到原因：" + operateBean.getExceptionTime() + "分钟");
                holder.mTvWhy.setVisibility(View.VISIBLE);
                break;
        }
        if (position >= getItemCount()-1) {
            holder.mTvLine.setVisibility(View.GONE);
        } else {
            holder.mTvLine.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置控件上的值
     *
     * @param holder
     * @param postName  名称
     * @param price     价格
     * @param unitName  单位
     * @param startTime 开始时间
     * @param endTime   结束时间
     */
    private void setViewValue(StatisticalOrderAdapter.ViewHolder holder, String postName, String price, String unitName, String startTime, String endTime) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(postName).append("  ").append(price).append("元");
        if (!TextUtils.equals("志愿义工", postName)) {
            if (!TextUtils.isEmpty(unitName)) {
                buffer.append("/").append(unitName);
            }
        }
        StringBuilder builderTime = new StringBuilder();
        if (!TextUtils.isEmpty(startTime)) {
            builderTime.append(DateUtils.dateTimeFormat("yyyy.MM.dd", startTime));
        }
        if (!TextUtils.isEmpty(endTime)) {
            if (!TextUtils.isEmpty(builderTime.toString())) {
                builderTime.append("-");
            }
            builderTime.append(DateUtils.dateTimeFormat("yyyy.MM.dd", endTime));
        }
        holder.mTvName.setText(buffer.toString());
        holder.mTvDate.setText(builderTime.toString());
    }

    @Override
    public int getItemCount() {
        switch (mOrderType) {
            case 1:
                return null != orderDispatchRestVos ? orderDispatchRestVos.size() : 0;
            case 2:
                return null != orderTakingRestVos ? orderTakingRestVos.size() : 0;
            case 3:
                return null != orderCancelRestVos ? orderCancelRestVos.size() : 0;
            case 4:
                return null != orderOperateRestVos ? orderOperateRestVos.size() : 0;
        }
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName; //名称
        private TextView mTvDate; //时间
        private TextView mTvWhy; //原因
        private TextView mTvLine; //分割线

        public ViewHolder(View view) {
            super(view);
            mTvName = (TextView) view.findViewById(R.id.tv_name);
            mTvDate = (TextView) view.findViewById(R.id.tv_date);
            mTvWhy = (TextView) view.findViewById(R.id.tv_why);
            mTvLine = (TextView) view.findViewById(R.id.tv_line);
        }
    }

    public void setOrderCancelRestVos(List<OrderCancelBean> orderCancelRestVos) {
        this.orderCancelRestVos = orderCancelRestVos;
        notifyDataSetChanged();
    }

    public void setOrderDispatchRestVos(List<OrderDispatchBean> orderDispatchRestVos) {
        this.orderDispatchRestVos = orderDispatchRestVos;
        notifyDataSetChanged();
    }

    public void setOrderOperateRestVos(List<OrderOperateBean> orderOperateRestVos) {
        this.orderOperateRestVos = orderOperateRestVos;
        notifyDataSetChanged();
    }

    public void setOrderTakingRestVos(List<OrderTakingBean> orderTakingRestVos) {
        this.orderTakingRestVos = orderTakingRestVos;
        notifyDataSetChanged();
    }
}
