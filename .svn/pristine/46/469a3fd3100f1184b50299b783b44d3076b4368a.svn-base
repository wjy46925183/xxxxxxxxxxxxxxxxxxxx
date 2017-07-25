package com.dlg.inc.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.common.utils.DateUtils;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.wallet.model.InvoiceListBean;
import com.dlg.inc.R;


import java.util.List;


/**
 * 作者：邹前坤
 * 主要功能： 发票列表适配器
 * 创建时间：2017/7/11 09:30
 */
public class IncInvoiceListAdapter extends BaseLoadMoreHeaderAdapter<InvoiceListBean> {

    public IncInvoiceListAdapter(Context mContext, RecyclerView recyclerView, List<InvoiceListBean> invoiceListBean, int mLayoutId) {
        super(mContext, recyclerView, invoiceListBean, mLayoutId);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, InvoiceListBean invoiceListBean) {
        if (holder instanceof BaseViewHolder){
            // TODO 这里要处理
            /**
             *  if(position==0){
             viewHolder.rl_day_bg.setVisibility(View.VISIBLE);
             }else {
             viewHolder.rl_day_bg.setVisibility(View.GONE);
             }

             viewHolder.tv_bill_price.setText(Math.abs(jsonMap.getDouble("amount", 0)) + "元");
             viewHolder.tv_bill_day.setText(DateUtils.getDatedian_yyyy_MM_dd(jsonMap.getString("createTime")));
             viewHolder.tv_bill_time.setText(DateUtils.getDate_Hm(jsonMap.getString("createTime")));
             viewHolder.tv_bill_month.setText(DateUtils.getDate_yyyyAndMM(jsonMap.getString("createTime")));
             viewHolder.cb_bill_check.setChecked(isAllSelect);
             */
            if ( invoiceListBean.getShowDate()!=null&&invoiceListBean.getShowDate().equals("0")){
                ((BaseViewHolder) holder).setVisible(R.id.invoice_list_rl_totle,true);
                ((BaseViewHolder) holder).setVisible(R.id.view_line,false);
            }else {
                ((BaseViewHolder) holder).setVisible(R.id.invoice_list_rl_totle,false);
                ((BaseViewHolder) holder).setVisible(R.id.view_line,true);
            }
            if (invoiceListBean.getSelect()){
                ((BaseViewHolder) holder).setChecked(R.id.invoice_list_check,true);

            }else {
                ((BaseViewHolder) holder).setChecked(R.id.invoice_list_check,false);
            }
            ((BaseViewHolder) holder).setText(R.id.invoice_list_tv_month, DateUtils.getDate_yyyyAndMM(invoiceListBean.getCreateTime()));

            ((BaseViewHolder) holder).setText(R.id.invoice_list_tv_day, DateUtils.getDatedian_yyyy_MM_dd(invoiceListBean.getCreateTime()));
            ((BaseViewHolder) holder).setText(R.id.invoice_list_tv_time, DateUtils.getDate_Hm(invoiceListBean.getCreateTime()));


            ((BaseViewHolder) holder).setText(R.id.invoice_list_tv_money,Math.abs(invoiceListBean.getAmount()) +"元");
           // ((BaseViewHolder) holder).setText(R.id.invoice_list_tv_type,invoiceListBean.getTradeType()+"");
            ((BaseViewHolder) holder).setText(R.id.invoice_list_tv_order,invoiceListBean.getPayeeId());
        }
    }

}
