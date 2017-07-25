package com.dlg.personal.wallet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.base.ToolBarType;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.viewmodel.Wallet.WithdrawalViewModel;
import com.dlg.viewmodel.Wallet.presenter.WithDrawalPresenter;

/**
 * 作者：关蕤
 * 主要功能：兑换券
 * 创建时间：2017/7/13 17:49
 */
public class ExchangeActivity extends BaseActivity implements WithDrawalPresenter {

    private EditText mEdWrite;
    private Button mBtExchange;

    private String mExchangeCode ;
    private WithdrawalViewModel viewModel ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_exchange, ToolBarType.Default);
        mToolBarHelper.getToolbarTitle().setText("兑换码");
        initView();
        initData();
    }

    /**
     * 页面控件初始化
     */
    private void initView() {
        mEdWrite = (EditText) findViewById(R.id.ed_write);
        mBtExchange = (Button) findViewById(R.id.bt_exchange);
        mBtExchange.setEnabled(false);

        /**
         * 兑换码输入监听
         */
        mEdWrite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mExchangeCode = s.toString().trim();
                if(TextUtils.isEmpty(s) || s.length() < 8){
                    mBtExchange.setEnabled(false);
                }else{
                    mBtExchange.setEnabled(true);
                }
            }
        });

        /**
         * 兑换按钮点击事件
         */
        mBtExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.exchange(mExchangeCode);
            }
        });
    }

    /**
     * 变量初始化
     */
    private void initData() {
        viewModel = new WithdrawalViewModel(this,this,this);
    }

    /**
     * 兑换结果//0.成功,1.失败
     * @param string
     */
    @Override
    public void toMap(String string) {
        if("0".equals(string)){
            ToastUtils.showLong(mContext,"兑换成功");
        }else{
            ToastUtils.showLong(mContext,"兑换失败");
        }
    }

    /**
     * 此页面无用
     * @param s
     */
    @Override
    public void toBind(String s) {

    }

    @Override
    public void toSet() {

    }
}
