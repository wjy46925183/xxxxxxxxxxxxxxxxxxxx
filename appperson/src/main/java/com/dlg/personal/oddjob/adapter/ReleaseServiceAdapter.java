package com.dlg.personal.oddjob.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.common.view.CommonAdapter;
import com.dlg.personal.R;
import com.dlg.personal.oddjob.activity.ReleaseServiceActivity;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import com.common.view.iamge.selector.MultiImageSelector;
import rx.functions.Action1;

/**
 * 作者：王进亚
 * 主要功能：发布服务图片
 * 创建时间：2017/7/14 12:56
 */

public class ReleaseServiceAdapter extends CommonAdapter<String> {
    public ReleaseServiceAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    public int getCount() {
        return datas.size() < 3 ? datas.size() + 1 : datas.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_release_pics, null);
            holder = new MyViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

        if (position == datas.size()) {
            holder.iv_photo.setVisibility(View.GONE);
            holder.iv_delete.setVisibility(View.GONE);
            holder.iv_add.setVisibility(View.VISIBLE);
            holder.iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RxPermissions rxPermissions = new RxPermissions((Activity) context);
                    rxPermissions.request(android.Manifest.permission.CAMERA)
                            .subscribe(new Action1<Boolean>() {
                                @Override
                                public void call(Boolean aBoolean) {
                                    if(aBoolean){
                                        MultiImageSelector.create(context)
                                                .showCamera(true) // show camera or not. true by default
                                                .count(3) // max select image size, 9 by default. used width #.multi()
                                                .single() // single mode
                                                .multi() // multi mode, default mode;
                                                .start((ReleaseServiceActivity) context,2);
                                    }else{
                                        Toast.makeText(context, "未开启相机权限", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            });
        } else {
            holder.iv_delete.setVisibility(View.VISIBLE);
            holder.iv_photo.setVisibility(View.VISIBLE);
            holder.iv_add.setVisibility(View.GONE);
            String s = datas.get(position);
            Log.i("====s==url adapter", s);
            Glide.with(context).load(s).diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_launcher).into(holder.iv_photo);
            holder.iv_delete.setTag(position);
            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    datas.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        return convertView;
    }

    class MyViewHolder {
        ImageView iv_photo, iv_delete, iv_add;

        public MyViewHolder(View itemView) {
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_item_serice);
            iv_add = (ImageView) itemView.findViewById(R.id.iv_item_add);
        }
    }
}
