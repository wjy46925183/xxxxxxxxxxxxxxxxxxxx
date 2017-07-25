package com.dlg.personal.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.common.view.MyViewPager;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.dlg.data.home.model.DataBean;
import com.dlg.data.home.model.OrderCreateInfo;
import com.dlg.data.home.model.XYCoordinateBean;
import com.dlg.data.oddjob.model.HirerMapBean;
import com.dlg.personal.R;
import com.dlg.personal.app.MApp;
import com.dlg.personal.base.BaseFragment;
import com.dlg.personal.base.DlgMapView;
import com.dlg.personal.home.activity.HomeActivity;
import com.dlg.personal.home.adapter.EmployeeCardAdapter;
import com.dlg.personal.home.adapter.HirerDeskAdapter;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.personal.oddjob.activity.HirerMapActivity;
import com.dlg.viewmodel.home.HirerDeskViewModel;
import com.dlg.viewmodel.home.presenter.HirerDeskPresenter;
import com.dlg.viewmodel.oddjob.OddHirerMapViewModel;
import com.dlg.viewmodel.oddjob.presenter.HirerMapDetailPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：王进亚
 * 主要功能：雇员工作台
 * 创建时间：2017/7/4 09:18
 */

public class HierDeskFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener, HirerDeskPresenter, HirerMapDetailPresenter {

