package com.dlg.inc.wallet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.common.sys.ActivityNavigator;
import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;


/**
 * 作者：邹前坤
 * 主要功能： 钱包内的设置界面
 * 创建时间： 2017/7/14  15:52
 */

public class IncWalletSettingActivity extends IncBaseActivity {
	private TextView mTvResetPwd;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inc_page_wallet_setting);
		initView();
		initData();
		initListener();
	}


	/**
	 * 初始化数据
	 */
	private void initData() {
		getToolBarHelper().setToolbarTitle("钱包设置");
	}

	private void initView() {
		mTvResetPwd = (TextView) findViewById(R.id.tv_reset_pwd);
	}
	private void initListener() {
		mTvResetPwd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityNavigator.navigator().navigateTo(IncWalletResetPwdActivity.class);
			}
		});
	}

}
