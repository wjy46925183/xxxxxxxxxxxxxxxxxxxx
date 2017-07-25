package com.dlg.inc.home.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.string.LogUtils;
import com.common.sys.ActivityNavigator;
import com.dlg.data.UrlNet;
import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseFragment;
import com.dlg.inc.base.IncLogInRepository;
import com.dlg.inc.home.activity.IncHomeActivity;
import com.dlg.inc.user.activity.IncSettingActivity;
import com.dlg.inc.wallet.activity.IncPublicInputPwdActivity;
import com.dlg.inc.wallet.activity.IncWalletActivity;
import com.dlg.viewmodel.Wallet.PayViewModel;
import com.dlg.viewmodel.Wallet.presenter.PayViewPresenter;
import com.dlg.viewmodel.key.AppKey;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * name:wangjinya 2017.6.19
 */
public class IncUserMenuFragment extends IncBaseFragment implements PayViewPresenter, View.OnClickListener {


    private PayViewModel model;
    private RelativeLayout mSlideLayoutHead; //切换按钮总布局
    private CircleImageView mIvHead; //头像
    private TextView mSlidName; //名称
    private TextView mSlidStatus; //实名认证后显示控件
    private TextView mSlideLinggong; //零工
    private TextView mSlideMoney; //钱包
    private TextView mSlideSetting; //设置
    private TextView mSlideKefu; //客服
    private LinearLayout mLayoutReally; //真我
    private CircleImageView mImReally; //真我图片
    private LinearLayout mLayoutWelfare; //福利社
    private CircleImageView mImWelfare; //福利社图片
    private LinearLayout mLayoutRecommended; //推荐有奖
    private CircleImageView mImRecommended; //推荐有奖图片

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inc_fragment_user_menu, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        model = new PayViewModel(mContext, this);
        mSlideLayoutHead = (RelativeLayout) view.findViewById(R.id.slide_layout_head);
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
        mSlideKefu = (TextView) view.findViewById(R.id.slide_kefu);
        Glide.with(this).load(UrlNet.getName() + "/static/images/zw.jpg").fitCenter().override(200, 200).error(R.mipmap.ic_launcher)
                .into(mImReally);
        Glide.with(this).load(UrlNet.getName() + "/static/images/jfsc.jpg").fitCenter().override(200, 200).error(R.mipmap.ic_launcher)
                .into(mImWelfare);
        Glide.with(this).load(UrlNet.getName() + "/static/images/tjyj.jpg").fitCenter().override(200, 200).error(R.mipmap.ic_launcher)
                .into(mImRecommended);

        mIvHead.setOnClickListener(this);
        mSlidName.setOnClickListener(this);
        mSlidStatus.setOnClickListener(this);
        mSlideLinggong.setOnClickListener(this);
        mSlideMoney.setOnClickListener(this);
        mSlideSetting.setOnClickListener(this);
        mLayoutReally.setOnClickListener(this);
        mLayoutWelfare.setOnClickListener(this);
        mLayoutRecommended.setOnClickListener(this);
        mSlideKefu.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Glide.with(this).load(mACache.getAsString(AppKey.CacheKey.USER_LOGO)).fitCenter().override(700,700).error(R.mipmap.mrtx)
                .into(mIvHead);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.iv_head || viewId == R.id.slid_name || viewId == R.id.slid_status) {
            startLogInForResult(new IncLogInRepository() {
                @Override
                public void logInSuccess() {
                 //   IncActivityNavigator.navigator().navigateTo(UserInfoActivity.class);
                }
            });
        } else if (viewId == R.id.slide_linggong) { //零工列表
            startLogInForResult(new IncLogInRepository() {
                @Override
                public void logInSuccess() {
                    if (mActivity instanceof IncHomeActivity) {
                      //  IncActivityNavigator.navigator().navigateTo(OddActivity.class);
                    }
                }
            });
        } else if (viewId == R.id.slide_money) { //钱包
            startLogInForResult(new IncLogInRepository() {
                @Override
                public void logInSuccess() {
                    if (null != model) {
                        model.judgePwd();
                    }
                }
            });
        } else if (viewId == R.id.slide_setting) { //设置
            startLogInForResult(new IncLogInRepository() {
                @Override
                public void logInSuccess() {
                    ActivityNavigator.navigator().navigateTo(IncSettingActivity.class);
                }
            });
        } else if (viewId == R.id.slide_kefu) { //客服
            startLogInForResult(new IncLogInRepository() {
                @Override
                public void logInSuccess() {
                    //     IncActivityNavigator.navigator().navigateTo(SettingActivity.class);
                }
            });
        } else if (viewId == R.id.layout_really) { //真我
            startLogInForResult(new IncLogInRepository() {
                @Override
                public void logInSuccess() {
//                    Bundle bundleH5 = new Bundle();
//                    bundleH5.putString(IncDefaultWebActivity.TITLE_NAME, H5WebType.really.getName());
//                    bundleH5.putString(IncDefaultWebActivity.H5_URL, CommonUrl.H5_WEB_URL);
//                    bundleH5.putString(IncDefaultWebActivity.H5_TYPE, H5WebType.really.getType() + "");
//                    ActivityNavigator.navigator().navigateTo(IncDefaultWebActivity.class, bundleH5);
                }
            });
        } else if (viewId == R.id.layout_welfare) { //福利社
//            startLogInForResult(new LogInRepository() {
//                @Override
//                public void logInSuccess() {
//                    Bundle bundleH5 = new Bundle();
//                    bundleH5.putString(IncDefaultWebActivity.TITLE_NAME, H5WebType.welfareAssociation.getName());
//                    bundleH5.putString(IncDefaultWebActivity.H5_URL, CommonUrl.H5_WEB_URL);
//                    bundleH5.putString(IncDefaultWebActivity.H5_TYPE, H5WebType.welfareAssociation.getType() + "");
//                    ActivityNavigator.navigator().navigateTo(IncDefaultWebActivity.class, bundleH5);
//                }
//            });
        } else if (viewId == R.id.layout_recommended) { //推荐有奖
//            startLogInForResult(new LogInRepository() {
//                @Override
//                public void logInSuccess() {
//                    Bundle bundleH5 = new Bundle();
//                    bundleH5.putString(IncDefaultWebActivity.TITLE_NAME, H5WebType.recommended.getName());
//                    bundleH5.putString(IncDefaultWebActivity.H5_URL, CommonUrl.H5_WEB_URL);
//                    bundleH5.putString(IncDefaultWebActivity.H5_TYPE, H5WebType.recommended.getType() + "");
//                    ActivityNavigator.navigator().navigateTo(IncDefaultWebActivity.class, bundleH5);
//                }
//            });
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
            ActivityNavigator.navigator().navigateTo(IncWalletActivity.class);
        } else {
            ActivityNavigator.navigator().navigateTo(IncPublicInputPwdActivity.class);
        }
    }
}
