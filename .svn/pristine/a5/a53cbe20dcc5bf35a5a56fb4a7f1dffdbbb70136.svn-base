package com.dlg.personal.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.common.overlay.AMapUtil;
import com.common.utils.DateUtils;
import com.dlg.data.common.model.ActionButtonsBean;
import com.dlg.data.common.model.AssistButtonBean;
import com.dlg.data.common.model.ButtonBean;
import com.dlg.data.common.model.CountdownBean;
import com.dlg.data.common.model.ShareDataBean;
import com.dlg.data.common.url.CommonUrl;
import com.dlg.data.home.model.DoingTaskOrderDetailBean;
import com.dlg.data.home.model.WorkCardBean;
import com.dlg.data.home.model.XYCoordinateBean;
import com.dlg.personal.R;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.app.MApp;
import com.dlg.personal.base.BaseFragment;
import com.dlg.personal.base.DlgMapView;
import com.dlg.personal.home.activity.HomeActivity;
import com.dlg.personal.home.view.HomeEmployeeCardView;
import com.dlg.personal.home.view.OrderButtnView;
import com.dlg.personal.oddjob.activity.CancleActivity;
import com.dlg.personal.oddjob.activity.EmployeeOddMapActivity;
import com.dlg.personal.oddjob.activity.EvaluateHirerActivity;
import com.dlg.personal.web.DefaultWebActivity;
import com.dlg.viewmodel.common.OrderButtnViewModel;
import com.dlg.viewmodel.common.OrderOperaButViewModel;
import com.dlg.viewmodel.common.ShareViewModel;
import com.dlg.viewmodel.common.presenter.OrderButtnPresenter;
import com.dlg.viewmodel.common.presenter.SharePresenter;
import com.dlg.viewmodel.common.presenter.SuccessObjectPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.key.H5WebType;
import com.example.umengshare.UmengShareUtil;

import java.util.Stack;

import static android.view.View.VISIBLE;

/**
 * 作者：wangdakuan
 * 主要功能：雇员进行中订单卡片显示fragment
 * 创建时间：2017/7/3 17:47
 */
