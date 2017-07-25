package com.dlg.personal.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.common.utils.DateUtils;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.wallet.model.BillHistoryBean;
import com.dlg.personal.R;

import java.util.List;


/**
 * 作者：邹前坤
 * 主要功能： 发票列表适配器
 * 创建时间：2017/7/11 09:30
 */
public class InvoiceHistoryListAdapter extends BaseLoadMoreHeaderAdapter<BillHistoryBean> {

    public InvoiceHistoryListAdapter(Context mContext, RecyclerView recyclerView, List<BillHistoryBean> invoiceListBean, int mLayoutId) {
        super(mContext, recyclerView, invoiceListBean, mLayoutId);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, BillHistoryBean billBean) {
        if (holder instanceof BaseViewHolder){
            /**
             *
             viewHolder.tvBillingHistoryPrice.setText(dataBean.getInvoiceAmount()+"");
             // JSONObject invoiceInfo = new JSONObject(jsonMap.getString("invoiceInformationRestVo"));
             */
            ((BaseViewHolder) holder).setText(R.id.tv_invoice_history_price,billBean.getInvoiceAmount()+"元");
            ((BaseViewHolder) holder).setText(R.id.tv_invoice_history_title,billBean.getInvoiceInformationRestVo().getCompanyName());
            ((BaseViewHolder) holder).setText(R.id.tv_invoice_history_time, DateUtils.dateTimeFormat("yyyy.MM.dd HH:mm", billBean.getCreateTime()));
            /**
             *  android:textColor="@color/green_textcolor"
             android:textColor="@color/tab_selected_textcolor"
             */
            if (billBean.getStatus()==1){
                ((BaseViewHolder) holder).setText(R.id.tv_invoice_type_advance,"申请中");
                ((BaseViewHolder) holder).setTextColor(R.id.tv_invoice_type_advance,R.color.tab_selected_textcolor);
            }else {
                ((BaseViewHolder) holder).setText(R.id.tv_invoice_type_advance,"已寄出");
                ((BaseViewHolder) holder).setTextColor(R.id.tv_invoice_type_advance,R.color.green_textcolor);
            }

        }
    }

}
