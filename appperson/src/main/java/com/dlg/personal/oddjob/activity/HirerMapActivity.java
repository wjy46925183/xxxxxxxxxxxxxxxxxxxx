package com.dlg.personal.oddjob.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import android.widget.FrameLayout;

import com.dlg.data.oddjob.model.ListBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.base.DlgMapView;
import com.dlg.personal.home.fragment.HierDeskFragment;

/**
 * 作者：王进亚
 * 主要功能：雇主地图零工详情
 * 创建时间：2017/7/10 18:56
 */

public class HirerMapActivity extends BaseActivity {

    private DlgMapView mDlgMapview;
    private FrameLayout mFl;
    public double mPay;
    public double mTip;
    public double mBaoxian;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_hirer_map);
        initView();
        mDlgMapview.onCreate(savedInstanceState);
    }

    public DlgMapView getDlgMapview() {
        return mDlgMapview;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mDlgMapview.onSaveInstanceState(outState);
    }

    private void initView() {
        ListBean listBean = (ListBean) getIntent().getSerializableExtra("bean");
        mPay = getIntent().getDoubleExtra("pay", 0);
        mTip = getIntent().getDoubleExtra("tip", 0);
        mBaoxian = getIntent().getDoubleExtra("baoxian", 0);
        String businessNumber = listBean.getBusinessNumber();

        mDlgMapview = (DlgMapView) findViewById(R.id.dlg_mapview);
        HierDeskFragment hierDeskFragment =
                (HierDeskFragment) getSupportFragmentManager().findFragmentById(R.id.frament_hirer);
        hierDeskFragment.setBusinessNumber(businessNumber);
        mFl = (FrameLayout) findViewById(R.id.fl);
        setToolBarGone(mFl);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDlgMapview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDlgMapview.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDlgMapview != null)
            mDlgMapview.onDestroy();
    }
}
