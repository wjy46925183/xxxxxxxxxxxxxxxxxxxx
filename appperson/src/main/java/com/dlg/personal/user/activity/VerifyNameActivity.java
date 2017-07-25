package com.dlg.personal.user.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.common.string.LogUtils;
import com.common.utils.DialogUtils;
import com.common.utils.StringUtils;
import com.dlg.data.user.model.UserNameBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.user.NameVerifyByCardViewModel;
import com.dlg.viewmodel.user.NameVerifyViewModel;
import com.dlg.viewmodel.user.presenter.NameVerifyByCardPresenter;
import com.dlg.viewmodel.user.presenter.NameVerifyPresenter;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

/**
 * 作者：xiaoming
 * 主要功能：
 * 创建时间：2017/7/20 11:02
 */
public class VerifyNameActivity extends BaseActivity implements View.OnClickListener,NameVerifyPresenter,NameVerifyByCardPresenter {
    private ImageView mImgStates;
    private LinearLayout mLlName;
    private EditText mEtName;
    private LinearLayout mLlCard;
    private EditText mEtCardNo;
    private Button mBtnNext;
    private NameVerifyViewModel verifyViewModel;
    private NameVerifyByCardViewModel viewModel;

    String authenticationState = "";//认证状态 1认证中，2认证成功，3认证失败，4未认证
    int cardType;
    private TextView mTvNum;
    private String name;
    private String num;
    // 要申请的权限
    String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_verify_name);
        initView();
    }

    private void initView() {
        mImgStates = (ImageView) findViewById(R.id.img_states);
        mLlName = (LinearLayout) findViewById(R.id.ll_name);
        mEtName = (EditText) findViewById(R.id.et_name);
        mLlCard = (LinearLayout) findViewById(R.id.ll_card);
        mEtCardNo = (EditText) findViewById(R.id.et_cardNo);
        mBtnNext = (Button) findViewById(R.id.btn_next);
        mTvNum = (TextView) findViewById(R.id.tv_num);
        mImgStates.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        mToolBarHelper.setToolbarTitle("实名认证");
        initStatue();
    }


    private void initStatue() {
        authenticationState = mACache.getAsString(AppKey.CacheKey.VERIFY_STATE);
        if (authenticationState.equals("2")) {
            mImgStates.setImageResource(R.mipmap.chenggong);
            mImgStates.setClickable(false);
            mEtName.setText(mACache.getAsString(AppKey.CacheKey.NAME));
            mEtCardNo.setText(mACache.getAsString(AppKey.CacheKey.NAME_NUMBER));
            mEtName.setFocusable(false);
            mEtCardNo.setFocusable(false);
            mBtnNext.setVisibility(View.GONE);

        } else {
            mImgStates.setImageResource(R.mipmap.shangchuan);
            cardType = getIntent().getIntExtra("authenticcode", 0);
            switch (cardType) {
                case 1:
                    mTvNum.setText("身份证号");
                    break;
                case 13:
                    mTvNum.setText("护照编号");
                    break;
                case 14:
                    mTvNum.setText("港澳通行证");
                    break;
                case 10:
                    mTvNum.setText("台胞通行证");
            }
        }
    }

    @Override
    public void onClick(View v) {
        name = mEtName.getText().toString().trim();
        num = mEtCardNo.getText().toString().trim();
        if (v.getId() == R.id.btn_next) {
            goToSubmit();

        } else if(v.getId()==R.id.img_states){
            goToPhotoSubmit();
        }
    }

    private void goToPhotoSubmit() {
        //打开摄像头
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(new Intent(this, VerifyNamePtotoActivity.class), 1);
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("照片权限")
                        .setMessage("是否允许使用相机")
                        .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(VerifyNameActivity.this, permissions, 10);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setCancelable(false).show();
            }
        } else {
            startActivityForResult(new Intent(this, VerifyNamePtotoActivity.class), 1);
        }
    }
    // 用户权限 申请 的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 10) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!b) {
                        Toast.makeText(this, "请至设置页设置权限", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    startActivityForResult(new Intent(this, VerifyNameActivity.class), 1);
                }
            }
        }
    }

    private void goToSubmit() {
        if (TextUtils.isEmpty(name)){
            ToastUtils.showShort(mContext,"姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(num)){
            ToastUtils.showShort(mContext,"身份证号码不能为空");
            return;
        }if (!StringUtils.personIdValidation(num)){
            ToastUtils.showShort(mContext,"身份证号码格式不正确");
            return;
        }
        if (verifyViewModel==null){
            verifyViewModel=new NameVerifyViewModel(mContext,this,this);
        }
        verifyViewModel.verifyName(name,num,cardType+"",mACache.getAsString(AppKey.CacheKey.MY_USER_ID));
    }
    //身份证验证回调
    @Override
    public void verifyName(List<String> result) {
        if (result.get(0).equals("2")){
            ToastUtils.showShort(getBaseContext(), "认证成功!");
            mACache.put(AppKey.CacheKey.VERIFY_STATE,"2");
            mACache.put(AppKey.CacheKey.NAME,name);
            mACache.put(AppKey.CacheKey.NAME_NUMBER,num);
            mImgStates.setImageResource(R.mipmap.chenggong);
            mImgStates.setClickable(false);
            mEtName.setFocusable(false);
            mEtCardNo.setFocusable(false);
            mBtnNext.setVisibility(View.GONE);
        }else {
            ToastUtils.showShort(getBaseContext(), "认证失败，请重新认证");
        }

    }
    //身份证拍照验证回调
    @Override
    public void getName(List<UserNameBean> nameBean) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {//获取图片显示，显示提交按钮，做提交操作.
                String filepath = data.getStringExtra("filepath");
                //从文件加载图片。
                Bitmap bitmap = BitmapFactory.decodeFile(filepath);
                //显示提交按钮
                discernIDCard(bitmap);
            } else {
                String str = data.getStringExtra("pic");
                String name = data.getStringExtra("name");
                String num = data.getStringExtra("num");

                mEtCardNo.setText(num);
                mEtName.setText(name);
                mEtName.setSelection(name.length());
            }
        }
    }
    public void discernIDCard(Bitmap bitmap) {
        if (null == dialog){
            dialog = DialogUtils.showLoadingDialog(this);
        }else {
            dialog.show();
        }
        if (viewModel==null){
            viewModel=new NameVerifyByCardViewModel(mContext,this,this);
        }
        try {
            viewModel.getVerify(URLEncoder.encode(getImageStr(bitmap), "utf-8"),mACache.getAsString(AppKey.CacheKey.MY_USER_ID),cardType+"");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    private String getImageStr(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        if (bitmap != null)
            mImgStates.setImageBitmap(bitmap);
        String s = Base64.encodeToString(bytes, Base64.NO_WRAP);
        return s;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewModel!=null){
            viewModel.onDestroy();
        }
        if (verifyViewModel!=null){
            verifyViewModel.onDestroy();

        }

    }
}