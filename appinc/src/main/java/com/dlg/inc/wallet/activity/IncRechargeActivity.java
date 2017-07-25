package com.dlg.inc.wallet.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.common.alipayUtils.AlipayUtils;
import com.common.alipayUtils2_0.PayResult;
import com.common.string.LogUtils;
import com.common.sys.ActivityNavigator;
import com.dlg.data.wallet.model.RechargeBean;
import com.dlg.data.wallet.model.TypeBean;
import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncToolBarType;
import com.dlg.inc.home.view.IncToastUtils;
import com.dlg.inc.wallet.adapter.IncRechargeAdapter;

import com.dlg.inc.web.IncCMBWebViewActivity;
import com.dlg.viewmodel.Wallet.RechargeErrorViewModel;
import com.dlg.viewmodel.Wallet.RechargeViewModel;
import com.dlg.viewmodel.Wallet.presenter.RechargeErrorPresenter;
import com.dlg.viewmodel.Wallet.presenter.RechargePresenter;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gongren.com.dlg.wxapi.WechatPayUtils;

/**
 * 作者：曹伟
 * 主要功能：充值页面
 * 创建时间：2017/7/7 11:01
 */

public class IncRechargeActivity extends IncBaseActivity implements RechargePresenter, IncRechargeAdapter.getSelectInterface, RechargeErrorPresenter {
    private List<TypeBean> types = new ArrayList<>();    //充值类型选择
    private ListView mLvPayType;
    private EditText mEdMoney;
    private Button mBtRecharge;
    private IncRechargeAdapter adapter ;
    private TypedArray array ;
    private String mCount ; //充值的输入金额
    private RechargeViewModel rechargeViewModel ;
    private String payType ;    //支付类型（1.支付宝,2.微信,3.招商银行4.一卡通(快捷支付)）
    private IWXAPI iwxapi ;
    private LocalBroadcastManager manager;
    private WeichatReceiver weichatReceiver;
    private RechargeBean rechargeBeans;
	private RechargeErrorViewModel rechargeErrorViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inc_page_recharge, IncToolBarType.Default);
        mToolBarHelper.getToolbarTitle().setText("充值");
        initView();
        initData();
    }

    /**
     * 控件操作
     */
    private void initView() {

        rechargeViewModel = new RechargeViewModel(this,this);
		rechargeErrorViewModel=new RechargeErrorViewModel(this,this);
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(mContext, WechatPayUtils.APP_ID, true);
        registerWeichatPayReceiver();
        // 将该app注册到微信
        msgApi.registerApp(WechatPayUtils.APP_ID);

        iwxapi = WXAPIFactory.createWXAPI(this, WechatPayUtils.APP_ID);
        mLvPayType = (ListView) findViewById(R.id.lv_pay_type);
        mEdMoney = (EditText) findViewById(R.id.ed_money);
        mBtRecharge = (Button) findViewById(R.id.bt_recharge);
        mBtRecharge.setEnabled(false);
        mBtRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rechargeViewModel.pay(mEdMoney.getText().toString().trim(),payType);
            }
        });
    }

    /**
     * 变量操作
     */
    private void initData() {
        array = mContext.getResources().obtainTypedArray(R.array.inc_pay_type_icon);
        for(int i=0 ; i < mContext.getResources().getStringArray(R.array.inc_pay_type_name).length;i++){
            types.add(new TypeBean(mContext.getResources().getStringArray(R.array.inc_pay_type_name)[i], (BitmapDrawable) array.getDrawable(i),false));
        }
        adapter = new IncRechargeAdapter(types,mContext,this);
        mLvPayType.setAdapter(adapter);

        mEdMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mCount = s.toString().trim();
                if(null != adapter){
                    selectEidt(mCount,adapter.getPosition());
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterReceiver(weichatReceiver);
        array.recycle();
    }

    /**
     * 请求到信息，此处做调用支付宝和微信的操作
     * @param rechargeBean
     */
    @Override
    public void toMap(RechargeBean rechargeBean) {
        rechargeBeans = rechargeBean;
            switch (payType){
                case "1" :      //1.支付宝
                    String tradeId = (String) rechargeBean.getBillBusinessNumber();
                    String returnRul = rechargeBean.getReturnRul();
                    AlipayUtils alipayUtils = new AlipayUtils(mContext, tradeId, returnRul, mHandler);
                    alipayUtils.aliPay("金橙", "金橙信息",mCount);
                    break;

                case "2" :      //2.微信
                    PayReq req = new PayReq();
                    req.appId = WechatPayUtils.APP_ID;//
                    req.partnerId = rechargeBean.getMchId();
                    req.prepayId = rechargeBean.getPrepayId();
                    req.nonceStr = rechargeBean.getNonceStr();
                    req.timeStamp = rechargeBean.getTime();
                    req.packageValue = rechargeBean.getOddPackage();
                    req.sign = rechargeBean.getSign();
                    req.extData = "app Data";
                    iwxapi.sendReq(req);
                    break;

                case "4" :      //3.招商银行一卡通支付
                    Bundle bundle = new Bundle();
                    bundle.putString("url", (String) rechargeBean.getPayUrl());
                    bundle.putString("code", rechargeBean.getJsonRquestData().toString());
                    ActivityNavigator.navigator().navigateTo(IncCMBWebViewActivity.class,bundle);
                    break;
            }
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                        //  支付宝支付失败处理
						String billBusinessNumber = (String) rechargeBeans.getBillBusinessNumber();
						rechargeErrorViewModel.rechargeError(billBusinessNumber);

					}
            }
    };

    /**
     * 支付宝  微信 银行卡 选中切换的 监听
     * @param position
     */
    @Override
    public void getSelect(int position) {
        selectEidt(mCount,position);
        LogUtils.d("得到 支付 type == "+payType);
    }

    private void selectEidt(String str,int position){

        if(str != null && !"".equals(str) && position != -1){
            if(position == 2){
                payType = "4";
            }else {
                payType = (position+1)+"";
            }
            mBtRecharge.setEnabled(true);

        }else{
            mBtRecharge.setEnabled(false);
        }
    }

	/**
	 * 各种异常
	 * @param string
	 */
	@Override
	public void rechargeError(String string) {
		LogUtils.d("接收到支付失败或退出支付");
	}

	public class WeichatReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.gongren.weichatPay")) {
                int errCode = intent.getIntExtra("errCode", -1);
                if (errCode == 0) {
                    IncToastUtils.showShort(context, "充值成功");
                } else
                    IncToastUtils.showShort(context, "充值失败");

               //  outTradeNo 微信  支付异常处理字段
				String outTradeNo = rechargeBeans.getOutTradeNo();
				rechargeErrorViewModel.rechargeError(outTradeNo);
			}
        }
    }

    /**
     * 注册广播，接收微信支付的errCode
     */
    private void registerWeichatPayReceiver() {
        manager = LocalBroadcastManager.getInstance(mContext);
        weichatReceiver = new WeichatReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.gongren.weichatPay");
        manager.registerReceiver(weichatReceiver, intentFilter);
    }
}
