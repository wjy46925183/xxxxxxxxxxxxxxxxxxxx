package com.dlg.personal.home.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dlg.data.common.model.ActionButtonsBean;
import com.dlg.data.common.model.AssistButtonBean;
import com.dlg.data.common.model.ButtonBean;
import com.dlg.personal.R;
import com.dlg.personal.home.activity.HomeActivity;

import java.util.List;

/**
 * 作者：wangdakuan
 * 主要功能：订单的操作按钮控件
 * 创建时间：2017/7/3 18:03
 */
public class OrderButtnView extends LinearLayout {

    protected LayoutInflater inflater;
    protected Context mContext;
    private TextView mTextView; //操作按钮

    public OrderButtnView(Context context) {
        super(context);
        mContext = context;
        inflater = LayoutInflater.from(context);
        setOrientation(HORIZONTAL);
    }

    public OrderButtnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        inflater = LayoutInflater.from(context);
        setOrientation(HORIZONTAL);
    }

    public OrderButtnView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inflater = LayoutInflater.from(context);
        setOrientation(HORIZONTAL);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public OrderButtnView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        inflater = LayoutInflater.from(context);
        setOrientation(HORIZONTAL);
    }

    /**
     * 添加按钮
     *
     * @param buttonData
     */
    public void setButtonData(ActionButtonsBean buttonData) {
        String statusText = buttonData.getStatusText();
        removeAllViews();
        if (null != buttonData) {
            List<ButtonBean> buttonBeen = buttonData.getButtonList();
            List<AssistButtonBean> assistButtonBeen = buttonData.getAssistButtonList();

            if (null != assistButtonBeen && assistButtonBeen.size() > 0) {
                for (final AssistButtonBean bean : assistButtonBeen) {
                    final View view = inflater.inflate(R.layout.item_button_type, this, false);
                    final TextView textView = (TextView) view.findViewById(R.id.tv_name);
                    textView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (null != buttonClickListener) {
                                buttonClickListener.assistButtOnClick(bean);
                            }
                        }
                    });
                    textView.setTextColor(mContext.getResources().getColor(R.color.gray_text));
                    textView.setBackgroundResource(R.drawable.pressed_layout);
                    textView.setText(bean.getButtonText());
                    if (getChildCount() > 0) {
                        View line = inflater.inflate(R.layout.item_line, this, false);
                        addView(line);
                    }
                    addView(view);
                }
            }

            if (null != buttonBeen && buttonBeen.size() > 0) {
                int num = 0;
                for (final ButtonBean buttonBean : buttonBeen) {
                    num++;
                    View view = inflater.inflate(R.layout.item_button_type, this, false);
                    final TextView textView = (TextView) view.findViewById(R.id.tv_name);
                    textView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (null != buttonClickListener) {
//                                textView.setVisibility(INVISIBLE);
                                buttonClickListener.buttOnClick(buttonBean);
                            }
                        }
                    });
                    //1.已取消 3.取消
                    if (num >= buttonBeen.size()&&buttonBean.getOperateStatusCode() != 1
                            &&buttonBean.getOperateStatusCode() != 3) {
                        mTextView = textView;
                        textView.setTextColor(mContext.getResources().getColor(R.color.app_color_white));
                        textView.setBackgroundResource(R.drawable.btn_selector_orange);
                    } else {
                        textView.setTextColor(mContext.getResources().getColor(R.color.gray_text));
                        textView.setBackgroundResource(R.drawable.pressed_layout);
                    }
                    if(buttonBean.getOperateStatusCode() == 40 && getContext() instanceof HomeActivity){//确认支付
                        View view2 = inflater.inflate(R.layout.item_button_type, this, false);
                        TextView textView2 = (TextView) view2.findViewById(R.id.tv_name);
                        textView2.setTextColor(mContext.getResources().getColor(R.color.gray_text));
                        textView2.setBackgroundResource(R.color.white);
                        textView2.setText("报酬："+price+"元");
                        addView(view2);
                    }
                    textView.setText(buttonBean.getOperateStatusText());
                    addView(view);

                }
            }
        }
    }

    public interface onButtonClickListener {
        void buttOnClick(ButtonBean buttonBean);

        void assistButtOnClick(AssistButtonBean assistButtonBean);
    }

    /**
     * 按钮事件添加
     */
    private onButtonClickListener buttonClickListener;

    public void setButtonClickListener(onButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    public TextView getTextView() {
        return mTextView;
    }

    /**
     * 是否可点击
     * @param isClickable
     */
    public void setClickButton(boolean isClickable) {
        if(null != mTextView){
            mTextView.setClickable(isClickable);
            mTextView.setEnabled(isClickable);
        }
    }

    private double price;
    public void setPrice(double price){
        this.price = price;
    }
}
