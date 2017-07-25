package com.dlg.inc.wallet.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.common.string.LogUtils;
import com.common.sys.ActivityNavigator;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.dlg.data.wallet.model.BillHistoryBean;

import com.dlg.inc.R;
import com.dlg.inc.base.IncBaseActivity;
import com.dlg.inc.base.IncToolBarType;
import com.dlg.inc.wallet.adapter.IncInvoiceHistoryListAdapter;
import com.dlg.viewmodel.Wallet.InvoiceHistoryViewModel;
import com.dlg.viewmodel.Wallet.presenter.InvoiceHistoryPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：邹前坤
 * 主要功能：发票 的开票历史
 * 创建时间： 2017/7/11  11:29
 */

public class IncInvoiceHistoryActivity extends IncBaseActivity implements SwipeRefreshLayout.OnRefreshListener, InvoiceHistoryPresenter, BaseLoadMoreHeaderAdapter.OnLoadMoreListener {
	private SwipeRefreshLayout mSwipeRefresh;//
	private RecyclerView mLvInvoiceHistoryList;//展示数据的view
	private InvoiceHistoryViewModel viewModel;//
	private int currtPage=0;//第几页
	private ArrayList<BillHistoryBean>billBeenData=new ArrayList<>();//展示数据的集合
	private IncInvoiceHistoryListAdapter adapter;//适配器

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inc_page_invoice_history, IncToolBarType.Default);
		initView();
		initData();
		initNet();

	}

	/**
	 * 初始化控件
	 */
	private void initView() {
		mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);

		mLvInvoiceHistoryList = (RecyclerView) findViewById(R.id.lv_invoice_history_list);
		mLvInvoiceHistoryList.setLayoutManager(new LinearLayoutManager(mContext));
		//mLvInvoiceHistoryList.addItemDecoration(new InvoiceItemDecoration());

	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		viewModel = new InvoiceHistoryViewModel(this,this,this);
		mToolBarHelper.setToolbarRightTextVisibility(View.GONE);
		mToolBarHelper.setToolbarTitle("开票历史");

		adapter = new IncInvoiceHistoryListAdapter(this,mLvInvoiceHistoryList,billBeenData, R.layout.inc_item_invoice_history);
		mLvInvoiceHistoryList.setAdapter(adapter);
		adapter.setOnLoadMoreListener(this);
		adapter.setOnItemClickListener(new BaseLoadMoreHeaderAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				BillHistoryBean billHistoryBean = billBeenData.get(position);
				Bundle bunder = new Bundle();
				bunder.putSerializable("bean",billHistoryBean);
				ActivityNavigator.navigator().navigateTo(IncInvoiceHistoryDetailsActivity.class,bunder);
			}
		});
		mSwipeRefresh.setColorSchemeResources(R.color.orange_yellow);
		mSwipeRefresh.setOnRefreshListener(this);
		autoRefresh();
	}

	/**
	 * 自动刷新
	 */
	private void autoRefresh() {
		if(mSwipeRefresh != null){
			mSwipeRefresh.post(new Runnable() {
				@Override
				public void run() {
					mSwipeRefresh.setRefreshing(true);
				}
			});
		}
	}

	/**
	 * 初始化联网
	 */
	private void initNet() {
		if (mViewModel == null) {
			mViewModel = new InvoiceHistoryViewModel(this, this, this);
		} else {
			mViewModel.onDestroy();//取消上次的请求
		}
		viewModel.getInvoiceHistory(currtPage);
	}

	@Override
	public void requestError(String msg) {
		super.requestError(msg);
		if (mSwipeRefresh.isRefreshing()) {
			mSwipeRefresh.setRefreshing(false);
		}
	}

	@Override
	public void logInError() {
		super.logInError();
		if (mSwipeRefresh.isRefreshing()) {
			mSwipeRefresh.setRefreshing(false);
		}
	}

	@Override
	public void onRefresh() {
		currtPage=0;
		initNet();
	}

	/**
	 * 得到数据
	 * @param bean
	 */
	@Override
	public void getDataList(List<BillHistoryBean> bean) {
		LogUtils.d("得到查询数据"+bean.size());
		adapter.completeLoadMore();
		if (null!=mSwipeRefresh&&mSwipeRefresh.isRefreshing()) {
			mSwipeRefresh.setRefreshing(false);
		}
		if (bean==null||bean.size()==0&&currtPage!=0){
			return;
		}
		if (currtPage==0){
			billBeenData.clear();
		}
		billBeenData.addAll(bean);
		adapter.notifyDataSetChanged();

	}

	@Override
	public void onLoadMore() {
		currtPage++;
		initNet();
	}
}