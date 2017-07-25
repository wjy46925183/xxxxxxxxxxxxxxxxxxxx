package com.dlg.personal.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.oddjob.model.OddServiceItemBean;
import com.dlg.personal.R;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/18 10:36
 */

public class ServiceAdapter extends BaseLoadMoreHeaderAdapter<OddServiceItemBean> {
    private int selectPosition = -1;
    public ServiceAdapter(Context mContext, RecyclerView recyclerView, List<OddServiceItemBean> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, final int position, OddServiceItemBean oddServiceItemBean) {
        if (holder instanceof BaseViewHolder) {
            ((BaseViewHolder) holder).setText(R.id.service_name,oddServiceItemBean.getServiceName());
            ((BaseViewHolder) holder).setText(R.id.service_type_name,oddServiceItemBean.getServiceTypeName());
            String unitName = null;
            if (oddServiceItemBean.getServiceMeterUnit() == 1) {
                unitName = "/天";
            } else if (oddServiceItemBean.getServiceMeterUnit() == 2) {
                unitName = "/时";
            } else if (oddServiceItemBean.getServiceMeterUnit() == 3) {
                unitName = "/单";
            }
            ((BaseViewHolder) holder).setText(R.id.service_price,oddServiceItemBean.getPrice()+"元"+unitName);

            final RadioButton rbService = ((BaseViewHolder) holder).getView(R.id.check_service);
            rbService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(rbService.isChecked()){
                        selectPosition = position;
                    }else{
                        selectPosition = -1;
                    }
                    notifyDataSetChanged();
                }
            });
            if(selectPosition == position){
                rbService.setChecked(true);
            }else{
                rbService.setChecked(false);
            }
        }
    }
    /**
     * 被选中位置
     * @return
     */
    public int getSelectedPosition(){
        return selectPosition;
    }
}
