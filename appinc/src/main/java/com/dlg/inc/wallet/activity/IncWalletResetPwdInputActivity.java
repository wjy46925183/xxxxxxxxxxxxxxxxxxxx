package com.dlg.inc.wallet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.common.string.LogUtils;
import com.common.sys.ActivityNavigator;
import com.common.view.gridpasswordview.GridPasswordView;

import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.home.view.IncToastUtils;
import com.dlg.viewmodel.Wallet.ResetPwdInputViewModel;
import com.dlg.viewmodel.Wallet.presenter.ResetPwdInputPresenter;

import java.util.Stack;

/**
 * 作者：邹前坤
 * 主要功能： 钱包重置密码 输入密码界面
 * 创建时间： 2017/7/17  10:31
 */

public class IncWalletResetPwdInputActivity extends IncBaseActivity implements ResetPwdInputPresenter {
	private TextView mTvMarkedWords; //提示语
	private GridPasswordView mPasswordInputView; //显示密码控件
	private String identifyingCode;// 验证码
	private String pwd;//输入的密码 用来判定是第几次输入，第一次 它是空，第二次他有值
	private ResetPwdInputViewModel viewModel;
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inc_page_public_input_pwd);
		initView();
		initData();
		initLIstener();
	}

	private void initView() {
		mTvMarkedWords = (TextView) findViewById(R.id.tv_marked_words);
		mPasswordInputView = (GridPasswordView) findViewById(R.id.passwordInputView);


	}
	private void initData() {
		Bundle bundle = getIntent().getExtras();
		if (bundle!=null){
			identifyingCode = bundle.getString("code");
		}

		viewModel=new ResetPwdInputViewModel(this,this,this);
		((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).showSoftInput(mTvMarkedWords, 0);
	}
	private void initLIstener() {
		mPasswordInputView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
			@Override
			public void onTextChanged(String psw) {
			}

			@Override
			public void onInputFinish(String psw) {
				if (TextUtils.isEmpty(pwd)){//第一次
					pwd = psw;
					mPasswordInputView.clearPassword();
					mTvMarkedWords.setText("请确认支付密码");
				}else {
					if (!pwd.equals(psw)){//两次不一致 重新录入 走第一次
						pwd = "";
						mPasswordInputView.clearPassword();
						mTvMarkedWords.setText("请输入6位字符的密码");
						IncToastUtils.showShort(IncWalletResetPwdInputActivity.this,"两次密码不一致");
					}else {
						submit();
					}
				}
			}
		});
	}

	/**
	 * 两次密码一致   提交服务器
	 */
	private void submit() {
		viewModel.getResetPwd(pwd,identifyingCode);
	}

	/**
	 * 修改密码成功
	 * @param succeed
	 */
	@Override
	public void getResetCodeOk(boolean succeed) {
		LogUtils.d("重置密码 成功");
		IncToastUtils.showShort(this,"重置密码 成功");

		Stack <Class> stack=new Stack<>();

		stack.add(IncWalletResetPwdInputActivity.class);
		stack.add(IncWalletResetPwdActivity.class);
		stack.add(IncWalletSettingActivity.class);

		ActivityNavigator.navigator().removerActivity(stack);

	}
}
