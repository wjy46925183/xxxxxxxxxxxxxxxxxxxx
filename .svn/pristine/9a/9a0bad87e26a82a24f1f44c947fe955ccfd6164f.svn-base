package com.dlg.personal.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.utils.DateUtils;
import com.common.utils.DialogUtils;
import com.common.view.calendar.DatePickerController;
import com.common.view.calendar.DayPickerView;
import com.common.view.calendar.SimpleMonthAdapter;
import com.common.view.pickerview.DatePackerUtil;
import com.common.view.pickerview.LoopListener;
import com.common.view.pickerview.LoopView;
import com.dlg.data.oddjob.model.OddServiceItemBean;
import com.dlg.personal.R;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.app.MApp;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.oddjob.activity.SerachNearPointActivity;
import com.dlg.viewmodel.home.SubscribeViewModel;
import com.dlg.viewmodel.home.presenter.SubscribePresenter;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/18 14:03
 */

public class WillServiceActivity extends BaseActivity implements View.OnClickListener, DatePickerController, SubscribePresenter {

    /**
     * 请选择日期
     */
    private TextView mTvDate;
    private LinearLayout mLlDate;
    /**
     * 请选择时间
     */
    private TextView mTvTime;
    private LinearLayout mLlTime;
    /**
     * 请选择地址
     */
    private TextView mTvAddress;
    private LinearLayout mLlAddress;
    /**
     * 提交
     */
    private TextView mTvCommitService;
    private OddServiceItemBean mItemBean;
    private AlertDialog alertDialog;
    private String startTime;
    private String endTime;
    private int mDemandType;
    private StringBuffer sbDate = new StringBuffer();
    private String mAreaId = MApp.getInstance().getMapLocation().getAdCode();

