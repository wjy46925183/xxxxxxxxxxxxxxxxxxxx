package com.dlg.personal.wallet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.common.string.LogUtils;
import com.common.view.gridpasswordview.GridPasswordView;
import com.dlg.data.home.model.DictionaryBean;
import com.dlg.personal.R;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.activity.HomeActivity;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.personal.oddjob.activity.OddActivity;
import com.dlg.personal.view.TextOrEditxtView;
import com.dlg.viewmodel.Wallet.PaymentViewModel;
import com.dlg.viewmodel.Wallet.presenter.PaymentPresenter;
import com.dlg.viewmodel.home.DictionaryViewModel;
import com.dlg.viewmodel.home.presenter.DictionaryPresenter;

import java.util.List;
import java.util.Stack;

/**
 * 作者：邹前坤
 * 主要功能：支付
 * 创建时间： 2017/7/18  10:37
 */

public class PaymentActivity extends BaseActivity implements  PaymentPresenter, DictionaryPresenter {
	private ScrollView mScrollview;
	private TextOrEditxtView mTvPayCompensation;// 报酬
	private TextOrEditxtView mTvPayTip;//小费
	private TextOrEditxtView mTvPayInsurance;//保险
	private TextOrEditxtView mTvPayServiceCharge;//平台服务费
	private TextOrEditxtView mTvPayAmount;//总金额
	private TextOrEditxtView mTvPayBalance;//余额
	private GridPasswordView mPasswordView;//密码输入控件
	private TextView mTvForgetPWD;//忘记密码

	private double compensation;// 报酬
	private double tip;// 小费
	private double insurance;// 保险
	private double serviceCharge;// 服务费
	private double allAmount;// 全部的总金额
	private double balance;// 余额
	private String orders;// 订单业务编号  老版参考

	private DictionaryViewModel mDictionaryViewModel; //词典
	private PaymentViewModel viewModel;
	private String type="-1";// -1默认是从我的零工进来的  支付完成返回我的零工 其余的具体判断处理
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_payment);
		initView();
		initData();
		initNet();
		initListener();
	}
	/**
	 * 初始化控件
	 */
	private void initView() {
		mScrollview = (ScrollView) findViewById(R.id.scrollview);
		mTvPayCompensation = (TextOrEditxtView) findViewById(R.id.tv_pay_compensation);
		mTvPayTip = (TextOrEditxtView) findViewById(R.id.tv_pay_tip);
		mTvPayInsurance = (TextOrEditxtView) findViewById(R.id.tv_pay_insurance);
		mTvPayServiceCharge = (TextOrEditxtView) findViewById(R.id.tv_pay_service_charge);
		mTvPayAmount = (TextOrEditxtView) findViewById(R.id.tv_pay_amount);
		mTvPayBalance = (TextOrEditxtView) findViewById(R.id.tv_pay_balance);
		mPasswordView = (GridPasswordView) findViewById(R.id.password_view);
		mTvForgetPWD = (TextView) findViewById(R.id.tv_forgetPWD);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		getToolBarHelper().setToolbarTitle("支付");
		getToolBarHelper().getToolbarTextRight().setVisibility(View.VISIBLE);
		getToolBarHelper().setToolbarTextRight("充值");

		Bundle bundle = getIntent().getExtras();
		if (bundle!=null){
			compensation = Double.parseDouble(bundle.getString("pay","0"));//报酬
			tip =Double.parseDouble(bundle.getString("tip","0")); //小费
			insurance = Double.parseDouble(bundle.getString("danger","0"));//保险
			orders = bundle.getString("businessNumbers");//订单业务编号

			allAmount =compensation+tip+insurance ;//合计
			balance = bundle.getDouble("",0.0);//余额

			type=bundle.getString("","-1");//
		}
		mTvPayCompensation.setRightText(compensation+"元");
		mTvPayTip.setRightText(tip+"元");
		mTvPayInsurance.setRightText(insurance+"元");
		mTvPayBalance.setRightText(balance+"元");
		mTvPayAmount.setRightText(allAmount+"元");
		mTvPayBalance.setVisibility(View.GONE);

		if (tip==0){
			mTvPayTip.setVisibility(View.GONE);
		}
		if (insurance==0){
			mTvPayInsurance.setVisibility(View.GONE);
		}
		if (balance==0){
			mTvPayBalance.setVisibility(View.GONE);
		}

	}
	private void initNet() {
		mDictionaryViewModel=new DictionaryViewModel(this,this,this);
		mDictionaryViewModel.getDictionaryData("server.rate","0");
		viewModel=new PaymentViewModel(this,this,this);
	}
	/**
	 * 初始化监听
	 */
	private void initListener() {
		mTvForgetPWD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityNavigator.navigator().navigateTo(WalletResetPwdActivity.class);
			}
		});
		mPasswordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
			@Override
			public void onTextChanged(String psw) {
			}

			@Override
			public void onInputFinish(String psw) {
				viewModel.getPayment(orders,psw);
			}
		});
		getToolBarHelper().getToolbarTextRight().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ActivityNavigator.navigator().navigateTo(RechargeActivity.class);
			}
		});
	}

	/**
	 *
	 * @param dictionaryBean
	 */
	@Override
	public void getDictionary(List<DictionaryBean> dictionaryBean) {
		LogUtils.d("得到了 服务费  ===   "+dictionaryBean.get(0).getDataValue());

		String format = String.format("%.1f", (compensation+tip)*Double.parseDouble(dictionaryBean.get(0).getDataValue()));
		mTvPayServiceCharge.setRightText(format+"元");
		if (format.equals("0")||format.equals("0.0")||format.equals("0.00")){
			mTvPayServiceCharge.setVisibility(View.GONE);
		}
		String format2 = String.format("%.2f", (compensation+tip+insurance)+Double.parseDouble(format));
		mTvPayAmount.setRightText(format2+"元");

	}

	/**
	 * 支付失败或成功
	 * @param bean
	 */
	@Override
	public void getPayOKOrNo(String bean) {
		mPasswordView.clearPassword();
		if (bean.equals("0")){
			LogUtils.d("支付成功");
			ToastUtils.showShort(this,"支付成功");
			finish();
			/**
			 * 保留哪几个
			 */
			Stack<Class> stack=new Stack<>();
			stack.add(HomeActivity.class);
			stack.add(OddActivity.class);
			ActivityNavigator.navigator().keepRemoverActivity(stack);

		}else {
			LogUtils.d("支付失败");
			ToastUtils.showShort(this,"支付失败");
		}
	}


}
