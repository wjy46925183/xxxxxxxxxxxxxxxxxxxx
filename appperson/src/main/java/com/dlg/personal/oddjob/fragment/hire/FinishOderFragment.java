package com.dlg.personal.oddjob.fragment.hire;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.dlg.data.oddjob.model.ListBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseFragment;
import com.dlg.personal.oddjob.activity.HirerMapActivity;
import com.dlg.personal.oddjob.adapter.FinishOrderAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/10 10:28
 */

public class FinishOderFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRecyFinish;
    private List<ListBean> mListBeen = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate =
                LayoutInflater.from(mActivity).inflate(R.layout.fragment_finish, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mRecyFinish = (RecyclerView) view.findViewById(R.id.recy_finish);

        mRecyFinish.setLayoutManager(new LinearLayoutManager(mContext));
        FinishOrderAdapter finishOrderAdapter =
                new FinishOrderAdapter(mActivity, mRecyFinish, mListBeen, R.layout.item_finish_order);
        mRecyFinish.setAdapter(finishOrderAdapter);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.orange_yellow));
        mSwipeRefresh.setOnRefreshListener(this);

        finishOrderAdapter.setOnItemClickListener(new BaseLoadMoreHeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mActivity, HirerMapActivity.class);
                intent.putExtra("bean",mListBeen.get(position));
                startActivity(intent);
            }
        });
    }

    /**
     * 填充数据
     * @param listBean
     */
    public void setListBean(List<ListBean> listBean){
        mListBeen.clear();
        mListBeen.addAll(listBean);
    }

    @Override
    public void onRefresh() {
        mSwipeRefresh.setRefreshing(false);
    }
}
