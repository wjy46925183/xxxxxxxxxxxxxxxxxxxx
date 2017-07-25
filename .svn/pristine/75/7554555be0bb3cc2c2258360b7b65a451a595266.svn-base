package com.dlg.personal.oddjob.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.utils.DateUtils;
import com.dlg.data.oddjob.model.OddHirerBean;
import com.dlg.data.oddjob.model.OrderStatusListBean;
import com.dlg.personal.R;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：雇主零工
 * 创建时间：2017/7/6 14:09
 */

public class HirerOddAdapter extends BaseAdapter {
    public Context mContext;
    private List<OddHirerBean> job;

    public HirerOddAdapter(Context context, List<OddHirerBean> job) {
        this.mContext = context;
        this.job = job;
    }

    @Override
    public int getCount() {
        return job == null ? 0 : job.size();
    }

    @Override
    public OddHirerBean getItem(int position) {
        return job.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        // menu type count
        return 2;
    }
    @Override
    public int getItemViewType(int position) {
        OddHirerBean item = getItem(position);
        if (item.orderStatusList.size() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_hirer_odd, null);
            viewHolder = new ViewHolder();
            viewHolder.mOrdernameText = (TextView) convertView.findViewById(R.id.ordername_text);
            viewHolder.mTypeText = (TextView) convertView.findViewById(R.id.type_text);
            viewHolder.mBaoxian = (TextView) convertView.findViewById(R.id.baoxian);
            viewHolder.mTimeText = (TextView) convertView.findViewById(R.id.time_text);
            viewHolder.mTvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.mLinearLayout = (LinearLayout) convertView.findViewById(R.id.ll_status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        OddHirerBean item = getItem(position);
        viewHolder.mOrdernameText.setText(item.postName);
        if (!item.postTypeName.equals("志愿义工")) {
            viewHolder.mTvPrice.setText(item.price + "元/" + item.jobMeterUnitName);
        } else {
            viewHolder.mTvPrice.setText("志愿义工");
        }
        String timeShow = DateUtils.getTimeShow(item.demandType, item.startYear, item.startMonth, item.startDay
                , item.startHour, item.startMinute, item.endYear, item.endMonth, item.endDay, item.endHour, item.endMinute);
        viewHolder.mTimeText.setText(timeShow);
        int type = item.type;
        if (type == 1) {
            viewHolder.mTypeText.setText("工作日");
        } else if (type == 2) {
            viewHolder.mTypeText.setText("双休日");
        } else {
            viewHolder.mTypeText.setText("计件");
        }
        int isFarmersInsurance = item.isFarmersInsurance;
        if (isFarmersInsurance == 0) {
            viewHolder.mBaoxian.setVisibility(View.GONE);
        } else {
            viewHolder.mBaoxian.setVisibility(View.VISIBLE);
        }

        /**
         * 多少种状态 for循环
         */
        viewHolder.mLinearLayout.removeAllViews();//移除所有的view 初始化
        for (int i = 0; i < item.orderStatusList.size(); i++) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_odd_status, null);
            TextView tv_status = (TextView) inflate.findViewById(R.id.tv_status);
            LinearLayout llHeads = (LinearLayout) inflate.findViewById(R.id.ll_status);
            TextView tvPerson = (TextView) inflate.findViewById(R.id.tv_deng_person);

            OrderStatusListBean orderStatusListBean = item.orderStatusList.get(i);

            /**
             * 头像及多少人
             */
            tvPerson.setVisibility(View.INVISIBLE);//初始化隐藏
            if (orderStatusListBean != null || orderStatusListBean.list != null) {
                int size = orderStatusListBean.list.size();
                for (int j = 0; j < size; j++) {
                    View headImg = LayoutInflater.from(mContext).inflate(R.layout.item_img, null);
                    ImageView iv_head = (ImageView) headImg.findViewById(R.id.head_iv);

                    Glide.with(mContext).load(orderStatusListBean.list.get(j).getLogo()).fitCenter()
                            .override(150, 150)
                            .error(R.mipmap.mrtx).into(iv_head);
                    llHeads.addView(headImg);
                    if(j == 2){//大于3个人的时候 跳出循环
                        break;
                    }
                }

                if(size >=3){
                    tvPerson.setText("等" + size + "人");
                    tvPerson.setVisibility(View.VISIBLE);
                }
            }
            /**
             * 状态描述
             */
            String statusText = "";
            switch (item.orderStatusList.get(i).status) {
                case 6:
                    statusText = "等待雇员同意";
                    break;
                case 7:
                    statusText = "雇员已拒绝";
                    break;
                case 10:
                    statusText = "有人接单";
                    break;
                case 20:
                case 21:
                case 22:
                    statusText = "正在干活中";
                    break;
                case 30:
                    statusText = "待付款";
                    break;
                case 40:
                    statusText = "已完成";
                    break;
                case 50:
                    statusText = "已取消";
                    break;
                case 8:
                    statusText = "等待雇员同意";
                    break;
            }
            tv_status.setText(statusText);
            viewHolder.mLinearLayout.addView(inflate);
        }

        return convertView;
    }

    class ViewHolder {
        TextView mOrdernameText, mTypeText, mBaoxian, mTimeText, mTvPrice;
        LinearLayout mLinearLayout;
    }
}
