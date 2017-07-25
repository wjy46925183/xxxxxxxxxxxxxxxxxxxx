package com.dlg.personal.oddjob.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dlg.data.oddjob.model.ListBean;
import com.dlg.data.oddjob.model.OrderStatusListBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.oddjob.fragment.hire.CancelOrderFrament;
import com.dlg.personal.oddjob.fragment.hire.DoingOrderFrament;
import com.dlg.personal.oddjob.fragment.hire.FinishOderFragment;
import com.dlg.personal.oddjob.fragment.hire.HasOrderFragment;
import com.dlg.personal.oddjob.fragment.hire.PayOderFrament;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：雇主零工处理页面
 * 创建时间：2017/7/7 13:36
 */

public class OddHirerHandleActivity extends BaseActivity {
    private OrderStatusListBean mStatusBean;
    private TextView mBossoderTitie;//标题
    private RelativeLayout mRlOddActivity;
    private RelativeLayout mBossoderLayout;
    private ImageView mToolbarBack;
    private TextView mTvPrice;
    private FrameLayout mFrameLayout;
    private int isFarmersInsurance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_odd_hirer_handle);
        mStatusBean = (OrderStatusListBean) getIntent().getSerializableExtra("statusBean");
        isFarmersInsurance = getIntent().getIntExtra("isFarmersInsurance",0);
        initView();
    }

    private void initView() {
        mRlOddActivity = (RelativeLayout) findViewById(R.id.rl_odd_activity);
        mBossoderLayout = (RelativeLayout) findViewById(R.id.bossoder_layout);
        mToolbarBack = (ImageView) findViewById(R.id.iv_back);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mFrameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        mBossoderTitie = (TextView) findViewById(R.id.bossoder_titie);

        mToolbarBack.setOnClickListener(new View.OnClickListener() {//退出界面
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setToolBarGone(mRlOddActivity);//展示自己的界面

        List<ListBean> list = mStatusBean.list;
        mTvPrice.setText(list.get(0).getPrice() + "元/" + list.get(0).getMeterUnitName());//设置价格
        switch (mStatusBean.status) {
            case 10://有人接单
                mBossoderTitie.setText("有人接单");
                HasOrderFragment hasOrderFragment = new HasOrderFragment();
                hasOrderFragment.setListBean(list);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, hasOrderFragment).commit();
                break;
            case 6:
                if(TextUtils.isEmpty(mBossoderTitie.getText())){
                    mBossoderTitie.setText("等待雇员同意");
                }
            case 7:
                if(TextUtils.isEmpty(mBossoderTitie.getText())){
                    mBossoderTitie.setText("拒绝邀请");
                }
            case 20://等待开工
                if(TextUtils.isEmpty(mBossoderTitie.getText())){
                    mBossoderTitie.setText("等待开工");
                }
            case 21://干活中
                if(TextUtils.isEmpty(mBossoderTitie.getText())){
                    mBossoderTitie.setText("干活中");
                }
            case 22://等待验收
                if(TextUtils.isEmpty(mBossoderTitie.getText())){
                    mBossoderTitie.setText("等待验收");
                }
                DoingOrderFrament doingOrderFrament = new DoingOrderFrament();
                doingOrderFrament.setListBean(list);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,doingOrderFrament).commit();
                break;
            case 30://"待付款";
                mBossoderTitie.setText("待付款");
                PayOderFrament payOderFrament = new PayOderFrament();
                payOderFrament.setListBean(list);
                payOderFrament.setIsFarmersInsurance(isFarmersInsurance);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,payOderFrament).commit();
                break;
            case 40://已完成
                mBossoderTitie.setText("已完成");
                FinishOderFragment finishOderFragment = new FinishOderFragment();
                finishOderFragment.setListBean(list);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,finishOderFragment)
                        .commit();
                break;
            case 50://已取消
                if(TextUtils.isEmpty(mBossoderTitie.getText())){
                    mBossoderTitie.setText("已取消");
                }
                CancelOrderFrament cancelOrderFrament = new CancelOrderFrament();
                cancelOrderFrament.setListBean(list);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout,cancelOrderFrament).commit();
                break;
        }

    }
}
