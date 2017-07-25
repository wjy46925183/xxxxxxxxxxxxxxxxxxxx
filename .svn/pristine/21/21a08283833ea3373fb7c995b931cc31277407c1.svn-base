package com.dlg.inc.user.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cache.ACache;
import com.common.string.LogUtils;
import com.common.sys.ActivityNavigator;
import com.common.sys.SystemUtil;
import com.common.utils.RxBus;
import com.dlg.data.common.url.CommonUrl;

import com.dlg.inc.R;
import com.dlg.inc.app.IncMApp;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.home.activity.IncHomeActivity;
import com.dlg.inc.user.view.IncAlertView;
import com.dlg.inc.user.view.IncOnItemClickListener;
import com.dlg.inc.web.IncDefaultWebActivity;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.key.H5WebType;
import com.dlg.viewmodel.user.LogOutViewModel;
import com.dlg.viewmodel.user.presenter.LogOutPresenter;

import java.util.Stack;

/**
 * 作者：邹前坤
 * 主要功能： 用户设置页
 * 创建时间： 2017/7/19  09:42
 * <p>
 * 退出登录时 要有语音提示 要处理极光推送   销毁 cookie 销毁所有的activity 回到homeactivity
 */

public class IncSettingActivity extends IncBaseActivity implements View.OnClickListener, LogOutPresenter {
	private TextView mTvVersionName;//版本号
	private LinearLayout mLlChangepwd;//修改密码
	private LinearLayout mLlPeopleName;//实名认证
	private LinearLayout mLlClearCache;//清除缓存
	private TextView mTvCache;//缓存量
	private LinearLayout mLlUserAgreement;//用户协议
	private LinearLayout mLlAboutUs;//关于我们
	private Button mBtExit;//退出登录
	private LogOutViewModel logOutViewModel;
	private IncAlertView alertView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inc_page_user_setting);
        initView();
        initData();
        initListener();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mTvVersionName = (TextView) findViewById(R.id.tv_version_name);
        mLlChangepwd = (LinearLayout) findViewById(R.id.ll_changepwd);
        mLlPeopleName = (LinearLayout) findViewById(R.id.ll_people_name);
        mLlClearCache = (LinearLayout) findViewById(R.id.ll_clear_cache);
        mTvCache = (TextView) findViewById(R.id.tv_cache);
        mLlUserAgreement = (LinearLayout) findViewById(R.id.ll_user_agreement);
        mLlAboutUs = (LinearLayout) findViewById(R.id.ll_about_us);
        mBtExit = (Button) findViewById(R.id.bt_exit);
    }

    /**
     * 初始化 数据 如缓存大小 版本号等
     */
    private void initData() {
        getToolBarHelper().setToolbarTitle("设置");
        mTvVersionName.setText(SystemUtil.getVersionName(this));
        logOutViewModel = new LogOutViewModel(this, this, this);
    }

    /**
     * 初始化点击事件
     */
    private void initListener() {
        mLlChangepwd.setOnClickListener(this);
        mLlPeopleName.setOnClickListener(this);
        mLlClearCache.setOnClickListener(this);
        mLlUserAgreement.setOnClickListener(this);
        mLlAboutUs.setOnClickListener(this);
        mBtExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ll_changepwd) {
            LogUtils.d("点击修改密码");
            RxBus.get().post(AppKey.PageRequestCodeKey.LOGIN_RXBUS, "去修改密码");
        } else if (i == R.id.ll_people_name) {
            LogUtils.d("点击实名认证");
            //ActivityNavigator.navigator().navigateTo(AboutUsAtivity.class);

        } else if (i == R.id.ll_clear_cache) {
            LogUtils.d("点击清理缓存");

		}else if (i == R.id.ll_user_agreement){
			LogUtils.d("点击用户协议");
			Bundle bundleH5 = new Bundle();
			bundleH5.putString(IncDefaultWebActivity.TITLE_NAME, H5WebType.agreement.getName());
			bundleH5.putString(IncDefaultWebActivity.H5_URL, CommonUrl.H5_WEB_URL);
			bundleH5.putString(IncDefaultWebActivity.H5_TYPE, H5WebType.agreement.getType() + "");
			ActivityNavigator.navigator().navigateTo(IncDefaultWebActivity.class, bundleH5);
		}else if (i == R.id.ll_about_us){
			LogUtils.d("点击关于我们");
			ActivityNavigator.navigator().navigateTo(IncAboutUsAtivity.class);
		}else if (i == R.id.bt_exit){
			LogUtils.d("退出");


			alertView = new IncAlertView("提示", "确定退出登录？", "取消", null,
					new String[]{"确定"}, IncSettingActivity.this, IncAlertView.Style.Alert, new IncOnItemClickListener() {
				@Override
				public void onItemClick(Object o, int position) {
					if (position != -1) {
						logOutViewModel.logOut(ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
					}
				}
			});
			alertView.setView(false, false, "").setCancelable(true);
			alertView.show();

        } else {

        }
    }

    /**
     * 退出登录 后 后台会自动调登录，登录失败就是退出成功了
     */
    @Override
    public void logInError() {

        LogUtils.d("收到退出登录成功");

        boolean b = IncMApp.getInstance().getCookieStore().removeAllCookie();
        if (b) {
            LogUtils.d("清除 cookie 成功");
        }
        ACache.get(mContext).remove(AppKey.CacheKey.MY_USER_ID);
        ACache.get(mContext).remove(AppKey.CacheKey.MY_USER_INFO);
        ACache.get(mContext).remove(AppKey.CacheKey.USER_PHONE);
        ACache.get(mContext).remove(AppKey.CacheKey.NAME);
        ACache.get(mContext).remove(AppKey.CacheKey.USER_LOGO);
        Stack<Class> stack = new Stack<>();
        stack.add(IncHomeActivity.class);
        ActivityNavigator.navigator().keepRemoverActivity(stack);
    }

    @Override
    public void requestError(String msg) {
    }

    /**
     * 退出登录成功  作废
     *
     * @param o
     */
    @Override
    public void logOut(Object o) {


	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (alertView != null && alertView.isShowing()) {
				alertView.dismiss();
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
		return super.onKeyDown(keyCode, event);
	}

}
