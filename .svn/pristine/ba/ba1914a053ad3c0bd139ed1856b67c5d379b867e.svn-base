package com.dlg.personal.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.common.utils.DateUtils;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.wallet.model.BillDetailListBean;
import com.dlg.data.wallet.model.InvoiceListBean;
import com.dlg.personal.R;

import java.util.List;


/**
 * 作者：邹前坤
 * 主要功能： 发票列表适配器
 * 创建时间：2017/7/11 09:30
 */
public class InvoiceDetailsListAdapter extends BaseLoadMoreHeaderAdapter<BillDetailListBean> {

    public InvoiceDetailsListAdapter(Context mContext, RecyclerView recyclerView, List<BillDetailListBean> invoiceListBean, int mLayoutId) {
        super(mContext, recyclerView, invoiceListBean, mLayoutId);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, BillDetailListBean invoiceListBean) {
        if (holder instanceof BaseViewHolder){

            ((BaseViewHolder) holder).setText(R.id.tv_receipt_bill_time, DateUtils.dateFormat("yyyy-MM-dd HH:mm:ss",invoiceListBean.getCreateTime()));

            ((BaseViewHolder) holder).setText(R.id.tv_receipt_bill_us, "付给"+invoiceListBean.getName());
            ((BaseViewHolder) holder).setText(R.id.tv_receipt_bill_price,invoiceListBean.getAmount()+"元");

        }
    }

}
