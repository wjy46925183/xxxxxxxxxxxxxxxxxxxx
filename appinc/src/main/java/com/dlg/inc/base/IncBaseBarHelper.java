package com.dlg.inc.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.sys.RuleUtils;
import com.dlg.inc.R;

import de.hdodenhof.circleimageview.CircleImageView;



/**
 * 作者：wangdakuan
 * 主要功能: 标题ToolBar 封装
 * 创建时间：2017/6/1 12:44
 */
public class IncBaseBarHelper extends LinearLayout {
    //上下文，创建view的时候需要用到
    protected Context mContext;

    protected View mToolBarView; //自定义的title布局

    //toolbar
    protected RelativeLayout mToolBar; //标题布局
    protected ImageView toolbarBack; //返回
    protected TextView toolbarTitle; //标题
    protected TextView toolbarTextRight; //右边文字
    protected TextView dividerLine;
    protected CircleImageView ivHead;

    //视图构造器
    protected LayoutInflater mInflater;

    public IncBaseBarHelper(Context context) {
        super(context);
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        /*初始化toolbar*/
        initToolBar();
    }

    public IncBaseBarHelper(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        /*初始化toolbar*/
        initToolBar();
    }

    public IncBaseBarHelper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        /*初始化toolbar*/
        initToolBar();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IncBaseBarHelper(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        /*初始化toolbar*/
        initToolBar();
    }

    protected void initToolBar() {
        /*通过inflater获取toolbar的布局文件*/
        mToolBarView = mInflater.inflate(R.layout.inc_toolbar_title_view, this);
        mToolBar = (RelativeLayout) mToolBarView.findViewById(R.id.toolbar);
        toolbarBack = (ImageView) mToolBarView.findViewById(R.id.toolbar_back);
        toolbarTitle = (TextView) mToolBarView.findViewById(R.id.toolbar_title);
        toolbarTextRight = (TextView) mToolBarView.findViewById(R.id.toolbar_text_right);
        dividerLine = (TextView) mToolBarView.findViewById(R.id.divider_line);
        ivHead = (CircleImageView) mToolBarView.findViewById(R.id.iv_head_title);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //设置状态栏背景状态
            //true：表明当前Android系统版本>=4.4
            mToolBarView.setPadding(0, RuleUtils.getStatusBarHeight(mContext), 0, 0);

        }
//        else {
//            LayoutParams linearParams = (LayoutParams) mToolBar.getLayoutParams(); //取控件textView当前的布局参数
//            linearParams.height = mContext.getResources().getDimensionPixelOffset(R.dimen.height_5_5_80);// 控件的高强制设成20
//            mToolBar.setLayoutParams(linearParams); //使设置好的布局参数应用到控件</pre>
//            mToolBar.setPadding(0, 0, 0, 0);
//        }
    }

    public RelativeLayout getToolBar() {
        return mToolBar;
    }

    public View getToolBarView() {
        return mToolBarView;
    }

    public TextView getToolbarTitle() {
        return toolbarTitle;
    }

    public void setToolbarTitle(String title) {
        if (null != toolbarTitle) {
            toolbarTitle.setText(title);
        }
    }

    public ImageView getToolbarBack() {
        return toolbarBack;
    }


    public TextView getToolbarTextRight() {
        return toolbarTextRight;
    }

    public void setToolbarTextRight(String textRight) {
        if (!TextUtils.isEmpty(textRight)) {
            if (null != toolbarTextRight && !toolbarTextRight.isShown()) {
                toolbarTextRight.setVisibility(View.VISIBLE);
            }
        }
        if (null != toolbarTextRight) {
            this.toolbarTextRight.setText(textRight);
        }
    }

    public void setToolbarTextRightVisibility(int visibility) {
        if (null != toolbarTextRight) {
            this.toolbarTextRight.setVisibility(visibility);
        }
    }

    public void setToolbarRightTextVisibility(int visibility) {
        if (null != toolbarTextRight) {
            this.toolbarTextRight.setVisibility(visibility);
        }
    }

    public void setToolbarTextRightOnClickListener(OnClickListener listener) {
        if (null != toolbarTextRight) {
            this.toolbarTextRight.setOnClickListener(listener);
        }
    }

    public void setIsShownDividerLine(boolean isShownDividerLine) {
        if (null != dividerLine) {
            dividerLine.setVisibility(isShownDividerLine ? View.VISIBLE : View.GONE);
        }
    }

    public CircleImageView getIvHead() {
        return ivHead;
    }

    public void setIvHead(String url){
        Glide.with(getContext())
                .load(url)
                .error(R.mipmap.icon_default_user)
                .into(ivHead);
        if(!TextUtils.isEmpty(url)){
            ivHead.setVisibility(View.VISIBLE);
        }
    }
}
