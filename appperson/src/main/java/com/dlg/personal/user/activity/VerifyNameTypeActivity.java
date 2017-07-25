package com.dlg.personal.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;

/**
 * 选择身份认证类型
 * Created by xiaoming on 2017/7/20.
 */

public class VerifyNameTypeActivity extends BaseActivity implements View.OnClickListener {
    RelativeLayout mLayoutCard;
    RelativeLayout mLayoutPassport;
    RelativeLayout mLayoutTai;
    RelativeLayout mLayoutGang;
    int authenticcode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_verify_name_type);
        initView();
    }

    private void initView() {
        mLayoutCard = (RelativeLayout) findViewById(R.id.layout_card);
        mLayoutPassport = (RelativeLayout) findViewById(R.id.layout_passport);
        mLayoutTai = (RelativeLayout) findViewById(R.id.layout_tai);
        mLayoutGang = (RelativeLayout) findViewById(R.id.layout_gang);
        mLayoutCard.setOnClickListener(this);
        mLayoutPassport.setOnClickListener(this);
        mLayoutTai.setOnClickListener(this);
        mLayoutGang.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.layout_card) {
            authenticcode = 1;
        }
        if (v.getId() == R.id.layout_passport) {
            authenticcode = 13;
        }
        if (v.getId() == R.id.layout_tai) {
            authenticcode = 10;
        }
        if (v.getId() == R.id.layout_gang) {
            authenticcode = 14;
        }
        Intent intent = new Intent(this, VerifyNameActivity.class);
        intent.putExtra("authenticcode", authenticcode);
        startActivity(intent);
    }
}
