package com.dlg.personal.oddjob.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.common.utils.DialogUtils;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.dlg.data.home.model.DictionaryBean;
import com.dlg.data.oddjob.model.CancleBean;
import com.dlg.personal.R;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.activity.HomeActivity;
import com.dlg.personal.oddjob.adapter.CancleAdapter;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.home.DictionaryViewModel;
import com.dlg.viewmodel.home.presenter.DictionaryPresenter;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.key.UserRole;
import com.dlg.viewmodel.oddjob.CancleOrderViewModel;
import com.dlg.viewmodel.oddjob.CancleTrucskViewModel;
import com.dlg.viewmodel.oddjob.presenter.CancleOrderPresenter;
import com.dlg.viewmodel.oddjob.presenter.CancleTruskPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：王进亚
 * 主要功能：订单取消 雇员和雇主
 * 创建时间：2017.07.13
 */

public class CancleActivity extends BaseActivity implements DictionaryPresenter, CancleTruskPresenter, CancleOrderPresenter {

    private ScrollView mScrollview;
    private CircleImageView mIvHead;
    private TextView mFenText;
    private TextView mGuizeText;
    private LinearLayout mIncomeLinear;
    private TextView mIncomeText;
    private LinearLayout mWeiyueLinear;
    private TextView mXmText;
    private RecyclerView mRecyclerCancle;
    private LinearLayout mBottomLinear;
    private TextView mSubmitBtn;
    private List<DictionaryBean> beans = new ArrayList<>();
    private List<Boolean> booleen = new ArrayList<>();
    private DictionaryViewModel dictionaryViewModel;
    private CancleTrucskViewModel cancleTrucskViewModel;
    private String businessNumber;
    private CancleAdapter cancleAdapter;
    private boolean isWY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_cancle);
        businessNumber = getIntent().getStringExtra("businessNumber");
        isWY = getIntent().getBooleanExtra("isWY", false);//是否违约

        initView();
    }

    private void initView() {
        getToolBarHelper().getToolbarTitle().setText("取消订单");
        TextView toolbarTextRight = getToolBarHelper().getToolbarTextRight();
        toolbarTextRight.setVisibility(View.VISIBLE);
        toolbarTextRight.setText("关闭");
        toolbarTextRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getToolBarHelper().getToolbarBack().setVisibility(View.INVISIBLE);
        getToolBarHelper().getToolbarBack().setClickable(false);

        mScrollview = (ScrollView) findViewById(R.id.scrollview);
        mIvHead = (CircleImageView) findViewById(R.id.iv_head);
        mFenText = (TextView) findViewById(R.id.fen_text);
        mGuizeText = (TextView) findViewById(R.id.guize_text);
        mIncomeLinear = (LinearLayout) findViewById(R.id.income_linear);
        mIncomeText = (TextView) findViewById(R.id.income_text);
        mWeiyueLinear = (LinearLayout) findViewById(R.id.weiyue_linear);
        mXmText = (TextView) findViewById(R.id.xm_text);
        mRecyclerCancle = (RecyclerView) findViewById(R.id.recycler_cancle);
        mBottomLinear = (LinearLayout) findViewById(R.id.bottom_linear);
        mSubmitBtn = (TextView) findViewById(R.id.submit_btn);
        View mLineView = findViewById(R.id.line_view);
        if (!isWY) {
            mIncomeLinear.setVisibility(View.GONE);
            mWeiyueLinear.setVisibility(View.GONE);
            mLineView.setVisibility(View.GONE);
        }

        //获取取消原因
        dictionaryViewModel = new DictionaryViewModel(this, this, this);
        if ((int) mACache.getAsObject(AppKey.CacheKey.USER_ROLE) == UserRole.hirer.getRole()) {//雇主
            dictionaryViewModel.getDictionaryData("employer.cancel.cause", "0");
        } else if ((int) mACache.getAsObject(AppKey.CacheKey.USER_ROLE) == UserRole.employee.getRole()) {//雇员
            dictionaryViewModel.getDictionaryData("employee.cancel.cause", "0");
        }
        //取消 违约金 诚信值 报酬
        cancleTrucskViewModel = new CancleTrucskViewModel(this, this, this);
        cancleTrucskViewModel.getTruskAndMoney(businessNumber);

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int seletetPosition = cancleAdapter.getSeletetPosition();
                if (seletetPosition == -1) {
                    Toast.makeText(mContext, "请选择取消原因", Toast.LENGTH_SHORT).show();
                    return;
                }
                dialog = DialogUtils.showLoadingDialog(mContext);
                CancleOrderViewModel cancleOrderViewModel
                        = new CancleOrderViewModel(CancleActivity.this, new BasePresenter() {
                    @Override
                    public void requestStart() {

                    }

                    @Override
                    public void requestComplete() {
                        dialog.dismiss();
                    }

                    @Override
                    public void requestNext(String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void requestError(String msg) {
                        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void logInError() {

                    }
                }, CancleActivity.this);
                //TODO 赔偿的时候 支付密码需要跳转获取 isWY为false
                if (cancleBeen.size() > 0) {
                    cancleOrderViewModel.goToCancleOrder(businessNumber, cancleAdapter.getCodeData(),
                            null, cancleBeen.get(0), isWY);
                } else {
                    cancleOrderViewModel.goToCancleOrder(businessNumber, cancleAdapter.getCodeData(),
                            null, null, isWY);
                }

            }
        });
    }

    /**
     * 获取取消原因 回调
     *
     * @param dictionaryBean
     */
    @Override
    public void getDictionary(List<DictionaryBean> dictionaryBean) {
        beans.clear();
        beans.addAll(dictionaryBean);
        initRecy();
    }

    /**
     * 初始化取消原因 列表
     */
    private void initRecy() {
        mRecyclerCancle.setLayoutManager(new LinearLayoutManager(this));
        cancleAdapter = new CancleAdapter(mContext, mRecyclerCancle, beans, R.layout.item_cancle_reason);
        mRecyclerCancle.setAdapter(cancleAdapter);

        cancleAdapter.setOnItemClickListener(new BaseLoadMoreHeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                cancleAdapter.setSeleted(position);
            }
        });
    }

    private List<CancleBean> cancleBeen = new ArrayList<>();

    /**
     * 违约金和 诚信值
     *
     * @param cancleBeen
     */
    @Override
    public void getTruskAndMoney(List<CancleBean> cancleBeen) {
        cancleBeen.clear();
        cancleBeen.addAll(cancleBeen);
        if (cancleBeen != null && cancleBeen.size() > 0) {
            CancleBean cancleBean = cancleBeen.get(0);
            mIncomeText.setText(cancleBean.getPrice() + "元");
            double compensatoryTrusty = cancleBean.getCompensatoryTrusty();
            mFenText.setText("扣除诚信值" + compensatoryTrusty + "分");

            double compensatoryPayment = cancleBean.getCompensatoryPayment();
            mXmText.setText(compensatoryPayment + "元");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dictionaryViewModel != null) {
            dictionaryViewModel.onDestroy();
        }
        if (cancleTrucskViewModel != null) {
            cancleTrucskViewModel.onDestroy();
        }
    }

    @Override
    public void cancleOrder(String cancleString) {
        Activity activity = ActivityNavigator.getLastActivity(2);
        if (null == activity) {
            return;
        }
        if (activity instanceof HomeActivity) {
            setResult(AppKey.PageRequestCodeKey.CANCLE_KEY);
            finish();
        } else if (activity instanceof HirerMapActivity || activity instanceof OddHirerHandleActivity
                || activity instanceof OddActivity || activity instanceof EmployeeOddMapActivity) {
            Stack<Class> classes = new Stack<>();
            classes.add(HomeActivity.class);
            classes.add(OddActivity.class);
            ActivityNavigator.navigator().keepRemoverActivity(classes);
        }
    }

    @Override
    public void requestNext(String msg) {
        super.requestNext(msg);
    }
}
