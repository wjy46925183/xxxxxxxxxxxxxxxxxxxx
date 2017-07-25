package com.dlg.personal.oddjob.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.services.help.Tip;
import com.common.map.MapManager;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.personal.oddjob.adapter.NearHotPointAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：小明
 * 主要功能：搜索附近热门地址界面
 * 创建时间：2017/7/7 0007 17:44
 */
public class SerachNearPointActivity extends BaseActivity implements View.OnClickListener, MapManager.NearPoiCallBack {
    private EditText searchText;
    private TextView cancel;
    private SwipeRefreshLayout refresh;
    private RecyclerView recycler;
    private LinearLayout layoutTitle; //头部标题总控件
    NearHotPointAdapter pointAdapter;
    private List<Tip> tip = new ArrayList<>();
    private String city;
    private String address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_search_address);
        initView();
        initData();


    }

    private void initData() {

        MapManager.searchNearPoi(this, address, city, this);

        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        pointAdapter = new NearHotPointAdapter(mContext, recycler, tip, R.layout.item_search_address);
        recycler.setAdapter(pointAdapter);
        pointAdapter.setOnItemClickListener(new BaseLoadMoreHeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("areaId", tip.get(position).getAdcode());
                intent.putExtra("backAddress", tip.get(position).getDistrict() + tip.get(position).getAddress() + tip.get(position).getName());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initView() {
        if (getIntent().getStringExtra("city")!=null){
            city = getIntent().getStringExtra("city");
        }
        else{
            city="";
        }
        if (getIntent().getStringExtra("location")!=null){
            address=getIntent().getStringExtra("location");
        }else {
            address="";
        }
        searchText = (EditText) findViewById(R.id.search_text);
        cancel = (TextView) findViewById(R.id.cancel);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        layoutTitle = (LinearLayout) findViewById(R.id.layout_title);
        setToolBarGone(layoutTitle);
        searchText.setText(getIntent().getStringExtra("location"));
        searchText.setSelection(searchText.getText().length());
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MapManager.searchNearPoi(SerachNearPointActivity.this, searchText.getText().toString().trim(), city, SerachNearPointActivity.this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cancel.setOnClickListener(this);

    }

    @Override
    public void nearPoi(List<Tip> tips) {
        tip.clear();
        tip.addAll(tips);
        pointAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

