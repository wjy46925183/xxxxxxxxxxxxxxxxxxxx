package com.dlg.personal.home.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.adapter.EmployeeCardAdapter;
import com.dlg.personal.home.fragment.NeedOrServiceFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：雇佣他详情页面 服务和需求
 * 创建时间：2017/7/17 15:41
 */

public class HireDetailActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {
    /**
     * 返回
     */
    private ImageView mIvBack;
    /**
     * 需求
     */
    private RadioButton mRb01;
    /**
     * 服务
     */
    private RadioButton mRb02;
    private RadioGroup mMapRg;
    private RelativeLayout mRlAction;
    private LinearLayout mActivityLl;
    private ViewPager mViewPager;
    private String mUserId;
    private List<Fragment> mFragments;
    private Button mBossOrderChecked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_hire_detail);
        initView();
    }

    private void initView() {
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mRb01 = (RadioButton) findViewById(R.id.rb_01);
        mRb02 = (RadioButton) findViewById(R.id.rb_02);
        mMapRg = (RadioGroup) findViewById(R.id.map_rg);
        mRlAction = (RelativeLayout) findViewById(R.id.rl_action);
        mActivityLl = (LinearLayout) findViewById(R.id.activity_ll);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mBossOrderChecked = (Button) findViewById(R.id.boss_order_checked);
        mBossOrderChecked.setOnClickListener(this);
        mUserId = getIntent().getStringExtra("userId");

        setToolBarGone(mActivityLl);
        initViewPage();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPage() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            NeedOrServiceFragment needOrServiceFragment = new NeedOrServiceFragment();
            Bundle bundle = new Bundle();
            bundle.putString("mUserId", mUserId);
            bundle.putInt("index", i);
            needOrServiceFragment.setArguments(bundle);
            mFragments.add(needOrServiceFragment);
        }
        EmployeeCardAdapter employeeCardAdapter
                = new EmployeeCardAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(employeeCardAdapter);
        mViewPager.addOnPageChangeListener(this);
        mMapRg.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_back) {
            finish();
        } else if (i == R.id.boss_order_checked) {
            if (mRb01.isChecked()) {
                NeedOrServiceFragment needOrServiceFragment = (NeedOrServiceFragment) mFragments.get(0);
                needOrServiceFragment.selectNeedOrService();
            } else if (mRb02.isChecked()) {
                NeedOrServiceFragment needOrServiceFragment = (NeedOrServiceFragment) mFragments.get(1);
                needOrServiceFragment.selectNeedOrService();
            }
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (i == 0) {
            mRb01.setChecked(true);
        } else {
            mRb02.setChecked(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        if (checkedId == R.id.rb_01) {
            mViewPager.setCurrentItem(0, false);
        } else {
            mViewPager.setCurrentItem(1, false);
        }
    }
}
