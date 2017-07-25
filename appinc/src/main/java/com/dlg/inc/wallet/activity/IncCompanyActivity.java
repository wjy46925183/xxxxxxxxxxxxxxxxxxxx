package com.dlg.inc.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.string.LogUtils;
import com.common.sys.ActivityNavigator;
import com.dlg.data.wallet.model.BankBean;
import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncToolBarType;
import com.dlg.inc.home.view.IncToastUtils;
import com.dlg.viewmodel.Wallet.WithdrawalViewModel;
import com.dlg.viewmodel.Wallet.presenter.WithDrawalPresenter;


/**
 * 作者：邹前坤
 * 主要功能：绑定企业账户
 * 创建时间：2017/7/21 15:48
 */
public class IncCompanyActivity extends IncBaseActivity implements WithDrawalPresenter {

	private EditText mEdBankName;//银行开户名
    private TextView mEdBank;//银行名、、、、、、、
    private EditText mEdCompanyCode;//公司对公账户
    private Button mBtBindCompany;//绑定
    private EditText mEdPeopleName;//身份证
    private WithdrawalViewModel viewModel;
    private RelativeLayout mRlBank;
	private static final int REQUEST_CODE = 326;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inc_page_company, IncToolBarType.Default);
        mToolBarHelper.setToolbarTitle("绑定公司账户");
        initView();
        initData();
        initListener();

    }

    private void initData() {
        viewModel=new WithdrawalViewModel(this,this,this);
    }

    /**
     * 控件初始化
     */
    private void initView() {

        mEdBankName = (EditText) findViewById(R.id.ed_bank_name);
        mEdPeopleName = (EditText) findViewById(R.id.ed_people_name);
        mEdBank = (TextView) findViewById(R.id.ed_bank);
        mEdCompanyCode = (EditText) findViewById(R.id.ed_company_code);
        mBtBindCompany = (Button) findViewById(R.id.bt_bindCompany);
        mRlBank = (RelativeLayout) findViewById(R.id.rl_bank);
    }

    /**
     * 变量初始化
     */
    private void initListener() {

        mRlBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNavigator.navigator().navigateTo(IncSelectBankActivity.class,REQUEST_CODE);
            }
        });
        mBtBindCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    /**
     * 检验 输入 并提交
     */
    private void submit() {
        String bankName=mEdBank.getText().toString().trim();
        String bankNumber=mEdCompanyCode.getText().toString().trim();
        String name=mEdBankName.getText().toString().trim();
        if (TextUtils.isEmpty(bankName)){
            IncToastUtils.showShort(this,"请先选择银行");
            return;
        }
        if (TextUtils.isEmpty(bankNumber)){
            IncToastUtils.showShort(this,"请先输入对公账户");
            return;
        }
        if (TextUtils.isEmpty(name)){
            IncToastUtils.showShort(this,"请先输入银行卡户名");
            return;
        }
        viewModel.incBindBank(bankNumber,bankName,name);

    }

    @Override
    public void toMap(String string) {

    }

    /**
     * 绑定成功或失败
     * @param s
     */
    @Override
    public void toBind(String s) {
        LogUtils.d("绑定成功 ");
        finish();
    }

    @Override
    public void toSet() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE&&resultCode==RESULT_OK&&data!=null){
           BankBean bean= (BankBean) data.getSerializableExtra("bean");
            mEdBank.setText(bean.getBankName());
        }
    }
}
