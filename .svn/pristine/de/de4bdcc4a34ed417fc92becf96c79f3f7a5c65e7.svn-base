package com.dlg.personal.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dlg.personal.R;

/**
 * 作者：wangdakuan
 * 主要功能：基本横向栏
 * 创建时间：2017/7/12 08:49
 */
public class TextOrEditxtView extends LinearLayout {

    private Context mContext;
    private LayoutInflater inflater;

    private String mLeftText; //左边文字
    private Integer mLeftTextColor; //左边文字颜色
    private float mLeftTextSize; //左边文字字体大小
    private String mRightText; //右边文字
    private Integer mRightTextColor; //右边文字颜色
    private float mRightTextSize; //右边文字字体大小
    private String mRightHintText; //右边提示文字
    private Integer mRightTextHintColor; //右边提示文字
    private float mRightTextHintSize; //右边提示文字
    private int mRightTextPaddingRight; //右边PaddingRight
    private boolean mBottomDivider; //右边提示文字
    private boolean mIsEnabled; //是否可编辑
    private boolean mIsNumber; //是否只可以输入数字
    private int maxLength; //右边可输入的最大值

    private static final int DEFAULT_TIPS_TEXT_SIZE = 16;
    private static final int DEFAULT_TIPS_TEXT_COLOR = Color.GREEN;
    private TextView mTvLeftText;
    private EditText mEdRightText;
    private TextView mTvLine;

    public TextOrEditxtView(Context context) {
        super(context);
        this.mContext = context;
    }

