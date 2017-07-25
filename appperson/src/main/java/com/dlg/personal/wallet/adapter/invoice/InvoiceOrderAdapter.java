package com.dlg.personal.wallet.adapter.invoice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.dlg.data.wallet.model.invoice.InvoiceOrderBean;

import java.util.List;

/**
 * 作者：关蕤
 * 主要功能：发票管理订单列表适配器
 * 创建时间：2017/7/7 11:32
 */
public class InvoiceOrderAdapter extends BaseLoadMoreHeaderAdapter<InvoiceOrderBean>{

    public InvoiceOrderAdapter(Context mContext, RecyclerView recyclerView, List<InvoiceOrderBean> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, InvoiceOrderBean invoiceOrderBean) {

    }
}
