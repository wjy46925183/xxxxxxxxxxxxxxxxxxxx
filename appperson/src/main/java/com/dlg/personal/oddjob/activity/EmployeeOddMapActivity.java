package com.dlg.personal.oddjob.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dlg.personal.R;
import com.dlg.personal.app.MApp;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.base.DlgMapView;
import com.dlg.personal.base.ToolBarType;
import com.dlg.personal.home.activity.HomeActivity;
import com.dlg.personal.home.fragment.EmployeeOngoingFragment;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.key.UserRole;

/**
 * 作者：wangdakuan
 * 主要功能：雇员零工订单地图详情工作台页面
 * 创建时间：2017/7/10 20:45
 */
public class EmployeeOddMapActivity extends BaseActivity {

    private DlgMapView mMapView; //地图控件
    private boolean isStartPage = false; //是否直接从引导页进入

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_employee_odd_map, ToolBarType.NO);
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            isStartPage = bundle.getBoolean("isStartPage", false);
        }
        if (isStartPage) {
            mACache.put(AppKey.CacheKey.USER_ROLE, UserRole.employee.getRole());
        }
        initView(savedInstanceState);
        initFragment();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 初始化布局
     */
    private void initView(Bundle bundle) {
        mMapView = (DlgMapView) findViewById(R.id.map_view);
        mMapView.onCreate(bundle);
        mMapView.setMyLng(MApp.getInstance().getMyLatLng());
    }

    /**
     * 初始化添加Fragment到布局中
     */
    private void initFragment() {
        EmployeeOngoingFragment ongoingFragment = new EmployeeOngoingFragment();
        ongoingFragment.setArguments(getIntent().getExtras());
        addFragment(R.id.employee_ongoing, ongoingFragment, "employee_ongoing");
    }

    public DlgMapView getMapView() {
        return mMapView;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMapView != null) {
            mMapView.onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isStartPage) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }
}
