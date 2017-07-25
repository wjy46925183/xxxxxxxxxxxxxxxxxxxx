package com.dlg.personal.oddjob.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.utils.DateUtils;
import com.common.utils.DialogUtils;
import com.common.utils.StringUtils;
import com.common.view.calendar.DatePickerController;
import com.common.view.calendar.DayPickerView;
import com.common.view.calendar.SimpleMonthAdapter;
import com.common.view.pickerview.DatePackerUtil;
import com.common.view.pickerview.LoopListener;
import com.common.view.pickerview.LoopView;
import com.dlg.data.model.MyMapLocation;
import com.dlg.data.oddjob.model.OddHirerBean;
import com.dlg.data.oddjob.model.ReleaseJobBean;
import com.dlg.personal.R;
import com.dlg.personal.app.MApp;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.activity.WorkTypeActivity;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.oddjob.ReleaseJobViewModel;
import com.dlg.viewmodel.oddjob.presenter.EditJobPresenter;
import com.dlg.viewmodel.oddjob.presenter.ReleaseJobPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：小明
 * 主要功能：发布和编辑零工页面
 * 创建时间：2017/7/4 0004 15:56
 */
public class ReleaseOrEditActivity extends BaseActivity implements View.OnClickListener, DatePickerController, ReleaseJobPresenter, EditJobPresenter {
    private final int TAG_ADDRESS = 2;
    private final int TAG_WORK_TYPE = 1;
    private TabLayout tablayout;
    private EditText workText;          //名称
    private LinearLayout typeLayout;
    private TextView typeText;          //类型
    private TextView descTab;
    private EditText describeText;      //描述
    private EditText numText;           //人数
    private LinearLayout jineLinear;
    private EditText salaryText;        //报酬
    private RelativeLayout unitLinear;
    private RadioButton rbDay;          //天
    private RadioButton rbHour;         //小时
    private RadioButton rbDan;          //单
    private LinearLayout dateLayout;
    private TextView dateText;          //日期
    private LinearLayout timeLayout;
    private TextView timeText;          //时间
    private RelativeLayout addressLayout;
    private TextView addressTab;
    private TextView addressText;       //地址
    private ImageView addRightImg;
    private LinearLayout baoxianLinear;
    private CheckBox checkbox;          //保险
    private TextView commitBtn;         //发布
    private RadioGroup rgDemandType;
    private final String[] tabArray = {"工作日", "双休日", "计件"};
    private List<String> listItem = new ArrayList<>();  //时间选择控件里的时间值
    private ReleaseJobBean bean;        //发布服务存储
    private AlertDialog alertDialog;    //时间选择的Dialog
    private LayoutInflater inflater;    //加载独立布局
    private long startDate;             //开始日期
    private long endDate;               //结束日期
    private String startJJTime;         //计件时的用工开始时间
    private String selectedDate;        //计件类型选中日期的那一天
    private String startTime;           //用工开始时间
    private String endTime;             //用工结束时间
    private String dateString = null;   //存储用工日期
    private boolean isToday = false;    //计件时，判断选择的日期，是否是当天
    private boolean isOneDay = false;   //判断是否只有一天
    private String postType;            //零工类型code
    private String time;                //获取的整行时间
    private String date;                //获取的整行日期
    private String id = "";
    OddHirerBean editBean;


    private int demandType = 1;         //1工作日,2双休日,3计件
    private int jobMeterUnit = 1;       //零工计量单位(1.天,2.时,3.单)
    private boolean isYG = false;       //是否是义工
    int num = 0;                        //防止二次以上选择 导致判断逻辑的错误
    private ReleaseJobViewModel viewModel;
    PopupWindow window;
    private TextView dailiService;
    MyMapLocation location;

