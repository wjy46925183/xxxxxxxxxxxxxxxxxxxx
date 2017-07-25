package com.dlg.inc.home.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.common.string.LogUtils;
import com.dlg.data.home.model.OrderCreateInfo;
import com.dlg.data.oddjob.model.HirerMapBean;
import com.dlg.data.user.model.UserAttributeInfoBean;
import com.dlg.data.user.model.UserInfoDataBean;
import com.dlg.data.user.model.UserRestInfoBean;
import com.dlg.inc.R;


import de.hdodenhof.circleimageview.CircleImageView;
import static com.dlg.inc.R.id.tv_service;


/**
 * 作者：wangdakuan
 * 主要功能：雇主内容卡片控件
 * 创建时间：2017/6/30 13:53
 */
public class IncHirerContentCardView extends LinearLayout {

    private CircleImageView mIvHead; //头像
    private TextView mTvCompany; //诚信值
    private TextView mTvName; //名称
    private TextView mTvAnswer; //接单数
    private TextView mTvService; //服务名称
    private TextView mTvDistance; //距离
    private RatingBar mBarStarts; //评分星级
    private TextView mTvIdentity; //身份
    private TextView mTvDescribe; //描述
    private Context mContext;

    public IncHirerContentCardView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public IncHirerContentCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public IncHirerContentCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public IncHirerContentCardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init();
    }

    private void init() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.inc_view_hirer_content_card, this);
        initView(contentView);
    }

    private void initView(View contentView) {
        mIvHead = (CircleImageView) contentView.findViewById(R.id.iv_head);
        mTvCompany = (TextView) contentView.findViewById(R.id.tv_company);
        mTvName = (TextView) contentView.findViewById(R.id.tv_name);
        mTvAnswer = (TextView) contentView.findViewById(R.id.tv_Answer);
        mTvService = (TextView) contentView.findViewById(tv_service);
        mTvDistance = (TextView) contentView.findViewById(R.id.tv_distance);
        mBarStarts = (RatingBar) contentView.findViewById(R.id.bar_starts);
        mTvIdentity = (TextView) contentView.findViewById(R.id.tv_identity);
        mTvDescribe = (TextView) contentView.findViewById(R.id.tv_describe);
    }

    public void setUserInfo(UserInfoDataBean infoDataBean) {
        setUserAttributeRestVo(infoDataBean.getUserAttributeRestVo());
        setUserRestVo(infoDataBean.getUserRestVo());
    }

    public void setUserRestVo(UserRestInfoBean userRestVo) {
        if (null != userRestVo) {
            if (!TextUtils.isEmpty(userRestVo.getUsername())) {
                if (TextUtils.isEmpty(mTvName.getText().toString())) {
                    mTvName.setText(userRestVo.getUsername());
                }
            }
        }
    }

    public void setUserAttributeRestVo(UserAttributeInfoBean userAttributeRestVo) {
        LogUtils.e("setUserAttributeRestVo===" + JSON.toJSONString(userAttributeRestVo));
        if (null != userAttributeRestVo) {
            Glide.with(mContext).load(userAttributeRestVo.getLogo())
                    .error(R.mipmap.ic_morentouxiang)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_morentouxiang)
                    .into(mIvHead);

            mTvDescribe.setText(userAttributeRestVo.getPersonalizedSignature());
            mTvCompany.setText(userAttributeRestVo.getCreditCount());
            if (userAttributeRestVo.getServiceCountNum() != 0) {
                mTvService.setVisibility(View.VISIBLE);
                mTvService.setText("服务" + userAttributeRestVo.getServiceCount() + "项");
            } else {
                mTvService.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(userAttributeRestVo.getName())) {
                mTvName.setText(userAttributeRestVo.getName());
            } else {
                mTvName.setText("");
            }
            mTvIdentity.setText(userAttributeRestVo.getIdentityName());

            mTvDistance.setText(String.format("距离%skm", userAttributeRestVo.getDistance()));
            mBarStarts.setRating(TextUtils.isEmpty(userAttributeRestVo.getScoreCount()) ? 5 : Float.parseFloat(userAttributeRestVo.getScoreCount()));
            mTvAnswer.setText(TextUtils.isEmpty(userAttributeRestVo.getJoinCount()) ? "" : "已接" + userAttributeRestVo.getJoinCount() + "单");
        }
    }

    public void setOrderCreateInfo(OrderCreateInfo orderCreateInfo) {
        if (null != orderCreateInfo) {
            Glide.with(mContext).load(orderCreateInfo.logo)
                    .error(R.mipmap.ic_morentouxiang)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_morentouxiang)
                    .into(mIvHead);

            if (!TextUtils.isEmpty(orderCreateInfo.description)) {
                mTvDescribe.setText(orderCreateInfo.description);
            }
            if (!TextUtils.isEmpty(orderCreateInfo.creditCount)) {
                mTvCompany.setText(orderCreateInfo.creditCount);
            }

            if (orderCreateInfo.serviceAmount != null) {
                mTvService.setVisibility(View.VISIBLE);
                mTvService.setText("服务" + orderCreateInfo.serviceAmount + "项");
            } else {
                mTvService.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(orderCreateInfo.name)) {
                mTvName.setText(orderCreateInfo.name);
            } else {
                mTvName.setText("");
            }

            switch (orderCreateInfo.identity) {
                case 1:
                    mTvIdentity.setText("自由工作者");
                    break;
                case 2:
                    mTvIdentity.setText("兼职人员");
                    break;
                case 0:
                    mTvIdentity.setText("在校学生");
                    break;
            }
            mTvDistance.setText(String.format("距离%skm", orderCreateInfo.distance));
            mBarStarts.setRating(TextUtils.isEmpty(orderCreateInfo.scoreCount) ? 5 : Float.parseFloat(orderCreateInfo.scoreCount));
            mTvAnswer.setText(TextUtils.isEmpty(orderCreateInfo.joinCount + "")
                    ? "" : "已接" + orderCreateInfo.joinCount + "单");
        }
    }

    /**
     * 从雇主零工地图而来的数据
     *
     * @param beans
     */
    public void setHirerMap(HirerMapBean beans) {
        if (null != beans) {
            Glide.with(mContext).load(beans.getLogo())
                    .error(R.mipmap.ic_morentouxiang)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_morentouxiang)
                    .into(mIvHead);

            if (!TextUtils.isEmpty(beans.getDescription())) {
                mTvDescribe.setText(beans.getDescription());
            }
            if (!TextUtils.isEmpty(beans.getCreditCount())) {
                mTvCompany.setText(beans.getCreditCount());
            }

            mTvService.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(beans.getName())) {
                mTvName.setText(beans.getName());
            } else {
                mTvName.setText("");
            }

            switch (beans.getIdentity()) {
                case 1:
                    mTvIdentity.setText("自由工作者");
                    break;
                case 2:
                    mTvIdentity.setText("兼职人员");
                    break;
                case 0:
                    mTvIdentity.setText("在校学生");
                    break;
            }
            mTvDistance.setText(String.format("距离%skm", beans.xyCoordinateVo.getDistanceToJob()));
            mBarStarts.setRating(TextUtils.isEmpty(beans.getScoreCount()) ?
                    5 : Float.parseFloat(beans.getScoreCount()));
            mTvAnswer.setText(TextUtils.isEmpty(beans.getJoinCount() + "")
                    ? "" : "已接" + beans.getJoinCount() + "单");
        }
    }
}
