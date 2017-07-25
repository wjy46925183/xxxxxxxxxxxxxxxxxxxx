package com.dlg.personal.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.home.model.DataBean;
import com.dlg.personal.R;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/4 10:13
 */

public class HirerDeskAdapter extends BaseLoadMoreHeaderAdapter<DataBean> {
    private int seletedPosition;
    public HirerDeskAdapter(Context mContext, RecyclerView recyclerView, List<DataBean> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, DataBean dataBean) {
        if(holder instanceof BaseViewHolder){
            ((BaseViewHolder) holder).setText(R.id.unit,dataBean.postName+" "+dataBean.price+
                    "元/"+dataBean.jobMeterUnitName);
            switch (dataBean.status){
                case 30:((BaseViewHolder) holder).setText(R.id.text,"待付款");break;
                case 22:((BaseViewHolder) holder).setText(R.id.text,"等待验收");break;
                case 21:((BaseViewHolder) holder).setText(R.id.text,"正在干活中");break;
                case 20:((BaseViewHolder) holder).setText(R.id.text,"等待开工");break;
                case 10:((BaseViewHolder) holder).setText(R.id.text,"等待同意");break;
                case 9:((BaseViewHolder) holder).setText(R.id.text,"拒绝邀请");break;
                case -1:((BaseViewHolder) holder).setText(R.id.text,"发出邀请");break;
            }
            ((BaseViewHolder) holder).setText(R.id.num,"("+dataBean.list.size()+")");
            CheckBox checkBox = ((BaseViewHolder) holder).getView(R.id.cb);
            if(position == seletedPosition){
                checkBox.setChecked(true);
            }else{
                checkBox.setChecked(false);
            }
        }
    }

    /**
     * 设置被选中的位置
     * @param seletedPosition
     */
    public void setSeletedPosition(int seletedPosition){
        this.seletedPosition = seletedPosition;
        notifyDataSetChanged();
    }
}
