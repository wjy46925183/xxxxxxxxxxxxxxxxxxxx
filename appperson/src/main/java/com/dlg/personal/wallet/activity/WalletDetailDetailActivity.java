package com.dlg.personal.wallet.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.string.LogUtils;
import com.common.utils.DateUtils;
import com.dlg.data.wallet.model.WalletListBean;
import com.dlg.data.wallet.model.WalletListDetailBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.viewmodel.Wallet.WalletDetailDetailViewModel;
import com.dlg.viewmodel.Wallet.presenter.WalletDetailDetailPresenter;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * 作者：邹前坤
 * 主要功能：钱包明细列表中的单条明细
 * 创建时间： 2017/7/14  13:10
 */

public class WalletDetailDetailActivity extends BaseActivity implements WalletDetailDetailPresenter {
	private CircleImageView mImgHead;//头图片
	private TextView mTvUsername;//姓名
	private TextView mTvMoney;//金额
	private TextView mTvType;//交易成功或失败
	private TextView mTvTransactionType;//交易类型
	private TextView mTvPaymentMode;//支付方式
	private LinearLayout mLinStatus;//处理的进度展示父控件 控制显示隐藏
	private ImageView mImageType; //左侧对勾 第一行
	private View mLineNormal;//三个对勾第一个分割线
	private TextView mTvProType;//第一行处理状态
	private TextView mTvProTime;//第一行处理时间
	private View mLineNormal1;//三个对勾第2个分割线
	private TextView mTvProType1;//第 2 行处理状态
	private TextView mTvProTime1;//第2行处理时间
	private ImageView mImageType1;//左侧对勾 第2行
	private RelativeLayout mRlTradeSuccess;//第三行可能要控制显示隐藏
	private ImageView mImageType2;//左侧对勾 第3行
	private TextView mTvProType2;//第 3 行处理状态
	private TextView mTvProTime2;//第3 行处理时间
	private TextView mTvEmarksExplain;//备注说明
	private TextView mTvCreateTime;//创建时间

