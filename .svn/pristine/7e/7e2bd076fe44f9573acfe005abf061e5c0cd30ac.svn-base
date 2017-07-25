package com.dlg.personal.oddjob.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.utils.DateUtils;
import com.dlg.data.oddjob.model.BaseOddDetailBean;
import com.dlg.data.oddjob.model.OrderTimeBean;
import com.dlg.personal.R;
import com.dlg.personal.oddjob.adapter.EmployeeOddAdapter;

/**
 * 作者：王达宽
 * 主要功能：雇员零工详情 公共view
 * 创建时间：2017/7/10 10:33
 */

public class EmployeePublicView extends FrameLayout {

    private TextView mTvOrderName; //订单名称
    private TextView mTvType; //订单类型
    private ImageView mTvInsurance; //是否有保险
    private TextView mTvTime; //时间
    private TextView mTvPrice; //价格

    public EmployeePublicView(@NonNull Context context) {
        super(context);
        init();
    }

    public EmployeePublicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmployeePublicView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EmployeePublicView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * 初始化view
     */
    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_employee_view, null);
        initView(view);
    }

    /**
     * 初始化view
     *
     * @param view
     */
    private void initView(View view) {
        mTvOrderName = (TextView) view.findViewById(R.id.tv_order_name);
        mTvType = (TextView) view.findViewById(R.id.tv_type);
        mTvInsurance = (ImageView) view.findViewById(R.id.tv_insurance);
        mTvTime = (TextView) view.findViewById(R.id.tv_time);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        addView(view);

    }

    /**
     * 填充数据
     *
     * @param oddDetailBean
     */
    public void setContent(final BaseOddDetailBean oddDetailBean) {
        mTvOrderName.setText(oddDetailBean.getPostName());
        mTvType.setText(oddDetailBean.getDemandTypeName());
        if (oddDetailBean.getIsFarmersInsurance() == 0) {
            mTvInsurance.setVisibility(View.GONE);
        } else {
            mTvInsurance.setVisibility(View.VISIBLE);
        }
        if ("志愿义工".equals(oddDetailBean.getPostTypeName())) {
            //单价
            mTvPrice.setText(oddDetailBean.getPostTypeName());
        } else {
            //单价
            mTvPrice.setText(oddDetailBean.getPrice() + "元/" + oddDetailBean.getMeterUnitName());
        }
        setOrderDate(oddDetailBean.getOrderTimeResultRpcVo(),oddDetailBean.getDemandType());
    }

    /**
     * 时间控件显示
     *
     * @param dateData
     * @param demadType
     */
    public void setOrderDate(OrderTimeBean dateData, int demadType) {
        if(null == dateData){
            return;
        }
        int startYear = dateData.getStartYear();
        int startMonth = dateData.getStartMonth();
        int startDay = dateData.getStartDay();
        int startHour = dateData.getStartHour();
        int startMinute = dateData.getStartMinute();
        int startSecond = dateData.getStartSecond();

        int endYear = dateData.getEndYear();
        int endMonth = dateData.getEndMonth();
        int endDay = dateData.getEndDay();
        int endHour = dateData.getEndHour();
        int endMinute = dateData.getEndMinute();
        int endSecond = dateData.getEndSecond();

        String dateStr = DateUtils.getTimeShow(demadType, startYear, startMonth, startDay, startHour
                , startMinute, endYear, endMonth, endDay, endHour, endMinute);
        mTvTime.setText(dateStr);
    }

}
