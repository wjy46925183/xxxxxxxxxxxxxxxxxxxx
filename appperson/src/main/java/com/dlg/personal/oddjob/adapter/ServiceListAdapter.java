package com.dlg.personal.oddjob.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.common.view.CommonAdapter;
import com.dlg.data.oddjob.model.ServiceListDataBean;
import com.dlg.personal.R;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/14 10:37
 */

public class ServiceListAdapter extends CommonAdapter<ServiceListDataBean> {
    public ServiceListAdapter(Context context, List<ServiceListDataBean> datas) {
        super(context, datas);
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_service_list, null);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        int serviceMeterUnit = datas.get(position).getServiceMeterUnit();
        String unitName = null;
        if (serviceMeterUnit == 1) {
            unitName = "/天";
        } else if (serviceMeterUnit == 2) {
            unitName = "/时";
        } else if (serviceMeterUnit == 3) {
            unitName = "/单";
        }
        holder.tv_price.setText(datas.get(position).getPrice() + "元" + unitName);
        holder.tv_position.setText(datas.get(position).getServiceName());
        if (datas.get(position).getImagesUrlList() != null && datas.get(position).getImagesUrlList().size() > 0) {
            String s = datas.get(position).getImagesUrlList().get(0);
            Log.i("====s===", s);
            Glide.with(context).load(s).placeholder(R.mipmap.mrtx).into(holder.iv_head);
        }
        return convertView;
    }

    class MyViewHolder {
        TextView tv_position, tv_price;
        ImageView iv_head;
        public MyViewHolder(View itemView) {
            tv_position = (TextView) itemView.findViewById(R.id.tv_position);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_item_myserice);
        }
    }
}
