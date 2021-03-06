package com.dlg.personal.oddjob.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.dlg.data.oddjob.model.OddServiceItemBean;
import com.dlg.personal.R;
import com.dlg.personal.oddjob.adapter.OddServiceAdapter;

import java.util.List;

/**
 * 作者：wangdakuan
 * 主要功能：零工服务数据展示
 * 创建时间：2017/7/13 15:14
 */
public class OddServiceView extends LinearLayout {

    private RecyclerView mRecyListView;
    private OddServiceAdapter mServiceAdapter;
    private OddServiceAdapter.onOddServiceItemClick oddServiceItemClick;

    public OddServiceView(Context context) {
        super(context);
        init();
    }

    public OddServiceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OddServiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public OddServiceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * 初始化view
     */
    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.include_recycler_view, this);
        initView(view);
        setVisibility(View.GONE);
    }

    private void initView(View view) {
        mServiceAdapter = new OddServiceAdapter();
        mRecyListView = (RecyclerView) view.findViewById(R.id.recy_list_view);
        mRecyListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyListView.setAdapter(mServiceAdapter);
    }

    public void setOddServiceData(List<OddServiceItemBean> mBeanList) {
        if (null != mServiceAdapter && null != mBeanList && mBeanList.size() > 0) {
            setVisibility(View.VISIBLE);
            mServiceAdapter.setBeanList(mBeanList);
        }
    }

    public void setOddServiceItemClick(OddServiceAdapter.onOddServiceItemClick oddServiceItemClick) {
        this.oddServiceItemClick = oddServiceItemClick;
        if(null != mServiceAdapter){
            mServiceAdapter.setOddServiceItemClick(oddServiceItemClick);
        }
    }
}
