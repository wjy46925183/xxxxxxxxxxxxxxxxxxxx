package com.dlg.personal.wallet.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlg.data.wallet.model.BindBean;
import com.dlg.personal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/12 14:54
 */
public class CashAdapter extends BaseAdapter {

    private List<BindBean> bindBeens = new ArrayList<>();   //绑定的卡的集合
    private LayoutInflater inflater ;
    private Context mContext ;
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private String type ;
    private CheckBoxChangeListener listener ;

    public String getType() {
        return type;
    }

    public CashAdapter(Context context , List<BindBean> bindBeens,CheckBoxChangeListener listener) {
        this.bindBeens = bindBeens;
        mContext = context ;
        this.listener = listener ;
        inflater = LayoutInflater.from(context);
    }

    public CashAdapter(Context context , List<BindBean> bindBeens) {
        this.bindBeens = bindBeens;
        mContext = context ;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bindBeens.size();
    }

    @Override
    public Object getItem(int position) {
        return bindBeens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final BindBean bean = bindBeens.get(position);
        final ViewHolder viewHolder ;
        if(convertView == null){
                viewHolder = new ViewHolder() ;
                convertView = inflater.inflate(R.layout.item_cash_bind,parent,false);
                viewHolder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
                viewHolder.tv_bind_name = (TextView) convertView.findViewById(R.id.tv_bind_name);
                viewHolder.img_to_bind = (ImageView) convertView.findViewById(R.id.img_to_bind);
                viewHolder.card_user_name = (TextView) convertView.findViewById(R.id.card_user_name);
                viewHolder.check_type = (CheckBox) convertView.findViewById(R.id.check_type);
                convertView.setTag(viewHolder);
        }else{
                viewHolder = (ViewHolder) convertView.getTag();
        }

        if(bindBeens.get(position).getPayType() == 1){      //支付宝
            if(bean.getPayAccount() != null && !"".equals(bean.getPayAccount())){
                viewHolder.card_user_name
                        .setText(bean.getPayAccount());
                viewHolder.img_icon.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.zhifu_alipay));
                viewHolder.tv_bind_name.setText("支付宝");
                viewHolder.check_type.setVisibility(View.VISIBLE);
                viewHolder.img_to_bind.setVisibility(View.GONE);
                viewHolder.img_to_bind.setTag(bean.getPayType());
                checkBoxes.add(viewHolder.check_type);
            }else{
                viewHolder.card_user_name
                        .setText("未绑定");
                viewHolder.img_icon.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.zhifu_alipay));
                viewHolder.tv_bind_name.setText("支付宝");
                viewHolder.check_type.setVisibility(View.GONE);
                viewHolder.img_to_bind.setVisibility(View.VISIBLE);
            }
        }else  if(bindBeens.get(position).getPayType() == 2){   //微信
            if(bean.getPayAccount() != null && !"".equals(bean.getPayAccount())){
                viewHolder.card_user_name
                        .setText(bean.getPayAccount());
                viewHolder.img_icon.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.zhifu_weixin));
                viewHolder.tv_bind_name.setText("微信");
                viewHolder.check_type.setVisibility(View.VISIBLE);
                viewHolder.img_to_bind.setVisibility(View.GONE);
                viewHolder.img_to_bind.setTag(bean.getPayType());
                checkBoxes.add(viewHolder.check_type);
            }else{
                viewHolder.card_user_name
                        .setText("未绑定");
                viewHolder.img_icon.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.zhifu_weixin));
                viewHolder.tv_bind_name.setText("微信");
                viewHolder.check_type.setVisibility(View.GONE);
                viewHolder.img_to_bind.setVisibility(View.VISIBLE);
            }
        }else{  //银行卡
            if(bean.getPayAccount() != null && !"".equals(bean.getPayAccount())){
                viewHolder.card_user_name
                        .setText(bean.getPayAccount());
                viewHolder.tv_bind_name.setText("银行卡");
                viewHolder.check_type.setVisibility(View.VISIBLE);
                viewHolder.img_to_bind.setVisibility(View.GONE);
                Glide.with(mContext).load(bean.getBankIconUrl()).into(viewHolder.img_icon);
                viewHolder.img_to_bind.setTag(bean.getPayType());
                checkBoxes.add(viewHolder.check_type);
            }else{
                viewHolder.card_user_name
                        .setText("未绑定");
                viewHolder.img_icon.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.zhifu_ka));
                viewHolder.tv_bind_name.setText("银行卡");
                viewHolder.check_type.setVisibility(View.GONE);
                viewHolder.img_to_bind.setVisibility(View.VISIBLE);
            }
        }
//        else{  //企业账户，目前1对应支付宝 2对应微信   3 对应银行卡  这边添加个判断即可，并将对应的图标修改
//            if(bean.getPayAccount() != null && !"".equals(bean.getPayAccount())){
//                viewHolder.card_user_name
//                        .setText(bean.getPayAccount());
//                viewHolder.tv_bind_name.setText("公司账户");
//                viewHolder.check_type.setVisibility(View.VISIBLE);
//                viewHolder.img_to_bind.setVisibility(View.GONE);
//                Glide.with(mContext).load(bean.getBankIconUrl()).into(viewHolder.img_icon);
//                viewHolder.img_to_bind.setTag(bean.getPayType());
//                checkBoxes.add(viewHolder.check_type);
//            }else{
//                viewHolder.card_user_name
//                        .setText("未绑定");
//                viewHolder.img_icon.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.zhifu_ka));
//                viewHolder.tv_bind_name.setText("公司账户");
//                viewHolder.check_type.setVisibility(View.GONE);
//                viewHolder.img_to_bind.setVisibility(View.VISIBLE);
//            }
//        }
        viewHolder.check_type.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    for (int i = 0 ; i < checkBoxes.size() ;i++ ){
                        checkBoxes.get(i).setChecked(false);
                    }
                    viewHolder.check_type.setChecked(true);
                    type = position + 1 + "";
                    listener.change(bean.getPayAccounthouseholder(),bean.getBindId());
                }else{
                    type ="";
                    listener.change(bean.getPayAccounthouseholder(),bean.getBindId());
                }
            }
        });
        return convertView;
    }

    class ViewHolder{
        ImageView img_icon ;
        TextView tv_bind_name ; //绑定类型名（支付宝，微信或者银行卡）
        TextView card_user_name ;   //  绑定的账户名
        ImageView img_to_bind ; //去绑定的标识
        CheckBox check_type ;   //提现账户选择
    }

    public interface CheckBoxChangeListener{
        void change(String userName,String bindId);
    }
}
