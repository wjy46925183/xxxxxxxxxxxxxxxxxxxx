package com.dlg.inc.wallet.view;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.dlg.inc.R;


/**
 * 作者：邹前坤
 * 主要功能： 时间的简单处理
 * 创建时间： 2017/7/14  17:04
 */

public class IncTimeCount extends CountDownTimer {
    private TextView tvCode;
	private static final int left=20;
	private static final int right=20;
	private static final int top=5;
	private static final int bottom=5;
	private static final int textsize=15;
    /**
     *
     * @param millisInFuture  倒计时 时长
     * @param countDownInterval  倒计时速度
     * @param tv 显示控件
     */
    public IncTimeCount(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tvCode = tv;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        tvCode.setBackgroundResource(R.drawable.inc_regist_suc);
        tvCode.setTextSize(textsize);
		tvCode.setPadding(left,top,right,bottom);
        tvCode.setText(millisUntilFinished / 1000 + "s后重发");
        tvCode.setClickable(false);
    }

    @Override
    public void onFinish() {
        tvCode.setBackgroundResource(R.drawable.inc_regist_suc);
        tvCode.setTextSize(textsize);
		tvCode.setPadding(left,top,right,bottom);
        tvCode.setText("重新发送");
        tvCode.setClickable(true);
    }

    public void onReSend() {
        tvCode.setBackgroundResource(R.drawable.inc_regist_suc);
        tvCode.setTextSize(textsize);
		tvCode.setPadding(left,top,right,bottom);
        tvCode.setText("获取验证码");
        tvCode.setClickable(true);
    }
}
