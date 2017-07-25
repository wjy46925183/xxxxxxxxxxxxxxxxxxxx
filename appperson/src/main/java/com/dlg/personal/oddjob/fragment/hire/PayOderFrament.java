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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.dlg.data.oddjob.model.ListBean;
import com.dlg.personal.R;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.base.BaseFragment;
import com.dlg.personal.oddjob.activity.HirerMapActivity;
import com.dlg.personal.oddjob.adapter.PayOrderAdapter;
import com.dlg.personal.wallet.activity.PaymentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：待付款
 * 创建时间：2017/7/10 10:30
 */

public class PayOderFrament extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, PayOrderAdapter.PayAndTipCallBack {

    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRecyView;
    private LinearLayout mSlidLinear;
    private ImageView mBottomPanel;
    private LinearLayout mDetailLayout;
    private TextView mIncomeText;
    private TextView mXiaofeiText;
    private RelativeLayout mBaoLinear;
    private TextView mBaoxianText;
    private TextView mTotalTab;
    private TextView mTotalText;
    private LinearLayout mBottomRe2;
    private RelativeLayout mBottomRe;
    private CheckBox mCheckbox;
    private TextView mTotalsTab;
    private TextView mTotalsText;
    private TextView mPayBtn;
    private List<ListBean> mListBeen = new ArrayList<>();
    private boolean isVisvible;
    private double pay, tip, danger;//报酬，小费，保险
    private int isFarmersInsurance;
    private String businessNumbers;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_pay_order, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mRecyView = (RecyclerView) view.findViewById(R.id.recy_view);
        mSlidLinear = (LinearLayout) view.findViewById(R.id.slid_linear);
        mBottomPanel = (ImageView) view.findViewById(R.id.bottom_panel);
        mDetailLayout = (LinearLayout) view.findViewById(R.id.detail_layout);
        mIncomeText = (TextView) view.findViewById(R.id.income_text);
        mXiaofeiText = (TextView) view.findViewById(R.id.xiaofei_text);
        mBaoLinear = (RelativeLayout) view.findViewById(R.id.bao_linear);
        mBaoxianText = (TextView) view.findViewById(R.id.baoxian_text);
        mTotalTab = (TextView) view.findViewById(R.id.total_tab);
        mTotalText = (TextView) view.findViewById(R.id.total_text);
        mBottomRe2 = (LinearLayout) view.findViewById(R.id.bottom_re2);
        mBottomRe = (RelativeLayout) view.findViewById(R.id.bottom_re);
        mCheckbox = (CheckBox) view.findViewById(R.id.checkbox);
        mTotalsTab = (TextView) view.findViewById(R.id.totals_tab);
        mTotalsText = (TextView) view.findViewById(R.id.totals_text);
        mPayBtn = (TextView) view.findViewById(R.id.pay_btn);

        mBottomPanel.setOnClickListener(this);
        mPayBtn.setOnClickListener(this);

        mRecyView.setLayoutManager(new LinearLayoutManager(mActivity));
        final PayOrderAdapter payOrderAdapter =
                new PayOrderAdapter(mContext, mRecyView, mListBeen, R.layout.item_pay_order);
        payOrderAdapter.setPayAndTipCallBack(this);
        payOrderAdapter.setIsFarmersInsurance(isFarmersInsurance);
        mRecyView.setAdapter(payOrderAdapter);

        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.orange_yellow));
        mSwipeRefresh.setOnRefreshListener(this);

        payOrderAdapter.setOnItemClickListener(new BaseLoadMoreHeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mActivity, HirerMapActivity.class);
                intent.putExtra("bean", mListBeen.get(position));
                intent.putExtra("pay",payOrderAdapter.getEveryDayMoney());
                intent.putExtra("tip",payOrderAdapter.getXiaoFei(position));
                intent.putExtra("baoxian",payOrderAdapter.getDangerMoney());
                startActivity(intent);
            }
        });
        //全选
        mCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckbox.isChecked()) {
                    payOrderAdapter.selectAll(true);
                } else {
                    payOrderAdapter.selectAll(false);
                }
            }
        });
    }

    /**
     * 填充数据
     *
     * @param listBean
     */
    public void setListBean(List<ListBean> listBean) {
        mListBeen.clear();
        mListBeen.addAll(listBean);
    }

    @Override
    public void onRefresh() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.bottom_panel) {
            if (isVisvible) {
                mDetailLayout.setVisibility(View.GONE);
                mBottomPanel.setImageResource(R.mipmap.down_pay_img);
                isVisvible = false;
            } else if (!isVisvible) {
                mDetailLayout.setVisibility(View.VISIBLE);
                mBottomPanel.setImageResource(R.mipmap.up_pay_img);
                isVisvible = true;
            }
        }else if(i == R.id.pay_btn){
            if(pay == 0){
                Toast.makeText(mContext, "请选择雇员支付", Toast.LENGTH_SHORT).show();
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("pay",pay+"");
            bundle.putString("tip",tip+"");
            bundle.putString("danger",danger+"");
            bundle.putString("businessNumbers",businessNumbers);
            ActivityNavigator.navigator().navigateTo(PaymentActivity.class,bundle);

        }
    }

    /**
     * 报酬
     *
     * @param pay
     */
    @Override
    public void payMoney(double pay) {
        this.pay = pay;
        mIncomeText.setText(pay + "元");
        setTitalText();
    }

    /**
     * 小费
     *
     * @param tip
     */
    @Override
    public void tipMoney(double tip) {
        this.tip = tip;
        mXiaofeiText.setText(tip + "元");
        setTitalText();
    }

    /**
     * 保险
     *
     * @param danger
     */
    @Override
    public void dangerMoney(double danger) {
        this.danger = danger;
        mBaoxianText.setText(danger + "元");
        setTitalText();
    }

    @Override
    public void getBusinessNumbers(String businessNumbers) {
        this.businessNumbers = businessNumbers;
    }

    //展示应付多少钱
    private void setTitalText() {
        mTotalsText.setText(getTitalMoney() + "元");
        mTotalText.setText(getTitalMoney() + "元");
    }

    /**
     * 总支出
     */
    private double getTitalMoney() {
        return pay + tip + danger;
    }

    /**
     * 是否有保险
     */
    public void setIsFarmersInsurance(int isFarmersInsurance){
        this.isFarmersInsurance = isFarmersInsurance;
    }
}
