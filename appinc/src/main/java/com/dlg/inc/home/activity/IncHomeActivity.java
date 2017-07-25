package com.dlg.inc.home.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.model.LatLng;
import com.common.map.MapManager;
import com.common.sys.ActivityNavigator;
import com.common.utils.NotificationsUtils;
import com.dlg.data.model.MyMapLocation;
import com.dlg.inc.R;
import com.dlg.inc.app.IncMApp;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncDlgMapView;
import com.dlg.inc.base.IncHomeToolBarHelper;
import com.dlg.inc.base.IncToolBarType;
import com.dlg.inc.home.fragment.IncHirerHomeFragment;
import com.dlg.inc.home.fragment.IncOddMarketFragment;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.key.UserRole;

import static com.dlg.viewmodel.key.AppKey.CacheKey.MAP_LOCATION_LATLNG;

/**
 * Created by Wangjinya on 2017/6/19.
 * 主页
 */
public class IncHomeActivity extends IncBaseActivity {
    private LinearLayout ll_content;
    private IncDlgMapView mMapView; //地图控件
    private DrawerLayout mDrawerLayout; //侧滑控件
    private RadioGroup mMapGroup; //切换按钮
    private RadioButton mBtnOddMarket; //零工市场按钮

    private IncHirerHomeFragment mHirerHomeFragment; //企业
    private IncOddMarketFragment mIncOddMarketFragment; //代理商
    private LatLng mLatLng;
    private MyMapLocation mMapLocation; //当前定位的数据
    private ProgressBar mPbLoading;

    private long exitTime; //退出时间判断


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotificationsUtils.requestPermissionNotification(this);//申请通知权限
        setContentView(R.layout.inc_page_home, IncToolBarType.Home);
        mMapLocation = (MyMapLocation) mACache.getAsObject(AppKey.CacheKey.MAP_LOCATION_LATLNG);
        initView(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }


    private void initView(Bundle savedInstanceState) {
        ll_content = (LinearLayout) findViewById(R.id.layout_main);
        mMapView = (IncDlgMapView) findViewById(R.id.map_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        mMapGroup = (RadioGroup) findViewById(R.id.map_group);
        mBtnOddMarket = (RadioButton) findViewById(R.id.btn_odd_market);
        if (null == mACache.getAsObject(AppKey.CacheKey.USER_ROLE)) {
            mACache.put(AppKey.CacheKey.USER_ROLE, UserRole.employee.getRole());
        }
        mMapView.onCreate(savedInstanceState);
        initMap();
        initToolbar();//初始化标题
        initContent();
        mMapGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (mContext instanceof IncHomeActivity) {
                    if (mBtnOddMarket.isChecked()) {
                        checkAgent();
                    }else {
                        checkHirer();
                    }
                }
            }
        });
    }

    /**
     * 初始化地图
     */
    private void initMap() {
        /**
         * 如果在引导页定位成功了就不需要在进行定位
         */
        if (null != mMapLocation) {
            IncMApp.getInstance().setMapLocation(mMapLocation);
            moveToLocation(mMapLocation);
        } else {
            MapManager.startLocation(this, new AMapLocationListener() {
                @Override
                public void onLocationChanged(AMapLocation aMapLocation) {
                    MapManager.stopLocation();
                    MyMapLocation mapLocation = new MyMapLocation();
                    mapLocation.setAdCode(aMapLocation.getAdCode());
                    mapLocation.setLatitude(aMapLocation.getLatitude());
                    mapLocation.setLongitude(aMapLocation.getLongitude());
                    mapLocation.setProvince(aMapLocation.getProvince());
                    mapLocation.setCity(aMapLocation.getCity());
                    mapLocation.setDistrict(aMapLocation.getDistrict());
                    mapLocation.setCityCode(aMapLocation.getCityCode());
                    mapLocation.setAdCode(aMapLocation.getAdCode());
                    mapLocation.setAddress(aMapLocation.getAddress());
                    mapLocation.setCountry(aMapLocation.getCountry());
                    mapLocation.setRoad(aMapLocation.getRoad());
                    mapLocation.setPoiName(aMapLocation.getPoiName());
                    mMapLocation = mapLocation;
                    IncMApp.getInstance().setMapLocation(mMapLocation);
                    moveToLocation(mapLocation);
                    mACache.put(MAP_LOCATION_LATLNG, mapLocation);
                }
            });
        }
    }

    /**
     * 移动到定位位置
     */
    private void moveToLocation(MyMapLocation mMapLocation) {
        mLatLng = new LatLng(mMapLocation.getLatitude(), mMapLocation.getLongitude());
        mMapView.moveToLocation(mLatLng);
        mMapView.setMyLng(mLatLng);
    }

    /**
     * 初始化主页面
     */
    private void initContent() {
        checkHirer();
    }

    /**
     * 切换企业
     */
    private void checkHirer() {
        mACache.put(AppKey.CacheKey.USER_ROLE, UserRole.enterprise.getRole());
//        setToolBarVisibility(ll_content);
        if (mHirerHomeFragment == null)
            mHirerHomeFragment = new IncHirerHomeFragment();
        mMapView.toMyLocation();
        addFragment(R.id.content_frame, mHirerHomeFragment, "hirerHomeFragment");
    }

    /**
     * 切换代理商
     */
    private void checkAgent() {
        mACache.put(AppKey.CacheKey.USER_ROLE, UserRole.agent.getRole());
        setToolBarVisibility(ll_content);
        if (mIncOddMarketFragment == null)
            mIncOddMarketFragment = new IncOddMarketFragment();
        mMapView.toMyLocation();
        addFragment(R.id.content_frame, mIncOddMarketFragment, "incOddMarketFragment");
    }

    /**
     * 标题初始化
     */
    private void initToolbar() {
        setToolBarVisibility(ll_content);
        mPbLoading = ((IncHomeToolBarHelper) mToolBarHelper).getPb();
        ((IncHomeToolBarHelper) mToolBarHelper).setToolbarRightIamgOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2017/6/10 消息点击事件
            }
        });
        ((IncHomeToolBarHelper) mToolBarHelper).setToolbarLeftIamgeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != mDrawerLayout) {
                    openDrawer();
                }
            }
        });
    }

    /**
     * 打开抽屉
     */
    public void openDrawer() {
        mDrawerLayout.openDrawer(Gravity.LEFT);//打开抽屉
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (mHirerHomeFragment != null)
            mHirerHomeFragment.setFromDesk(false);//从其他的界面返回的时候 重新初始化 boolean值
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

    public IncDlgMapView getMapView() {
        return mMapView;
    }

    /**
     * 设置切换按钮
     * @param visibility
     */
    public void setMapGroupVisibility(int visibility) {
        mMapGroup.setVisibility(visibility);
    }

    /**
     * 是否加载
     *
     * @param isLoading
     */
    public void setLoading(boolean isLoading) {
        if (isLoading) {
            mPbLoading.setVisibility(View.VISIBLE);
        } else {
            mPbLoading.setVisibility(View.GONE);
        }
    }

    /**
     * 是否切换雇主工作台
     *
     * @param isCheck
     */
    public void checkHireDesk(boolean isCheck) {
        if (isCheck) {
            setToolBarGone(ll_content);
            mMapView.clearAllMarkers();
            //  addFragment(R.id.content_frame, new IncHierDeskFragment(), "hierDesk");
            Toast.makeText(this, "是否切换雇主工作台 ，完善后请销毁土司", Toast.LENGTH_SHORT).show();
        } else {
            mHirerHomeFragment.setFromDesk(true);
            checkHirer();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(mContext, "再按一次退出打零工", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityNavigator.navigator().removeAll();
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}