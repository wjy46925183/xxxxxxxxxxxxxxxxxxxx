package com.dlg.inc.home.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.dlg.inc.R;
import com.dlg.inc.app.IncMApp;


/**
 * 作者：wangdakuan
 * 主要功能：雇主、雇员首页底部框布局
 * 创建时间：2017/6/28 14:21
 */
public class IncHomePromptCardView extends LinearLayout {

    private TextView mTvAddress; //地址显示数据
    private TextView mBtnReleaseJobs; //提示与布局
    private String address;

    public IncHomePromptCardView(Context context) {
        super(context);
        init();
    }

    public IncHomePromptCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IncHomePromptCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IncHomePromptCardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.inc_view_home_prompt_card, this);
        initView(contentView);
    }

    private void initView(View contentView) {
        mTvAddress = (TextView) contentView.findViewById(R.id.tv_address);
        mBtnReleaseJobs = (TextView) contentView.findViewById(R.id.btn_release_jobs);
        mTvAddress.setText(IncMApp.getInstance().getAddress()==null?"正在获取我的位置": IncMApp.getInstance().getAddress());

    }

    /**
     * 显示地图地址控件
     * @return
     */
    public TextView getTvAddress() {
        return mTvAddress;
    }


    /**
     * 设置监听
     * @param addressClick
     */
    public void setTvAddressClick(OnClickListener addressClick) {
        if(null != mTvAddress && null != addressClick){
            mTvAddress.setOnClickListener(addressClick);
        }
    }
    /**
     * 设置监听
     * @param jobsClick
     */
    public void setBtnReleaseJobsClick(OnClickListener jobsClick) {
        if(null != mBtnReleaseJobs && null != jobsClick){
            mBtnReleaseJobs.setOnClickListener(jobsClick);
        }
    }
}