    private DlgMapView mMapView;
    private RelativeLayout mBossLayoutOrdertitle;
    private LinearLayout mLlToolbar;
    private CircleImageView mBossorderMine;
    private RelativeLayout mBossoderLayout;
    private TextView mBossoderTitie;
    private TextView mTvPrice;
    private ImageView mIvDownUp;
    private TextView mTvLots;
    private RecyclerView mRecyHireDesk;
    private MyViewPager mViewPagerDesk;
    private List<DataBean> datas = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();
    private boolean isVisibility = false;
    private HirerDeskViewModel mHirerDeskViewModel;
    private List<DataBean> mBeans;
    private HirerDeskAdapter mHirerDeskAdapter;
    private EmployeeCardAdapter mCardAdapter;
    private ProgressBar mPbLoading;
    private String businessNumber;
    private OddHirerMapViewModel mOddHirerMapViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_hier_desk, null);
        initView(view);
        return view;
    }

    /**
     * 初始化View
     *
     * @param view
     */
    private void initView(View view) {
        mBossLayoutOrdertitle = (RelativeLayout) view.findViewById(R.id.boss_layout_ordertitle);
        mLlToolbar = (LinearLayout) view.findViewById(R.id.ll_toolbar);
        mBossorderMine = (CircleImageView) view.findViewById(R.id.bossorder_mine);
        mBossoderLayout = (RelativeLayout) view.findViewById(R.id.bossoder_layout);
        mBossoderTitie = (TextView) view.findViewById(R.id.bossoder_titie);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mIvDownUp = (ImageView) view.findViewById(R.id.iv_down_up);
        mTvLots = (TextView) view.findViewById(R.id.tv_lots);
        mRecyHireDesk = (RecyclerView) view.findViewById(R.id.recy_hire_desk);
        mViewPagerDesk = (MyViewPager) view.findViewById(R.id.home_hire_desk_pager);
        mPbLoading = (ProgressBar) view.findViewById(R.id.pb_loading);
        mBossorderMine.setOnClickListener(this);
        /**
         * 主页
         */
        if (mActivity instanceof HomeActivity) {
            mBossoderLayout.setOnClickListener(this);
            mMapView = ((HomeActivity) mActivity).getMapView();
            mMapView.setMapClickListener(new AMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    if (isVisibility) {
                        mRecyHireDesk.setVisibility(View.GONE);
                        isVisibility = false;
                        mIvDownUp.setImageResource(R.mipmap.down);
                    } else {
                        ((HomeActivity) mActivity).checkHireDesk(false);
                    }
                }
            });
            mMapView.setOnCameraChangeFinish(null);
            goToOrders();//获取订单
            mLlToolbar.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;//触摸标题 防止地图移动
                }
            });
        }
        /**
         * 雇主零工地图详情
         */
        if (mActivity instanceof HirerMapActivity) {
            mIvDownUp.setVisibility(View.GONE);
            mTvPrice.setVisibility(View.GONE);
            mTvLots.setVisibility(View.GONE);
            mBossorderMine.setImageResource(R.mipmap.ic_black);
            mBossoderTitie.setText("loading...");
            mPbLoading.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 获取进行中的订单
     */
    public void goToOrders() {
        mHirerDeskViewModel = new HirerDeskViewModel(mContext, this);
        mPbLoading.setVisibility(View.VISIBLE);
        mHirerDeskViewModel.getHirerData();//获取正在进行中订单
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bossoder_layout) {
            if (isVisibility) {
                mRecyHireDesk.setVisibility(View.GONE);
                isVisibility = false;
                mIvDownUp.setImageResource(R.mipmap.down);
            } else {
                mRecyHireDesk.setVisibility(View.VISIBLE);
                mIvDownUp.setImageResource(R.mipmap.up);
                isVisibility = true;
            }
        } else if (id == R.id.bossorder_mine) {
            if (mActivity instanceof HomeActivity) {
                ((HomeActivity) mActivity).openDrawer();//打开抽屉
            } else {
                mActivity.finish();//关闭当前页面
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    private int curentPosition;

    @Override
    public void onPageSelected(int position) {
        if (curentPosition < position) {
            ToastUtils.getMessageToast(mContext, "进入下一个订单", "已同意").show();
        } else if (curentPosition > position) {
            ToastUtils.getMessageToast(mContext, "进入上一个订单", "已同意").show();
        }
        curentPosition = position;
        if (mInfos.size() > 0) {
            setTitle(position);
        }
        mHirerDeskAdapter.setSeletedPosition(mIntegerMap.get(mInfos.get(position).jobId));
    }

    /**
     * 设置雇主工作台 标题 地图marker
     *
     * @param position
     */
    private void setTitle(int position) {
        OrderCreateInfo orderCreateInfo = mInfos.get(position);
        mTvPrice.setText(orderCreateInfo.price + " 元/" + orderCreateInfo.meterUnitName);
        XYCoordinateBean xyCoordinateVo = orderCreateInfo.xyCoordinateVo;
        if (xyCoordinateVo != null) {
            mMapView.clearAllMarkers();
            double y = 0, x = 0;
            y = TextUtils.isEmpty(xyCoordinateVo.getUserYCoordinate()) ? 0 : Double.parseDouble(xyCoordinateVo.getUserYCoordinate());
            x = TextUtils.isEmpty(xyCoordinateVo.getUserXCoordinate()) ? 0 : Double.parseDouble(xyCoordinateVo.getUserXCoordinate());
            LatLng latLng = new LatLng(y, x);
            mMapView.addHireMarker(orderCreateInfo.logo, latLng);
            mMapView.addMyMarker(mMapView.getMyLng());//添加我的位置图标
            mMapView.moveToLocation(latLng);
        }
        setBossoderTitie(orderCreateInfo.status);
    }

    @Override
    public void onPageScrollStateChanged(int status) {

    }

    /**
     * 设置标题
     *
     * @param status
     */
    public void setBossoderTitie(int status) {
        switch (status) {
            case 30:
                mBossoderTitie.setText("待付款");
                break;
            case 22:
                mBossoderTitie.setText("等待验收");
                break;
            case 21:
                mBossoderTitie.setText("正在干活中");
                break;
            case 20:
                mBossoderTitie.setText("等待开工");
                break;
            case 10:
                mBossoderTitie.setText("等待同意");
                break;
            case 50:
                mBossoderTitie.setText("已取消");
                break;
            case 7:
                mBossoderTitie.setText("拒绝邀请");
                break;
            case 6:
                mBossoderTitie.setText("等待雇员同意");
                break;
            case -1:
                mBossoderTitie.setText("发出邀请");
                break;
            case 40:
                mBossoderTitie.setText("已完成");
                break;
        }
    }

    /**
     * 网络数据
     *
     * @param dataBeen
     */
    @Override
    public void getHirerDesk(List<DataBean> dataBeen) {
        mPbLoading.setVisibility(View.GONE);
        initRecy(dataBeen);
    }

    private List<OrderCreateInfo> mInfos = new ArrayList<>();
    private Map<String, Integer> mIntegerMap = new HashMap<>();//记录状态位置
    private Map<Integer, String> mStringMap = new HashMap<>();//记录jobid

    /**
     * 初始化列表 工作台
     */
    private void initRecy(List<DataBean> dataBeen) {
        if (dataBeen == null || dataBeen.size() == 0) {
            ((HomeActivity) mActivity).checkHireDesk(false);//没有进行中订单 进入首页

            return;
        }
        mBeans = dataBeen;
        mInfos.clear();
        mIntegerMap.clear();
        mStringMap.clear();
        mMapView.clearAllMarkers();
        mFragments.clear();
        for (int i = 0; i < dataBeen.size(); i++) {
            DataBean dataBean = dataBeen.get(i);
            for (int j = 0; j < dataBean.list.size(); j++) {
                HirerDeskCardFragment hirerDeskCardFragment = new HirerDeskCardFragment();
                OrderCreateInfo orderCreateInfo = dataBean.list.get(j);
                hirerDeskCardFragment.setOrderCreateInfo(orderCreateInfo);
                mInfos.add(orderCreateInfo);
                mFragments.add(hirerDeskCardFragment);
            }
            mIntegerMap.put(dataBean.id, i);
            mStringMap.put(i, dataBean.id);
        }
        datas.clear();
        datas.addAll(dataBeen);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        mRecyHireDesk.setLayoutManager(manager);
        mHirerDeskAdapter = new HirerDeskAdapter(mContext, mRecyHireDesk, datas, R.layout.item_hire_order);
        mRecyHireDesk.setAdapter(mHirerDeskAdapter);
        mHirerDeskAdapter.setOnItemClickListener(new BaseLoadMoreHeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = mStringMap.get(position);

                for (int i = 0; i < mInfos.size(); i++) {
                    OrderCreateInfo orderCreateInfo = mInfos.get(i);
                    String jobId = orderCreateInfo.jobId;
                    if (id.equals(jobId)) {
                        mViewPagerDesk.setCurrentItem(i, false);
                        return;
                    }
                }

            }
        });

        mViewPagerDesk.setVisibility(View.VISIBLE);
        mCardAdapter = new EmployeeCardAdapter(getChildFragmentManager(), mFragments);
        mViewPagerDesk.setAdapter(mCardAdapter);
        mCardAdapter.notifyDataSetChanged();
        mViewPagerDesk.addOnPageChangeListener(this);
        setTitle(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mHirerDeskViewModel != null)
            mHirerDeskViewModel.onDestroyView();
        if (mOddHirerMapViewModel != null)
            mOddHirerMapViewModel.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHirerDeskViewModel != null)
            mHirerDeskViewModel.onDestroyView();
        if (mOddHirerMapViewModel != null)
            mOddHirerMapViewModel.onDestroyView();
    }

    /**
     * '
     * 雇主零工地图 不是工作台
     *
     * @param businessNumber
     */
    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
        if (mActivity instanceof HirerMapActivity) {
            mMapView = ((HirerMapActivity) mActivity).getDlgMapview();
        }
        mOddHirerMapViewModel = new OddHirerMapViewModel(mContext, this);
        mOddHirerMapViewModel.getMapHirerList(businessNumber);
    }

    /**
     * 雇主零工地图详情
     *
     * @param beans
     */
    @Override
    public void getOddMap(List<HirerMapBean> beans) {
        mPbLoading.setVisibility(View.GONE);
        HirerMapBean hirerMapBean = beans.get(0);
        HirerDeskCardFragment hirerDeskCardFragment = new HirerDeskCardFragment();
        hirerDeskCardFragment.setHirerMap(hirerMapBean);

        mFragments.clear();
        mFragments.add(hirerDeskCardFragment);
        mViewPagerDesk.setVisibility(View.VISIBLE);
        mCardAdapter = new EmployeeCardAdapter(getChildFragmentManager(), mFragments);
        mViewPagerDesk.setAdapter(mCardAdapter);

        setBossoderTitie(hirerMapBean.getStatus());
        LatLng myLatLng = MApp.getInstance().getMyLatLng();
        XYCoordinateBean bean = hirerMapBean.getXyCoordinateVo();
        double y = 0, x = 0;
        if (null != bean) {
            y = TextUtils.isEmpty(bean.getJobYCoordinate()) ? 0 : Double.parseDouble(bean.getJobYCoordinate());
            x = TextUtils.isEmpty(bean.getJobXCoordinate()) ? 0 : Double.parseDouble(bean.getJobXCoordinate());
        }

        LatLng latLng = new LatLng(y, x);
        mMapView.clearAllMarkers();
        mMapView.addMyMarker(myLatLng);//添加我的marker
        mMapView.moveToLocation(latLng);
        mMapView.addHireMarker(hirerMapBean.getLogo(), latLng);
    }
}
