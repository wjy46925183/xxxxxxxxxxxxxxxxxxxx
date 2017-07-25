package com.dlg.inc.wallet.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cache.ACache;
import com.common.string.StringUtil;
import com.common.sys.ActivityNavigator;
import com.dlg.data.wallet.model.BindBean;
import com.dlg.data.wallet.model.WalletBean;

import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncToolBarType;
import com.dlg.inc.home.view.IncToastUtils;
import com.dlg.inc.wallet.adapter.IncCashAdapter;
import com.dlg.viewmodel.Wallet.CashViewModel;
import com.dlg.viewmodel.Wallet.WithdrawalViewModel;
import com.dlg.viewmodel.Wallet.presenter.CashPresenter;
import com.dlg.viewmodel.Wallet.presenter.WithDrawalPresenter;
import com.dlg.viewmodel.common.RechargePriceViewModel;
import com.dlg.viewmodel.common.SendCodeViewModel;
import com.dlg.viewmodel.common.presenter.SuccessObjectPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.example.umengshare.UmengShareUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者：关蕤
 * 主要功能：提现页面
 * 创建时间：2017/7/11 16:12
 */
public class IncWithdrawalActivity extends IncBaseActivity implements WithDrawalPresenter, CashPresenter, SuccessObjectPresenter, IncCashAdapter.CheckBoxChangeListener {
    private ListView mLvWithdrawal;
    private EditText mEdMoney;
    private EditText mEdCode;
    private Button mBtSendMsg;
    private TextView mTvPrompt;
    private Button mBtRecharge;
    private WithdrawalViewModel viewModel;
    private CashViewModel model;   //提现
    private int CODE = 0x0001;
    private static final int REQUEST_BIND_CODE =526 ;
    /**
     * 变量相关
     */
    private WalletBean bean;
    private TextView mTvCanWithdraw;
    private String mCount; //提现金额
    private String mCode;
    private List<BindBean> bindBeanList = new ArrayList<>();
    private IncCashAdapter adapter;
    private UMShareAPI umShareAPI;
    private RechargePriceViewModel mPriceViewModel; //提现
    private SendCodeViewModel mSendCodeViewModel; //发送验证码
    private String type; //选择提现到账类型（支付宝，微信，银行卡）

    private int i = 60;    //定义的倒计时

    private String userName;
    private String bindId;

    private Handler handler = new Handler();
    /**
     * 读秒倒计时
     */
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                if (i < 1) {
                    mBtSendMsg.setText("再次发送");
                    mBtSendMsg.setEnabled(true);
                    i = 60;
                } else {
                    handler.postDelayed(this, 1000);
                    mBtSendMsg.setText(i-- + "s后重发");
                    mBtSendMsg.setEnabled(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inc_page_withdrawal, IncToolBarType.Default);
        mToolBarHelper.getToolbarTitle().setText("提现");
        mToolBarHelper.getToolbarTextRight().setText("编辑");
        mToolBarHelper.getToolbarTextRight().setVisibility(View.VISIBLE);
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 查询绑定的列表
         */
        model.getBindList();
    }

    /**
     * 页面初始化
     */
    private void initView() {
        mLvWithdrawal = (ListView) findViewById(R.id.lv_withdrawal);
        mEdMoney = (EditText) findViewById(R.id.ed_money);
        mEdCode = (EditText) findViewById(R.id.ed_code);
        mBtSendMsg = (Button) findViewById(R.id.bt_send_msg);
        mTvPrompt = (TextView) findViewById(R.id.tv_prompt);
        mBtRecharge = (Button) findViewById(R.id.bt_recharge);
        mTvCanWithdraw = (TextView) findViewById(R.id.tv_canWithdraw);

    }

