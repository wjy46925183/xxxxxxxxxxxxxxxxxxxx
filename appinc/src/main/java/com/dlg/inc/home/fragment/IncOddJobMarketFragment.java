package com.dlg.inc.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.amap.api.maps.model.LatLng;
import com.common.string.LogUtils;
import com.dlg.data.home.model.OddJobMarketBean;
import com.dlg.inc.R;
import com.dlg.inc.app.IncMApp;
import com.dlg.inc.base.IncBaseFragment;
import com.dlg.inc.home.activity.IncHomeActivity;
import com.dlg.viewmodel.home.OddJobMarketViewModel;
import com.dlg.viewmodel.home.presenter.OddJobMarketPresenter;

import java.util.List;

/**
 * 作者：邹前坤
 * 主要功能：
 * 创建时间： 2017/7/24  15:08
 */

public class IncOddJobMarketFragment extends IncBaseFragment implements OddJobMarketPresenter, SwipeRefreshLayout.OnRefreshListener {
	private SwipeRefreshLayout mSwipeRefresh;
	private RecyclerView mLvOddjobList;
	private ImageView mBosslistMap;

	private OddJobMarketViewModel mOddJobMarketViewModel;
	private int currentPage=0;
	private String xCoordinate="";
	private String yCoordinate="";

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View inflate = inflater.inflate(R.layout.inc_fragment_odd_job_market, null);
		initView(inflate);
		initData();
		initNet();
		initLister();
		return inflate;
	}
	private void initView(View inflate) {
		mSwipeRefresh = (SwipeRefreshLayout) inflate.findViewById(R.id.swipe_refresh);
		mLvOddjobList = (RecyclerView) inflate.findViewById(R.id.lv_oddjob_list);
		mBosslistMap = (ImageView) inflate.findViewById(R.id.bosslist_map);
		mLvOddjobList.setLayoutManager(new LinearLayoutManager(mContext));

	}
	private void initData() {
		mOddJobMarketViewModel=new OddJobMarketViewModel(getActivity(),this,this);
		mSwipeRefresh.setColorSchemeResources(R.color.orange_yellow);
		mSwipeRefresh.setOnRefreshListener(this);
		autoRefresh();
		LatLng _latLng = null;
		if(mActivity instanceof IncHomeActivity){
			_latLng = ((IncHomeActivity)mActivity).getMapView().getMyLng();
		}
		if(null == _latLng){
			_latLng = IncMApp.getInstance().getMyLatLng();
		}
		if(null != _latLng){
			xCoordinate=_latLng.longitude+"";
			yCoordinate=_latLng.latitude+"";
		}


	}
	/**
	 * 自动刷新
	 */
	private void autoRefresh(){
		if(mSwipeRefresh != null){
			mSwipeRefresh.post(new Runnable() {
				@Override
				public void run() {
					mSwipeRefresh.setRefreshing(true);
				}
			});
		}
	}
	private void initNet() {
		mOddJobMarketViewModel.getOddJobMarketList(currentPage, xCoordinate, yCoordinate);
	}
	private void initLister() {
		mBosslistMap.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO  跳转到 别的fragment
				if (getParentFragment() instanceof IncOddMarketFragment) {
					((IncOddMarketFragment) getParentFragment()).removeListFragment();
				}
			}
		});
	}

	/**
	 * 查询到 零工市场的 列表
	 * @param
	 */
	@Override
	public void getOddJobMarketList(List<OddJobMarketBean> homeMapListBeans) {
		LogUtils.d(" 得到了  代理商的零工市场列表 ");

	//	mInvoiceListAdapter.completeLoadMore();
		if (null!=mSwipeRefresh&&mSwipeRefresh.isRefreshing()) {
			mSwipeRefresh.setRefreshing(false);
		}
		if (homeMapListBeans==null||homeMapListBeans.size()==0&& currentPage !=0){
			return;
		}
		if (currentPage ==0){
		 //	mInvoiceListBeanData.clear();
		}

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if(null != mOddJobMarketViewModel){
			mOddJobMarketViewModel.onDestroyView();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if(null != mOddJobMarketViewModel){
			mOddJobMarketViewModel.onDestroy();
		}
	}

	/**
	 * 刷新
	 */
	@Override
	public void onRefresh() {

		initNet();
	}
}
