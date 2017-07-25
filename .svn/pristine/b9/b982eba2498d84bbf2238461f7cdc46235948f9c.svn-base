package com.dlg.personal.home.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.string.LogUtils;
import com.dlg.data.UrlNet;
import com.dlg.data.common.url.CommonUrl;
import com.dlg.personal.R;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.base.BaseFragment;
import com.dlg.personal.base.LogInRepository;
import com.dlg.personal.home.activity.HomeActivity;
import com.dlg.personal.oddjob.activity.OddActivity;
import com.dlg.personal.user.activity.SettingActivity;
import com.dlg.personal.user.activity.UserInfoActivity;
import com.dlg.personal.wallet.activity.PublicInputPwdActivity;
import com.dlg.personal.wallet.activity.WalletActivity;
import com.dlg.personal.web.DefaultWebActivity;
import com.dlg.viewmodel.Wallet.PayViewModel;
import com.dlg.viewmodel.Wallet.presenter.PayViewPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.key.H5WebType;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * name:wangjinya 2017.6.19
 */
public class UserMenuFragment extends BaseFragment implements PayViewPresenter, View.OnClickListener {


    private PayViewModel model;
    private RelativeLayout mSlideLayoutHead; //切换按钮总布局
    private RadioGroup mMapRg; //切换按钮布局
    private RadioButton mRb01; //雇员
    private RadioButton mRb02; //雇主
    private CircleImageView mIvHead; //头像
    private TextView mSlidName; //名称
    private TextView mSlidStatus; //实名认证后显示控件
    private TextView mSlideLinggong; //零工
    private TextView mSlideMoney; //钱包
    private TextView mSlideSetting; //设置
    private LinearLayout mLayoutReally; //真我
    private CircleImageView mImReally; //真我图片
    private LinearLayout mLayoutWelfare; //福利社
    private CircleImageView mImWelfare; //福利社图片
    private LinearLayout mLayoutRecommended; //推荐有奖
    private CircleImageView mImRecommended; //推荐有奖图片
    private String statues;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_menu, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        model = new PayViewModel(mContext, this);
        mSlideLayoutHead = (RelativeLayout) view.findViewById(R.id.slide_layout_head);
        mMapRg = (RadioGroup) view.findViewById(R.id.map_rg);
        mRb01 = (RadioButton) view.findViewById(R.id.rb_01);
        mRb02 = (RadioButton) view.findViewById(R.id.rb_02);
        mIvHead = (CircleImageView) view.findViewById(R.id.iv_head);
        mSlidName = (TextView) view.findViewById(R.id.slid_name);
        mSlidStatus = (TextView) view.findViewById(R.id.slid_status);
        mSlideLinggong = (TextView) view.findViewById(R.id.slide_linggong);
        mSlideMoney = (TextView) view.findViewById(R.id.slide_money);
        mSlideSetting = (TextView) view.findViewById(R.id.slide_setting);
        mLayoutReally = (LinearLayout) view.findViewById(R.id.layout_really);
        mImReally = (CircleImageView) view.findViewById(R.id.im_really);
        mLayoutWelfare = (LinearLayout) view.findViewById(R.id.layout_welfare);
        mImWelfare = (CircleImageView) view.findViewById(R.id.im_welfare);
        mLayoutRecommended = (LinearLayout) view.findViewById(R.id.layout_recommended);
        mImRecommended = (CircleImageView) view.findViewById(R.id.im_recommended);
        statues = mACache.getAsString(AppKey.CacheKey.VERIFY_STATE);
        mSlidStatus.setVisibility(View.INVISIBLE);
        if (statues !=null){
            if (statues.equals("2")) {
                mSlidName.setVisibility(View.VISIBLE);
                mSlidStatus.setText("已认证");
                mSlidStatus.setVisibility(View.VISIBLE);
                mSlidName.setText(mACache.getAsString(AppKey.CacheKey.NAME));
            } else if (statues.equals("1")) {
                mSlidStatus.setText("认证中");
                mSlidStatus.setVisibility(View.VISIBLE);
            } else if (statues.equals("3")) {
                mSlidStatus.setText("认证失败");
            } else if (statues.equals("0")) {
                mSlidStatus.setText("登录");
            }
        }


        mMapRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (mActivity instanceof HomeActivity) {
                    ((HomeActivity) mActivity).checkRole(mRb01.isChecked());//将状态传递给activity
                }
            }
        });
        Glide.with(this).load(UrlNet.getName() + "/static/images/zw.jpg").fitCenter().override(200, 200).error(R.mipmap.ic_launcher)
                .into(mImReally);
        Glide.with(this).load(UrlNet.getName() + "/static/images/jfsc.jpg").fitCenter().override(200, 200).error(R.mipmap.ic_launcher)
                .into(mImWelfare);
        Glide.with(this).load(UrlNet.getName() + "/static/images/tjyj.jpg").fitCenter().override(200, 200).error(R.mipmap.ic_launcher)
                .into(mImRecommended);

        mIvHead.setOnClickListener(this);
        mSlidName.setOnClickListener(this);
        mSlideLinggong.setOnClickListener(this);
        mSlideMoney.setOnClickListener(this);
        mSlideSetting.setOnClickListener(this);
        mLayoutReally.setOnClickListener(this);
        mLayoutWelfare.setOnClickListener(this);
        mLayoutRecommended.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(this).load(mACache.getAsString(AppKey.CacheKey.USER_LOGO)).fitCenter().override(700, 700).error(R.mipmap.mrtx)
                .into(mIvHead);
        if (statues !=null){
            if (statues.equals("2")) {
                mSlidName.setVisibility(View.VISIBLE);
                mSlidStatus.setText("已认证");
                mSlidName.setText(mACache.getAsString(AppKey.CacheKey.NAME));
            } else if (statues.equals("1")) {
                mSlidStatus.setText("认证中");
            } else if (statues.equals("3")) {
                mSlidStatus.setText("认证失败");
            } else if (statues.equals("0")) {
                mSlidStatus.setText("登录");
            }
        }

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_head || viewId == R.id.slid_name || viewId == R.id.slid_status) {
            startLogInForResult(new LogInRepository() {
                @Override
                public void logInSuccess() {
                    ActivityNavigator.navigator().navigateTo(UserInfoActivity.class,new Intent().putExtra("role",(int)mACache.getAsObject(AppKey.CacheKey.USER_ROLE)));
                }
            });
        } else if (viewId == R.id.slide_linggong) { //零工列表
            startLogInForResult(new LogInRepository() {
                @Override
                public void logInSuccess() {
                    if (mActivity instanceof HomeActivity) {
                        ActivityNavigator.navigator().navigateTo(OddActivity.class);
                    }
                }
            });
        } else if (viewId == R.id.slide_money) { //钱包
            startLogInForResult(new LogInRepository() {
                @Override
                public void logInSuccess() {
                    if (null != model) {
                        model.judgePwd();
                    }
                }
            });
        } else if (viewId == R.id.slide_setting) { //设置
            startLogInForResult(new LogInRepository() {
                @Override
                public void logInSuccess() {
                    ActivityNavigator.navigator().navigateTo(SettingActivity.class);
                }
            });
        } else if (viewId == R.id.layout_really) { //真我
            startLogInForResult(new LogInRepository() {
                @Override
                public void logInSuccess() {
                    Bundle bundleH5 = new Bundle();
                    bundleH5.putString(DefaultWebActivity.TITLE_NAME, H5WebType.really.getName());
                    bundleH5.putString(DefaultWebActivity.H5_URL, CommonUrl.H5_WEB_URL);
                    bundleH5.putString(DefaultWebActivity.H5_TYPE, H5WebType.really.getType() + "");
                    ActivityNavigator.navigator().navigateTo(DefaultWebActivity.class, bundleH5);
                }
            });
        } else if (viewId == R.id.layout_welfare) { //福利社
            startLogInForResult(new LogInRepository() {
                @Override
                public void logInSuccess() {
                    Bundle bundleH5 = new Bundle();
                    bundleH5.putString(DefaultWebActivity.TITLE_NAME, H5WebType.welfareAssociation.getName());
                    bundleH5.putString(DefaultWebActivity.H5_URL, CommonUrl.H5_WEB_URL);
                    bundleH5.putString(DefaultWebActivity.H5_TYPE, H5WebType.welfareAssociation.getType() + "");
                    ActivityNavigator.navigator().navigateTo(DefaultWebActivity.class, bundleH5);
                }
            });
        } else if (viewId == R.id.layout_recommended) { //推荐有奖
            startLogInForResult(new LogInRepository() {
                @Override
                public void logInSuccess() {
                    Bundle bundleH5 = new Bundle();
                    bundleH5.putString(DefaultWebActivity.TITLE_NAME, H5WebType.recommended.getName());
                    bundleH5.putString(DefaultWebActivity.H5_URL, CommonUrl.H5_WEB_URL);
                    bundleH5.putString(DefaultWebActivity.H5_TYPE, H5WebType.recommended.getType() + "");
                    ActivityNavigator.navigator().navigateTo(DefaultWebActivity.class, bundleH5);
                }
            });
        }
    }


    /**
     * 做判断是否设置过支付密码
     *
     * @param isSet
     */
    @Override
    public void getHasPwd(boolean isSet) {
        LogUtils.d("判断是否设置过支付密码" + isSet);
        if (isSet) {
            ActivityNavigator.navigator().navigateTo(WalletActivity.class);
        } else {
            ActivityNavigator.navigator().navigateTo(PublicInputPwdActivity.class);
        }
    }
}
