package com.dlg.personal.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dlg.data.home.model.HistoryBean;
import com.dlg.personal.R;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.adapter.ConditionHistoryAdapter;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.personal.view.RecycleViewDivider;
import com.dlg.viewmodel.home.AddJobConditionViewModel;
import com.dlg.viewmodel.home.DeletHistoryViewModel;
import com.dlg.viewmodel.home.HistoryViewModel;
import com.dlg.viewmodel.home.presenter.AddJobConditionPresenter;
import com.dlg.viewmodel.home.presenter.DeletHistoryPresenter;
import com.dlg.viewmodel.home.presenter.HistoryPresenter;
import com.dlg.viewmodel.key.AppKey;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：小明
 * 主要功能：你想找什么样的零工页面
 * 创建时间：2017/6/29 0029 16:34
 */
public class ConditionsSearchActivity extends BaseActivity implements View.OnClickListener, HistoryPresenter, AddJobConditionPresenter,DeletHistoryPresenter {
    TabLayout tablayout;
    EditText workName;
    LinearLayout layoutWorkerType;
    TextView workType;//零工类型
    LinearLayout llDownAcceptPay;
    EditText lowPay;//最低支付
    LinearLayout layoutZhengshu;
    TextView unitDay;//天
    TextView unitTime;//小时
    TextView unitNum;//单
    Button btnComit;//搜索
    RecyclerView recycler;

    String jobType; //选择的类型名称
    String jobTypeCode; //选择的类型code
    String postName; //零工名称
    String price; //价格
    int jobMeterUnit; //单位
    int demandType = 1;//工种类型

