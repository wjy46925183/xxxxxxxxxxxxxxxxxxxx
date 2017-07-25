package com.dlg.personal.wallet.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.common.cache.ACache;
import com.common.string.StringUtil;
import com.dlg.data.user.model.UserAttributeInfoBean;
import com.dlg.personal.R;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.base.ToolBarType;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.viewmodel.Wallet.WithdrawalViewModel;
import com.dlg.viewmodel.Wallet.presenter.WithDrawalPresenter;
import com.dlg.viewmodel.common.OrderOperaButViewModel;
import com.dlg.viewmodel.common.SendCodeViewModel;
import com.dlg.viewmodel.common.presenter.SuccessObjectPresenter;
import com.dlg.viewmodel.key.AppKey;

/**
 * 作者：关蕤
 * 主要功能：绑定银行卡
 * 创建时间：2017/7/13 10:47
 */
public class BindBankCardActivity extends BaseActivity implements WithDrawalPresenter, SuccessObjectPresenter {
    private TextView mTvRealName;
    private EditText mEdAccount;
    private EditText mEdPhoneNum;
    private EditText mEdCode;
    private Button mBtSendMsg;
    private Button mBtBindBank;

    private String mCardNum ;   //卡号
    private String mPhoneNum ;  //手机号
    private String mVaildCode ; //验证码

    private int i = 60 ;    //定义的倒计时

    private WithdrawalViewModel viewModel ;
    private OrderOperaButViewModel objViewModel;
    private UserAttributeInfoBean bean ;
    private SendCodeViewModel mSendCodeViewModel; //发送验证码

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_bind_back, ToolBarType.Default);
        mToolBarHelper.getToolbarTitle().setText("绑定银行卡");
        initView();
        initData();
    }

    /**
     * 控件初始化
     */
    private void initView() {
        viewModel = new WithdrawalViewModel(this,this,this);
        objViewModel = new OrderOperaButViewModel(this,this,this);
        mSendCodeViewModel = new SendCodeViewModel(this,this,this);
        mTvRealName = (TextView) findViewById(R.id.tv_realName);
        mEdAccount = (EditText) findViewById(R.id.ed_account);
        mEdPhoneNum = (EditText) findViewById(R.id.ed_phoneNum);
        mEdCode = (EditText) findViewById(R.id.ed_code);
        mBtSendMsg = (Button) findViewById(R.id.bt_send_msg);
        mBtBindBank = (Button) findViewById(R.id.bt_bindBank);
        mBtBindBank.setEnabled(false);
    }

    /**
     * 变量初始化
     */
    private void initData() {
        bean = (UserAttributeInfoBean) ACache.get(mContext).getAsObject(AppKey.CacheKey.MY_USER_INFO);
        mTvRealName.setText(bean.getName());

        /**
         *   卡号输入框输入监听
         */
        mEdAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mCardNum = s.toString().trim() ;
                if(TextUtils.isEmpty(s)){
                    mBtBindBank.setEnabled(false);
                }else if(TextUtils.isEmpty(mPhoneNum) || TextUtils.isEmpty(mVaildCode)){
                    mBtBindBank.setEnabled(false);
                }else{
                    mBtBindBank.setEnabled(true);
                }
            }
        });

        /**
         * 手机号输入框输入监听
         */
        mEdPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPhoneNum = s.toString().trim() ;
                if(TextUtils.isEmpty(s)){
                    mBtBindBank.setEnabled(false);
                }else if(TextUtils.isEmpty(mCardNum) || TextUtils.isEmpty(mVaildCode)){
                    mBtBindBank.setEnabled(false);
                }else{
                    mBtBindBank.setEnabled(true);
                }
            }
        });

        /**
         * 验证码输入框输入监听
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
                mVaildCode = s.toString().trim();
                if(TextUtils.isEmpty(s)){
                    mBtBindBank.setEnabled(false);
                }else if(TextUtils.isEmpty(mPhoneNum) || TextUtils.isEmpty(mCardNum)){
                    mBtBindBank.setEnabled(false);
                }else{
                    mBtBindBank.setEnabled(true);
                }
            }
        });

        /**
         * 发送验证码点击事件
         */
        mBtSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mPhoneNum)){
                    ToastUtils.showLong(mContext,"请输入开户行预留手机号");
                }else if(StringUtil.isMobileNO(mPhoneNum)){
                    mSendCodeViewModel.sendMsg(mPhoneNum);
                }else{
                    ToastUtils.showLong(mContext,"请输入正确的手机号");
                }
            }
        });

        /**
         * 绑定点击监听事件
         */
        mBtBindBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.bindBank(bean.getName(),mPhoneNum,mCardNum,mVaildCode);
            }
        });
    }

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

    /**
     * 获取到的剩余次数，此页面用不到
     * @param string
     */
    @Override
    public void toMap(String string) {

    }

    /**
     * 绑定银行卡成功
     * @param s
     */
    @Override
    public void toBind(String s) {
        ToastUtils.showLong(mContext,"银行卡绑定成功");
        Bundle bundle =  getIntent().getExtras();
        if(null != bundle  && null !=bundle.getString("first")) { //第一次使用，选择绑定进入
            ActivityNavigator.navigator().finishLastTwo();
            ActivityNavigator.navigator().navigateTo(WalletActivity.class);
        }else {
            finish();
        }
    }

    @Override
    public void toSet() {

    }

    /**
     * @param success
     */
    @Override
    public void onSuccess(boolean success) {
        ToastUtils.showLong(mContext,"验证码发送成功，请注意查收");
        handler.postDelayed(runnable, 1000);
    }
}
