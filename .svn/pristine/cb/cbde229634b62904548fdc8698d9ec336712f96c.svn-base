package com.dlg.personal.oddjob.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlg.data.home.model.DoingTaskOrderDetailBean;
import com.dlg.data.oddjob.model.BaseOddDetailBean;
import com.dlg.data.oddjob.model.ListBean;
import com.dlg.personal.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import de.hdodenhof.circleimageview.CircleImageView;
import rx.functions.Action1;

/**
 * 作者：王进亚
 * 主要功能：雇主零工详情 公共view
 * 创建时间：2017/7/10 10:33
 */

public class HirerPublicView extends FrameLayout {
    private CheckBox mCheckbox;
    private CircleImageView mIvHead;
    private TextView mNameText;
    private TextView mFenText;
    private RatingBar mStarbar01;
    private ImageView mCallBtn;

    public HirerPublicView(@NonNull Context context) {
        super(context);
        init();
    }

    public HirerPublicView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HirerPublicView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HirerPublicView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    /**
     * 初始化view
     */
    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_public_hire, null);
        initView(view);
    }

    /**
     * 初始化view
     *
     * @param view
     */
    private void initView(View view) {
        mCheckbox = (CheckBox) view.findViewById(R.id.checkbox);
        mIvHead = (CircleImageView) view.findViewById(R.id.iv_head);
        mNameText = (TextView) view.findViewById(R.id.name_text);
        mFenText = (TextView) view.findViewById(R.id.fen_text);
        mStarbar01 = (RatingBar) view.findViewById(R.id.starbar_01);
        mCallBtn = (ImageView) view.findViewById(R.id.call_btn);
        addView(view);
    }

    /**
     * 返回CheckBox
     *
     * @return
     */
    public CheckBox getCheckBox() {
        return mCheckbox;
    }

    /**
     * 填充数据
     *
     * @param listBean
     */
    public void setContent(final ListBean listBean) {
        Glide.with(getContext()).load(listBean.getLogo())
                .placeholder(R.mipmap.mrtx).error(R.mipmap.mrtx).into(mIvHead);
        mNameText.setText(listBean.getName());
        mFenText.setText(listBean.getCreditCount());
        mStarbar01.setRating(Float.parseFloat(listBean.getScoreCount()));
        if (TextUtils.isEmpty(listBean.getPhone())) {
            mCallBtn.setVisibility(View.GONE);
        }
        mCallBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions rx = new RxPermissions((Activity) getContext());
                rx.request(Manifest.permission.CALL_PHONE)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    callPhone(listBean.getPhone());
                                }
                            }
                        });
            }
        });
    }

    /**
     * 填充数据
     *
     * @param contentEmployee
     */
    public void setContentEmployee(final BaseOddDetailBean contentEmployee) {
        Glide.with(getContext()).load(contentEmployee.getLogo())
                .placeholder(R.mipmap.mrtx).error(R.mipmap.mrtx).into(mIvHead);
        mNameText.setText(contentEmployee.getName());
        mFenText.setText(contentEmployee.getCreditCount());
        mStarbar01.setRating(Float.parseFloat(contentEmployee.getScoreCount()));
        if (TextUtils.isEmpty(contentEmployee.getPhone())) {
            mCallBtn.setVisibility(View.GONE);
        }
        mCallBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                RxPermissions rx = new RxPermissions((Activity) getContext());
                rx.request(Manifest.permission.CALL_PHONE)
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                if (aBoolean) {
                                    callPhone(contentEmployee.getPhone());
                                }
                            }
                        });
            }
        });
    }

    /**
     * 打电话
     */
    private void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri u = Uri.parse("tel:" + phoneNum);
        intent.setData(u);
        getContext().startActivity(intent);
    }
}
