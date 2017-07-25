package com.dlg.personal.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.string.LogUtils;
import com.dlg.data.wallet.model.InvoiceInformationRestVoBean;
import com.dlg.data.wallet.model.OrderBillMoreBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.view.ToastUtils;

/**
 * 作者：邹前坤
 * 主要功能：按订单开票 更多信息页
 * 创建时间： 2017/7/13  09:29
 * <p>
 * 根据上个页面传递过来的数据 进行展示 可以编辑
 * 然后再把数据传递回去
 */

public class OrderMoreInfoActivity extends BaseActivity {

	private InvoiceInformationRestVoBean bean;// 上个页面传来的模板数据
	private int type;
	private OrderBillMoreBean orderBillMoreBean;//上个页面传递过来的更多bean 也是这个页面传递出去的bean
	private EditText mEtTaxpayerDistinguishNumber;//纳税人的识别号
	private EditText mEtCompanyAddress; //地址
	private EditText mEtCompanyPhone;//电话
	private EditText mEtBankAccount;//开户行
	private EditText mEtBankAccountNumber;//开户行账号
	private EditText mEtRemarksExplain;//备注
	private TextView mTvConfirm;//queen

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_order_more_info);
		initView();
		initData();
		initListener();
	}



	/**
	 * 初始化 控件
	 */
	private void initView() {
		mEtTaxpayerDistinguishNumber = (EditText) findViewById(R.id.et_taxpayer_distinguish_number);
		mEtCompanyAddress = (EditText) findViewById(R.id.et_company_address);
		mEtCompanyPhone = (EditText) findViewById(R.id.et_company_phone);
		mEtBankAccount = (EditText) findViewById(R.id.et_bank_account);
		mEtBankAccountNumber = (EditText) findViewById(R.id.et_bank_account_number);
		mEtRemarksExplain = (EditText) findViewById(R.id.et_remarks_explain);
		mTvConfirm = (TextView) findViewById(R.id.tv_confirm);
	}

	/**
	 * 初始化数据
	 * 根据数据源不同分别设置
	 */
	private void initData() {
		getToolBarHelper().setToolbarTitle("按订单开票");
		type = getIntent().getIntExtra("type", 2);
		orderBillMoreBean=new OrderBillMoreBean();
		LogUtils.d("传递过来的type"+type);
		if (type == 0) {
			orderBillMoreBean = (OrderBillMoreBean) getIntent().getSerializableExtra("bean");
		} else {
			bean = (InvoiceInformationRestVoBean) getIntent().getSerializableExtra("bean");
		}


		if (type == 0) { // orderBillMoreBean
			mEtTaxpayerDistinguishNumber.setText(orderBillMoreBean.getTaxpayerDistinguishNumber());
			mEtCompanyAddress.setText(orderBillMoreBean.getCompanyAddress());
			mEtCompanyPhone.setText(orderBillMoreBean.getCompanyPhone());
			mEtBankAccount.setText(orderBillMoreBean.getBankAccount());
			mEtBankAccountNumber.setText(orderBillMoreBean.getBankAccountNumber());
			mEtRemarksExplain.setText(orderBillMoreBean.getRemarksExplain());
		} else { 	// bean
			mEtTaxpayerDistinguishNumber.setText(bean.getTaxpayerIdentificationCode());
			mEtCompanyAddress.setText(bean.getRegisterAddress());
			mEtCompanyPhone.setText(bean.getRegisterPhone());
			mEtBankAccount.setText(bean.getBankName());
			mEtBankAccountNumber.setText(bean.getBankAccount());
		}

	}

	/**
	 * 确认按钮点击的监听
	 */
	private void initListener() {
		mTvConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LogUtils.d("点击确认 ");

				submit();

				LogUtils.d("信息确认通过");
				Intent intent =new Intent();
				intent.putExtra("bean",orderBillMoreBean);
				setResult(RESULT_OK,intent);

				finish();
			}
		});
	}

	/**
	 * 返回数据前的校验
	 */
	private void submit() {
		String taxpayerDistinguishNumber = mEtTaxpayerDistinguishNumber.getText().toString().trim();
		String companyAddress = mEtCompanyAddress.getText().toString().trim();
		String companyPhone = mEtCompanyPhone.getText().toString().trim();
		String bankAccount = mEtBankAccount.getText().toString().trim();
		String bankAccountNumber = mEtBankAccountNumber.getText().toString().trim();

		if (taxpayerDistinguishNumber.isEmpty()){
			ToastUtils.showShort(this,"纳税人识别号不能为空");
			return;
		}
		if (companyAddress.isEmpty()){
			ToastUtils.showShort(this,"地址不能为空");
			return;
		}
		if (companyPhone.isEmpty()){
			ToastUtils.showShort(this,"电话不能为空");
			return;
		}
		if (bankAccount.isEmpty()){
			ToastUtils.showShort(this,"开户行不能为空");
			return;
		}
		if (bankAccountNumber.isEmpty()){
			ToastUtils.showShort(this,"开户行账号不能为空");
			return;
		}
		orderBillMoreBean.setTaxpayerDistinguishNumber(taxpayerDistinguishNumber);
		orderBillMoreBean.setCompanyAddress(companyAddress);
		orderBillMoreBean.setCompanyPhone(companyPhone);
		orderBillMoreBean.setBankAccount(bankAccount);
		orderBillMoreBean.setBankAccountNumber(bankAccountNumber);
		if (!mEtRemarksExplain.getText().toString().trim().isEmpty()){
			orderBillMoreBean.setRemarksExplain(mEtRemarksExplain.getText().toString().trim());
		}
	}

}
