package com.dlg.personal.wallet.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dlg.data.wallet.model.BindBean;
import com.dlg.personal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：关蕤
 * 主要功能：解绑列表适配器
 * 创建时间：2017/7/13 16:22
 */
public class UnBindAdapter extends BaseAdapter {
    private List<BindBean> bindBeanList = new ArrayList<>();
    private LayoutInflater inflater ;
    private Context mContext ;
    private unBindClickListener listener ;

    public UnBindAdapter(List<BindBean> bindBeanList, Context mContext,unBindClickListener listener) {
        this.bindBeanList = bindBeanList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.listener = listener ;
    }

    @Override
    public int getCount() {
        return bindBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return bindBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        BindBean bean = bindBeanList.get(position);
        ViewHolder viewHolder ;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_unbind,parent,false);
            viewHolder.card_user_name = (TextView) convertView.findViewById(R.id.card_user_name);
            viewHolder.tv_unbind = (TextView) convertView.findViewById(R.id.tv_unbind);
            viewHolder.tv_bind_name = (TextView) convertView.findViewById(R.id.tv_bind_name);
            viewHolder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        switch (bean.getPayType()){
            case 1 : //支付宝
                viewHolder.card_user_name.setText("支付宝");
                viewHolder.tv_bind_name .setText(bean.getPayAccount());
                viewHolder.img_icon .setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.zhifu_alipay));
                break;

            case 2 : //微信
                viewHolder.card_user_name.setText("微信");
                viewHolder.tv_bind_name .setText(bean.getPayAccount());
                viewHolder.img_icon .setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.zhifu_weixin));
                break;

            case 3 : //银行卡
                viewHolder.card_user_name.setText("银行卡");
                viewHolder.tv_bind_name .setText(bean.getPayAccount());
                Glide.with(mContext).load(bean.getBankIconUrl()).into(viewHolder.img_icon);
                break;
        }

        viewHolder.tv_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.unBind(position);
            }
        });
        return convertView;
    }

    class ViewHolder{
        TextView tv_unbind ;    //解绑按钮
        ImageView img_icon ;    //类型图标
        TextView tv_bind_name ; //类型名
        TextView card_user_name ;   //账号
    }

    public interface unBindClickListener{
        void unBind(int position);
    }
}