    private String address = MApp.getInstance().getAddress();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_will_service);
        initView();
    }

    private void initView() {
        getToolBarHelper().getToolbarTitle().setText("预约");
        Intent intent = getIntent();
        if(intent != null&&intent.getExtras() != null){
            mItemBean = (OddServiceItemBean) intent.getExtras().getSerializable("oddServiceItemBean");
            mDemandType = mItemBean.getDemandType();
        }
        mTvDate = (TextView) findViewById(R.id.tv_date);
        mTvDate.setOnClickListener(this);
        mLlDate = (LinearLayout) findViewById(R.id.ll_date);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mTvTime.setOnClickListener(this);
        mLlTime = (LinearLayout) findViewById(R.id.ll_time);
        mTvAddress = (TextView) findViewById(R.id.tv_address);
        mTvAddress.setOnClickListener(this);
        mTvAddress.setText(address);
        mLlAddress = (LinearLayout) findViewById(R.id.ll_address);
        mTvCommitService = (TextView) findViewById(R.id.tv_commit_service);
        mTvCommitService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_date) {
            sbDate.setLength(0);
            showDateDialog();
        } else if (i == R.id.tv_time) {
            showTime();
        } else if (i == R.id.tv_address) {//获取地址
            String city = MApp.getInstance().getCity();
            Intent intent = new Intent(this, SerachNearPointActivity.class);
            intent.putExtra("location", TextUtils.isEmpty(address)?"":address);
            intent.putExtra("city", TextUtils.isEmpty(city)?"":city);
            startActivityForResult(intent,1);
        } else if (i == R.id.tv_commit_service) {//预约
            dialog = DialogUtils.showLoadingDialog(this);
            SubscribeViewModel subscribeViewModel = new SubscribeViewModel(this, this);
            subscribeViewModel.subscribeService(mTvDate.getText(),sbDate.toString(),
                    mTvTime.getText(),mItemBean.getId(),mAreaId,mTvAddress.getText());
        }
    }

    /**
     * 时间
     */
    private void showTime() {
        final List<String> listItem = DatePackerUtil.getTimeAllList();
        View timeView = LayoutInflater.from(this).inflate(R.layout.picker_time, null);
        LoopView loopView1 = (LoopView) timeView.findViewById(R.id.loopView1);
        LoopView loopView2 = (LoopView) timeView.findViewById(R.id.loopView2);
        LinearLayout ll_end = (LinearLayout) timeView.findViewById(R.id.ll_end);
        Button btnCancle = (Button) timeView.findViewById(R.id.btn_cancle);//关闭按钮
        Button btnOk = (Button) timeView.findViewById(R.id.btn_ok);    //确定按钮
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                if (mDemandType == 3) {
                    mTvTime.setText(startTime);
                } else {
                    mTvTime.setText(startTime + "--" + endTime);
                }

            }
        });
        loopView1.setList(listItem);
        defaultTime(loopView1);
        startTime = listItem.get(loopView1.getCurrentItem());
        loopView1.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                startTime = listItem.get(item);
            }
        });

        if (mDemandType == 3) {//计件
            ll_end.setVisibility(View.GONE);
        } else {
            ll_end.setVisibility(View.VISIBLE);
            loopView2.setList(listItem);
            endTime = listItem.get(loopView2.getCurrentItem());
            loopView2.setListener(new LoopListener() {
                @Override
                public void onItemSelect(int item) {
                    endTime = listItem.get(item);
                }
            });
        }
        alertDialog = DialogUtils.showViewDialog(mContext, timeView);
    }

    //---------------------默认时间
    private void defaultTime(LoopView loopView) {
        int hour = DateUtils.getHour();
        int minute = DateUtils.getMinute();
        if (minute < 30) {
            loopView.setCurrentItem(hour * 2 + 1);
        } else {
            loopView.setCurrentItem((hour + 1) * 2);
        }
    }
    /**
     * 日期
     */
    private void showDateDialog() {
        View dateView = LayoutInflater.from(this).inflate(R.layout.data_picker, null);
        DayPickerView dayPickerView = (DayPickerView) dateView.findViewById(R.id.pickerView);
        Button btnCancle = (Button) dateView.findViewById(R.id.btn_cancle);
        Button btnOK = (Button) dateView.findViewById(R.id.btn_ok);

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog != null && alertDialog.isShowing())
                    alertDialog.dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = sbDate.toString();
                String start = "";
                String end = "";
                if (mDemandType == 3) {
                    if(s.contains("--")){
                        Toast.makeText(mContext, "计件为单个时间，不能跨天", Toast.LENGTH_SHORT).show();
                    }else{
                        if (s.length() == 9) {
                            start = s.substring(5, 9);//06月23日--2017年06月30日
                        } else if (s.length() == 10) {
                            start = s.substring(5, 10);
                        }
                        mTvDate.setText(start);
                        alertDialog.dismiss();
                    }
                } else {
                    if (s.contains("--")) {
                        start = s.substring(5, 11);//06月23日--2017年06月30日
                        end = s.substring(s.length() - 6, s.length());
                    } else {
                        if (s.length() == 9) {
                            start = s.substring(5, 9);//06月23日--2017年06月30日
                        } else if (s.length() == 10) {
                            start = s.substring(5, 10);
                        }

                    }
                    if (TextUtils.isEmpty(end)) {
                        mTvDate.setText(start);
                    } else if (start.equals(end)) {
                        mTvDate.setText(start);
                    } else {
                        mTvDate.setText(start + "--" + end);
                    }
                    alertDialog.dismiss();
                }

            }
        });
        dayPickerView.setController(this);
        alertDialog = DialogUtils.showViewDialog(mContext, dateView);
    }

    @Override
    public int getMaxYear() {
        return Integer.parseInt(DateUtils.dateFormat("yyyy", System.currentTimeMillis() + "")) + 1;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {
        Log.i("====s====", month + "--" + day);
        if (sbDate.length() == 0 || sbDate.toString().contains("--")) {
            sbDate.setLength(0);
            sbDate.append(year + "年" + (month + 1) + "月" + day + "日");
        }
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {
        sbDate.setLength(0);
        long first = selectedDays.getFirst().getDate().getTime();
        long last = selectedDays.getLast().getDate().getTime();

        sbDate.append(DateUtils.dateFormat("yyyy年MM月dd日", Math.min(first, last) + ""))
                .append("--" + DateUtils.dateFormat("yyyy年MM月dd日", Math.max(first, last) + ""));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if (requestCode == 1){
                mTvAddress.setText(data.getStringExtra("backAddress")==null?"":data.getStringExtra("backAddress"));
                if (null!=data.getStringExtra("areaId")){
                    mAreaId = data.getStringExtra("areaId");
                }
            }
        }
    }

    @Override
    public void subscribeService(String msg) {
        Toast.makeText(mContext,"成功", Toast.LENGTH_SHORT).show();
        if(dialog != null && dialog.isShowing())
        dialog.dismiss();
        ActivityNavigator.navigator().navigateTo(HomeActivity.class);
    }

    @Override
    public void error(String errorMsg) {
        Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
