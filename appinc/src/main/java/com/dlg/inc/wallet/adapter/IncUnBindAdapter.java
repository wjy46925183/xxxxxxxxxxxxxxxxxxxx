package com.dlg.inc.wallet.adapter;

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
import com.dlg.inc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：关蕤
 * 主要功能：解绑列表适配器
 * 创建时间：2017/7/13 16:22
 */
public class IncUnBindAdapter extends BaseAdapter {
    private List<BindBean> bindBeanList = new ArrayList<>();
    private LayoutInflater inflater ;
    private Context mContext ;
    private unBindClickListener listener ;

    public IncUnBindAdapter(List<BindBean> bindBeanList, Context mContext, unBindClickListener listener) {
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
        ViewHolder viewHolder=null ;

		View view = View.inflate(mContext, R.layout.inc_item_unbind, null);
		if (convertView == null) {
			viewHolder = new ViewHolder(view);
			convertView = view;
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

//		if(convertView == null){
//            viewHolder = new ViewHolder();
//            convertView = inflater.inflate(R.layout.inc_item_unbind,parent,false);
//            viewHolder.card_user_name = (TextView) convertView.findViewById(R.id.card_user_name);
//            viewHolder.tv_unbind = (TextView) convertView.findViewById(R.id.tv_unbind);
//            viewHolder.tv_bind_name = (TextView) convertView.findViewById(R.id.tv_bind_name);
//            viewHolder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
//            convertView.setTag(viewHolder);
//        }else{
//            viewHolder = (ViewHolder) convertView.getTag();
//        }

		int payType = bean.getPayType();
		if (payType==1){
			viewHolder.card_user_name.setText("支付宝");
			viewHolder.tv_bind_name .setText(bean.getPayAccount());
			Glide.with(mContext).load(bean.getBankIconUrl()).fitCenter().error(R.mipmap.zhifu_alipay)
					.into(viewHolder.img_icon);

		}else if (payType==2){
			viewHolder.card_user_name.setText("微信");
			viewHolder.tv_bind_name .setText(bean.getPayAccount());
//			viewHolder.img_icon .setImageResource(R.mipmap.zhifu_weixin);
			Glide.with(mContext).load(bean.getBankIconUrl()).fitCenter().error(R.mipmap.zhifu_weixin)
					.into(viewHolder.img_icon);
		}else {
			viewHolder.card_user_name.setText("银行卡");
			viewHolder.tv_bind_name .setText(bean.getPayAccount());
			Glide.with(mContext).load(bean.getBankIconUrl()).fitCenter().into(viewHolder.img_icon);
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

		ViewHolder(View view) {
			card_user_name = (TextView) view.findViewById(R.id.card_user_name);
			tv_unbind = (TextView) view.findViewById(R.id.tv_unbind);
			tv_bind_name = (TextView) view.findViewById(R.id.tv_bind_name);
			img_icon = (ImageView) view.findViewById(R.id.img_icon);
		}


	}

    public interface unBindClickListener{
        void unBind(int position);
    }
}
