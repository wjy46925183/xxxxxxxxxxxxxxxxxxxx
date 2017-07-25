package com.dlg.personal.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.common.cache.ACache;
import com.common.string.LogUtils;
import com.common.utils.StringUtils;
import com.dlg.personal.R;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.personal.wallet.view.TimeCount;
import com.dlg.viewmodel.Wallet.ResetPwdViewModel;
import com.dlg.viewmodel.Wallet.presenter.ResetPwdPresenter;
import com.dlg.viewmodel.common.SendCodeViewModel;
import com.dlg.viewmodel.common.presenter.SuccessObjectPresenter;
import com.dlg.viewmodel.key.AppKey;

/**
 * 作者：邹前坤
 * 主要功能： 重置密码 接收验证码和确认界面
 * 创建时间： 2017/7/14  16:04
 */

public class WalletResetPwdActivity extends BaseActivity implements SuccessObjectPresenter, ResetPwdPresenter {
	private TextView mTvPhoneNum;//手机号  要特殊处理
	private EditText mEtCode;//验证码输入框
	private TextView mBtnCode;//重新获取验证码
	private Button mBtnNext;//下一步
	private String userId;//用户id
	private String userPhone;//用户手机
	private TimeCount timeCount;
	private SendCodeViewModel sendCodeViewModel;
	private ResetPwdViewModel resetPwdViewModel;
	private String identifyingCode;
	private static  final  int REQUEST_INPUT=632;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_wallet_reset_pwd);

		initView();
		initData();
		initNet();
		initlistener();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {

		mTvPhoneNum = (TextView) findViewById(R.id.tv_phoneNum);
		mEtCode = (EditText) findViewById(R.id.et_code);
		mBtnCode = (TextView) findViewById(R.id.btn_code);
		mBtnNext = (Button) findViewById(R.id.btn_next);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		getToolBarHelper().setToolbarTitle("重置密码");
		userId = ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID);
		userPhone =ACache.get(mContext).getAsString(AppKey.CacheKey.USER_PHONE);

		if (!TextUtils.isEmpty(userPhone) && userPhone.length() == 11){
			mTvPhoneNum.setText(userPhone.substring(0, 3) + "****" + userPhone.substring(7));
		}
		sendCodeViewModel = new SendCodeViewModel(this,this,this);
		resetPwdViewModel = new ResetPwdViewModel(this,this,this);
		timeCount = new TimeCount(60000,1000,mBtnCode);

	}

	/**
	 * 初始化联网 获取验证码
	 */
	private void initNet() {

		sendCodeViewModel.sendMsg(userId,userPhone);
	}

	/**
	 * 初始化监听
	 */
	private void initlistener() {

		mBtnCode.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				timeCount.start();
			}
		});

		mBtnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				submit();
			}
		});
	}

	/**
	 * 点击下一步
	 */
	private void submit() {
		identifyingCode = mEtCode.getText().toString().trim();

		if (!StringUtils.isVerifyCode(identifyingCode)) {
			ToastUtils.showShort(this, "请输入正确的验证码格式");
		} else {
		resetPwdViewModel.getVerifyVerificationCode(userPhone, identifyingCode);
		}
	}

	/**
	 * 获取验证码成功
	 * @param success
	 */
	@Override
	public void onSuccess(boolean success) {
		LogUtils.d("获取验证码成功 ");
		if (success){
			timeCount.start();
		}


		/**
		 * 验证码输入后联网调一下
		 *  time.cancel();
		 time.onReSend();
		 */
	}

	@Override
	public void geterifyCodeOk(boolean success) {
		if (success){
			timeCount.cancel();
			timeCount.onReSend();

			LogUtils.d("重置密码验证验证码成功");

			Bundle bunder = new Bundle();
			bunder.putString("code",identifyingCode);
			ActivityNavigator.navigator().navigateTo(WalletResetPwdInputActivity.class,bunder,REQUEST_INPUT);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==REQUEST_INPUT&&resultCode==RESULT_OK&&data!=null){
			//用管理类实现 批量的activity关闭 不需要反悔了
//			boolean isOK = data.getBooleanExtra("isOK", false);
//			if (isOK){
//				finish();
//			}
		}
	}
}
