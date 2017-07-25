package com.dlg.data.wallet.model;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * 作者：关蕤
 * 主要功能：选择充值类型的实体
 * 创建时间：2017/7/10 15:58
 */
public class TypeBean {
    private String name ;
    private BitmapDrawable drawable ;
    private boolean isSelect ;

    public TypeBean(String name, BitmapDrawable drawable, boolean isSelect) {
        this.name = name;
        this.drawable = drawable;
        this.isSelect = isSelect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(BitmapDrawable drawable) {
        this.drawable = drawable;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