public class EmployeeOngingCardFragment extends BaseFragment implements OrderButtnPresenter,
        SuccessObjectPresenter, SharePresenter {

    private HomeEmployeeCardView mCardView; //卡片信息数据
    private OrderButtnView mBntOrderView; //订单按钮控件
    private RelativeLayout mRelaReward; //显示报酬价格
    private TextView mTvReward; //报酬
    private TextView mTvPayStatus; //状态
    private TextView mTvCountDown; //倒计时
    private TextView mToolbarTitle; //上一个页面传过来的头部控件
    private DlgMapView mMapView; //地图控件

    private WorkCardBean cardBean = new WorkCardBean();
    private DoingTaskOrderDetailBean mOrderDetailBean;
    private OrderButtnViewModel mOrderButtnViewModel;
    private ShareViewModel mShareViewModel; //分享
    private OrderOperaButViewModel operaButViewModel; //订单按钮操作viewModel
    private int num; //是否是第一个页面
    private int position; //页面个数
    private int statusCode; //状态代码
    private CountDownTimer mCountDownTimer; //倒计时控件

    private onKnowClick knowClick; //点击知道了的回调接口

    public void setKnowClick(onKnowClick knowClick) {
        this.knowClick = knowClick;
    }

    public interface onKnowClick {
        void nextOrder(EmployeeOngingCardFragment fragment);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mOrderButtnViewModel) {
            mOrderButtnViewModel.onDestroyView();
        }
        if (null != operaButViewModel) {
            operaButViewModel.onDestroyView();
        }
        if (null != mCountDownTimer) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        if (null != mShareViewModel) {
            mShareViewModel.onDestroyView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mOrderButtnViewModel) {
            mOrderButtnViewModel.onDestroyView();
        }
        if (null != operaButViewModel) {
            operaButViewModel.onDestroyView();
        }
        if (null != mCountDownTimer) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        if (null != mShareViewModel) {
            mShareViewModel.onDestroy();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_employee_onging_card, null);
        initView(inflate);
        initData();
        return inflate;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (num != 0 && isVisibleToUser && null != mOrderButtnViewModel && null != mOrderDetailBean) {
            mOrderButtnViewModel.getOrderButtnData(mOrderDetailBean.getBusinessNumber());
        }
    }

    /**
     * 控件初始化
     *
     * @param inflate
     */
    private void initView(View inflate) {
        mOrderButtnViewModel = new OrderButtnViewModel(mContext, this);
        mCardView = (HomeEmployeeCardView) inflate.findViewById(R.id.card_view);
        mBntOrderView = (OrderButtnView) inflate.findViewById(R.id.bnt_order_view);
        mBntOrderView.setButtonClickListener(new OrderButtnView.onButtonClickListener() {
            @Override
            public void buttOnClick(ButtonBean buttonBean) {
                onClickButton(buttonBean);
            }

            @Override
            public void assistButtOnClick(AssistButtonBean assistButtonBean) {
                onClickAssistButton(assistButtonBean);
            }
        });
        mRelaReward = (RelativeLayout) inflate.findViewById(R.id.rela_reward);
        mTvReward = (TextView) inflate.findViewById(R.id.tv_reward);
        mTvPayStatus = (TextView) inflate.findViewById(R.id.tv_payStatus);
        mTvCountDown = (TextView) inflate.findViewById(R.id.tv_countDown);
    }

    /**
     * 操作按钮点击事件
     */
    private void onClickButton(ButtonBean buttonBean) {
        switch (buttonBean.getOperateStatusCode()) {
            /**
             * 完成按钮销毁当前页到首页
             */
            case 0:
            case 1: //知道了
            case 2: //在来一单
                if (mContext instanceof HomeActivity) {
                    if (null != knowClick) {
                        knowClick.nextOrder(this);
                    }
//                    ((HomeActivity) mActivity).checkEmploueeDesk(false);
                }
                if (mContext instanceof EmployeeOddMapActivity) {
                    if(ActivityNavigator.navigator().isThereActivity(HomeActivity.class)){
                        Stack<Class> classes = new Stack<>();
                        classes.add(HomeActivity.class);
                        ActivityNavigator.navigator().keepRemoverActivity(classes);
                    }else {
                        Intent intent=new Intent(getActivity(),HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                }
                break;
            /**
             * 功能按钮
             */
            case 7: //拒绝邀请
            case 20: //同意邀请
            case 10: //拒绝邀请
            case 21: //开工打卡
            case 22: //收工打卡
            case 23: //确认完成
            case 24: //确认完成
                if (null != operaButViewModel && null != mOrderDetailBean) {
                    operaButViewModel.updateOrderOperaStatus(buttonBean, mOrderDetailBean.getBusinessNumber());
                }
                break;


        }
    }

    /**
     * 辅助按钮点击事件
     */
    private void onClickAssistButton(AssistButtonBean assistButtonBean) {
        switch (assistButtonBean.getButtonStatus()) {
            case "101": //取消按钮
                Bundle beans = new Bundle();
                String businessNumber = "";
                if (mOrderDetailBean != null) {
                    businessNumber = mOrderDetailBean.getBusinessNumber();
                }
                if (statusCode == 21) {
                    beans.putBoolean("isWY", true);
                } else {
                    beans.putBoolean("isWY", false);
                }
                beans.putString("businessNumber", businessNumber);
                ActivityNavigator.navigator().navigateTo(CancleActivity.class, beans, AppKey.PageRequestCodeKey.CANCLE_KEY);
                break;
            case "104": //分享按钮
            case "102": //分享按钮
                if (null == mShareViewModel) {
                    mShareViewModel = new ShareViewModel(mContext, EmployeeOngingCardFragment.this);
                }
                if (null != mOrderDetailBean) {
                    mShareViewModel.getShareData(mOrderDetailBean.getJobId());
                }
                break;
            case "103": //帮助按钮
                Bundle bundleH5 = new Bundle();
                bundleH5.putString(DefaultWebActivity.TITLE_NAME, H5WebType.help.getName());
                bundleH5.putString(DefaultWebActivity.H5_URL, CommonUrl.H5_WEB_URL);
                bundleH5.putString(DefaultWebActivity.H5_TYPE, H5WebType.help.getType() + "");
                ActivityNavigator.navigator().navigateTo(DefaultWebActivity.class, bundleH5);
                break;
            case "105": //评价按钮
                Bundle bundle = new Bundle();
                bundle.putSerializable("DetailBean", mOrderDetailBean);
                ActivityNavigator.navigator().navigateTo(EvaluateHirerActivity.class, bundle);
                break;

        }
    }

    /**
     * 从页面传递过来的数据对象
     *
     * @param orderDetailBean
     */
    public void setOrderDetailBean(DoingTaskOrderDetailBean orderDetailBean,
                                   DlgMapView mapView, TextView toolbarTitle, int num, int position) {
        this.mToolbarTitle = toolbarTitle;
        this.num = num;
        this.position = position;
        this.mMapView = mapView;
        if (null != orderDetailBean) {
            mOrderDetailBean = orderDetailBean;
            setCardBean();
        }
    }

    /**
     * 封装成卡片对象
     */
    private void setCardBean() {
        if (null == mOrderDetailBean) {
            return;
        }
        cardBean.setProvinceName(mOrderDetailBean.getProvinceName());
        cardBean.setCityName(mOrderDetailBean.getAreaName());
        cardBean.setAreaName(mOrderDetailBean.getAreaName());
        cardBean.setWorkAddress(mOrderDetailBean.getWorkAddress());
        cardBean.setPostName(mOrderDetailBean.getPostName());
        cardBean.setPostTypeName(mOrderDetailBean.getPostTypeName());
        cardBean.setUserCreditCount(mOrderDetailBean.getCreditCount());
        cardBean.setUserLogo(mOrderDetailBean.getLogo());
        cardBean.setPrice(mOrderDetailBean.getPrice() + "");
        cardBean.setJobMeterUnitName(mOrderDetailBean.getMeterUnitName());
        cardBean.setIsFarmersInsurance(mOrderDetailBean.getIsFarmersInsurance());

        int startYear = DateUtils.getDate_yyyy(mOrderDetailBean.getStartDate());
        int endYear = DateUtils.getDate_yyyy(mOrderDetailBean.getEndDate());
        int startMonth = DateUtils.getDate_MM(mOrderDetailBean.getStartDate());
        int endMonth = DateUtils.getDate_MM(mOrderDetailBean.getEndDate());
        int startDay = DateUtils.getDate_dd(mOrderDetailBean.getStartDate());
        int endDay = DateUtils.getDate_dd(mOrderDetailBean.getEndDate());
        int startHour = DateUtils.getDate_HH(mOrderDetailBean.getStartDate());
        int endHour = DateUtils.getDate_HH(mOrderDetailBean.getEndDate());
        int startMinute = DateUtils.getDate_mm(mOrderDetailBean.getStartDate());
        int endMinute = DateUtils.getDate_mm(mOrderDetailBean.getEndDate());
        cardBean.setStartYear(startYear);
        cardBean.setStartMonth(startMonth);
        cardBean.setStartDay(startDay);
        cardBean.setStartHour(startHour);
        cardBean.setStartMinute(startMinute);
        cardBean.setEndYear(endYear);
        cardBean.setEndMonth(endMonth);
        cardBean.setEndDay(endDay);
        cardBean.setEndHour(endHour);
        cardBean.setEndMinute(endMinute);
        cardBean.setDemandType(mOrderDetailBean.getDemandType());
    }

    /**
     * 获取按钮接口
     *
     * @param buttonsBean
     */
    @Override
    public void onOrderButtnList(ActionButtonsBean buttonsBean) {
        mBntOrderView.setButtonData(buttonsBean);
        if (null != mToolbarTitle && null != buttonsBean) {
            mToolbarTitle.setText(buttonsBean.getStatusText());
        }
        setMap(buttonsBean);
        getDownTimer(buttonsBean);
    }

    /**
     * 是否显示倒计时
     *
     * @param buttonsBean
     */
    private void getDownTimer(final ActionButtonsBean buttonsBean) {
        if (null == buttonsBean) {
            return;
        }
        if (null != buttonsBean.getButtonList() && buttonsBean.getButtonList().size() > 0) {
            mBntOrderView.setClickButton(!buttonsBean.getButtonList().get(0).getIsGray());
        }
        if (null == buttonsBean.getCountdownVo()) {
            return;
        }
        if (null != mCountDownTimer) {
            mCountDownTimer.start();
            mCountDownTimer = null;
        }
        final CountdownBean countdownBean = buttonsBean.getCountdownVo();
        long time = countdownBean.getRemainingTime();
        if (time > 1000) { //也就是倒计时需要大于1秒才会显示
            mTvCountDown.setVisibility(VISIBLE);
            mTvCountDown.setText(DateUtils.formatTime(time)
                    + countdownBean.getMapTipsText());
            if (null == mCountDownTimer) {
                mCountDownTimer = new CountDownTimer(time, 1000) { //倒计时
                    @Override
                    public void onTick(long l) {
                        mTvCountDown.setText(DateUtils.formatTime(l)
                                + countdownBean.getMapTipsText());
                    }

                    @Override
                    public void onFinish() {
                        mCountDownTimer.start();
                        showPrompt(buttonsBean);
                        mTvCountDown.setVisibility(View.INVISIBLE);
                    }
                };
            }
        } else {
            showPrompt(buttonsBean);
        }
        if (null != mCountDownTimer) {
            mCountDownTimer.start();
        }
    }

    /**
     * 判断是否在范围之内 如果不是就显示提示框
     *
     * @param buttonsBean
     */
    private void showPrompt(ActionButtonsBean buttonsBean) {
        if (null == buttonsBean) {
            return;
        }
        if (null == buttonsBean.getButtonList()) {
            return;
        }
        if (buttonsBean.getButtonList().size() > 0) {
            int operateStatusCode = buttonsBean.getButtonList().get(0).getOperateStatusCode();
            mBntOrderView.setClickButton(!buttonsBean.getButtonList().get(0).getIsGray());
            /**
             * 计价类不需要判断
             */
            if (!buttonsBean.getButtonList().get(0).getIsGray() && mOrderDetailBean.getCurrentOperateStatus() != 3) {
                if (operateStatusCode == 21 || operateStatusCode == 22 || operateStatusCode == 23) {
                    if (null != buttonsBean && null != buttonsBean.getCountdownVo()) {
                        mTvCountDown.setText(buttonsBean.getCountdownVo().getOtherMapTipsText());
                    } else {
                        mTvCountDown.setText("请到地图上蓝色范围内进行打卡");
                    }
                    mTvCountDown.setVisibility(VISIBLE);
                    mBntOrderView.setClickButton(false);
                }
            }
        }

    }

    /**
     * 操作按钮成功
     *
     * @param success
     */
    @Override
    public void onSuccess(boolean success) {
        if (null != mOrderButtnViewModel && null != mOrderDetailBean) {
            /**
             * 操作成功后重新请求按钮接口更新
             */
            mOrderButtnViewModel.getOrderButtnData(mOrderDetailBean.getBusinessNumber());
        }
    }

    /**
     * 操作角色	当前订单状态	状态文本	操作按钮值	操作按钮文本	下一个订单状态	消息发送模版
     * 2(雇主)	-1	发出邀请	8	发出邀请	8	【打零工】"{标题}"邀请你接单
     * 1(雇员)	8	等待邀请	20	同意邀请	20	【打零工】"{标题}"同意了你的邀请
     * 1(雇员)	8	等待邀请	10	拒绝邀请	9	【打零工】"{标题}"拒绝了你的邀请
     * 2(雇主)	9	拒绝邀请	1	知道了	0
     * 1(雇员)	-1	无状态	10	接单	10	【打零工】"{标题}"有人接单
     * 2(雇主)	10	等待同意	20	同意	20	【打零工】雇主同意了你的接单
     * 2(雇主)	10	等待同意	11	拒绝	11	【打零工】雇主拒绝了你的接单
     * 1(雇员)	11	雇主拒绝	1	知道了	0
     * 1(雇员)	20	等待开工	21	开工打卡	21
     * 2(雇主)	20	等待开工	3	取消	3(跳转取消页面)
     * 1(雇员)	21	正在干活中	22	收工打卡	20
     * 1(雇员)	21	正在干活中	23	确认完成（最后一个时间段）		【打零工】"{标题}"已经完成任务，去支付报酬吧
     * 1(雇员)	21	正在干活中	24	确认完成（计件，雇员）	22	【打零工】"{标题}"已经完成任务，去验收吧
     * 2(雇主)	21	正在干活中	3	取消	3(跳转取消页面)
     * 2(雇主)	21	正在干活中	30	确认完成（雇主）	30
     * 3(系统)	21	正在干活中	31	确认完成（系统）	30	【打零工】"{标题}"已经完成任务，去支付报酬吧
     * 1(雇员)	22	等待验收	24	确认完成（计件，雇员）
     * 2(雇主)	22	等待验收	25	验收通过	30	【打零工】"{标题}"验收通过
     * 2(雇主)	22	等待验收	26	验收不通过	21	【打零工】"{标题}"验收不通过
     * 1(雇员)	30	待收款	2	再来一单	0
     * 2(雇主)	30	代收款	40	确认支付	40	【打零工】"{标题}"已支付
     * 1(雇员)	10	等待同意	10	接单
     * 1(雇员)	40	已完成	2	再来一单	0
     */
    private void setMap(ActionButtonsBean buttonsBean) {
        statusCode = buttonsBean.getStatusCode();
        LatLng latLonPoint = null;
        if (null != mOrderDetailBean && null != mOrderDetailBean.getXyCoordinateVo()) {
            XYCoordinateBean coordinateBean = mOrderDetailBean.getXyCoordinateVo();
            double x = Double.valueOf(coordinateBean.getJobXCoordinate());
            double y = Double.valueOf(coordinateBean.getJobYCoordinate());
            latLonPoint = new LatLng(y, x);
        }
        if (null == mMapView || null == latLonPoint) {
            return;
        }
        boolean isWithinArea = false;
        Float distance = AMapUtils.calculateLineDistance(mMapView.getMyLng(), latLonPoint);
        if (distance > MApp.dataValueDistance) {//测量雇员位置和工作地址距离 在工作范围内
            isWithinArea = true;//在工作范围内
        }

        mMapView.clearAllMarkers();
        int statusCode = buttonsBean.getStatusCode(); //状态码
        mMapView.toMyLocation();
        switch (statusCode) {
            /**
             * 需要显示价格在地图上
             * 需要显示当前位置
             */
            case 6:
            case 7:
            case 8:
            case 11:
            case 10:
            case 50:
                mMapView.addMyMarker(mMapView.getMyLng()); //用户当前位置
                if (!"志愿义工".equals(mOrderDetailBean.getPostTypeName())) {
                    //订单价格显示到图层上
                    mMapView.addMarkerPrice(latLonPoint, mOrderDetailBean.getPrice() + "", mOrderDetailBean.getMeterUnitName());
                }
                mMapView.moveToLocation(latLonPoint, mMapView.getZoom() > 13 ? 13 : 13);
                break;
            /**
             * 需要显示用户当前位置
             */
            case 20:
                if (isWithinArea) {
                    mMapView.addLocationMarker(mMapView.getMyLng(), R.mipmap.amap_start); //当前用户启点位置
                    mMapView.setPath(AMapUtil.convertToLatLonPoint(mMapView.getMyLng()),
                            AMapUtil.convertToLatLonPoint(latLonPoint)); //路线
                }
            case 21:
            case 22:
            case 30:
            case 40:
                mMapView.moveToLocation(latLonPoint, mMapView.getZoom() > 13 ? 13 : mMapView.getZoom());
                mMapView.addCircleMarker(latLonPoint, (int) MApp.dataValueDistance);
                mMapView.addLocationMarker(latLonPoint, R.mipmap.amap_end); //用户当前位置
                mMapView.addMyMarker(mMapView.getMyLng()); //用户当前位置
                break;
        }
    }

    /**
     * 数据绑定
     */
    private synchronized void initData() {
        if (num == 0) {
            num = -1;
            if (mOrderDetailBean != null)
                mOrderButtnViewModel.getOrderButtnData(mOrderDetailBean.getBusinessNumber());
            else
                return;
        }
        operaButViewModel = new OrderOperaButViewModel(mContext, this, this);
        mCardView.setDataCard(cardBean, true);
        if (mOrderDetailBean.getStatus().equals("30")) {
            setRemuneration();
            mTvPayStatus.setText("待雇主支付");
        } else if (mOrderDetailBean.getStatus().equals("40")) {
            setRemuneration();
            mTvPayStatus.setText("雇主已支付");
        } else {
            mRelaReward.setVisibility(View.GONE);
        }

    }

    /**
     * 设置报酬显示控件的值
     */
    private void setRemuneration() {
        if (null == mOrderDetailBean) {
            return;
        }
        mRelaReward.setVisibility(VISIBLE);
        float totalPrice = TextUtils.isEmpty(mOrderDetailBean.getTotalPrice() + "") ? 0 : Float.parseFloat(mOrderDetailBean.getTotalPrice() + "");
        float tipAmount = TextUtils.isEmpty(mOrderDetailBean.getTipAmount()) ? 0 : Float.parseFloat(mOrderDetailBean.getTipAmount());
        float price = totalPrice + tipAmount;
        if (tipAmount > 0) {
            mTvReward.setText("报酬：" + price + "元 (含小费" + tipAmount + "元)");
        } else {
            mTvReward.setText("报酬：" + mOrderDetailBean.getTotalPrice() + "元");
        }
    }

    @Override
    public void onShareData(ShareDataBean shareDataBean) {
        if (null != shareDataBean) {
            UmengShareUtil.Builder(mActivity).initListener()
                    .initShareAction(shareDataBean.getTaskTitle(), shareDataBean.getTaskDescription()
                            , shareDataBean.getDetailsUrl(), shareDataBean.getUserLogo()).open();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppKey.PageRequestCodeKey.CANCLE_KEY || resultCode == AppKey.PageRequestCodeKey.CANCLE_KEY) {
            if (null != mOrderButtnViewModel && null != mOrderDetailBean) {
                /**
                 * 操作成功后重新请求按钮接口更新
                 */
                mOrderButtnViewModel.getOrderButtnData(mOrderDetailBean.getBusinessNumber());
            }
        }
    }
}
