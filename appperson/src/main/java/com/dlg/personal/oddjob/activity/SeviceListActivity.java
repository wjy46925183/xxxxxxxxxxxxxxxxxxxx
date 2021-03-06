package com.dlg.personal.oddjob.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.common.view.swipemenulistview.SwipeMenu;
import com.common.view.swipemenulistview.SwipeMenuCreator;
import com.common.view.swipemenulistview.SwipeMenuItem;
import com.common.view.swipemenulistview.SwipeMenuListView;
import com.dlg.data.home.model.ServiceShareBean;
import com.dlg.data.oddjob.model.ServiceListDataBean;
import com.dlg.personal.R;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.oddjob.adapter.ServiceListAdapter;
import com.dlg.viewmodel.common.ShareViewModel;
import com.dlg.viewmodel.home.ServiceShareViewModel;
import com.dlg.viewmodel.home.presenter.ShareServicePresenter;
import com.dlg.viewmodel.oddjob.ServiceListViewModel;
import com.dlg.viewmodel.oddjob.presenter.ServicePresenter;
import com.example.umengshare.UmengShareUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：服务列表
 * 创建时间：2017/7/14 09:45
 */

public class SeviceListActivity extends BaseActivity implements View.OnClickListener,
        ServicePresenter, SwipeRefreshLayout.OnRefreshListener,
        SwipeMenuListView.OnLoadMoreListener, ShareServicePresenter {

    private SwipeMenuListView mSwipeListView;
    private SwipeRefreshLayout mSwipeRefresh;
    private ShareViewModel mShareViewModel; //分享
    /**
     * 新增服务提交按钮
     */
    private TextView mTvAddSerice;
    private ServiceListViewModel serviceListViewModel;
    private int page = 0;
    private List<ServiceListDataBean> beanList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_sevice_list);
        initView();
        iniServiceList();
    }

    /**
     * 获取服务列表
     */
    private void iniServiceList() {
        serviceListViewModel = new ServiceListViewModel(this, this);
    }

    private void initView() {
        getToolBarHelper().getToolbarTitle().setText("我的服务");
        mSwipeListView = (SwipeMenuListView) findViewById(R.id.swipe_sevice_list);
        mSwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        mTvAddSerice = (TextView) findViewById(R.id.tv_add_serice);
        mTvAddSerice.setOnClickListener(this);

        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.orange_yellow));
        mSwipeRefresh.setOnRefreshListener(this);
        mSwipeListView.setOnLoadMoreListener(this);
        mSwipeListView.setMenuCreator(new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                switch (menu.getViewType()) {
                    case 1:
                        addCHMenu(menu);
                        break;
                }
            }
        });

        mSwipeListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {

            private ServiceShareViewModel mServiceShareViewModel;

            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {

                ServiceListDataBean dataBean = beanList.get(position);
                if (null == dataBean) {
                    return false;
                }
                SwipeMenuItem menuItem = menu.getMenuItem(index);
                if (null == menuItem) {
                    return false;
                }
                switch (menuItem.getId()) {
                    case 0://删除

                        break;
                    case 1://编辑
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("beanService", dataBean);
                        ActivityNavigator.navigator().navigateTo(ReleaseServiceActivity.class, bundle);
                        break;
                    case 2://分享
                        if (null == mShareViewModel) {
//                            mShareViewModel = new ShareViewModel(SeviceListActivity.this, SeviceListActivity.this);
                            mServiceShareViewModel = new ServiceShareViewModel(SeviceListActivity.this, SeviceListActivity.this);
                        }
                        mServiceShareViewModel.shareService(dataBean.getId());
                        break;
                }
                return false;
            }
        });
    }

    //
    public void addCHMenu(SwipeMenu menu) {
        SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
        deleteItem.setIcon(R.mipmap.delete);
        deleteItem.setTitleColor(Color.WHITE);
        deleteItem.setWidth(getResources().getDimensionPixelSize(R.dimen.delete_width));
        deleteItem.setId(0);
        // 将创建的菜单项添加进菜单中
        menu.addMenuItem(deleteItem);

        SwipeMenuItem editItem = new SwipeMenuItem(getApplicationContext());
        editItem.setIcon(R.mipmap.edit);
        editItem.setWidth(getResources().getDimensionPixelSize(R.dimen.delete_width));
        editItem.setId(1);
        editItem.setTitleColor(Color.WHITE);
        // 将创建的菜单项添加进菜单中
        menu.addMenuItem(editItem);

        SwipeMenuItem shareItem = new SwipeMenuItem(getApplicationContext());
        shareItem.setIcon(R.mipmap.share);
        shareItem.setWidth(getResources().getDimensionPixelSize(R.dimen.delete_width));
        shareItem.setId(2);
        // 将创建的菜单项添加进菜单中
        menu.addMenuItem(shareItem);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_add_serice) {
            ActivityNavigator.navigator().navigateTo(ReleaseServiceActivity.class);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (serviceListViewModel != null) {//刷新数据
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    mSwipeRefresh.setRefreshing(true);
                }
            });
            page = 0;
            serviceListViewModel.httpServiceList(page);
        }
    }

    /**
     * 刷新列表
     *
     * @param serviceListDataBeen
     */
    @Override
    public void getServiceList(List<ServiceListDataBean> serviceListDataBeen) {
        mSwipeRefresh.setRefreshing(false);
        mSwipeListView.setLoadState(false);
        if (page == 0) {
            beanList.clear();
        }
        beanList.addAll(serviceListDataBeen);

        ServiceListAdapter serviceListAdapter = new ServiceListAdapter(this, beanList);
        mSwipeListView.setAdapter(serviceListAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceListViewModel != null) {
            serviceListViewModel.onDestroy();
        }
        if (null != mShareViewModel) {
            mShareViewModel.onDestroy();
        }
    }

    @Override
    public void onRefresh() {
        page = 0;
        serviceListViewModel.onDestroy();
        serviceListViewModel.httpServiceList(page);
    }

    @Override
    public void loadMore() {
        page++;
        serviceListViewModel.onDestroy();
        serviceListViewModel.httpServiceList(page);
    }

    @Override
    public void shareService(List<ServiceShareBean> serviceShareBeen) {
        ServiceShareBean serviceShareBean = serviceShareBeen.get(0);
        UmengShareUtil.Builder(this).initListener()
                .initShareAction(serviceShareBean.getServiceTitle(), serviceShareBean.getServiceDescription()
                        , serviceShareBean.getDetailsUrl(), serviceShareBean.getUserLogo()).open();
    }

    @Override
    public void error(String errorMsg) {
        Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
