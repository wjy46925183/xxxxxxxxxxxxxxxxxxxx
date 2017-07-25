package com.dlg.personal.oddjob.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.common.utils.DialogUtils;
import com.common.view.NoScrollGridView;
import com.dlg.data.oddjob.model.ServiceListDataBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.activity.WorkTypeActivity;
import com.dlg.personal.oddjob.adapter.ReleaseServiceAdapter;
import com.dlg.viewmodel.oddjob.ReleaseServiceViewModel;
import com.dlg.viewmodel.oddjob.presenter.ReleaseServicePresenter;

import java.util.ArrayList;
import java.util.List;

import com.common.view.iamge.selector.MultiImageSelectorActivity;

/**
 * 作者：王进亚
 * 主要功能：发布服务
 * 创建时间：2017/7/14 11:28
 */

public class ReleaseServiceActivity extends BaseActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener, ReleaseServicePresenter, ReleaseServiceViewModel.ReleasePicturesCallBack {

    private TabLayout mTablayout;
    /**
     * 请输入零工名称
     */
    private EditText mTvOddSerice;
    private TextView mTypeText;
    private LinearLayout mTypeSelectLinear;
    /**
     * 我希望得到多少报酬
     */
    private EditText mPayText;
    private LinearLayout mJineLinear;
    /**
     * 天
     */
    private RadioButton mDayRbtn;
    /**
     * 小时
     */
    private RadioButton mHourRbtn;
    /**
     * 单
     */
    private RadioButton mHourDan;
    private RadioGroup mRg;
    private RelativeLayout mUnitLinear;
    /**
     * 描述
     */
    private TextView mDescTab;
    /**
     * 说一说具体的零工技能，清楚明确的描述更容易吸引雇主(5-200字)
     */
    private EditText mDescText;
    private NoScrollGridView mGridview;
    private ScrollView mScroll;
    /**
     * 确认发布
     */
    private TextView mTvRelese;
    private LinearLayout mLlRelese;
    private List<String> list = new ArrayList<>();
    private ReleaseServiceAdapter releaseServiceAdapter;
    private String worktypeCode;
    private ReleaseServiceViewModel releaseServiceViewModel;
    private String id, demandType = "1", serviceName, serviceTypeName, serviceType, serviceMeterUnit, price, serviceDescription;
    private ServiceListDataBean dataBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_release_service);
        initView();
        editService();
    }

    /**
     * 编辑服务
     */
    private void editService() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle) {
            dataBean = (ServiceListDataBean) bundle.getSerializable("beanService");
            initReleaseEdit();
        }
    }

    /**
     * 编辑数据初始化
     */
    private void initReleaseEdit() {
        id = dataBean.getId();
        mTvOddSerice.setText(dataBean.getServiceName());
        mTypeText.setText(dataBean.getServiceTypeName());
        mPayText.setText(dataBean.getPrice() + "");
        int demandType = dataBean.getDemandType();
        worktypeCode = dataBean.getServiceType();
        mTablayout.getTabAt(demandType - 1).select();
        serviceMeterUnit = dataBean.getServiceMeterUnit() + "";
        switch (serviceMeterUnit) {
            case 1 + "":
                mDayRbtn.setChecked(true);
                break;
            case 2 + "":
                mHourRbtn.setChecked(true);
                break;
            case 3 + "":
                mHourDan.setChecked(true);
                break;
        }
        if (dataBean.getImagesUrlList() != null && dataBean.getImagesUrlList().size() > 0) {
            list.addAll(dataBean.getImagesUrlList());
        }
        releaseServiceAdapter.notifyDataSetChanged();
        mDescText.setText(dataBean.getServiceDescription());
    }

    private void initView() {
        getToolBarHelper().getToolbarTitle().setText("发布服务");
        mTablayout = (TabLayout) findViewById(R.id.tablayout);
        mTvOddSerice = (EditText) findViewById(R.id.tv_odd_serice);
        mTypeText = (TextView) findViewById(R.id.type_text);
        mTypeSelectLinear = (LinearLayout) findViewById(R.id.type_select_linear);
        mPayText = (EditText) findViewById(R.id.pay_text);
        mJineLinear = (LinearLayout) findViewById(R.id.jine_linear);
        mDayRbtn = (RadioButton) findViewById(R.id.day_rbtn);
        mHourRbtn = (RadioButton) findViewById(R.id.hour_rbtn);
        mHourDan = (RadioButton) findViewById(R.id.hour_dan);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mUnitLinear = (RelativeLayout) findViewById(R.id.unit_linear);
        mDescTab = (TextView) findViewById(R.id.desc_tab);
        mDescText = (EditText) findViewById(R.id.desc_text);
        mGridview = (NoScrollGridView) findViewById(R.id.gridview);
        mScroll = (ScrollView) findViewById(R.id.scroll);
        mTvRelese = (TextView) findViewById(R.id.tv_relese);
        mTvRelese.setOnClickListener(this);
        mLlRelese = (LinearLayout) findViewById(R.id.ll_relese);

        mTablayout.addTab(mTablayout.newTab().setText("工作日").setTag(0));
        mTablayout.addTab(mTablayout.newTab().setText("双休日").setTag(1));
        mTablayout.addTab(mTablayout.newTab().setText("计件").setTag(2));
        mTablayout.setOnTabSelectedListener(this);
        mTypeSelectLinear.setOnClickListener(this);

        releaseServiceAdapter = new ReleaseServiceAdapter(this, list);
        mGridview.setAdapter(releaseServiceAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_relese) {//发布零工服务
            if (TextUtils.isEmpty(mTvOddSerice.getText())) {
                Toast.makeText(mContext, "零工名称不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(mTypeText.getText())) {
                Toast.makeText(mContext, "零工类型不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(mDescText.getText()) || mDescText.getText().length() < 5) {
                Toast.makeText(mContext, "描述不能小于5字！", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(mPayText.getText())) {
                Toast.makeText(mContext, "报酬不能为空！", Toast.LENGTH_SHORT).show();
                return;
            }
            serviceName = mTvOddSerice.getText().toString();
            serviceTypeName = mTypeText.getText().toString();
            serviceType = worktypeCode;
            serviceMeterUnit = demandType;
            price = mPayText.getText().toString();
            serviceDescription = mDescText.getText().toString();

            dialog = DialogUtils.showLoadingDialog(this);
            releaseServiceViewModel = new ReleaseServiceViewModel(this, this, this);
            releaseServiceViewModel.getBase64Imgs(list, this);
        } else if (id == R.id.type_select_linear) {//选择零工类型
            startActivityForResult(new Intent(this, WorkTypeActivity.class), 1);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int tabIndex = (int) tab.getTag();
        demandType = tabIndex + 1 + "";
        if (tabIndex == 2) {//计件
            mHourDan.setVisibility(View.VISIBLE);
            mHourDan.setChecked(true);
            mHourRbtn.setVisibility(View.GONE);
            mDayRbtn.setVisibility(View.GONE);
        } else {
            mHourDan.setVisibility(View.GONE);
            mDayRbtn.setChecked(true);
            mHourRbtn.setVisibility(View.VISIBLE);
            mDayRbtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                mTypeText.setText(data.getStringExtra("worktype"));
                worktypeCode = data.getStringExtra("worktypeCode");
            } else if (requestCode == 2) {
                // Get the result list of select image paths
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                if (list.size() + path.size() > 3) {
                    Toast.makeText(mContext, "最多只能上传三张", Toast.LENGTH_SHORT).show();
                    return;
                }
                list.addAll(path);
                releaseServiceAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 发布服务 成功
     *
     * @param msg
     */
    @Override
    public void releaseServiceSuccess(String msg) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void requestNext(String msg) {
        super.requestNext(msg);
        finish();
    }

    /**
     * 获取base64编码
     *
     * @param base64Imgs
     */
    @Override
    public void success(String base64Imgs) {
        //发布服务或者编辑服务
        releaseServiceViewModel.releaseService(id, demandType, serviceName, serviceTypeName, serviceType, serviceMeterUnit
                , price, serviceDescription,list, base64Imgs);
    }

    @Override
    public void onError(String error) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            Toast.makeText(mContext, "图片转换错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (releaseServiceViewModel != null) {
            releaseServiceViewModel.onDestroy();
        }
    }
}
