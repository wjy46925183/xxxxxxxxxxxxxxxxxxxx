package com.dlg.inc.wallet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlg.data.wallet.model.TypeBean;
import com.dlg.inc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：关蕤
 * 主要功能：充值类型选择列表适配器
 * 创建时间：2017/7/10 16:25
 */
public class IncRechargeAdapter extends BaseAdapter {
    private List<TypeBean> typeBeanList = new ArrayList<>();
    private LayoutInflater inflater ;
    private  getSelectInterface getSelectInterface;
    private int mPosition = -1;

    public IncRechargeAdapter(List<TypeBean> typeBeanList, Context context, getSelectInterface getSelectInterface) {
        this.typeBeanList = typeBeanList;
        this.getSelectInterface=getSelectInterface;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return typeBeanList.size();
    }

    @Override
    public TypeBean getItem(int position) {
        return typeBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        final TypeBean typeBean = typeBeanList.get(position);
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.inc_item_recharge,parent,false);
            viewHolder.mCheckType = (CheckBox) convertView.findViewById(R.id.check_type);
            viewHolder.mTypeIcon = (ImageView) convertView.findViewById(R.id.type_icon);
            viewHolder.mTypeName = (TextView) convertView.findViewById(R.id.type_name);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTypeName.setText(typeBean.getName());
        viewHolder.mTypeIcon.setBackground(typeBean.getDrawable());
        viewHolder.mCheckType.setChecked(mPosition == position);
        viewHolder.mCheckType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPosition != position){
                    mPosition = position;
                }else {
                    mPosition = -1;
                }
//                for(int i = 0 ;i<typeBeanList.size();i++){
//                    if(position == i){
//                        typeBeanList.get(position).setSelect(!typeBean.isSelect());
//                    }else{
//                        typeBeanList.get(i).setSelect(false);
//                    }
//                }
                getSelectInterface.getSelect(mPosition);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    public interface getSelectInterface{
        void getSelect(int position);
    }
    class ViewHolder{
            ImageView mTypeIcon ;
            TextView mTypeName ;
            CheckBox mCheckType ;
    }

    public int getPosition() {
        return mPosition;
    }
}
