package com.dlg.inc.wallet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.string.LogUtils;
import com.common.sys.ActivityNavigator;
import com.dlg.data.common.url.CommonUrl;
import com.dlg.data.wallet.model.WalletBean;
import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncToolBarType;

import com.dlg.inc.web.IncDefaultWebActivity;
import com.dlg.viewmodel.Wallet.WalletViewModel;
import com.dlg.viewmodel.Wallet.presenter.WalletPresenter;
import com.dlg.viewmodel.key.H5WebType;

/**
 * Created by Administrator on 2017/7/6.
 * pro : 钱包
 */

public class IncWalletActivity extends IncBaseActivity implements WalletPresenter{
    private TextView tvAmount;  //金额，设置的时候需要把¥拼接上去
    private TextView tvCount;   //金橙数量，设置的时候需要把"个"拼接上去
    private TextView tvRecharge;    //充值
    private TextView tvWithdrawal;  //提现
    private TextView tvVoucher;     //兑换券
    private TextView tvInvoiceManage;   //发票管理
    private TextView tvSetting;     //设置
    private WalletViewModel viewModel ;
    private WalletBean bean ;
    private ImageView mIvExplain;//右上角问号 ------>  h5
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inc_page_wallet, IncToolBarType.Default);
        mToolBarHelper.setToolbarTitle("我的钱包");
        mToolBarHelper.setToolbarTextRight("明细");
        viewModel = new WalletViewModel(this,this,this);
        initView();
        addListener();
        initData();
    }

    /**
     *
     */
    private void initData() {
//        viewModel.getWalletBalance();
    }

    /**
     * 控件的初始化操作
     */
    private void initView() {
        tvAmount = (TextView) findViewById(R.id.tv_amount);
        tvCount = (TextView) findViewById(R.id.tv_count);
        tvRecharge = (TextView) findViewById(R.id.tv_recharge);
        tvWithdrawal = (TextView) findViewById(R.id.tv_withdrawal);
        tvVoucher = (TextView) findViewById(R.id.tv_voucher);
        tvInvoiceManage = (TextView) findViewById(R.id.tv_invoice_manage);
        tvSetting = (TextView) findViewById(R.id.tv_setting);
        mIvExplain= (ImageView) findViewById(R.id.iv_explain);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getWalletBalance();
    }

    /**
     * 控件的监听事件
     */
    private void addListener() {
        /**
         * 充值点击事件
         */
        tvRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNavigator.navigator().navigateTo(IncRechargeActivity.class);
            }
        });

        /**
         * 提现点击事件
         */
        tvWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean",bean);
                ActivityNavigator.navigator().navigateTo(IncWithdrawalActivity.class,bundle);
            }
        });

        /**
         * 兑换券点击事件
         */
        tvVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNavigator.navigator().navigateTo(IncExchangeActivity.class);
            }
        });

        /**
         * 发票管理点击事件
         */
        tvInvoiceManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNavigator.navigator().navigateTo(IncInvoiceActivity.class);
            }
        });

        /**
         * 设置点击事件
         */
        tvSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNavigator.navigator().navigateTo(IncWalletSettingActivity.class);
            }
        });

        /**
         * 明细的点击事件
         */
        mToolBarHelper.getToolbarTextRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityNavigator.navigator().navigateTo(IncWalletDetailActivity.class);
            }
        });
        mIvExplain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.d("点击了问号");
                Bundle bundleH5 = new Bundle();
                bundleH5.putString(IncDefaultWebActivity.TITLE_NAME, H5WebType.wallet.getName());
                bundleH5.putString(IncDefaultWebActivity.H5_URL, CommonUrl.H5_WEB_URL);
                bundleH5.putString(IncDefaultWebActivity.H5_TYPE, H5WebType.wallet.getType() + "");
                ActivityNavigator.navigator().navigateTo(IncDefaultWebActivity.class, bundleH5);
            }
        });
    }


    @Override
    public void toMap(WalletBean bean) {
        this.bean = bean ;
        tvAmount.setText("¥   " + bean.getAmount());
        tvCount.setText(Double.valueOf(bean.getAmount())*10 + "个");
    }
}
