package com.dlg.personal.user.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.common.sys.ActivityNavigator;
import com.common.utils.CropImage;
import com.common.utils.PicHelper;
import com.dlg.data.user.model.UserInfoDataBean;
import com.dlg.data.user.model.UserUpHeadBean;
import com.dlg.personal.R;
import com.dlg.personal.base.BaseActivity;
import com.dlg.personal.base.ToolBarType;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.personal.user.view.ChangeAddressPopwindow;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.user.UpHeadViewModel;
import com.dlg.viewmodel.user.UpUserInfoMainViewModel;
import com.dlg.viewmodel.user.UpUserInfoViewModel;
import com.dlg.viewmodel.user.UserInfoViewModel;
import com.dlg.viewmodel.user.presenter.UpHeadPresenter;
import com.dlg.viewmodel.user.presenter.UpUserInfoMainPresenter;
import com.dlg.viewmodel.user.presenter.UpUserInfoPresenter;
import com.dlg.viewmodel.user.presenter.UserInfoPresenter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：小明
 * 主要功能：用户信息详情
 * 创建时间：2017/7/12 0012 17:38
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener, UpHeadPresenter, UserInfoPresenter,UpUserInfoPresenter,UpUserInfoMainPresenter {
    AppBarLayout mAppBarLayout;
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    LinearLayout mHeadLayout;
    CircleImageView mIvHead;
    TextView mIvText;
    Toolbar mLayoutToolbar;
    EditText mNickNameText;
    LinearLayout mNameLayout;
    TextView mNameText;
    LinearLayout mSignLayout;
    TextView mStatuesText;
    EditText mHeightText;
    EditText mWeightText;
    LinearLayout mAddressLayout;
    TextView mAddressText;
    EditText mMailText;
    EditText mQqText;
    EditText mSignText;

    String path;             //图片全路径
    File img;                //头像
    String permissionInfo;   //权限名
    private final int SDK_PERMISSION_REQUEST = 127;
    private static final int TAG_CAMERA = 10;     //拍照
    private static final int TAG_LOCAL = 11;      //本地
    private static final int TAG_COMPLETE = 12;        //选取完成
    private PopupWindow window;
    private UpHeadViewModel upHeadViewModel;
    private UserInfoViewModel infoViewModel;
    private UpUserInfoViewModel upInfoViewModel;
    private UpUserInfoMainViewModel upInfoMainViewModel;
    UserInfoDataBean userInfo;
    private ImageView ivName;
    private int role;
    String sign="1";
    String statues;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_user_info, ToolBarType.Default, R.id.layout_toolbar);
        initView();
    }

    private void initView() {
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        mHeadLayout = (LinearLayout) findViewById(R.id.head_layout);
        mIvHead = (CircleImageView) findViewById(R.id.iv_head);
        mIvText = (TextView) findViewById(R.id.iv_text);
        mLayoutToolbar = (Toolbar) findViewById(R.id.layout_toolbar);
        mNickNameText = (EditText) findViewById(R.id.nickName_text);
        mNameLayout = (LinearLayout) findViewById(R.id.name_layout);
        mNameText = (TextView) findViewById(R.id.name_text);
        mSignLayout = (LinearLayout) findViewById(R.id.sign_layout);
        mStatuesText = (TextView) findViewById(R.id.statues_text);
        mHeightText = (EditText) findViewById(R.id.height_text);
        mWeightText = (EditText) findViewById(R.id.weight_text);
        mAddressLayout = (LinearLayout) findViewById(R.id.address_layout);
        mAddressText = (TextView) findViewById(R.id.address_text);
        mMailText = (EditText) findViewById(R.id.mail_text);
        mQqText = (EditText) findViewById(R.id.qq_text);
        mSignText = (EditText) findViewById(R.id.sign_text);
        ivName = (ImageView) findViewById(R.id.iv_name);
        role = getIntent().getIntExtra("role", 0);
        if (role == 1) {
            mHeadLayout.setBackground(getResources().getDrawable(R.mipmap.icon_bg));//雇员
        }
        if (role == 2){
            mHeadLayout.setBackground(getResources().getDrawable(R.mipmap.icon_bg_black));//雇主
        }

        Glide.with(this).load(mACache.getAsString(AppKey.CacheKey.USER_LOGO)).fitCenter().override(700, 700).error(R.mipmap.mrtx)
                .into(mIvHead);
        mToolBarHelper.setIsShownDividerLine(false);
        mToolBarHelper.getToolBar().setBackgroundResource(android.R.color.transparent);
        mToolBarHelper.setToolbarRightTextVisibility(View.VISIBLE);
        mToolBarHelper.setToolbarTextRight("保存");
        mToolBarHelper.setToolbarTitle("个人中心");
        getUserInformation();
        mNameLayout.setOnClickListener(this);
        mSignLayout.setOnClickListener(this);
        mAddressLayout.setOnClickListener(this);
        mNameLayout.setOnClickListener(this);
        mIvHead.setOnClickListener(this);
        mToolBarHelper.setToolbarTextRightOnClickListener(this);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset > -170) {
                    mToolBarHelper.getToolbarBack().setImageResource(R.mipmap.ic_black_white);
                    mToolBarHelper.getToolbarTextRight().setTextColor(getResources().getColor(R.color.white));
                    mToolBarHelper.getToolbarTitle().setTextColor(getResources().getColor(R.color.white));
                } else {
                    mToolBarHelper.getToolbarBack().setImageResource(R.mipmap.ic_black);
                    mToolBarHelper.getToolbarTextRight().setTextColor(getResources().getColor(R.color.black_text));
                    mToolBarHelper.getToolbarTitle().setTextColor(getResources().getColor(R.color.black_text));
                }
            }
        });

    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_head) {//头像
            checkPermission();

        } else if (v.getId() == R.id.name_layout) {//实名认证
            if (mACache.getAsString(AppKey.CacheKey.VERIFY_STATE).equals("0")) {
                ActivityNavigator.navigator().navigateTo(VerifyNameTypeActivity.class);
            } else {
                ActivityNavigator.navigator().navigateTo(VerifyNameActivity.class);
            }
           // ActivityNavigator.navigator().navigateTo(VerifyNameTypeActivity.class);

        } else if (v.getId() == R.id.sign_layout) {//身份
            choseSign();

        } else if (v.getId() == R.id.toolbar_text_right) {//保存页面信息
            saveData();

        }else if (v.getId() == R.id.address_layout) {//所在地
            ChangeAddressPopwindow mChangeAddressPopwindow = new ChangeAddressPopwindow(UserInfoActivity.this);
            mChangeAddressPopwindow.setAddress("广东", "深圳", "福田区");
            mChangeAddressPopwindow.showAtLocation(mAddressText, Gravity.BOTTOM, 0, 0);
            mChangeAddressPopwindow.setAddresskListener(new ChangeAddressPopwindow.OnAddressCListener() {
                        @Override
                        public void onClick(String province, String city, String area) {

                            mAddressText.setText(province + city + area);
                        }
                    });
        }
    }
    /**
     *获取用户信息
     */
    private void getUserInformation() {
        if (infoViewModel == null) {
            infoViewModel = new UserInfoViewModel(mContext, this);
        }
        infoViewModel.queryUserDetail(mACache.getAsString(AppKey.CacheKey.MY_USER_ID));
    }

    /**
     *保存用户信息
     */
    private void saveData() {
        String height=mHeightText.getText().toString().trim();
        String weight=mWeightText.getText().toString().trim();
        String address=mAddressText.getText().toString().trim();
        String username=mNickNameText.getText().toString().trim();
        String email=mMailText.getText().toString().trim();
        String oicq=mQqText.getText().toString().trim();
        String personalizedSignature=mSignText.getText().toString().trim();
        if (upInfoViewModel==null){
            upInfoViewModel=new UpUserInfoViewModel(mContext,this,this);
        }
        if (upInfoMainViewModel==null){
            upInfoMainViewModel=new UpUserInfoMainViewModel(mContext,this,this);
        }
        upInfoViewModel.upUserInfo(height,weight,sign,address,personalizedSignature,mACache.getAsString(AppKey.CacheKey.MY_USER_ID));
        upInfoMainViewModel.upUserInfoMain(username,email,oicq,mACache.getAsString(AppKey.CacheKey.MY_USER_ID));
    }


    /**
     * 选择身份类型
     * */
    private void choseSign() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_chose_sign, null);
        final TextView free = (TextView) view.findViewById(R.id.free);
        final TextView student = (TextView) view.findViewById(R.id.student);
        final TextView jianzhi = (TextView) view.findViewById(R.id.jianzhi);
        final LinearLayout content = (LinearLayout) view.findViewById(R.id.content);
        window = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        View rootview = LayoutInflater.from(this).inflate(R.layout.main, null);
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        statues = userInfo.getUserAttributeRestVo().getIdentity();
        if (statues.equals("0")) {
            student.setTextColor(getResources().getColor(R.color.app_color_yellow));
        }
        if (statues.equals("1")) {
            free.setTextColor(getResources().getColor(R.color.app_color_yellow));
        }
        if (statues.equals("2")) {
            jianzhi.setTextColor(getResources().getColor(R.color.app_color_yellow));
        }
        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign = "1";
                mStatuesText.setText("自由工作者");
                window.dismiss();
            }
        });
        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign = "0";
                mStatuesText.setText("在校学生");
                window.dismiss();
            }
        });
        jianzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign = "2";
                mStatuesText.setText("兼职人员");
                window.dismiss();
            }
        });
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });

    }
    /**
     * 检查相机权限
     * */
    @TargetApi(23)
    public void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<String>();
            /**
             * 权限为必须权限，用户如果禁止，则每次进入都会申请
             */

            if (mContext.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {// 使用摄像头
                permissions.add(Manifest.permission.CAMERA);
            }
            if (mContext.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {// 读写权限
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {// 读写权限
                permissionInfo += "Manifest.permission.WRITE_EXTERNAL_STORAGE Deny \n";
            }
            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), SDK_PERMISSION_REQUEST);
            } else {
                choosePhoto();
            }
        } else {
            choosePhoto();
        }
    }

    /**
     * 本地选择或者拍照
     * */
    private void choosePhoto() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_pop_chose_phote, null);
        TextView btnTake = (TextView) view.findViewById(R.id.take);
        TextView btnLocal = (TextView) view.findViewById(R.id.local);
        LinearLayout content = (LinearLayout) view.findViewById(R.id.content);
        window = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        View rootview = LayoutInflater.from(this).inflate(R.layout.main, null);
        window.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CropImage.getImageIntent();
                startActivityForResult(intent, TAG_LOCAL);
                window.dismiss();
            }
        });
        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                if (!Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    return;
                }
                File file = getCacheDir(mContext, "photo");
                if (!file.exists())
                    file.mkdirs();
                img = new File(file, "IMG" + System.currentTimeMillis() + ".jpg");
                if (!img.exists()) {
                    try {
                        img.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(img));
                startActivityForResult(intent, TAG_CAMERA);
                window.dismiss();
            }
        });
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAG_LOCAL:
                    Uri selectedImage = data.getData();
                    path = PicHelper.getPath(mContext, selectedImage);
                    Intent intent3 = new Intent(UserInfoActivity.this, CropHeadIconActivity.class);
                    intent3.putExtra("path", path);
                    startActivityForResult(intent3, TAG_COMPLETE);

                    break;
                case TAG_CAMERA:
                    path = img.getAbsolutePath();
                    Intent intent2 = new Intent(UserInfoActivity.this, CropHeadIconActivity.class);
                    intent2.putExtra("path", path);
                    startActivityForResult(intent2, TAG_COMPLETE);
                    break;
                case TAG_COMPLETE:
                    final Uri path = data.getData();
                    img = new File(path.getPath());
                    Glide.with(mContext)
                            .load(path)
                            .placeholder(R.mipmap.icon_default_user)
                            .error(R.mipmap.icon_default_user)
                            .dontAnimate()
                            .override(600, 200)
                            .fitCenter()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(mIvHead);
                    mACache.put(AppKey.CacheKey.USER_LOGO, path.getPath());
                    upLoadHead(img);
                    break;
            }
        }
    }

    private void upLoadHead(File img) {
        if (upHeadViewModel == null) {
            upHeadViewModel = new UpHeadViewModel(mContext, this, this);
        }
        try {
            upHeadViewModel.upHead(getImageStr(img), mACache.getAsString(AppKey.CacheKey.MY_USER_ID));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getImageStr(File file) {
        ByteArrayOutputStream stream = null;
        try {
            Bitmap photo = null;
            if (file != null) {
                photo = BitmapFactory.decodeFile(file.getPath());
            }
            return Bitmap2StrByBase64(photo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 通过Base32将Bitmap转换成Base64字符串
     *
     * @param bit
     * @return
     */
    public String Bitmap2StrByBase64(Bitmap bit) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.JPEG, 100, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    //获取存储路径
    private File getCacheDir(Context context, String cacheName) {
        File cacheDir = context.getExternalCacheDir();
        if (cacheDir == null) {
            cacheDir = context.getCacheDir();
        }
        cacheDir = new File(cacheDir, cacheName);
        return cacheDir;
    }

    //集中处理权限
    @TargetApi(23)
    private boolean addPermission(ArrayList<String> permissionsList, String permission) {
        if (mContext.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) { // 如果应用没有获得对应权限,则添加到列表中,准备批量申请
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            } else {
                permissionsList.add(permission);
                return false;
            }
        } else {
            return true;
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (SDK_PERMISSION_REQUEST == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {// 授权
                choosePhoto();
            } else {
                //小米拒绝后需要用户手动开启
                ToastUtils.showLong(mContext, "如果您想使用与摄像头有关的功能，请到系统设置中手动开启权限。");
            }
        }
    }

    //更新头像回调
    @Override
    public void upUserHead(List<UserUpHeadBean> result) {
        if (result.get(0).getIsSucess() == 0) {
            mACache.put(AppKey.CacheKey.USER_LOGO, result.get(0).getLogo());
        } else {
            ToastUtils.showShort(mContext, "上传头像失败，请重新上传");
        }
    }

    //用户信息回调
    @Override
    public void userInfoData(UserInfoDataBean userInfoDataBean) {
        this.userInfo = userInfoDataBean;
        mNickNameText.setText(userInfoDataBean.getUserRestVo().getUsername());//昵称
        mStatuesText.setText(userInfoDataBean.getUserAttributeRestVo().getIdentityName());//身份
        mHeightText.setText(userInfoDataBean.getUserAttributeRestVo().getHeight());//身高
        mWeightText.setText(userInfoDataBean.getUserAttributeRestVo().getWeight());//体重
        mAddressText.setText(userInfoDataBean.getUserAttributeRestVo().getLocation());//地址
        mMailText.setText(userInfoDataBean.getUserRestVo().getEmail());//EMAIL
        mQqText.setText(userInfoDataBean.getUserRestVo().getOicq());//QQ
        mSignText.setText(userInfoDataBean.getUserAttributeRestVo().getPersonalizedSignature());//个性签名
        if (mACache.getAsString(mACache.getAsString(AppKey.CacheKey.NAME)) == null) {//头像下的文字
            mIvText.setText(TextUtils.isEmpty(userInfoDataBean.getUserAttributeRestVo().getName()) ? mACache.getAsString(mACache.getAsString(AppKey.CacheKey.USER_PHONE)) : userInfoDataBean.getUserAttributeRestVo().getName());
        } else {
            mIvText.setText(mACache.getAsString(mACache.getAsString(AppKey.CacheKey.NAME)));
        }
        if (userInfoDataBean.getUserRestVo().getAuditStatus().equals("2")) {//姓名
            mNameText.setText(userInfoDataBean.getUserAttributeRestVo().getName());
            mNameText.setTextColor(getResources().getColor(R.color.app_color_yellow));
            ivName.setImageResource(R.mipmap.enter_yellow);
        } else {
            mNameText.setText("未认证");
            mNameText.setTextColor(getResources().getColor(R.color.text_color));
            ivName.setImageResource(R.mipmap.enter);
        }
    }
    int up1=0;
    //更新用户信息回调
    @Override
    public void upUserInfo(String result) {
        up1=1;
    }
    //更新用户主表信息回调
    @Override
    public void upUserInfoMain(String result) {
       if (up1==1){
           ToastUtils.showShort(mContext,"提交成功");
       }
    }
}



