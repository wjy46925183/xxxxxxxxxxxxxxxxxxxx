package com.dlg.personal.wallet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.base.ToolBarType;

/**
 * 作者：关蕤
 * 主要功能：绑定企业账户
 * 创建时间：2017/7/14 15:48
 */
public class CompanyActivity extends BaseActivity {
    private EditText mEdBankName;
    private EditText mEdBank;
    private EditText mEdCompanyCode;
    private Button mBtBindCompany;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_company, ToolBarType.Default);
        mToolBarHelper.setToolbarTitle("绑定公司账户");
        initView();
        initData();
    }

    /**
     * 控件初始化
     */
    private void initView() {

        mEdBankName = (EditText) findViewById(R.id.ed_bank_name);
        mEdBank = (EditText) findViewById(R.id.ed_bank);
        mEdCompanyCode = (EditText) findViewById(R.id.ed_company_code);
        mBtBindCompany = (Button) findViewById(R.id.bt_bindCompany);
    }

    /**
     * 变量初始化
     */
    private void initData() {

    }
}
