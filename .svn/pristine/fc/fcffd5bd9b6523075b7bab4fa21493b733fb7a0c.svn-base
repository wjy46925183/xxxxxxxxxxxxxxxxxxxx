package com.dlg.personal.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.dlg.data.home.model.DictionaryBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.adapter.WorkTypeAdapter;
import com.dlg.personal.view.RecycleViewDivider;
import com.dlg.viewmodel.home.DictionaryViewModel;
import com.dlg.viewmodel.home.presenter.DictionaryPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：小明
 * 主要功能：选择零工类型列表
 * 创建时间：2017/6/29 0029 18:18
 */
public class WorkTypeActivity extends BaseActivity implements DictionaryPresenter {
    private RecyclerView recycler;
    private DictionaryViewModel dictionaryViewModel;
    private List<DictionaryBean> mDictionaryBean = new ArrayList<>();
    WorkTypeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_worktype_select);
        mToolBarHelper.setToolbarTitle("选择零工类型");
        initView();
        initData();
    }

    private void initData() {
        dictionaryViewModel = new DictionaryViewModel(this,this,this);
        dictionaryViewModel.getDictionaryData("job.type","0");
    }

    private void initView() {
        recycler = (RecyclerView) findViewById(R.id.recycler_type);
        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        recycler.addItemDecoration(new RecycleViewDivider(
                mContext, LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        adapter = new WorkTypeAdapter(mContext, recycler, mDictionaryBean, R.layout.item_worktype);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseLoadMoreHeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                String name = mDictionaryBean.get(position).getDataName();
                String nameCode = mDictionaryBean.get(position).getDataCode();
                intent.putExtra("worktype", name);
                intent.putExtra("worktypeCode", nameCode);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        dictionaryViewModel.onDestroy();
    }

    @Override
    public void getDictionary(List<DictionaryBean> dictionaryBean) {
        mDictionaryBean.clear();
        mDictionaryBean.addAll(dictionaryBean);
        adapter.notifyDataSetChanged();
    }
}
