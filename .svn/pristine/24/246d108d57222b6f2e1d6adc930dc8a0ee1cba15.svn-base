package com.dlg.inc.wallet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.common.cache.ACache;
import com.common.utils.DateUtils;
import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.wallet.model.WalletListBean;
import com.dlg.inc.R;
import com.dlg.viewmodel.key.AppKey;

import java.util.Calendar;
import java.util.List;


/**
 * 作者：邹前坤
 * 主要功能： 发票列表适配器
 * 创建时间：2017/7/11 09:30
 */
public class IncWalletDetailListAdapter extends BaseLoadMoreHeaderAdapter<WalletListBean> {

    public IncWalletDetailListAdapter(Context mContext, RecyclerView recyclerView, List<WalletListBean> invoiceListBean, int mLayoutId) {
        super(mContext, recyclerView, invoiceListBean, mLayoutId);
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, int position, WalletListBean invoiceListBean) {
        if (holder instanceof BaseViewHolder){
            // TODO 这里要处理
            if ( invoiceListBean.getShowTitle()!=null&&invoiceListBean.getShowTitle().equals("0")){
                ((BaseViewHolder) holder).setVisible(R.id.rl_title,true);
                ((BaseViewHolder) holder).setVisible(R.id.tv_title_line,false);
            }else {
                ((BaseViewHolder) holder).setVisible(R.id.rl_title,false);
                ((BaseViewHolder) holder).setVisible(R.id.tv_title_line,true);
            }


            Calendar cal = Calendar.getInstance();

            int year = Integer.parseInt(DateUtils.dateTimeFormat("yyyy", invoiceListBean.getCreateTime()));//创建年
            int month = Integer.parseInt(DateUtils.dateTimeFormat("MM", invoiceListBean.getCreateTime()));//创建月份
            int day = Integer.parseInt(DateUtils.dateTimeFormat("dd", invoiceListBean.getCreateTime()));//创建天
            int currentYear = cal.get(Calendar.YEAR);
            int currentMonth = cal.get(Calendar.MONTH) + 1;
            int currentDay = cal.get(Calendar.DATE);
            if (month == currentMonth && year == currentYear) {
                ((BaseViewHolder) holder).setText(R.id.tv_title_time,"本月");
            } else {
                ((BaseViewHolder) holder).setText(R.id.tv_title_time,DateUtils.dateTimeFormat("yyyy年MM月", invoiceListBean.getCreateTime()));
            }

            if (day == currentDay && year == currentYear && month == currentMonth) {
                ((BaseViewHolder) holder).setText(R.id.tv_month,"今天");
            } else if (day == (currentDay - 1) && year == currentYear && month == currentMonth) {
                ((BaseViewHolder) holder).setText(R.id.tv_month,"昨天");
            } else {
                ((BaseViewHolder) holder).setText(R.id.tv_month,DateUtils.dateTimeFormat("dd日", invoiceListBean.getCreateTime()));
            }
            ((BaseViewHolder) holder).setText(R.id.tv_date,DateUtils.dateTimeFormat("HH:mm", invoiceListBean.getCreateTime()));


            String status = invoiceListBean.getStatus()+"";
            String tradeStatus = "";
            if(status.equals("0")){
                tradeStatus ="待支付";
                ((BaseViewHolder) holder).setTextColor(R.id.tv_status,mContext.getResources().getColor(R.color.right_change_textcolor));
            }else if(status.equals("1")){
                tradeStatus ="进行中";
                ((BaseViewHolder) holder).setTextColor(R.id.tv_status,mContext.getResources().getColor(R.color.right_change_textcolor));
            }else if(status.equals("2")){
                tradeStatus ="成功";
                ((BaseViewHolder) holder).setTextColor(R.id.tv_status,mContext.getResources().getColor(R.color.green_textcolor));
            } else if(status.equals("3")){
                tradeStatus ="失败";
                ((BaseViewHolder) holder).setTextColor(R.id.tv_status,mContext.getResources().getColor(R.color.textColor_alert_button_destructive));
            }
            ((BaseViewHolder) holder).setText(R.id.tv_status,"交易"+tradeStatus);

            String payeeId = invoiceListBean.getPayeeId();
            String userId =  ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID);
            String unit="";
            if(TextUtils.equals(userId,payeeId)){
                unit = "+" ;
            }else {
                unit = "-" ;
            }
            ((BaseViewHolder) holder).setText(R.id.tv_money,unit+invoiceListBean.getAmount()+"元");


            int tradeType = invoiceListBean.getTradeType();
            //TODO  这里的 tradeType是从几开始的
            StringBuilder type=new StringBuilder();
            String[] tradeTypes = mContext.getResources().getStringArray(R.array.inc_wallet_list_trade_type);
            if (tradeType<=tradeTypes.length){
                type.append(tradeTypes[tradeType-1]);
            }


            int paymentType = Integer.parseInt(invoiceListBean.getPaymentType());
            String[] paymentTypes = mContext.getResources().getStringArray(R.array.inc_payment_type);
            if (paymentType<paymentTypes.length){
                type.append(paymentTypes[paymentType]);
            }
            ((BaseViewHolder) holder).setText(R.id.tv_balance,type.toString());


        }
    }

}
