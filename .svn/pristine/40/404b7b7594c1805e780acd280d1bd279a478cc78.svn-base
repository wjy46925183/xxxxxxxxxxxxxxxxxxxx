package com.common.view;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/14 10:38
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected List<T> datas;
    protected Context context;

    public CommonAdapter(Context context, List<T> datas) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
