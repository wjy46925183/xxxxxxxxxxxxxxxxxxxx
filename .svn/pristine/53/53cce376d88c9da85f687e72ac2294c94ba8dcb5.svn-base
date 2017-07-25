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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.utils.DialogUtils;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.dlg.data.oddjob.model.ListBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseFragment;
import com.dlg.personal.oddjob.activity.HirerMapActivity;
import com.dlg.personal.oddjob.adapter.HasOderAdapter;
import com.dlg.viewmodel.oddjob.OddLotsHandleViewModel;
import com.dlg.viewmodel.oddjob.presenter.OddLotsPresenter;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 作者：王进亚
 * 主要功能：有人接单
 * 创建时间：2017/7/10 10:26
 */

public class HasOrderFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener, OddLotsPresenter {
    private static final String NEXTSTATUSCODE_AGREE = 20 + "";
    private static final String NEXTSTATUSCODE_DENY = 11 + "";


    private SwipeRefreshLayout mSwipeRefresh;
    private RecyclerView mRecyHas;
    private RelativeLayout mAllSelectRe;
    private CheckBox mAllSelectCbox;
    private TextView mAllSelectText;
    private Button mCancleBtn;
    private Button mAgreeBtn;
    private List<ListBean> mListBeen = new ArrayList<>();
    private HasOderAdapter mHasOderAdapter;
    private SweetAlertDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_has, null);
        initView(inflate);
        return inflate;
    }

    private void initView(View view) {
        mSwipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mRecyHas = (RecyclerView) view.findViewById(R.id.recy_has);
        mAllSelectRe = (RelativeLayout) view.findViewById(R.id.all_select_re);
        mAllSelectCbox = (CheckBox) view.findViewById(R.id.all_select_cbox);
        mAllSelectText = (TextView) view.findViewById(R.id.all_select_text);
        mCancleBtn = (Button) view.findViewById(R.id.cancle_btn);
        mAgreeBtn = (Button) view.findViewById(R.id.agree_btn);
        mAgreeBtn.setOnClickListener(this);
        mCancleBtn.setOnClickListener(this);

        mRecyHas.setLayoutManager(new LinearLayoutManager(mActivity));
        mHasOderAdapter = new HasOderAdapter(mActivity, mRecyHas, mListBeen, R.layout.item_has_ordered);
        mHasOderAdapter.setAllCheckBox(mAllSelectCbox);
        mRecyHas.setAdapter(mHasOderAdapter);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.orange_yellow));
        mSwipeRefresh.setOnRefreshListener(this);

        mHasOderAdapter.setOnItemClickListener(new BaseLoadMoreHeaderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mActivity, HirerMapActivity.class);
                intent.putExtra("bean", mListBeen.get(position));
                startActivity(intent);
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
        if (i == R.id.cancle_btn) {//拒绝
            mDialog = DialogUtils.loadingProgressDialog(mContext);

            OddLotsHandleViewModel oddLotsHandleViewModel = new OddLotsHandleViewModel(mContext, this, this);
            oddLotsHandleViewModel.toHandleLots(mHasOderAdapter.getBusinessNumbers()
                    , NEXTSTATUSCODE_DENY, NEXTSTATUSCODE_DENY);
        } else if (i == R.id.agree_btn) {//赞同
            mDialog = DialogUtils.loadingProgressDialog(mContext);
            OddLotsHandleViewModel oddLotsHandleViewModel = new OddLotsHandleViewModel(mContext, this, this);
            oddLotsHandleViewModel.toHandleLots(mHasOderAdapter.getBusinessNumbers()
                    , NEXTSTATUSCODE_AGREE, NEXTSTATUSCODE_AGREE);
        }
    }

    @Override
    public void hanleLots(String s) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void requestNext(String msg) {
        super.requestNext(msg);
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        mActivity.finish();//关闭页面
    }

    @Override
    public void requestComplete() {
        super.requestComplete();
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
