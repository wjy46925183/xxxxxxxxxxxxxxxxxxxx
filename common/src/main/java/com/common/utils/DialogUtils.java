package com.common.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;

import cn.pedant.SweetAlert.ProgressHelper;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 作者：小明
 * 主要功能：弹出框Dialog工具集
 * 创建时间：2017/7/10 0010 15:02
 */
public class DialogUtils {
    /**
     * 弹出自定义布局的对话框
     *
     * @param context
     */
    public static AlertDialog showViewDialog(Context context, View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setView(view)
                .show();
        return alertDialog;
    }

    /**
     * 网络等待确认的Dialog
     * @param context
     * @return
     */
    public static Dialog showLoadingDialog(Context context) {
        return loadingProgressDialog(context);
    }

    public static SweetAlertDialog loadingProgressDialog(Context context){
        SweetAlertDialog pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        ProgressHelper progressHelper = pDialog.getProgressHelper();
        progressHelper.setBarColor(Color.parseColor("#ff9752"));
        pDialog.setTitleText("loading");
        pDialog.setCancelable(true);
        pDialog.show();
        return pDialog;
    }
}
