package com.dlg.personal.oddjob.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.amap.api.maps.model.LatLng;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.base.DlgMapView;
import com.dlg.personal.oddjob.fragment.EmployeeMapInfoFragment;

/**
 * 作者：wangdakuan
 * 主要功能：雇员零工地图详情页面
 * 创建时间：2017/7/11 17:52
 */
public class EmployeeMapInfoActivity extends BaseActivity {

    private DlgMapView mMapView; //地图控件
    private FrameLayout mLayoutInfo; //零工详情填充

    private String mId;  //零工ID
    private double mYCoordinated; //经度
    private double mXCoordinate; //纬度
    private String mPostTypeName; //类型名称
    private String mPostName; //名称
    private String mPrice; //价格
    private String mJobMeterUnitName; //单位

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_employee_map_info);
        initView(savedInstanceState);
        initData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }


    /**
     * 获取上个页面数据
     */
    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            mId = bundle.getString("id");
            mYCoordinated = bundle.getDouble("yCoordinate");
            mXCoordinate = bundle.getDouble("xCoordinate");
            mPostTypeName = bundle.getString("postTypeName");
            mPostName = bundle.getString("postName");
            mPrice = bundle.getString("price");
            mJobMeterUnitName = bundle.getString("jobMeterUnitName");
        }
        LatLng latLng = new LatLng(mYCoordinated, mXCoordinate);
        StringBuffer title = new StringBuffer(mPostName);
        if (!"志愿义工".equals(mPostTypeName)) {
            title.append(" ").append(mPrice).append("元/").append(mJobMeterUnitName);
            mToolBarHelper.setToolbarTitle(title.toString());
            mMapView.addMarkerPrice(latLng, mPrice + "", mJobMeterUnitName);
        } else {
            mMapView.addMarkerPrice(latLng, 0 + "", "单");
        }
        mMapView.moveToLocation(latLng, 14);
        mToolBarHelper.setToolbarTitle(title.toString());
        mMapView.setTouchEvent(true);
        initFragment();
    }

    private void initFragment() {
        EmployeeMapInfoFragment mapInfoFragment = new EmployeeMapInfoFragment();
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            mapInfoFragment.setArguments(bundle);
        }
        addFragment(R.id.layout_info, mapInfoFragment, "mapInfoFragment");
    }


    /**
     * 初始化布局
     */
    private void initView(Bundle bundle) {
        mMapView = (DlgMapView) findViewById(R.id.map_view);
        mMapView.onCreate(bundle);
        mLayoutInfo = (FrameLayout) findViewById(R.id.layout_info);
    }
}
