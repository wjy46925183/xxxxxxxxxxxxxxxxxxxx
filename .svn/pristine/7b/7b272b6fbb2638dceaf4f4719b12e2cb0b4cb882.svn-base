package com.dlg.personal.oddjob.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.oddjob.model.ListBean;
import com.dlg.personal.R;
import com.dlg.personal.oddjob.view.HirerPublicView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：有人接单
 * 创建时间：2017/7/10 11:11
 */

public class HasOderAdapter extends BaseLoadMoreHeaderAdapter<ListBean>{
    private List<Boolean> mBooleanList = new ArrayList<>();

    public HasOderAdapter(Context mContext, RecyclerView recyclerView, List<ListBean> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
        mBooleanList.clear();
        for (int i = 0; i < mDatas.size(); i++) {
            mBooleanList.add(false);
        }
    }

    /**
     * 绑定数据
     * @param mContext
     * @param holder
     * @param position
     * @param listBean
     */
    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, final int position, ListBean listBean) {
        if (holder instanceof BaseViewHolder) {
            HirerPublicView view = ((BaseViewHolder) holder).getView(R.id.hirer_public_view);
            CheckBox checkBox = view.getCheckBox();
            checkBox.setChecked(mBooleanList.get(position));
            view.setContent(listBean);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    mBooleanList.set(position, isChecked);
                    if(isAllSeleted()){
                        mCheckBox.setChecked(true);
                    }else{
                        mCheckBox.setChecked(false);
                    }
                }
            });
        }
    }

    /**
     * 全选
     */
    public void allSeleted() {
        mBooleanList.clear();
        for (int i = 0; i < mDatas.size(); i++) {
            mBooleanList.add(true);
        }
        notifyDataSetChanged();
    }

    /**
     * 全都不选
     */
    public void allUnSeleted() {
        mBooleanList.clear();
        for (int i = 0; i < mDatas.size(); i++) {
            mBooleanList.add(false);
        }
        notifyDataSetChanged();
    }

    /**
     * 是否全选 是 true 否 false
     *
     * @return
     */
    public boolean isAllSeleted() {
        for (int i = 0; i < mBooleanList.size(); i++) {
            if (!mBooleanList.get(i)) {
                return false;
            }
        }
        return true;
    }

    private CheckBox mCheckBox;

    /**
     * 全部选择按钮
     * @param checkBox
     */
    public void setAllCheckBox(CheckBox checkBox) {
        this.mCheckBox = checkBox;
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCheckBox.isChecked()){
                    allSeleted();
                }else{
                    allUnSeleted();
                }
            }
        });
    }

    public String getBusinessNumbers(){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mBooleanList.size(); i++) {
            if(mBooleanList.get(i)){
                sb.append(mDatas.get(i).getBusinessNumber()+",");
            }
        }
        return sb.toString();
    }
}