    private String mCityId;
    private String mProId;
    private String mAreaId;
    private String mCity;
    private String mPro;
    private String mArea;
    private String mAdr;
    private View jineLine2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_release_or_edit);
        initView();
    }

    private void initView() {
        bean = new ReleaseJobBean();

        inflater = LayoutInflater.from(mContext);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        workText = (EditText) findViewById(R.id.work_text);
        typeLayout = (LinearLayout) findViewById(R.id.type_layout);
        typeText = (TextView) findViewById(R.id.type_text);
        descTab = (TextView) findViewById(R.id.desc_tab);
        describeText = (EditText) findViewById(R.id.describe_text);
        numText = (EditText) findViewById(R.id.num_text);
        jineLinear = (LinearLayout) findViewById(R.id.jine_linear);
        salaryText = (EditText) findViewById(R.id.salary_text);
        unitLinear = (RelativeLayout) findViewById(R.id.unit_linear);
        rbDay = (RadioButton) findViewById(R.id.rb_day);
        rbHour = (RadioButton) findViewById(R.id.rb_hour);
        rbDan = (RadioButton) findViewById(R.id.rb_dan);
        dateLayout = (LinearLayout) findViewById(R.id.date_layout);
        dateText = (TextView) findViewById(R.id.date_text);
        timeLayout = (LinearLayout) findViewById(R.id.time_layout);
        timeText = (TextView) findViewById(R.id.time_text);
        addressLayout = (RelativeLayout) findViewById(R.id.address_layout);
        addressTab = (TextView) findViewById(R.id.address_tab);
        addressText = (TextView) findViewById(R.id.address_text);
        addRightImg = (ImageView) findViewById(R.id.add_right_img);
        baoxianLinear = (LinearLayout) findViewById(R.id.baoxian_linear);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
        commitBtn = (TextView) findViewById(R.id.commit_btn);
        rgDemandType = (RadioGroup) findViewById(R.id.rg_demand_type);
        dailiService = (TextView) findViewById(R.id.daili_service);
        jineLine2 =  findViewById(R.id.jine_line2);
        typeLayout.setOnClickListener(this);
        addressLayout.setOnClickListener(this);
        timeLayout.setOnClickListener(this);
        dateLayout.setOnClickListener(this);
        dailiService.setOnClickListener(this);
        commitBtn.setOnClickListener(this);
        location = MApp.getInstance().getMapLocation();//获取地址
        if (null != location) {
            addressText.setText(location.getAddress());
            mCityId = location.getCityCode();
            mAreaId = location.getAdCode();

        }
        if (null == viewModel) {
            viewModel = new ReleaseJobViewModel(mContext, this, this);
        }
        mToolBarHelper.setToolbarTitle("发布服务");
        initTab();
        initDemandType();
        editBean = (OddHirerBean) getIntent().getSerializableExtra("editBean");
        if (editBean != null) {
            initBean();
        }



    }

    /**
     * 从编辑页面来的填充数据
     */
    private void initBean() {
        workText.setText(editBean.postName);
        typeText.setText(editBean.postTypeName);
        describeText.setText(editBean.taskDescription);
        numText.setText(editBean.recruitNumber+"");

        demandType = editBean.demandType;
        if (editBean.postTypeName.equals("志愿义工")) {
            isYG = true;
            jineLinear.setVisibility(View.GONE);
            jineLine2.setVisibility(View.GONE);
        } else {
            jineLine2.setVisibility(View.VISIBLE);
            jineLinear.setVisibility(View.VISIBLE);
            salaryText.setText(editBean.price +"");
            isYG = false;
        }
        if (demandType >= 1) {
            tablayout.getTabAt(demandType - 1).select();
        }
        if (editBean.jobMeterUnit == 1) {
            rbDay.setChecked(true);
        }
        if (editBean.jobMeterUnit == 2) {
            rbHour.setChecked(true);
        }
        if (editBean.isFarmersInsurance == 1) {
            checkbox.setChecked(true);
        } else {
            checkbox.setChecked(false);
        }
        if (editBean.demandType == 3) {
            dateText.setText(getData(editBean.startMonth, editBean.startDay, editBean.startYear));
            String time = StringUtils.get2Str(editBean.startHour) + ":" + StringUtils.get2Str(editBean.startMinute);
            isOneDay = true;
            isToday = true;
            timeText.setText(time);
        } else {
            String startData = getData(editBean.startMonth, editBean.startDay, editBean.startYear);
            String endData = getData(editBean.endMonth, editBean.endDay, editBean.endYear);
            String startTime = StringUtils.get2Str(editBean.startHour) + ":" + StringUtils.get2Str(editBean.startMinute);
            String endTime = StringUtils.get2Str(editBean.endHour) + ":" + StringUtils.get2Str(editBean.endMinute);
            if (startData.equals(endData)) {//如果是单个一天的话，我们只需要显示出来其中的
                isOneDay = true;
                isToday = true;
                dateText.setText(startData);
            } else {
                isOneDay = false;
                isToday = false;
                dateText.setText(startData + "-" + endData);
            }
            timeText.setText(startTime + "-" + endTime);
        }
        mCity = editBean.cityName;
        mPro = editBean.provinceName;
        mArea = editBean.areaName;
        mAdr = editBean.workAddress;
        addressText.setText(mPro + mCity + mArea + mAdr);

    }

    public String getData(int month, int day, int year) {
        String selectedDate;
        if (month < 9 && day < 10)
            selectedDate = year + ".0" + (month) + ".0" + day;
        else if (month >= 9 && day < 10)
            selectedDate = year + "." + (month) + ".0" + day;
        else if (month < 9 && day >= 10)
            selectedDate = year + ".0" + (month) + "." + day;
        else
            selectedDate = year + "." + (month) + "." + day;
        return selectedDate;
    }

    /**
     * Tab初始化Tab的选择
     */
    private void initTab() {
        for (int i = 0; i < tabArray.length; i++) {
            rbDay.setChecked(true);
            TabLayout.Tab tab = tablayout.newTab().setText(tabArray[i]);
            tab.setTag(i);
            tablayout.addTab(tab);
        }
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch ((int) tab.getTag()) {
                    case 0://工作日
                        demandType = 1;
                        rbDay.setVisibility(View.VISIBLE);
                        rbHour.setVisibility(View.VISIBLE);
                        rbDay.setChecked(true);
                        rbDan.setVisibility(View.GONE);
                        baoxianLinear.setVisibility(View.VISIBLE);
                        timeText.setText("");
                        dateText.setText("");
                        break;
                    case 1://双休日
                        demandType = 2;
                        rbDay.setVisibility(View.VISIBLE);
                        rbHour.setVisibility(View.VISIBLE);
                        rbDay.setChecked(true);
                        rbDan.setVisibility(View.GONE);
                        baoxianLinear.setVisibility(View.VISIBLE);
                        timeText.setText("");
                        dateText.setText("");
                        break;
                    case 2://计件
                        demandType = 3;
                        jobMeterUnit = 3;
                        rbDay.setVisibility(View.GONE);
                        rbHour.setVisibility(View.GONE);
                        rbDan.setVisibility(View.VISIBLE);
                        rbDan.setChecked(true);
                        baoxianLinear.setVisibility(View.GONE);
                        timeText.setText("");
                        dateText.setText("");
                        break;
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

    /**
     * 天 小时 单  选择器
     */
    private void initDemandType() {
        rgDemandType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb_day) {
                    jobMeterUnit = 1;
                    rbDay.setTextColor(getResources().getColor(R.color.yellow_textcolor));
                    rbHour.setTextColor(getResources().getColor(R.color.text_colors_grey));
                } else {
                    jobMeterUnit = 2;
                    rbDay.setTextColor(getResources().getColor(R.color.text_colors_grey));
                    rbHour.setTextColor(getResources().getColor(R.color.yellow_textcolor));
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.type_layout) {//工种
            Intent intent = new Intent(this, WorkTypeActivity.class);
            startActivityForResult(intent, TAG_WORK_TYPE);
        }
        if (v.getId() == R.id.address_layout) {//地址
            Intent intent = new Intent(this, SerachNearPointActivity.class);
            intent.putExtra("location", TextUtils.isEmpty(location.getAddress())?"":location.getAddress());
            intent.putExtra("city", TextUtils.isEmpty(location.getCity())?"":location.getCity());
            startActivityForResult(intent, TAG_ADDRESS);
        }
        if (v.getId() == R.id.time_layout) {
            if (demandType == 3) {
                selectOneTime();//选择一个时间
            } else {
                selectTwoTime();//选择两个时间
            }
        }
        if (v.getId() == R.id.date_layout) {
            if (demandType == 3) {//计件,选一个日期
                addDate(true);
            } else {
                addDate(false);//选两个
            }
        }
        if (v.getId() == R.id.daili_service) {
            showPop();
        }
        if (v.getId() == R.id.commit_btn) {
            checkEmpty();
        }
    }

    //TODO H5弹窗
    private void showPop() {
        View view = LayoutInflater.from(ReleaseOrEditActivity.this).inflate(R.layout.view_daili_service_notice, null);
        TextView btn = (TextView) view.findViewById(R.id.iknow);
        window = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        View rootview = LayoutInflater.from(ReleaseOrEditActivity.this).inflate(R.layout.main, null);
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
    }


    /**
     * 检查发布条件是否完整
     */
    private void checkEmpty() {
        if (TextUtils.isEmpty(workText.getText().toString())) {
            ToastUtils.showShort(mContext, "请输入零工名称");
            return;
        }
        if (TextUtils.isEmpty(typeText.getText().toString())) {
            ToastUtils.showShort(mContext, "请选择零工类型");
            return;
        }
        if (TextUtils.isEmpty(describeText.getText().toString())) {
            ToastUtils.showShort(mContext, "请输入零工描述");
            return;
        }
        if (describeText.getText().toString().length() <= 5) {
            ToastUtils.showShort(mContext, "零工描述不得少于5个字");
            return;
        }
        if (TextUtils.isEmpty(numText.getText().toString())) {
            ToastUtils.showShort(mContext, "请输入零工人数");
            return;
        }
        if (TextUtils.isEmpty(salaryText.getText().toString())) {
            ToastUtils.showShort(mContext, "请输入支付报酬");
            return;
        }
        if (TextUtils.isEmpty(dateText.getText().toString())) {
            ToastUtils.showShort(mContext, "请选择零工日期");
            return;
        }
        if (TextUtils.isEmpty(timeText.getText().toString())) {
            ToastUtils.showShort(mContext, "请选择零工时间");
            return;
        }
        if (TextUtils.isEmpty(addressText.getText().toString())) {
            ToastUtils.showShort(mContext, "请选择零工地址");
        }
        submit();
    }
    /**
     * 提交参数*/

    private void submit() {
        time = timeText.getText().toString();
        date = dateText.getText().toString().trim();
        bean.setId(editBean == null ? "" : editBean.id);
        bean.setUserId(mACache.getAsString(AppKey.CacheKey.MY_USER_ID));
        bean.setType("1");//1发布零工 2发布服务
        bean.setPostName(workText.getText().toString().trim());             //名称
        bean.setTaskTitle(workText.getText().toString().trim());            //标题
        bean.setTaskDescription(describeText.getText().toString().trim());  //描述
        bean.setRecruitNumber(numText.getText().toString().trim());                             //人数
        bean.setSurplusRecruitNumber(numText.getText().toString().trim());  //剩余招聘人数
        bean.setPrice(isYG ? "" : salaryText.getText().toString().trim());      //报酬
        bean.setDemandType(demandType + "");
        bean.setAreaId(TextUtils.isEmpty(mAreaId) ? "" : mAreaId);
        bean.setProvinceName(TextUtils.isEmpty(location.getProvince()) ? "" : location.getProvince());
        bean.setCityName(TextUtils.isEmpty(location.getCity()) ? "" : location.getCity());
        bean.setWorkAddress(TextUtils.isEmpty(location.getAddress()) ? "" : location.getAddress());
        bean.setVillageName(TextUtils.isEmpty(location.getStreet()) ? "" : location.getStreet());
        if (checkbox.isChecked()) {
            bean.setIsFarmersInsurance("1");//买保险
        } else {
            bean.setIsFarmersInsurance("0");//不买保险
        }
        if (demandType == 1 || demandType == 2) {
            bean.setJobMeterUnit(isYG ? "0" : jobMeterUnit + "");                            //零工计量单位(1.天,2.时,3.单)
            bean.setJobMeterUnitName(isYG ? "" : (rbDay.isChecked() ? "天" : "时"));       //计量单位
        } else {
            bean.setJobMeterUnit(isYG ? "" : "3");                                         //零工计量单位(1.天,2.时,3.单)
            bean.setJobMeterUnitName(isYG ? "" : "单");                                    //计量单位
        }
        if (demandType == 3) {
            bean.setStartTime(date.replaceAll("\\.", "-") + " " + time + ":01");
            bean.setEndTime("");
        } else {
            if (isOneDay) {
                bean.setStartTime(date.replaceAll("\\.", "-") + " " + time.toString().split("-")[0].replaceAll("当日", "") + ":01");
                bean.setEndTime(date.replaceAll("\\.", "-") + " " + time.toString().split("-")[1].replaceAll("次日", "") + ":01");
            } else {
                bean.setStartTime(date.split("-")[0].replaceAll("\\.", "-") + " " + time.toString().split("-")[0].replaceAll("当日", "") + ":01");
                bean.setEndTime(date.split("-")[1].replaceAll("\\.", "-") + " " + time.toString().split("-")[1].replaceAll("次日", "") + ":01");
            }

        }
        viewModel.getReleaseJob(bean);
    }



    /**
     * 添加用工日期
     *
     * @param isJJ 是否计件
     */
    private void addDate(final boolean isJJ) {
        selectedDate = null;
        startDate = 0;
        endDate = 0;
        View dateView = inflater.inflate(R.layout.item_date_picker, null);
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
                if (isJJ) {
                    selectOneDate();    //是计件，选1个日期
                } else {
                    selectTwoDate();    //不是计件,选2个日期
                }
                timeText.setText("");   //切换日期,清空时间
            }
        });

        dayPickerView.setController(this);
        alertDialog = DialogUtils.showViewDialog(mContext, dateView);
    }

    /**
     * 选择两个日期
     */
    private void selectTwoDate() {

        if (selectedDate != null && startDate == 0 && endDate == 0) {
            ToastUtils.showShort(mContext, "请选择用工起点日期，终点日期");
            return;
        }
        //判断选中的日期是否在当日以及当日之前
        int currentDate = Integer.parseInt(DateUtils.dateFormat("yyyyMMdd", System.currentTimeMillis() + ""));
        if ((selectedDate == null && Integer.parseInt(DateUtils.dateFormat("yyyyMMdd", startDate + "")) < currentDate)
                || (selectedDate != null && StringUtils.replaceDate(selectedDate) < currentDate)) {
            ToastUtils.showShort(mContext, "如选择当天日期，再次点击该日期");
            return;
        }

        //判断是否是当天
        if (selectedDate == null && Integer.parseInt(DateUtils.dateFormat("yyyyMMdd", startDate + "")) == currentDate) {
            isToday = true;
        } else {
            isToday = false;
        }
        String start = DateUtils.dateFormat("yyyy.MM.dd", startDate + "");
        String end = DateUtils.dateFormat("yyyy.MM.dd", endDate + "");
        if (start.equals(end)) {//如果是单个一天的话，我们只需要显示出来其中的
            dateString = start;
            isOneDay = true;
            isToday = true;
        } else {
            dateString = start + "-" + end;
            isOneDay = false;
            isToday = false;
        }
        dateText.setText(dateString);

        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }

    /**
     * 选择一个日期
     */
    private void selectOneDate() {
        if (selectedDate == null) {
            ToastUtils.showShort(mContext, "请选择一个用工日期");
            return;
        }
        //判断选中的日期是否在当日以及当日之前
        int currentDate = Integer.parseInt(DateUtils.dateFormat("yyyyMMdd", System.currentTimeMillis() + ""));

        if (StringUtils.replaceDate(selectedDate) < currentDate) {
            ToastUtils.showShort(mContext, "用工日期不得早于当前日期");
            return;
        } else if (StringUtils.replaceDate(selectedDate) == currentDate) {//是当天
            isToday = true;
        } else {//不是当天
            isToday = false;
        }
        dateString = selectedDate;
        dateText.setText(dateString);

        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();

    }

    /**
     * 选择两个用工时间
     */
    private void selectTwoTime() {
        listItem = DatePackerUtil.getTimeAllList();
        if (listItem == null)
            listItem = new ArrayList<>();
        final View timeView = inflater.inflate(R.layout.item_time_picker_twotime, null);
        final LoopView loopView1 = (LoopView) timeView.findViewById(R.id.loopView1);
        final LoopView loopView2 = (LoopView) timeView.findViewById(R.id.loopView2);
        Button btnCancle = (Button) timeView.findViewById(R.id.btn_cancle);
        Button btnOk = (Button) timeView.findViewById(R.id.btn_ok);
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertDialog != null && alertDialog.isShowing())
                    alertDialog.cancel();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int startTimeInt = StringUtils.replaceTime(startTime);
                int endTimeInt = StringUtils.replaceTime(endTime);
                if (startTimeInt == endTimeInt) {
                    ToastUtils.showShort(mContext, "开始时间不能等于结束时间");
                    return;
                }

                if (isToday) {//如果是从当天开始的，那就要判断，起点时间是否在当前时间之前。
                    String currentTime = DateUtils.dateTimeFormat("yyyy.MM.dd HH:mm", System.currentTimeMillis() + "");
                    String selectdate = DateUtils.dateFormat("yyyy.MM.dd", startDate + "");
                    String select = selectdate + " " + startTime;
                    if (select.compareTo(currentTime) <= 0) {//小于等于当前时间
                        ToastUtils.showShort(mContext, "开始时间不能早于现在时间");
                        return;
                    }
                }


                if (startTimeInt < endTimeInt) {//判断是否是跨天的时间段
                    timeText.setText(startTime + "-" + endTime);//当天时间
                } else {//跨天时间段

                    timeText.setText("当日" + startTime + "-次日" + endTime);
                    ToastUtils.showShort(mContext, "您选择的是跨天任务");
                }

                if (alertDialog != null && alertDialog.isShowing())
                    alertDialog.cancel();
            }
        });
        loopView1.setList(listItem);
        loopView1.setNotLoop();
        defaultTime(loopView1);
        loopView1.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                startTime = listItem.get(item);
            }
        });
        loopView2.setList(listItem);
        loopView2.setNotLoop();
        loopView2.setCurrentItem(0);
        loopView2.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                endTime = listItem.get(item);
            }
        });
        alertDialog = DialogUtils.showViewDialog(mContext, timeView);
    }

    /**
     * 默认时间
     */
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
     * 选择一个用工时间点
     */
    private void selectOneTime() {
        listItem = DatePackerUtil.getTimeAllList();
        if (listItem == null)
            listItem = new ArrayList<>();
        final View timeView = inflater.inflate(R.layout.item_timepicker_onetime, null);
        final LoopView loopView1 = (LoopView) timeView.findViewById(R.id.loopView);
        Button btnCancle = (Button) timeView.findViewById(R.id.btn_cancle);//关闭按钮
        Button btnOk = (Button) timeView.findViewById(R.id.btn_ok);    //确定按钮
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alertDialog != null && alertDialog.isShowing())
                    alertDialog.cancel();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentTime = DateUtils.dateTimeFormat("yyyy.MM.dd HH:mm", System.currentTimeMillis() + "");
                String select = selectedDate + " " + startJJTime;
                if (select.compareTo(currentTime) <= 0) {//小于等于当前时间
                    ToastUtils.showShort(mContext, "开始时间是在当前时间之后");
                    return;
                }
                timeText.setText(startJJTime);
                if (alertDialog != null && alertDialog.isShowing())
                    alertDialog.cancel();
            }
        });
        loopView1.setList(listItem);
        loopView1.setNotLoop();
        defaultTime(loopView1);
        loopView1.setListener(new LoopListener() {
            @Override
            public void onItemSelect(int item) {
                startJJTime = listItem.get(item);
            }
        });

        alertDialog = DialogUtils.showViewDialog(mContext, timeView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && null != data) {
            if (requestCode == TAG_WORK_TYPE) {//类型
                postType = data.getStringExtra("worktypeCode");
                typeText.setText(data.getStringExtra("worktype"));
                bean.setPostType(postType);
                bean.setPostTypeName(data.getStringExtra("worktype"));
                if (data.getStringExtra("worktype").equals("志愿义工")) {
                    isYG = true;
                    jineLinear.setVisibility(View.GONE);
                    jineLine2.setVisibility(View.GONE);
                } else {
                    isYG = false;
                    jineLine2.setVisibility(View.VISIBLE);
                    jineLinear.setVisibility(View.VISIBLE);
                }
            }
            if (requestCode == TAG_ADDRESS) {
                addressText.setText(data.getStringExtra("backAddress")==null?"":data.getStringExtra("backAddress"));
                if (null!=data.getStringExtra("areaId")){
                    mAreaId = data.getStringExtra("areaId");
                }

            }
        }
    }

    /**
     * 默认年
     */
    @Override
    public int getMaxYear() {
        return Integer.parseInt(DateUtils.dateFormat("yyyy", System.currentTimeMillis() + "")) + 1;
    }

    /**
     * 选择月
     */
    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {
        //两次调用这个方法，onDateRangeSelected方法被调用一次，单次不能点击确认
        num++;
        if (num % 2 == 1 && demandType != 3) {
            endDate = 0;
            startDate = 0;
            selectedDate = null;
            return;
        }
        if (month < 9 && day < 10)
            selectedDate = year + ".0" + (month + 1) + ".0" + day;
        else if (month >= 9 && day < 10)
            selectedDate = year + "." + (month + 1) + ".0" + day;
        else if (month < 9 && day >= 10)
            selectedDate = year + ".0" + (month + 1) + "." + day;
        else
            selectedDate = year + "." + (month + 1) + "." + day;
    }

    /**
     * 选择天
     */
    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {
        long firstTime = selectedDays.getFirst().getDate().getTime();
        long lastTime = selectedDays.getLast().getDate().getTime();
        num = 0;//重置为 0
        if (Math.abs(lastTime - firstTime) < 24 * 60 * 60 * 1000) {//时间小于一天就说明是一天
            selectedDate = DateUtils.dateFormat("yyyy.MM.dd", firstTime + "");
            startDate = endDate = firstTime;
        } else {
            startDate = lastTime > firstTime ? firstTime : lastTime;
            endDate = lastTime > firstTime ? lastTime : firstTime;
            selectedDate = null;
        }
    }

    /**
     * 发布零工后回传数据
     */
    @Override
    public void ReleaseJobData(List<String> result) {
        ToastUtils.showShort(mContext, "发布零工成功");
        finish();
    }

    /**
     * 编辑零工后回传数据
     */
    @Override
    public void EditJobData(List<String> result) {
        ToastUtils.showShort(mContext, "编辑零工成功");
        finish();
    }
}
