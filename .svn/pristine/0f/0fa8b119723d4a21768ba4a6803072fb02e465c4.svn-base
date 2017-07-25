package com.dlg.personal.user.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.utils.StringUtils;
import com.dlg.data.user.model.UserAttributeInfoBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.viewmodel.common.SendCodeViewModel;
import com.dlg.viewmodel.common.presenter.SuccessObjectPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.user.LogInViewModel;
import com.dlg.viewmodel.user.presenter.LogInPresenter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：小明
 * 主要功能：
 * 创建时间：2017/7/13 0013 20:45
 */
public class ThirdUnitLogin extends BaseActivity implements View.OnClickListener ,SuccessObjectPresenter,LogInPresenter{
    private CircleImageView ivHead;
    private TextView nameText;
    private EditText phoneText;
    private EditText codeText;
    private LinearLayout getcodeLayout;
    private LinearLayout waitLayout;
    private TextView time;
    private TextView getNew;
    private TextView getcode;
    private Button btnLogin;
    private SendCodeViewModel codeViewModel;
    private LogInViewModel logInViewModel;
    private String phone;
    private String code;
    private String opoenId;
    private String loginType;
    private String isBinding;
    private String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_third_unit_login);
        initView();
    }

    private void initView() {
        ivHead = (CircleImageView) findViewById(R.id.iv_head);
        nameText = (TextView) findViewById(R.id.name_text);
        phoneText = (EditText) findViewById(R.id.phone_text);
        codeText = (EditText) findViewById(R.id.code_text);
        getcodeLayout = (LinearLayout) findViewById(R.id.getcode_layout);
        waitLayout = (LinearLayout) findViewById(R.id.wait_layout);
        time = (TextView) findViewById(R.id.time);
        getNew = (TextView) findViewById(R.id.getNew);
        getcode = (TextView) findViewById(R.id.getcode);
        btnLogin = (Button) findViewById(R.id.btn_login);

        getcodeLayout.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        opoenId = getIntent().getExtras().getString("openId");
        loginType = getIntent().getExtras().getString("loginType");
        isBinding = getIntent().getExtras().getString("isBinding");
        name = getIntent().getExtras().getString("name");
        if (loginType.equals("3")){
            mToolBarHelper.setToolbarTitle("微信联合登录");
        }
        if (loginType.equals("4")){
            mToolBarHelper.setToolbarTitle("QQ联合登录");
        }
        if (loginType.equals("5")){
            mToolBarHelper.setToolbarTitle("新浪联合登录");
        }
        getNew.setText("获取验证码");
        nameText.setText(name);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.getcode_layout){//验证码
            getCode();
        }
        if (v.getId()==R.id.btn_login){//登录
            go2Login();

        }
    }

    private void go2Login() {
        phone = phoneText.getText().toString().trim();
        code = codeText.getText().toString().trim();
        if (TextUtils.isEmpty(phone)){
            ToastUtils.showShort(mContext,"电话不能为空");
            return;
        }
        if (TextUtils.isEmpty(code)){
            ToastUtils.showShort(mContext,"验证码不能为空");
            return;
        }
        if (!StringUtils.isPhoneNumber(phone)){
            ToastUtils.showShort(mContext,"电话格式不正确");
            return;
        }
        if (!StringUtils.isVerifyCode(code)){
            ToastUtils.showShort(mContext,"验证码格式不正确");
            return;
        }
        if (logInViewModel==null){
            logInViewModel=new LogInViewModel(mContext,this,this);
        }
        logInViewModel.thirdUnBindLogIn(phone,code,opoenId,loginType,isBinding);

    }

    //获取验证码
    private void getCode() {
        phone = phoneText.getText().toString().trim();
        if (!StringUtils.isPhoneNumber(phone)){
            ToastUtils.showShort(mContext,"电话格式不正确");
            return;
        }
        if (null == codeViewModel) {
            codeViewModel = new SendCodeViewModel(mContext, this, this);
        }
        codeViewModel.sendMsg(phone);
    }
    //验证码回调
    @Override
    public void onSuccess(boolean success) {
        ToastUtils.showShort(mContext, "验证码发送成功");
        getcodeLayout.setEnabled(false);
        MyCount myCount = new MyCount(60000, 1000);
        myCount.start();
    }
    //登录成功回调
    @Override
    public void logInUserInfo(UserAttributeInfoBean infoBean) {
        mACache.put(AppKey.CacheKey.MY_USER_ID, infoBean.getUserId());
        mACache.put(AppKey.CacheKey.NAME, infoBean.getName());
        mACache.put(AppKey.CacheKey.USER_LOGO, infoBean.getLogo());
    }

    //计时器
    private class MyCount extends CountDownTimer {
        private MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            waitLayout.setVisibility(View.GONE);
            getcodeLayout.setEnabled(true);
            getNew.setVisibility(View.VISIBLE);
            getNew.setText("重新获取");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            waitLayout.setVisibility(View.VISIBLE);
            time.setText(""+ millisUntilFinished / 1000 + "秒后");
            getNew.setVisibility(View.GONE);

        }
    }
}
