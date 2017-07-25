package com.dlg.personal.oddjob.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dlg.data.oddjob.model.OrderCancelBean;
import com.dlg.data.oddjob.model.OrderDispatchBean;
import com.dlg.data.oddjob.model.OrderOperateBean;
import com.dlg.data.oddjob.model.OrderTakingBean;
import com.dlg.personal.R;
import com.dlg.personal.oddjob.adapter.StatisticalOrderAdapter;

import java.util.List;

/**
 * 作者：wangdakuan
 * 主要功能：订单数据统计
 * 创建时间：2017/7/13 15:40
 */
public class StatisticalOrderView extends LinearLayout {

    private RecyclerView mRecyListView;
    private TextView mTvName;
    private StatisticalOrderAdapter mOrderAdapter;
    private int orderType; //订单类型  1 =发单；2=接单；3=取消；4=迟到

    private String hintText;

    public StatisticalOrderView(Context context) {
        super(context);
        init();
    }

    public StatisticalOrderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttribute(attrs);
    }

    public StatisticalOrderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttribute(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StatisticalOrderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttribute(attrs);
    }

    private void initAttribute(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.StatisticalOrderView);
        try {
            orderType = a.getInteger(R.styleable.StatisticalOrderView_orderType, 0);
            hintText = a.getString(R.styleable.StatisticalOrderView_hintText);
        } finally {
            a.recycle();
        }
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
        mOrderAdapter = new StatisticalOrderAdapter(orderType);
        mRecyListView = (RecyclerView) view.findViewById(R.id.recy_list_view);
        mTvName = (TextView) view.findViewById(R.id.tv_name);
        mTvName.setText(hintText);
        mRecyListView.setHasFixedSize(true);
        mRecyListView.setNestedScrollingEnabled(false);
        mRecyListView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyListView.setAdapter(mOrderAdapter);
    }

    /**
     * 取消
     *
     * @param orderCancelRestVos
     */
    public void setOrderCancelRestVos(List<OrderCancelBean> orderCancelRestVos) {
        if (null != mOrderAdapter && null != orderCancelRestVos && orderCancelRestVos.size() > 0) {
            setVisibility(View.VISIBLE);
            mOrderAdapter.setOrderCancelRestVos(orderCancelRestVos);
        }
    }

    /**
     * 发单
     *
     * @param orderDispatchRestVos
     */
    public void setOrderDispatchRestVos(List<OrderDispatchBean> orderDispatchRestVos) {
        if (null != mOrderAdapter && null != orderDispatchRestVos && orderDispatchRestVos.size() > 0) {
            setVisibility(View.VISIBLE);
            mOrderAdapter.setOrderDispatchRestVos(orderDispatchRestVos);
        }
    }

    /**
     * 迟到
     *
     * @param orderOperateRestVos
     */
    public void setOrderOperateRestVos(List<OrderOperateBean> orderOperateRestVos) {
        if (null != mOrderAdapter && null != orderOperateRestVos && orderOperateRestVos.size() > 0) {
            setVisibility(View.VISIBLE);
            mOrderAdapter.setOrderOperateRestVos(orderOperateRestVos);
        }
    }

    /**
     * 接单
     *
     * @param orderTakingRestVos
     */
    public void setOrderTakingRestVos(List<OrderTakingBean> orderTakingRestVos) {
        if (null != mOrderAdapter && null != orderTakingRestVos && orderTakingRestVos.size() > 0) {
            setVisibility(View.VISIBLE);
            mOrderAdapter.setOrderTakingRestVos(orderTakingRestVos);
        }
    }
}