	private WalletListDetailBean bean;
	private WalletDetailDetailViewModel viewModel;
	private String billid="";
	private WalletListBean walletListBean;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page_wallet_detail_detail);

		initView();
		initData();
		initNet();
	}

	/**
	 * 初始化控件
	 */
	private void initView() {

		mImgHead = (CircleImageView) findViewById(R.id.img_head);
		mTvUsername = (TextView) findViewById(R.id.tv_username);
		mTvMoney = (TextView) findViewById(R.id.tv_money);
		mTvType = (TextView) findViewById(R.id.tv_type);
		mTvTransactionType = (TextView) findViewById(R.id.tv_transaction_type);
		mTvPaymentMode = (TextView) findViewById(R.id.tv_payment_mode);
		mLinStatus = (LinearLayout) findViewById(R.id.lin_status);
		mImageType = (ImageView) findViewById(R.id.image_type);
		mLineNormal = (View) findViewById(R.id.line_normal);
		mTvProType = (TextView) findViewById(R.id.tv_pro_type);
		mTvProTime = (TextView) findViewById(R.id.tv_pro_time);
		mLineNormal1 = (View) findViewById(R.id.line_normal1);
		mTvProType1 = (TextView) findViewById(R.id.tv_pro_type1);
		mTvProTime1 = (TextView) findViewById(R.id.tv_pro_time1);
		mImageType1 = (ImageView) findViewById(R.id.image_type1);
		mRlTradeSuccess = (RelativeLayout) findViewById(R.id.rl_trade_success);
		mImageType2 = (ImageView) findViewById(R.id.image_type2);
		mTvProType2 = (TextView) findViewById(R.id.tv_pro_type2);
		mTvProTime2 = (TextView) findViewById(R.id.tv_pro_time2);
		mTvEmarksExplain = (TextView) findViewById(R.id.tv_emarks_explain);
		mTvCreateTime = (TextView) findViewById(R.id.tv_create_time);

	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		getToolBarHelper().setToolbarTitle("账单详情");
		viewModel=new WalletDetailDetailViewModel(this,this,this);
		Bundle bundle = getIntent().getExtras();
		if (bundle!=null){
			walletListBean = (WalletListBean) bundle.getSerializable("bean");
		}

		if (walletListBean==null){
			LogUtils.d("传递过来的bean 是 null ");
			return;
		}
		billid=walletListBean.getSubBillBusinessNumber();
	}

	/**
	 * 初始化联网
	 */
	private void initNet() {
		viewModel.getWalletDetaildetail(billid);
	}

	/**
	 * 得到需要展示的数据bean
	 * @param bean
	 */
	@Override
	public void getWalletDetailDetailBean(WalletListDetailBean bean) {
		LogUtils.d(" 得到需要展示的数据bean =="+bean.getName() );

		setTextView(bean);
	}

	/**
	 * 设置数据
	 * @param bean
	 */
	private void setTextView(WalletListDetailBean bean) {

		mTvMoney.setText(bean.getAmount()+"元");
		Glide.with(this).load(bean.getLogo()).error(R.mipmap.icon_default_user)
				.into(mImgHead);

		if(TextUtils.isEmpty(bean.getName())){
			mTvUsername.setText("打零工平台");
		}else if("7777777777777".equals(bean.getName())){
			mTvUsername.setText("打零工平台");
			Glide.with(this).load(R.mipmap.ic_launcher).into(mImgHead);
		}else{
			mTvUsername.setText(bean.getName());
		}

		int  tradeType = bean.getTradeType();
		if (tradeType==2){
			mLinStatus.setVisibility(View.VISIBLE);
		}else {
			mLinStatus.setVisibility(View.GONE);
		}
		String tradeTypeStr = "";
		String[] stringArray = getResources().getStringArray(R.array.wallet_list_trade_type);
		if (tradeType<=stringArray.length){
			tradeTypeStr=stringArray[tradeType-1];
		}
		mTvTransactionType.setText(tradeTypeStr);

		int status = bean.getStatus();
		String tradeStatus = "";
		if(status==0){
			tradeStatus ="待支付";
		}else if(status==1){
			tradeStatus ="进行中";
			mTvProType.setText("已提交");
			mTvProType1.setText("正在处理中");
			mTvProType2.setText("");
			mImageType2.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.mipmap.duihaoblack));
			mTvProTime2.setText("");
			mLineNormal1.setBackgroundResource(R.color.gray_text);
		}else if(status==2){
			tradeStatus ="成功";
			mTvProType.setText("已提交");
			mTvProType1.setText("正在处理中");
			mTvProType2.setText(tradeTypeStr+tradeStatus);
		} else if(status==3){
			tradeStatus ="失败";
			mTvProType.setText("已提交");
			mTvProType1.setText("正在处理中");
			mTvProType2.setText(tradeTypeStr+tradeStatus);
		}
		mTvType.setText("交易"+tradeStatus);
		String paymentType = bean.getPaymentType();
		String paymentTypeStr = "";
		if(paymentType.equals("0")){
			paymentTypeStr ="橙子";
		}else if(paymentType.equals("1")){
			paymentTypeStr ="支付宝";
		}else if(paymentType.equals("2")){
			paymentTypeStr ="微信";
		} else if(paymentType.equals("3")){
			paymentTypeStr ="银行卡";
		}
		mTvPaymentMode.setText(paymentTypeStr);

		mTvEmarksExplain.setText("打零工平台-"+paymentTypeStr+tradeTypeStr);
		String createTimeData = bean.getCreateTime(); ;
		if(!TextUtils.isEmpty(createTimeData)){
			mTvProTime.setText(DateUtils.dateTimeFormat("yyyy-MM-dd HH:mm:ss", createTimeData));
			mTvProTime1.setText(DateUtils.dateTimeFormat("yyyy-MM-dd HH:mm:ss", createTimeData));
			mTvCreateTime.setText(DateUtils.dateTimeFormat("yyyy-MM-dd HH:mm:ss", createTimeData));
		}
		String mtime = bean.getModifyTime();
		if(!TextUtils.isEmpty(mtime)){
			mTvProTime2.setText(DateUtils.dateTimeFormat("yyyy-MM-dd HH:mm:ss", mtime));
		}
		if(status==1){
			mTvProTime2.setVisibility(View.INVISIBLE);
		}else {
			mTvProTime2.setVisibility(View.VISIBLE);
		}

	}
}
