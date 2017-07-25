package com.dlg.inc.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.common.sys.ActivityNavigator;
import com.common.view.gridpasswordview.GridPasswordView;

import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncToolBarType;
import com.dlg.inc.home.view.IncToastUtils;
import com.dlg.viewmodel.Wallet.WithdrawalViewModel;
import com.dlg.viewmodel.Wallet.presenter.WithDrawalPresenter;

/**
 * 作者：邹前坤
 * 主要功能： 公共的输入密码的界面
 * 创建时间： 2017/7/13  15:10
 *
 * 可能有多个地方在用，根据传递过来的值，进行不同的操作
 */

public class IncPublicInputPwdActivity extends IncBaseActivity implements WithDrawalPresenter{
	private TextView mTvMarkedWords;// 提示文字
	private GridPasswordView mPasswordInputView;// 输入框
	private String pwd ;	//输入的密码，由于前后两次输入都放在一个页面处理了，所以这个也做输入完成后如何操作的判断
	private WithdrawalViewModel viewModel ;
	private String type="-1"; //当前定位  3 是输入密码 一次就返回

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inc_page_public_input_pwd, IncToolBarType.Default);
		initView();
		initData();
		initListener();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mTvMarkedWords = (TextView) findViewById(R.id.tv_marked_words);
		mPasswordInputView = (GridPasswordView) findViewById(R.id.passwordInputView);

		/**
		 * 弹出软键盘  还不起作用 要看看为什么
		 */
		mPasswordInputView.forceInputViewGetFocus();
	}

	/**
	 * 处理一些传递过来的数据
	 *
	 */
	private void initData() {
		viewModel = new WithdrawalViewModel(this,this,this);
		getToolBarHelper().setToolbarTitle("设置密码");
		Bundle bundle = getIntent().getExtras();
		if (bundle!=null){
			type =bundle .getString("type");
		}


	}
	/**
	 * 密码输入是否完成的监听
	 */
	private void initListener() {

		mPasswordInputView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
			@Override
			public void onTextChanged(String psw) {

			}

			@Override
			public void onInputFinish(String psw) {
				if (type.equals("3")){// 3代表输入密码     根据类型判断  ，一个类型写在一起 保持清晰逻辑
					Intent intent =new Intent();
					intent.putExtra("pwd",psw);
					setResult(RESULT_OK,intent);
					finish();
				}else {
					if(TextUtils.isEmpty(pwd)){	//当定义的pwd为空，则标识这次为第一次输入。将输入的密码赋值到定义的变量，并清空输入框
						pwd = psw;
						mPasswordInputView.clearPassword();
					}else if(!pwd.equals(psw)){		//前后密码输入不一致，则清空输入框并提示
						IncToastUtils.showShort(mContext,"前后密码输入不一致，请重新输入");
						mPasswordInputView.clearPassword();
					}else{		//前后密码一致，则上传到服务器
						viewModel.setPayPwd(psw);
					}
				}

			}
		});
	}


	/**
	 * 无用
	 * @param string
	 */
	@Override
	public void toMap(String string) {

	}

	/**
	 * 无用
	 * @param s
	 */
	@Override
	public void toBind(String s) {

	}

	/**
	 * 密码设置成功
	 */
	@Override
	public void toSet() {
		ActivityNavigator.navigator().navigateTo(IncSetBindActivity.class);
	}
}
