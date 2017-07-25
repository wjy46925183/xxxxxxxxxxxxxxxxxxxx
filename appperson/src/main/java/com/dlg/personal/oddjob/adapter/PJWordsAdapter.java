package com.dlg.personal.oddjob.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.dlg.data.home.model.DictionaryBean;
import com.dlg.personal.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 评价页的评价文字选择。
 */
public class PJWordsAdapter extends BaseAdapter {

    private boolean mIsShowDeleteCheckBox;
    ViewHolder holder;
    private Context context;
    private List<DictionaryBean> list;
    private List<Boolean> booleens = new ArrayList<>();

    public PJWordsAdapter(Context context, List<DictionaryBean> list) {
        this.context = context;
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            booleens.add(false);
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public DictionaryBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pj, null);
            holder = new ViewHolder();
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.check_box);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DictionaryBean item = getItem(position);
        holder.checkBox.setText(item.getDataValue());

        booleens.set(position, holder.checkBox.isChecked());
        if (holder.checkBox.isChecked()) {
            holder.checkBox.setTextColor(context.getResources().getColor(R.color.yellow_textcolor));
            holder.checkBox.setBackgroundResource(R.drawable.gw_seleted_item_bg);
        } else {
            holder.checkBox.setTextColor(context.getResources().getColor(R.color.gw_unselect_textcolor));
            holder.checkBox.setBackgroundResource(R.drawable.gw_unseleted_item_bg);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                notifyDataSetChanged();
            }
        });
        return convertView;
    }


    class ViewHolder {
        CheckBox checkBox;
    }

    /**
     * 刷新数据，切换内容
     */
    public void setEvaluateItems(List<DictionaryBean> list) {
        this.list = list;
        notifyDataSetChanged();
        booleens.clear();
        for (int i = 0; i < list.size(); i++) {//初始化选择
            booleens.add(false);
        }
    }

    /**
     * 获取标签
     * @return
     */
    public String getTabsFromView() {
        String result = "";

        for (int i = 0; i < booleens.size(); i++) {

            if (list != null && booleens.get(i)) {
                if (TextUtils.isEmpty(result)) {
                    result += list.get(i).getDataCode();
                } else {
                    result += "," +list.get(i).getDataCode();
                }
            }
        }
        return result;
    }
}