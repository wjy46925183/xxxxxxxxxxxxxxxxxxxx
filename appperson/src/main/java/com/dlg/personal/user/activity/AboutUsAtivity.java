package com.dlg.personal.user.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.common.sys.SystemUtil;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;

/**
 * 作者：邹前坤
 * 主要功能：关于我们
 * 创建时间： 2017/7/19  11:04
 */

public class AboutUsAtivity extends BaseActivity {
	private ImageView mIvAboat;
	private TextView mTvDalinggong;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_about_us);
		initView();
		initData();
	}

	private void initData() {
		getToolBarHelper().setToolbarTitle("关于我们");
		Glide.with(this).load(R.mipmap.aboat_us)
				.asGif()
				.diskCacheStrategy(DiskCacheStrategy.SOURCE)
				.into(mIvAboat);
		mTvDalinggong.setText("打零工（上海）互联网科技有限公司\n©2017 v"+ SystemUtil.getVersionName(this));
	}

	private void initView() {
		mIvAboat = (ImageView) findViewById(R.id.iv_aboat);
		mTvDalinggong = (TextView) findViewById(R.id.tv_dalinggong);
	}
}
