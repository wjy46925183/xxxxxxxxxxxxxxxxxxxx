package com.dlg.personal.oddjob.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.view.loadmore.BaseLoadMoreHeaderAdapter;
import com.common.view.loadmore.BaseViewHolder;
import com.dlg.data.oddjob.model.ListBean;
import com.dlg.personal.R;
import com.dlg.personal.oddjob.view.HirerPublicView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：王进亚
 * 主要功能：付款给雇员
 * 创建时间：2017/7/10 15:57
 */

public class PayOrderAdapter extends BaseLoadMoreHeaderAdapter<ListBean> {
    private List<Boolean> selected = new ArrayList<>();
    private Map<Integer, Double> tips = new HashMap<>();
    private double mTotalPrice;
    private String mDanger = "0";
    private int isFarmersInsurance;

    public PayOrderAdapter(Context mContext, RecyclerView recyclerView, List<ListBean> mDatas, int mLayoutId) {
        super(mContext, recyclerView, mDatas, mLayoutId);
        selected.clear();
        for (int i = 0; i < mDatas.size(); i++) {
            selected.add(false);
            tips.put(i, 0.0);
        }
    }

    @Override
    public void convert(Context mContext, RecyclerView.ViewHolder holder, final int position, ListBean listBean) {
        if (holder instanceof BaseViewHolder) {
            HirerPublicView view = ((BaseViewHolder) holder).getView(R.id.public_view);
            view.setContent(listBean);
            CheckBox checkBox = view.getCheckBox();
            checkBox.setChecked(selected.get(position));

            TextView tvIncome = ((BaseViewHolder) holder).getView(R.id.income_text);
            final EditText editText = ((BaseViewHolder) holder).getView(R.id.xiaofei_edit);
            TextView tvBaoxian = ((BaseViewHolder) holder).getView(R.id.baoxian_text);
            mTotalPrice = listBean.getTotalPrice();
            tvIncome.setText(mTotalPrice + "元");
            RelativeLayout relativeLayout = ((BaseViewHolder) holder).getView(R.id.baoxian_linear);
            mDanger = listBean.insuranceAmount == null ? "2" : listBean.insuranceAmount;
            if (isFarmersInsurance == 1) {
                tvBaoxian.setVisibility(View.VISIBLE);
                tvBaoxian.setText(mDanger +"元");
            } else {
                relativeLayout.setVisibility(View.GONE);
                mDanger = "0";
            }
            /**
             * 是否被选择
             */
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    selected.set(position, isChecked);
                    double pay = getPay();
                    double danger = getDanger();
                    double tip = getTip();
                    String bussinesses = getBussinesses();
                    if (mPayAndTipCallBack != null) {
                        mPayAndTipCallBack.payMoney(pay);
                        mPayAndTipCallBack.dangerMoney(danger);
                        mPayAndTipCallBack.tipMoney(tip);
                        mPayAndTipCallBack.getBusinessNumbers(bussinesses);
                    }
                }
            });
            /**
             * 检测editText输入值
             */
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(TextUtils.isEmpty(s)){
                        tips.put(position, 0.0);
                    }else{
                        double tip = Double.parseDouble(s.toString());
                        if(tip > mTotalPrice){
                            editText.setText(mTotalPrice+"");
                            return;
                        }
                        tips.put(position, tip);
                    }
                    double tip = getTip();
                    if (mPayAndTipCallBack != null) {
                        mPayAndTipCallBack.tipMoney(tip);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    /**
     * 获取所有选择的报酬 元
     *
     * @return
     */
    public double getPay() {
        double pay = 0;
        for (int i = 0; i < selected.size(); i++) {
            if (selected.get(i)) {
                pay += mTotalPrice;
            }
        }
        return pay;
    }

    /**
     * 获取所有的选择的小费总数
     */
    public double getTip() {
        double tip = 0;
        for (int i = 0; i < selected.size(); i++) {
            if (selected.get(i)) {
                tip += tips.get(i);
            }
        }
        return tip;
    }

    public double getDanger(){
        double danger = 0;
        for (int i = 0; i < selected.size(); i++) {
            if(selected.get(i)){
                danger += Double.parseDouble(mDanger);
            }
        }
        return danger;
    }

    /**
     * 订单编号
     * @return
     */
    public String getBussinesses(){
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < selected.size(); i++) {
            if(selected.get(i)){
                String businessNumber = mDatas.get(i).getBusinessNumber();
                if(!TextUtils.isEmpty(stringBuffer.toString())){
                    stringBuffer.append(",");
                }
                stringBuffer.append(businessNumber+"#"+tips.get(i));
            }
        }
        return stringBuffer.toString();
    }
    /**
     * 接口回调
     */
    private PayAndTipCallBack mPayAndTipCallBack;

    public void setPayAndTipCallBack(PayAndTipCallBack payAndTipCallBack) {
        this.mPayAndTipCallBack = payAndTipCallBack;
    }

    public interface PayAndTipCallBack {
        void payMoney(double pay);

        void tipMoney(double tip);

        void dangerMoney(double danger);

        void getBusinessNumbers(String businessNumbers);
    }

    /**
     * 全选
     */
    public void selectAll(boolean isSelectAll){
        for (int i = 0; i < selected.size(); i++) {
            if(isSelectAll){
                selected.set(i,true);
            }else{
                selected.set(i,false);
            }
        }
        notifyDataSetChanged();
    }

    public void setIsFarmersInsurance(int isFarmersInsurance){
        this.isFarmersInsurance = isFarmersInsurance;
    }

    /**
     * 每天付的钱数
     * @return
     */
    public double getEveryDayMoney(){
        return mTotalPrice;
    }

    /**
     * 小费
     * @param postion
     * @return
     */
    public double getXiaoFei(int postion){
        Double aDouble = tips.get(postion);
        return aDouble;
    }

    /**
     * 保险费用单个的
     * @return
     */
    public double getDangerMoney(){
        return Double.parseDouble(mDanger);
    }
}
