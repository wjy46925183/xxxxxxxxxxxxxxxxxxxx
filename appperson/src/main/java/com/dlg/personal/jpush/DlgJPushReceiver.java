package com.dlg.personal.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.common.sys.ActivityNavigator;
import com.dlg.personal.iflytalk.IflyTekUtils;
import com.dlg.personal.jpush.bean.JPushBean;

import cn.jpush.android.api.JPushInterface;

import static android.support.v7.widget.StaggeredGridLayoutManager.TAG;

/**
 * 作者：王进亚
 * 主要功能：JPush推送
 * 创建时间：2017/7/19 11:08
 */

public class DlgJPushReceiver extends BroadcastReceiver {
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
        if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {//接收通知，得到id值
//            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);//得到发送的id
            for (String key : bundle.keySet()) {
                if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                    String extraData = bundle.getString(JPushInterface.EXTRA_EXTRA);
                    if (TextUtils.isEmpty(extraData)) {
                        Log.i(TAG, "This message has no Extra data");
                        break;
                    }
                    JPushBean pushExtraData = JSON.parseObject(extraData, JPushBean.class);
                    if (pushExtraData.orderStatus == 10 || pushExtraData.orderStatus == 40) {
                        String string = bundle.getString(JPushInterface.EXTRA_ALERT);//有人接单的文字
                        IflyTekUtils.playIfly(string, ActivityNavigator.getLastActivity());//语音播报
                    }
                    break;
                }
            }
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
            parseExtraData(bundle);
        }
    }

    /**
     * 解析协议数据，协议数据放在EXTRA_EXTRA里面
     *
     * @param bundle
     */
    private void parseExtraData(Bundle bundle) {
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_EXTRA)) {
                String extraData = bundle.getString(JPushInterface.EXTRA_EXTRA);
                Log.i(TAG, "extraData：" + extraData);
                if (TextUtils.isEmpty(extraData)) {
                    Log.i(TAG, "This message has no Extra data");
                    break;
                }
                try {
                    JPushBean jPushBean = JSON.parseObject(extraData, JPushBean.class);
                    dispenseEvent(jPushBean);
                } catch (Exception e) {
                    Log.i(TAG, "通知消息事件分发出现异常:" + e.getMessage());
                }
                break;
            }
        }
    }

    /**
     * 分发通知栏操作事件
     *
     * @param pushExtraData
     */
    private void dispenseEvent(JPushBean pushExtraData) {
        switch (pushExtraData.toRoleType) {
            case 1://跳转订单详情

                break;
            case 2://雇主

                break;
        }
    }
}