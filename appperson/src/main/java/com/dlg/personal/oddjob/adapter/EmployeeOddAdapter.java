package com.dlg.personal.oddjob.adapter;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.common.utils.DateUtils;
import com.dlg.data.common.model.ActionButtonsBean;
import com.dlg.data.common.model.CountdownBean;
import com.dlg.data.oddjob.model.EmployeeOrderItemBean;
import com.dlg.personal.R;
import com.dlg.personal.oddjob.view.EmployeePublicView;

import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * 作者：wangdakuan
 * 主要功能：雇员我的零工适配器
 * 创建时间：2017/7/7 10:35
 */
public class EmployeeOddAdapter extends BaseAdapter {

    private Context mContext;
    private List<EmployeeOrderItemBean> orderItemBeen; //我的零工数据雇员
    private onReviewBtnClick reviewBtnClick; //点评点击事件接口

    public EmployeeOddAdapter(Context context, List<EmployeeOrderItemBean> orderItemBeen) {
        this.mContext = context;
        this.orderItemBeen = orderItemBeen;
    }

    @Override
    public int getCount() {
        return null != orderItemBeen ? orderItemBeen.size() : 0;
    }

    @Override
    public EmployeeOrderItemBean getItem(int i) {
        return orderItemBeen.get(i);
    }

    @Override
    public int getViewTypeCount() {
        // menu type count
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        EmployeeOrderItemBean data = getItem(position);
        int isCancel = data.getIsCancel();
        if (isCancel == 1) {//订单已取消，可以侧滑删除
            return 0;
        } else {//订单没有取消，不可以侧滑删除
            return 1;
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_employee_odd, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        EmployeeOrderItemBean itemBean = orderItemBeen.get(position);
        viewHolder.mEmployeePublicView.setContent(itemBean);

        if (null != itemBean.getButtonsBeen()) {
            setPerStatusView(itemBean, itemBean.getButtonsBeen(), viewHolder);
        }
        return convertView;
    }

    /**
     * 根据不同的状态，显示不同的布局。
     */
    public void setPerStatusView(final EmployeeOrderItemBean itemBean, ActionButtonsBean buttonsBean, ViewHolder viewHolder) {
        viewHolder.mLayoutTypeView.removeAllViews();
        switch (itemBean.getStatus()) {
            case 40://已完成(需要判断已点评还是未点评)
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_odd_employee_type, null);
                ViewHolderType holderType = new ViewHolderType(view);
                viewHolder.mLayoutTypeView.addView(view);
                holderType.mTvLeft.setText(buttonsBean.getStatusText());

                if (itemBean.getIsEvaluate() == 0) { //未点评
                    View view1 = LayoutInflater.from(mContext).inflate(R.layout.item_odd_employee_type, null);
                    ViewHolderType holderType1 = new ViewHolderType(view1);
                    viewHolder.mLayoutTypeView.addView(view1);
                    holderType1.mTvLeft.setText("未点评");
                    holderType1.mTvRight.setText("点评");
                    holderType1.mTvRight.setVisibility(View.VISIBLE);
                    holderType1.mTvRight.setTextColor(mContext.getResources().getColor(R.color.app_color_yellow_01));
                    holderType1.mTvRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (null != reviewBtnClick) {
                                reviewBtnClick.review(itemBean);
                            }
                        }
                    });
                } else { //已点评
                    View view1 = LayoutInflater.from(mContext).inflate(R.layout.item_odd_employee_type, null);
                    ViewHolderType holderType1 = new ViewHolderType(view1);
                    viewHolder.mLayoutTypeView.addView(view1);
                    holderType1.mTvLeft.setText("已点评");
                    holderType1.mRbScoreGrade.setVisibility(View.VISIBLE);
                    int evaluateLevel = TextUtils.isEmpty(itemBean.getEvaluateLevel()) ? 5 : parseInt(itemBean.getEvaluateLevel());
                    holderType1.mRbScoreGrade.setRating(evaluateLevel);
                }
                break;
            default: //已完成其它的状态
                View view2 = LayoutInflater.from(mContext).inflate(R.layout.item_odd_employee_type, null);
                ViewHolderType holderType2 = new ViewHolderType(view2);
                viewHolder.mLayoutTypeView.addView(view2);
                holderType2.mTvLeft.setText(buttonsBean.getStatusText());
                if (itemBean.getIsCancel() == 1) {
                    holderType2.mTvRight.setText(itemBean.getCancelCause());
                    holderType2.mTvRight.setVisibility(View.VISIBLE);
                }
                CountdownBean bean = buttonsBean.getCountdownVo();
                if (null != bean && bean.getRemainingTime() != 0) {
                    countTime(bean.getRemainingTime() + "", bean.getMapTipsText(), holderType2);
                }
                break;
        }
    }

    /**
     * 设置时间
     *
     * @param remainingTime
     * @param statusText
     * @param viewHolder
     */
    private void countTime(String remainingTime, final String statusText, final ViewHolderType viewHolder) {
        if (null == remainingTime) return;
        long time = Long.parseLong(remainingTime);
        if (null != viewHolder.countDownTimer) {
            viewHolder.countDownTimer.cancel();
            viewHolder.countDownTimer = null;
        }
        viewHolder.mTvRight.setText("");
        CountDownTimer countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (null != viewHolder && null != viewHolder.mTvRight) {
                    viewHolder.mTvRight.setText(
                            DateUtils.formatTime(millisUntilFinished) + statusText);
                }
            }

            @Override
            public void onFinish() {
                cancel();
                if (null != viewHolder && null != viewHolder.mTvRight) {
                    viewHolder.mTvRight.setText("");
                }
            }
        }.start();
        viewHolder.countDownTimer = countDownTimer;
    }

    class ViewHolder {
        private EmployeePublicView mEmployeePublicView;
        private LinearLayout mLayoutTypeView; //订单状态类型总布局

        public ViewHolder(View view) {
            mLayoutTypeView = (LinearLayout) view.findViewById(R.id.layout_type_view);
            mEmployeePublicView = (EmployeePublicView) view.findViewById(R.id.employee_view);
        }
    }

    class ViewHolderType {
        private TextView mTvLeft; //左边控件显示
        private TextView mTvRight; //右边控件显示
        private RatingBar mRbScoreGrade; //评价星级
        private CountDownTimer countDownTimer;

        public ViewHolderType(View view) {
            mTvLeft = (TextView) view.findViewById(R.id.tv_left);
            mTvRight = (TextView) view.findViewById(R.id.tv_right);
            mRbScoreGrade = (RatingBar) view.findViewById(R.id.rb_score_grade);
        }
    }

    public interface onReviewBtnClick {
        void review(EmployeeOrderItemBean orderItemBean);
    }

    /**
     * 点评点击事件
     *
     * @param reviewBtnClick
     */
    public void setReviewBtnClick(onReviewBtnClick reviewBtnClick) {
        this.reviewBtnClick = reviewBtnClick;
    }
}
