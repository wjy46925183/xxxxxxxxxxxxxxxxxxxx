package com.dlg.inc.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.cache.ACache;
import com.common.string.LogUtils;
import com.common.sys.ActivityNavigator;
import com.common.utils.Base64Utils;
import com.common.utils.MD5Utils;
import com.dlg.data.home.model.DictionaryBean;
import com.dlg.data.wallet.model.InvoiceInformationRestVoBean;
import com.dlg.data.wallet.model.InvoiceListBean;
import com.dlg.data.wallet.model.OrderBillMoreBean;

import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncToolBarType;
import com.dlg.inc.home.view.IncToastUtils;
import com.dlg.viewmodel.Wallet.OrderBillNewViewModel;
import com.dlg.viewmodel.Wallet.OrderBillViewModel;
import com.dlg.viewmodel.Wallet.presenter.OrderBillPresenter;
import com.dlg.viewmodel.home.DictionaryViewModel;
import com.dlg.viewmodel.home.presenter.DictionaryPresenter;
import com.dlg.viewmodel.key.AppKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：邹前坤
 * 主要功能：按订单开发票
 * 创建时间： 2017/7/12  13:55
 *
 * 先请求到一个发票模板，
 * 将数据做上去，
 * 然后这些又可以修改
 * 最后调输入密码和支付以及数据上传
 */

public class IncOrderBillActivity extends IncBaseActivity implements OrderBillPresenter, DictionaryPresenter {

	private EditText mEtCompanyTitle;//公司抬头
	private TextView mTvServiceMoney;//服务费字段
	private TextView mTvInvoiceMoney;//发票金额
	private RelativeLayout mRlMoreInfo;//更多信息
	private EditText mEtAddressee;//收件人
	private EditText mEtRelationTelephone;//联系电话
	private EditText mEtLocationArea;//地址
	private EditText mEtBankaccountAddress;//详细地址 ---X 开户行地址
	private TextView mTvSelectReceiptPrice; //交多少钱
	private TextView mTvBtnPayment; // 支付
	private TextView mTvPayOnMoney;//代缴几点 税

	private OrderBillViewModel viewModel;// 发票模板的 viewModel
	private OrderBillNewViewModel newViewModel;// 发票新建 viewModel

	private DictionaryViewModel mDictionaryViewModel; //词典
	private ArrayList<InvoiceListBean> mData; //上个页面传过来的集合 ，那些消费记录
	private double billnoMoney;// 发票钱数 由上个页面传入的数据集合计算
	private InvoiceInformationRestVoBean bean;//模板数据

