package com.dlg.inc.wallet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.common.cache.ACache;
import com.common.sys.ActivityNavigator;
import com.dlg.data.user.model.UserAttributeInfoBean;

import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncToolBarType;
import com.dlg.inc.home.view.IncToastUtils;
import com.dlg.viewmodel.Wallet.WithdrawalViewModel;
import com.dlg.viewmodel.Wallet.presenter.WithDrawalPresenter;
import com.dlg.viewmodel.key.AppKey;

/**
 * 作者：关蕤
 * 主要功能：绑定支付宝
 * 创建时间：2017/7/13 09:56
 */
public class IncBindAliActivity extends IncBaseActivity implements WithDrawalPresenter {
    private TextView mTvRealName;
    private EditText mEdAccount;
    private Button mBtBindAli;

    private String mAccount ;
    private WithdrawalViewModel viewModel ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inc_page_bind_ali, IncToolBarType.Default);
        mToolBarHelper.getToolbarTitle().setText("绑定支付宝");
        initView();
        initData();
    }

    /**
     * 控件初始化
     */
    private void initView() {
        viewModel = new WithdrawalViewModel(this,this,this);
        mTvRealName = (TextView) findViewById(R.id.tv_realName);
        mEdAccount = (EditText) findViewById(R.id.ed_account);
        mBtBindAli = (Button) findViewById(R.id.bt_bindAli);
        mBtBindAli.setEnabled(false);
        /**
         * 账号输入监听，只是做个简单的有输入按钮即可用
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
                if(TextUtils.isEmpty(s)){
                    mBtBindAli.setEnabled(false);
                }else{
                    mBtBindAli.setEnabled(true);
                    mAccount = s.toString().trim();
                }
            }
        });

        /**
         * 绑定按钮监听
         */
        mBtBindAli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.BindAli(mTvRealName.getText().toString().trim(),mAccount);
            }
        });
    }

    /**
     * 变量初始化
     */
    private void initData() {
        UserAttributeInfoBean bean = (UserAttributeInfoBean) ACache.get(mContext).getAsObject(AppKey.CacheKey.MY_USER_INFO);
        mTvRealName.setText(bean.getName());
    }

    /**
     * 查询到的可提现次数，此处无用
     * @param string
     */
    @Override
    public void toMap(String string) {

    }

    /**
     * 绑定成功
     * @param s
     */
    @Override
    public void toBind(String s) {
        IncToastUtils.showLong(mContext,"绑定成功");
        Bundle bundle = getIntent().getExtras();
        if(null != null && null != bundle.getString("first")){ //第一次使用，选择绑定进入
            ActivityNavigator.navigator().finishLastTwo();
            ActivityNavigator.navigator().navigateTo(IncWalletActivity.class);
        }else{
            finish();
        }
    }

    @Override
    public void toSet() {

    }
}