    public TextOrEditxtView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initAttribute(attrs);
    }

    public TextOrEditxtView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initAttribute(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextOrEditxtView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        initAttribute(attrs);
    }

    private void initAttribute(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.TextOrEditxtView);
        try {
            mLeftText = a.getString(R.styleable.TextOrEditxtView_leftText);
            mLeftTextColor = a.getColor(R.styleable.TextOrEditxtView_leftTextColor, DEFAULT_TIPS_TEXT_COLOR);
            mLeftTextSize = a.getDimension(R.styleable.TextOrEditxtView_leftTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, DEFAULT_TIPS_TEXT_SIZE, getResources().getDisplayMetrics()));

            mRightText = a.getString(R.styleable.TextOrEditxtView_rightText);
            mRightTextColor = a.getColor(R.styleable.TextOrEditxtView_rightTextColor, DEFAULT_TIPS_TEXT_COLOR);
            mRightTextSize = a.getDimension(R.styleable.TextOrEditxtView_rightTextSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, DEFAULT_TIPS_TEXT_SIZE, getResources().getDisplayMetrics()));

            mRightHintText = a.getString(R.styleable.TextOrEditxtView_rightHintText);
            mRightTextHintColor = a.getColor(R.styleable.TextOrEditxtView_rightTextHintColor, DEFAULT_TIPS_TEXT_COLOR);
            mRightTextHintSize = a.getDimension(R.styleable.TextOrEditxtView_rightTextHintSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, DEFAULT_TIPS_TEXT_SIZE, getResources().getDisplayMetrics()));
            mRightTextPaddingRight = a.getDimensionPixelOffset(R.styleable.TextOrEditxtView_rightTextPaddingRight, 0);
            maxLength = a.getInteger(R.styleable.TextOrEditxtView_maxLength, 100000);

            mBottomDivider = a.getBoolean(R.styleable.TextOrEditxtView_bottomDivider, true);
            mIsEnabled = a.getBoolean(R.styleable.TextOrEditxtView_isEnabled, true);
            mIsNumber = a.getBoolean(R.styleable.TextOrEditxtView_isNumber, false);
        } finally {
            a.recycle();
        }
        initView();
    }

    private void initView() {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view_text_or_editxt, this);
        mTvLeftText = (TextView) view.findViewById(R.id.tv_left_text);
        mTvLine = (TextView) view.findViewById(R.id.tv_line);
        mEdRightText = (EditText) view.findViewById(R.id.ed_right_text);
        mEdRightText.setPadding(mEdRightText.getPaddingLeft(), mEdRightText.getPaddingTop(), mRightTextPaddingRight, mEdRightText.getPaddingBottom());
        setLeftText(mLeftText);
        setLeftTextColor(mLeftTextColor);
        setLeftTextSize(mLeftTextSize);
        setRightText(mRightText);
        setRightTextColor(mRightTextColor);
        setRightTextSize(mRightTextSize);
        setRightHintText(mRightHintText);
        setRightTextHintColor(mRightTextHintColor);
        setBottomDivider(mBottomDivider);
        setIsEnabled(mIsEnabled);
        setIsNumber(mIsNumber);
    }

    public String getLeftText() {
        return mLeftText;
    }

    public void setLeftText(String mLeftText) {
        this.mLeftText = mLeftText;
        if (null != mTvLeftText) {
            mTvLeftText.setText(mLeftText);
        }
    }

    public Integer getLeftTextColor() {
        return mLeftTextColor;
    }

    public void setLeftTextColor(Integer mLeftTextColor) {
        this.mLeftTextColor = mLeftTextColor;
        if (null != mTvLeftText) {
            mTvLeftText.setTextColor(mLeftTextColor);
        }
    }

    public float getLeftTextSize() {
        return mLeftTextSize;
    }

    public void setLeftTextSize(float mLeftTextSize) {
        this.mLeftTextSize = mLeftTextSize;
        if (null != mTvLeftText) {
            mTvLeftText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mLeftTextSize);
        }
    }

    public String getRightText() {
        return mRightText;
    }

    public void setRightText(String mRightText) {
        this.mRightText = mRightText;
        if (null != mEdRightText) {
            mEdRightText.setText(mRightText);
        }
    }

    public Integer getRightTextColor() {
        return mRightTextColor;
    }

    public void setRightTextColor(Integer mRightTextColor) {
        this.mRightTextColor = mRightTextColor;
        if (null != mEdRightText) {
            mEdRightText.setTextColor(mRightTextColor);
        }
    }

    public float getRightTextSize() {
        return mRightTextSize;
    }

    public void setRightTextSize(float mRightTextSize) {
        this.mRightTextSize = mRightTextSize;
        if (null != mEdRightText) {
            mEdRightText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mRightTextSize);
        }
    }

    public String getRightHintText() {
        return mRightHintText;
    }

    public void setRightHintText(String mRightHintText) {
        this.mRightHintText = mRightHintText;
        if (null != mEdRightText) {
            mEdRightText.setHint(mRightHintText);
        }
    }

    public Integer getRightTextHintColor() {
        return mRightTextHintColor;
    }

    public void setRightTextHintColor(Integer mRightTextHintColor) {
        this.mRightTextHintColor = mRightTextHintColor;
        if (null != mEdRightText) {
            mEdRightText.setHintTextColor(mRightTextHintColor);
        }
    }

    public float getRightTextHintSize() {
        return mRightTextHintSize;
    }

    public boolean isBottomDivider() {
        return mBottomDivider;
    }

    public void setBottomDivider(boolean mBottomDivider) {
        this.mBottomDivider = mBottomDivider;
        if (null != mTvLine) {
            mTvLine.setVisibility(mBottomDivider ? View.VISIBLE : View.GONE);
        }
    }

    public boolean isEnabled() {
        return mIsEnabled;
    }

    public void setIsEnabled(boolean mIsEnabled) {
        this.mIsEnabled = mIsEnabled;
        if (null != mEdRightText) {
            mEdRightText.setEnabled(mIsEnabled);
        }
    }

    public boolean isNumber() {
        return mIsNumber;
    }

    public void setIsNumber(boolean mIsNumber) {
        this.mIsNumber = mIsNumber;
        if (null != mEdRightText) {
            if (mIsNumber) {
                mEdRightText.setInputType(InputType.TYPE_CLASS_NUMBER);
            } else {
                mEdRightText.setInputType(InputType.TYPE_NULL);
            }

        }
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        if (null != mEdRightText) {
            mEdRightText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        }
    }
}