	private static final int MORE_REQUEST=201;//更多信息开启activity用
	private static final int PWD_REQUEST = 652;//验证密码用
	private OrderBillMoreBean orderBillMoreBean;
	private StringBuilder arrStr;// 为 businessNumbers 赋值
	private StringBuilder subBillId;// 为 subBillBusinessNumbers 赋值
	private HashMap<String, String> map;//联网塞数据
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inc_page_order_bill, IncToolBarType.Default);
		initView();
		initData();
		initNet();
		initListener();
	}




	/**
	 * 初始化控件
	 */
	private void initView() {

		mEtCompanyTitle = (EditText) findViewById(R.id.et_company_title);
		mTvServiceMoney = (TextView) findViewById(R.id.tv_service_money);
		mTvInvoiceMoney = (TextView) findViewById(R.id.tv_invoice_money);
		mRlMoreInfo = (RelativeLayout) findViewById(R.id.rl_more_info);
		mEtAddressee = (EditText) findViewById(R.id.et_addressee);
		mEtRelationTelephone = (EditText) findViewById(R.id.et_relation_telephone);
		mEtLocationArea = (EditText) findViewById(R.id.et_location_area);
		mEtBankaccountAddress = (EditText) findViewById(R.id.et_bankaccount_address);

		mTvSelectReceiptPrice = (TextView) findViewById(R.id.tv_select_receipt_price);
		mTvBtnPayment = (TextView) findViewById(R.id.tv_btn_payment);
		mTvPayOnMoney = (TextView) findViewById(R.id.tv_pay_on_money);




	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		getToolBarHelper().setToolbarTitle("按订单开票");
		mData = (ArrayList<InvoiceListBean>) getIntent().getExtras().getSerializable("bean");
		billnoMoney = 0.0;
        // TODO   这里处理两个累加的字段  businessNumbers 和  subBillBusinessNumbers 参考旧代码处理方式
		arrStr = new StringBuilder("{");
		subBillId = new StringBuilder("");
		if (mData!=null){
			for (int i = 0; i <mData.size() ; i++) {
				billnoMoney +=Math.abs(mData.get(i).getAmount());

				arrStr.append(mData.get(i).getBillId()+",");
				subBillId.append(mData.get(i).getSubBillBusinessNumber()+",");
			}
		}

		arrStr.replace(arrStr.length()-1, arrStr.length(), "}");
		subBillId.replace(subBillId.length()-1, subBillId.length(), " ");

		String format = String.format("%.2f", billnoMoney);
		mTvInvoiceMoney.setText(format+"元");

		viewModel=new OrderBillViewModel(this,this,this);
		newViewModel=new OrderBillNewViewModel(this,this,this);

		mDictionaryViewModel = new DictionaryViewModel(this, this, this);
		mDictionaryViewModel.getDictionaryData("tax.rate", "0");//查询税率
	}

	/**
	 * 初始化联网以及初始化处理联网
	 */
	private void initNet() {
		viewModel.getOrderBIlltemplate();
	}

	/**
	 * 点击监听
	 */
	private void initListener() {
		mTvBtnPayment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LogUtils.d("点击 支付");
			}
		});
		mRlMoreInfo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LogUtils.d("点击 更多");
				if (bean!=null){
					/**
					 * 参考老代码  模板bean 和 更多bean不是一个
					 * 更多 bean不是null时传递模板bean。否则 传递 更多bean
					 * 处理  先传递一个int数字 1代表模板 2代表更多
					 */
					if (orderBillMoreBean!=null){

						Intent intent=new Intent();
						intent.putExtra("type",0);
						intent.putExtra("bean",orderBillMoreBean);
						ActivityNavigator.navigator().navigateTo(IncOrderMoreInfoActivity.class,intent,MORE_REQUEST);

					}else {

						Intent intent=new Intent();
						intent.putExtra("type",1);
						intent.putExtra("bean",bean);
						ActivityNavigator.navigator().navigateTo(IncOrderMoreInfoActivity.class,intent,MORE_REQUEST);

					}
					}
			}
		});
		/**
		 * 点击支付
		 */
		mTvBtnPayment.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				submit();
				LogUtils.d("得到密码前的验证 通过");
				//TODO 跳往验证密码的页面  是否需要携带一点东西
				Bundle bunder = new Bundle();
				bunder.putString("type","3");
				ActivityNavigator.navigator().navigateTo(IncPublicInputPwdActivity.class,bunder,PWD_REQUEST);
			}
		});
	}

	/**
	 * 点击支付后的校验
	 */
	private void submit() {
		String companyRise = mEtCompanyTitle.getText().toString().trim();
		String addressee = mEtAddressee.getText().toString().trim();
		String relationTelephone = mEtRelationTelephone.getText().toString().trim();
		String locationArea = mEtLocationArea.getText().toString().trim();
		String bankaccountAddress = mEtBankaccountAddress.getText().toString().trim();

		if(orderBillMoreBean==null){
			if(bean!=null){

				orderBillMoreBean = new OrderBillMoreBean();
				orderBillMoreBean.setTaxpayerDistinguishNumber(bean.getTaxpayerIdentificationCode());
				orderBillMoreBean.setCompanyAddress(bean.getRegisterAddress());
				orderBillMoreBean.setCompanyPhone(bean.getRegisterPhone());
				orderBillMoreBean.setBankAccount(bean.getBankName());
				orderBillMoreBean.setBankAccountNumber(bean.getBankAccount());
			}
		}
		if(TextUtils.isEmpty(companyRise)){
			IncToastUtils.showShort(this, "公司抬头不能为空");
			return;
		}
		if(billnoMoney<=0){
			IncToastUtils.showShort(this, "发票金额不能少于0");
			return;
		}
		if(TextUtils.isEmpty(addressee)){
			IncToastUtils.showShort(this, "收件人不能为空");
			return;
		}
		if(TextUtils.isEmpty(locationArea)){
			IncToastUtils.showShort(this, "所在地区不能为空");
			return;
		}
		if(TextUtils.isEmpty(relationTelephone)){
			IncToastUtils.showShort(this, "联系电话不能为空");
			return;
		}
		if(TextUtils.isEmpty(bankaccountAddress)){
			IncToastUtils.showShort(this, "开户行地址不能为空");
			return;
		}

		if(orderBillMoreBean==null){
			IncToastUtils.showShort(this, "请填写更多信息");
			return;
		}

		map = new HashMap<>();
		map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
		map.put("businessNumbers", arrStr.toString());
		map.put("subBillBusinessNumbers", subBillId.toString().trim());
		map.put("invoiceAmount", billnoMoney+"");
		map.put("bankAccount",orderBillMoreBean.getBankAccountNumber());
		map.put("companyName", companyRise);
		map.put("taxpayerIdentificationCode", orderBillMoreBean.getTaxpayerDistinguishNumber());
		map.put("registerAddress", orderBillMoreBean.getCompanyAddress());
		map.put("registerPhone", orderBillMoreBean.getCompanyPhone());
		map.put("bankName", orderBillMoreBean.getBankAccount());
		map.put("checkTakerName", addressee);
		map.put("checkTakerPhone", relationTelephone);
		map.put("checkTakerAddress", locationArea);
		map.put("remark", orderBillMoreBean.getRemarksExplain());
		if(bean!=null ){
			map.put("invoiceInformationId", bean.getId()+"");

		}

	}

	@Override
	public void getTemplateBean(InvoiceInformationRestVoBean bean) {
		this.bean=bean;
		LogUtils.d("得到了模板数据   id== "+bean.getId());
		settextView(bean);
	}

	@Override
	public void getOkOrErroe(String bean) {
		LogUtils.d("新建成功 或者新建失败"+bean);
		Intent intent=new Intent();
		intent.putExtra("isUpdata",true);
		setResult(RESULT_OK,intent);
		finish();

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==MORE_REQUEST&&resultCode==RESULT_OK&&data!=null){ // 更多界面返回
			OrderBillMoreBean morBean = (OrderBillMoreBean) data.getSerializableExtra("bean");
			orderBillMoreBean=morBean;
			LogUtils.d(" 收到更多界面返回的信息 "+orderBillMoreBean.getCompanyAddress());
		}
		if (requestCode==PWD_REQUEST&&resultCode==RESULT_OK&&data!=null){ // 更多界面返回

			String pwd = data.getStringExtra("pwd");
			LogUtils.d(" 收到验证密码返回的信息  "+pwd);
			if(map!=null && map.size()>0){
				map.put("payPassword", Base64Utils.getBase64(MD5Utils.MD5Encode(pwd) + "/."));
			}
			submitDatas(map);
		}
	}

	/**
	 * 联网 提交数据  新建发票
	 * @param map
	 */
	private void submitDatas(HashMap<String, String> map) {
		/**
		 * "/api/invoiceRest/addInvoice"
		 */
		newViewModel.getOrderBIllNew(map);
	}

	/**
	 * 这里得到数据了 给控件塞数据
	 * @param bean
	 */
	private void settextView(InvoiceInformationRestVoBean bean) {
		mEtCompanyTitle.setText(bean.getCompanyName());
		mEtAddressee.setText(bean.getCheckTakerName());
		mEtRelationTelephone.setText(bean.getCheckTakerPhone());
		mEtLocationArea.setText(bean.getRegisterAddress());

	}

	/**
	 * 得到  税率
	 * @param dictionaryBean
	 */
	@Override
	public void getDictionary(List<DictionaryBean> dictionaryBean) {
		LogUtils.d("得到了 税率 ===   "+dictionaryBean.get(0).getDataValue());
		mTvPayOnMoney.setText("代收税点"+Double.parseDouble(dictionaryBean.get(0).getDataValue())*100+"%");

		String format = String.format("%.2f", billnoMoney*Double.parseDouble(dictionaryBean.get(0).getDataValue()));
		mTvSelectReceiptPrice.setText(format+"元");

	}


}
