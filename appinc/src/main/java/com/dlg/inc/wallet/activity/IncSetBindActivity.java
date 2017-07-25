package com.dlg.inc.wallet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.common.string.StringUtil;
import com.common.sys.ActivityNavigator;
import com.dlg.data.wallet.model.BindBean;

import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncToolBarType;
import com.dlg.inc.home.view.IncToastUtils;
import com.dlg.inc.wallet.adapter.IncCashAdapter;
import com.dlg.viewmodel.Wallet.WithdrawalViewModel;
import com.dlg.viewmodel.Wallet.presenter.WithDrawalPresenter;
import com.example.umengshare.UmengShareUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者：关蕤
 * 主要功能：    第一次进入时绑定页面
 * 创建时间：2017/7/14 14:19
 */
public class IncSetBindActivity extends IncBaseActivity implements WithDrawalPresenter {
    private ListView mLvPayType;
    private UMShareAPI umShareAPI;
    private WithdrawalViewModel viewModel;
    private IncCashAdapter adapter;
    private List<BindBean> bindBeanList = new ArrayList<>();

//    private UMAuthListener authListener = new UMAuthListener() {
//        @Override
//        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
//            //上传微信信息
//            BandingWeichat(map);
//        }
//
//        private void BandingWeichat(Map<String, String> map) {
//            String name = map.get("screen_name");
//            name  = StringUtil.filterEmoji(name,"");
//            viewModel.getBindWeChat(name,map.get("openid"),"2");
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
//            if (throwable.getMessage().contains("没有安装应用")) {
//                ToastUtils.showLong(mContext, "你尚未安装微信，请安装后再进行操作");
//            }
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA share_media, int i) {
//            ToastUtils.showLong(mContext, "您取消了微信授权");
//        }
//    };

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(dialog);
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            //上传微信信息
            BandingWeichat(data);
        }

        private void BandingWeichat(Map<String, String> map) {
            String name = map.get("screen_name");
            name = StringUtil.filterEmoji(name, "");
            viewModel.getBindWeChat(name, map.get("openid"), "2");
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
//            SocializeUtils.safeCloseDialog(dialog);
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inc_page_set_bind, IncToolBarType.Default);
        mToolBarHelper.setToolbarTitle("选择绑定");
        mToolBarHelper.setToolbarTextRight("跳过");
        initView();
        initData();
    }

    /**
     * 控件初始化
     */
    private void initView() {
        mLvPayType = (ListView) findViewById(R.id.lv_pay_type);
        mToolBarHelper.getToolbarTextRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNavigator.navigator().navigateTo(IncWalletActivity.class);
                finish();
            }
        });
    }

    /**
     * 变量初始化
     */
    private void initData() {
        viewModel = new WithdrawalViewModel(this, this, this);
        umShareAPI = UMShareAPI.get(mContext);
        for (int i = 0; i < mContext.getResources().getStringArray(R.array.inc_cash_type_icon).length; i++) {
            bindBeanList.add(new BindBean(i + 1, ""));
        }
        adapter = new IncCashAdapter(mContext, bindBeanList);
        mLvPayType.setAdapter(adapter);
        mLvPayType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = (ImageView) view.findViewById(R.id.img_to_bind);
                Bundle bundle = new Bundle();
                bundle.putString("first", "first");
                if (img.getVisibility() == View.VISIBLE) {
                    switch (position) {
                        case 0:  //支付宝绑定，跳转到支付宝绑定页面
                            ActivityNavigator.navigator().navigateTo(IncBindAliActivity.class, bundle);
                            break;

                        case 1:    //微信绑定，通过微信授权
                            UmengShareUtil.Builder(IncSetBindActivity.this).getPlatformInfo(SHARE_MEDIA.WEIXIN, authListener);
//                            umShareAPI.getPlatformInfo(SetBindActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                            break;

                        case 2:    //银行卡绑定
                            ActivityNavigator.navigator().navigateTo(IncBindBankCardActivity.class, bundle);
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void toMap(String string) {

    }

    /**
     * 微信绑定成功
     *
     * @param s
     */
    @Override
    public void toBind(String s) {
        IncToastUtils.showLong(mContext, "微信绑定成功");
        ActivityNavigator.navigator().navigateTo(IncWalletActivity.class);
        finish();
    }

    @Override
    public void toSet() {

    }
}
