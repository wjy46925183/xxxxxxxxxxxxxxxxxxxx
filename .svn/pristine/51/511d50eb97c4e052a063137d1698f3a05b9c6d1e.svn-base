package com.dlg.personal.oddjob.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.home.model.DictionaryBean;
import com.dlg.personal.R;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/13 15:34
 */

public class CancleAdapter extends BaseLoadMoreHeaderAdapter<DictionaryBean> {
    private int position = -1;

    public CancleAdapter(Context mContext, RecyclerView recyclerView, List<DictionaryBean> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, DictionaryBean bean) {
        if (holder instanceof BaseViewHolder) {
            ((BaseViewHolder) holder).setText(R.id.name_text, bean.getDataName());
            CheckBox checkBox = ((BaseViewHolder) holder).getView(R.id.checkbox);
            checkBox.setClickable(false);
            if(this.position == position){
                checkBox.setChecked(true);
            }else{
                checkBox.setChecked(false);
            }
        }
    }

    public void setSeleted(int seletedPostion){
        this.position = seletedPostion;
        notifyDataSetChanged();
    }
    public int getSeletetPosition(){
        return position;
    }

    public String getCodeData(){
        return mDatas.get(position).getDataCode();
    }
}