    private String[] tabArray = {"工作日", "双休日", "计件"};
    private HistoryViewModel historyViewModel;
    private AddJobConditionViewModel addJobViewModel;
    private ConditionHistoryAdapter conditionHistoryAdapter;
    private List<HistoryBean> beanhis = new ArrayList<>(); //历史数据
    private TextView history;
    private DeletHistoryViewModel deletViewModel;
    int position = 0;
    private final int TAG_WORK_TYPE = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_condition_search);
        initView();
    }

    private void initView() {
        mToolBarHelper.setToolbarTitle("你想找什么样的零工");
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        workName = (EditText) findViewById(R.id.work_name);
        layoutWorkerType = (LinearLayout) findViewById(R.id.layout_worker_type);
        workType = (TextView) findViewById(R.id.work_type);
        llDownAcceptPay = (LinearLayout) findViewById(R.id.ll_down_accept_pay);
        lowPay = (EditText) findViewById(R.id.low_pay);
        layoutZhengshu = (LinearLayout) findViewById(R.id.layout_zhengshu);
        unitDay = (TextView) findViewById(R.id.unit_day);
        unitTime = (TextView) findViewById(R.id.unit_time);
        unitNum = (TextView) findViewById(R.id.unit_num);
        btnComit = (Button) findViewById(R.id.btn_comit);
        recycler = (RecyclerView) findViewById(R.id.recycler);

        history = (TextView) findViewById(R.id.history);
        layoutWorkerType.setOnClickListener(this);
        unitDay.setOnClickListener(this);
        unitTime.setOnClickListener(this);
        btnComit.setOnClickListener(this);
        initTab();
        initHistory();
    }


    private void initHistory() {
        if (null == historyViewModel) {
            historyViewModel = new HistoryViewModel(mContext, this);
        }
        if (null==deletViewModel){
            deletViewModel=new DeletHistoryViewModel(mContext,this,this);
        }
        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        recycler.addItemDecoration(new RecycleViewDivider(
                mContext, LinearLayoutManager.VERTICAL, R.drawable.divider_line));
        historyViewModel.getHistoryData(mACache.getAsString(AppKey.CacheKey.MY_USER_ID));
        conditionHistoryAdapter = new ConditionHistoryAdapter(mContext, recycler, beanhis, R.layout.item_condition_history);
        recycler.setAdapter(conditionHistoryAdapter);

    }
    /**
     * Tab更改监听*/
    private void initTab() {
        for (int i = 0; i < tabArray.length; i++) {
            TabLayout.Tab tab = tablayout.newTab().setText(tabArray[i]);
            tab.setTag(i);
            tablayout.addTab(tab);
        }
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if ((int) (tab.getTag()) == 2) {
                    unitDay.setVisibility(View.GONE);
                    unitTime.setVisibility(View.GONE);
                    unitNum.setVisibility(View.VISIBLE);
                } else {
                    unitDay.setVisibility(View.VISIBLE);
                    unitNum.setVisibility(View.GONE);
                    unitTime.setVisibility(View.VISIBLE);
                }
                demandType = (int) (tab.getTag()) + 1;
                if (demandType == 3) {
                    jobMeterUnit = 3;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.layout_worker_type) {
            startActivityForResult(new Intent(ConditionsSearchActivity.this, WorkTypeActivity.class), TAG_WORK_TYPE);
        }
        if (id == R.id.btn_comit) {
            checkSearch();
        }
        if (id == R.id.unit_day) {
            unitDay.setTextColor(getResources().getColor(R.color.tab_selected_textcolor));
            unitDay.setBackground(getResources().getDrawable(R.drawable.circle_empty_yellow));
            unitTime.setTextColor(getResources().getColor(R.color.gray_textcolor));
            unitTime.setBackground(getResources().getDrawable(R.color.white));
            jobMeterUnit = 1;
        }
        if (id == R.id.unit_time) {
            unitTime.setTextColor(getResources().getColor(R.color.tab_selected_textcolor));
            unitTime.setBackground(getResources().getDrawable(R.drawable.circle_empty_yellow));
            unitDay.setTextColor(getResources().getColor(R.color.gray_textcolor));
            unitDay.setBackground(getResources().getDrawable(R.color.white));
            jobMeterUnit = 2;
        }
    }

    /**
     * 提交按钮检索*/
    private void checkSearch() {
        postName = workName.getText().toString().trim();
        price = lowPay.getText().toString().trim();
        if (!TextUtils.isEmpty(postName) || !TextUtils.isEmpty(jobType) || !TextUtils.isEmpty(price)) {
            if (null == addJobViewModel) {
                addJobViewModel = new AddJobConditionViewModel(mContext, this, this);
            }
            addJobViewModel.getDictionaryData(mACache.getAsString(AppKey.CacheKey.MY_USER_ID), jobType,jobTypeCode, postName, demandType, price, jobMeterUnit);
        } else {
            ToastUtils.showShort(mContext, "请选择一个搜索标签");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == RESULT_OK) {
            if (requestCode==TAG_WORK_TYPE){
                jobType = data.getStringExtra("worktype");
                if (jobType.equals("志愿义工")){
                    llDownAcceptPay.setVisibility(View.GONE);
                    layoutZhengshu.setVisibility(View.GONE);
                }else {
                    llDownAcceptPay.setVisibility(View.VISIBLE);
                    layoutZhengshu.setVisibility(View.VISIBLE);
                }
                jobTypeCode = data.getStringExtra("worktypeCode");
                workType.setText(jobType);
            }

        }
    }

    /**
     * 历史数据返回*/
    @Override
    public void getHistoryList(List<HistoryBean> historyBean) {
        beanhis.clear();
        if(null != historyBean){
            beanhis.addAll(historyBean);
        }
        conditionHistoryAdapter.notifyDataSetChanged();
        if (beanhis.size() == 0) {
            history.setVisibility(View.GONE);
        }
    }

    /**
     * 增加零工条件返回*/
    @Override
    public void getAddJobCondition(List<String> b) {
        Bundle bundle=new Bundle();
        bundle.putString("conditionId",b.get(0));
        bundle.putInt("page",2);
        ActivityNavigator.navigator().navigateTo(SearchActivity.class,bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != addJobViewModel){
            addJobViewModel.onDestroy();
        }
        if(null != historyViewModel){
            historyViewModel.onDestroy();
        }
        if (null!=deletViewModel){
            deletViewModel.onDestroy();
        }
    }
    //TODO 模拟删除零工历史
    public void deletHistory(int position){
        this.position=position;
        if (null!=deletViewModel){
            deletViewModel=new DeletHistoryViewModel(mContext,this,this);
        }
        deletViewModel.deletHistory(beanhis.get(position).getId(),mACache.getAsString(AppKey.CacheKey.MY_USER_ID));


    }

    /**
     * 删除历史返回*/
    @Override
    public void delethistory(List<String> delethistory) {
        beanhis.remove(position);
        conditionHistoryAdapter.notifyDataSetChanged();
        ToastUtils.showShort(mContext,"删除零工历史成功");
    }
}