    /**
     * 变量初始化
     */
    private void initData() {
        mBtRecharge.setEnabled(false);
        /**
         * 企业版的换成 cash_type_icon_company 对应的array
         * 并且配置内图标需要更换
         */
        for (int i = 0; i < mContext.getResources().getStringArray(R.array.inc_cash_type_icon_company).length; i++) {
            bindBeanList.add(new BindBean(i + 1, ""));
        }
        adapter = new IncCashAdapter(mContext, bindBeanList, this);
        mLvWithdrawal.setAdapter(adapter);
        setListViewHeight(mLvWithdrawal);

        viewModel = new WithdrawalViewModel(this, this, this);
        mPriceViewModel = new RechargePriceViewModel(this, this, this);
        mSendCodeViewModel = new SendCodeViewModel(this, this, this);
        model = new CashViewModel(this, this, this);
        umShareAPI = UMShareAPI.get(mContext);
        bean = (WalletBean) getIntent().getExtras().getSerializable("bean");
        if (bean!=null){
            mTvCanWithdraw.setText("可提现金额 ：" + bean.getWithdrawCashAmount() + "元");
        }

        /**
         * 查询可提现金额
         */
        viewModel.getFrequency();

        /**
         * 提现金额输入框
         */
        mEdMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                type = adapter.getType();
                mCount = s.toString().trim();
                if (TextUtils.isEmpty(type) || TextUtils.isEmpty(mCount)
                        || TextUtils.isEmpty(mCode)) {
                    mBtRecharge.setEnabled(false);
                } else {
                    mBtRecharge.setEnabled(true);
                }
            }
        });

        /**
         * listView item点击监听，但是只在未绑定情况下
         */
        mLvWithdrawal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView img = (ImageView) view.findViewById(R.id.img_to_bind);
                if (img.getVisibility() == View.VISIBLE) {
                    switch (position) {
                        case 0:  //支付宝绑定，跳转到支付宝绑定页面
                            ActivityNavigator.navigator().navigateTo(IncBindAliActivity.class);
                            break;

                        case 1:    //微信绑定，通过微信授权
                            UmengShareUtil.Builder(IncWithdrawalActivity.this).getPlatformInfo(SHARE_MEDIA.WEIXIN, authListener);
//                            umShareAPI.getPlatformInfo(WithdrawalActivity.this, SHARE_MEDIA.WEIXIN, authListener);
                            break;

                        case 2:    //银行卡绑定
                            ActivityNavigator.navigator().navigateTo(IncBindBankCardActivity.class);
                            break;

                        case 3 :    //企业账户绑定
                            ActivityNavigator.navigator().navigateTo(IncCompanyActivity.class);
                            break;
                    }
                }
            }
        });

        /**
         * 可提现帮助点击事件
         */
        mTvCanWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("提示")
                        .setMessage("自2017年1月1日起，充值金橙\n不能提现，只能用于支付劳务报酬\n或用于平台服务消费")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
        });

        /**
         * 验证码获取监听
         */
        mBtSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 此处的手机号为登录的手机号
                 */
                mSendCodeViewModel.sendMsg(ACache.get(mContext).getAsString(AppKey.CacheKey.USER_PHONE));
            }
        });

        /**
         * 验证码输入监听
         */
        mEdCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                type = adapter.getType();
                mCode = s.toString().trim();
                if (TextUtils.isEmpty(type) || TextUtils.isEmpty(mCount)
                        || TextUtils.isEmpty(mCode)) {
                    mBtRecharge.setEnabled(false);
                } else {
                    mBtRecharge.setEnabled(true);
                }
            }
        });

        /**
         * 提现操作
         */
        mBtRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNavigator.navigator().navigateTo(IncWritePwdActivity.class, CODE);
            }
        });

        /**
         * 编辑,跳转到解绑页面
         */
        mToolBarHelper.getToolbarTextRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivityNavigator.navigator().navigateTo(IncUnBindActivity.class, REQUEST_BIND_CODE);
            }
        });

    }

    /**
     * 查询到的已提现次数
     *
     * @param string
     */
    @Override
    public void toMap(String string) {
        mTvPrompt.setText("本月剩余免费提现次数：" + (2 - Integer.parseInt(string)));
    }

    /**
     * 绑定微信成功
     */
    @Override
    public void toBind(String s) {

    }

    @Override
    public void toSet() {

    }

    /**
     * 已绑定的列表
     *
     * @param bean
     */
    @Override
    public void toMap(BindBean bean) {
        int type = bean.getPayType();
        if(type == 3 && !TextUtils.isEmpty(bean.getPayAccountType()) &&TextUtils.equals("2",bean.getPayAccountType())){
            bindBeanList.remove(3);      //先将原列表中的移除，再讲现有获取到的添加进来
            bindBeanList.add(3, bean);
        }else {
            bindBeanList.remove(type - 1);      //先将原列表中的移除，再讲现有获取到的添加进来
            bindBeanList.add(type - 1, bean);
        }
        adapter.notifyDataSetChanged();
        setListViewHeight(mLvWithdrawal);
    }

    private UMAuthListener authListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            //上传微信信息
            BandingWeichat(map);
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            if (throwable.getMessage().contains("没有安装应用")) {
                IncToastUtils.showLong(mContext, "你尚未安装微信，请安装后再进行操作");
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            IncToastUtils.showLong(mContext, "您取消了微信授权");
        }

        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }
    };

    /**
     * 绑定微信
     *
     * @param map
     */
    private void BandingWeichat(Map<String, String> map) {
        String name = map.get("screen_name");
        name = StringUtil.filterEmoji(name, "");
        viewModel.getBindWeChat(name, map.get("openid"), "2");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        umShareAPI.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            mPriceViewModel.recharge(type, mCode, mCount, userName, bindId, data.getStringExtra("pwd"));
        }
        if (requestCode==REQUEST_BIND_CODE&&resultCode==RESULT_OK){
            bindBeanList.clear();
            initData();
        }
    }

    /**
     * 验证码发送成功
     *
     * @param success
     */
    @Override
    public void onSuccess(boolean success) {
        if (success) {
            IncToastUtils.showLong(mContext, "验证码发送成功，请注意查收");
            handler.postDelayed(runnable, 1000);
        } else {
            IncToastUtils.showLong(mContext, "提现成功");
        }
    }

    /**
     * 选项列表选择切换后
     */
    @Override
    public void change(String userName, String bindId) {
        this.userName = userName;
        this.bindId = bindId;
        type = adapter.getType();
        if (TextUtils.isEmpty(type) || TextUtils.isEmpty(mCount)
                || TextUtils.isEmpty(mCode)) {
            mBtRecharge.setEnabled(false);
        } else {
            mBtRecharge.setEnabled(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mSendCodeViewModel) {
            mSendCodeViewModel.onDestroy();
        }
    }



    public void setListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        ((ViewGroup.MarginLayoutParams) params).setMargins(0, 10, 0, 10);
        listView.setLayoutParams(params);

    }

}
