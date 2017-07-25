package com.dlg.inc.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.common.utils.DateUtils;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.UrlNet;
import com.dlg.data.wallet.model.BankBean;
import com.dlg.data.wallet.model.InvoiceListBean;
import com.dlg.inc.R;

import java.util.List;


/**
 * 作者：邹前坤
 * 主要功能： 发票列表适配器
 * 创建时间：2017/7/11 09:30
 */
public class BankListAdapter extends BaseLoadMoreHeaderAdapter<BankBean> {

    public BankListAdapter(Context mContext, RecyclerView recyclerView, List<BankBean> invoiceListBean, int mLayoutId) {
        super(mContext, recyclerView, invoiceListBean, mLayoutId);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, BankBean invoiceListBean) {
        if (holder instanceof BaseViewHolder){
            ((BaseViewHolder) holder).setText(R.id.tv_bank,invoiceListBean.getBankName());
            ImageView icBank = ((BaseViewHolder) holder).getView(R.id.iv_bank);
            Glide.with(mContext).load(invoiceListBean.getBankIcon()).fitCenter().error(R.mipmap.ic_launcher)
                    .into(icBank);
        }
    }

}
