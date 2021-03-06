package com.dlg.personal.oddjob.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.common.sys.ActivityNavigator;
import com.common.utils.DialogUtils;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.common.model.ButtonBean;
import com.dlg.data.oddjob.model.ListBean;
import com.dlg.personal.R;

import com.dlg.personal.oddjob.activity.CancleActivity;
import com.dlg.personal.oddjob.activity.OddActivity;
import com.dlg.personal.oddjob.view.HirerPublicView;
import com.dlg.viewmodel.common.OrderOperaButViewModel;
import com.dlg.viewmodel.common.presenter.SuccessObjectPresenter;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：正在进行中 20.等待开工 21.干活中 22.等待验收
 * 创建时间：2017/7/10 11:55
 */

public class DoingOrderAdapter extends BaseLoadMoreHeaderAdapter<ListBean> implements SuccessObjectPresenter {

    private Dialog mDialog;
    private OrderOperaButViewModel mOddHandleOrderViewModel;

    public DoingOrderAdapter(Context mContext, RecyclerView recyclerView, List<ListBean> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
        mOddHandleOrderViewModel = new OrderOperaButViewModel(mContext, null, DoingOrderAdapter.this);
    }

    @Override
    public void convert(final Context mContext, RecyclerView.ViewHolder holder, int position, final ListBean listBean) {
        if (holder instanceof BaseViewHolder) {
            HirerPublicView view = ((BaseViewHolder) holder).getView(R.id.public_view);
            view.setContent(listBean);
            view.getCheckBox().setVisibility(View.GONE);

            final TextView tvStatus = ((BaseViewHolder) holder).getView(R.id.status_text);
            TextView tvCancleReseason = ((BaseViewHolder) holder).getView(R.id.cancle_reseason_text);
            TextView btnCancle = ((BaseViewHolder) holder).getView(R.id.cancle_btn);
            TextView tvAgree = ((BaseViewHolder) holder).getView(R.id.agree_tv);

            tvCancleReseason.setVisibility(View.GONE);
            btnCancle.setVisibility(View.GONE);
            tvAgree.setVisibility(View.GONE);

            switch (listBean.status) {
                case 6:
                    tvCancleReseason.setVisibility(View.VISIBLE);
                    btnCancle.setVisibility(View.GONE);
                    tvAgree.setVisibility(View.GONE);
                    tvStatus.setText("等待雇员同意");
                    tvCancleReseason.setText("知道了");
                    break;
                case 7:
                    tvCancleReseason.setVisibility(View.VISIBLE);
                    btnCancle.setVisibility(View.GONE);
                    tvAgree.setVisibility(View.GONE);
                    tvStatus.setText("拒绝邀请");
                    tvCancleReseason.setText("知道了");
                    break;
                case 20:
                    tvCancleReseason.setVisibility(View.VISIBLE);
                    btnCancle.setVisibility(View.GONE);
                    tvAgree.setVisibility(View.GONE);
                    tvStatus.setText("等待开工");
                    tvCancleReseason.setText("取消");
                    tvCancleReseason.setOnClickListener(new View.OnClickListener() {//取消城信用值
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, CancleActivity.class);
                            intent.putExtra("businessNumber", listBean.getBusinessNumber());
                            intent.putExtra("isWY", false);
                            mContext.startActivity(intent);
                        }
                    });
                    break;
                case 21:
                    tvStatus.setText("干活中");
                    tvCancleReseason.setVisibility(View.GONE);
                    btnCancle.setVisibility(View.VISIBLE);
                    tvAgree.setVisibility(View.VISIBLE);
                    tvAgree.setText("确认完成");
                    btnCancle.setText("取消");
                    btnCancle.setOnClickListener(new View.OnClickListener() {//取消城信用值 和 赔偿金
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, CancleActivity.class);
                            intent.putExtra("businessNumber", listBean.getBusinessNumber());
                            intent.putExtra("isWY", true);
                            mContext.startActivity(intent);
                        }
                    });
                    tvAgree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDialog = DialogUtils.showLoadingDialog(mContext);
                            ButtonBean buttonBean = new ButtonBean();
                            buttonBean.setOperateStatusCode(30);
                            buttonBean.setNextStatusCode(30);
                            mOddHandleOrderViewModel.updateOrderOperaStatus(buttonBean, listBean.getBusinessNumber());
                        }
                    });
                    break;
                case 22:
                    tvStatus.setText("等待验收");
                    tvCancleReseason.setVisibility(View.GONE);
                    btnCancle.setVisibility(View.VISIBLE);
                    tvAgree.setVisibility(View.VISIBLE);
                    tvAgree.setText("通过");
                    btnCancle.setText("不通过");
                    tvAgree.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDialog = DialogUtils.showLoadingDialog(mContext);
                            ButtonBean buttonBean = new ButtonBean();
                            buttonBean.setOperateStatusCode(25);
                            buttonBean.setNextStatusCode(30);
                            mOddHandleOrderViewModel.updateOrderOperaStatus(buttonBean, listBean.getBusinessNumber());
                        }
                    });
                    btnCancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDialog = DialogUtils.showLoadingDialog(mContext);
                            ButtonBean buttonBean = new ButtonBean();
                            buttonBean.setOperateStatusCode(26);
                            buttonBean.setNextStatusCode(21);
                            mOddHandleOrderViewModel.updateOrderOperaStatus(buttonBean, listBean.getBusinessNumber());
                        }
                    });
                    break;
            }
        }
    }

    @Override
    public void onSuccess(boolean success) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        ActivityNavigator.navigator().navigateTo(OddActivity.class);
    }
}
