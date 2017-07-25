package gongren.com.dlg.login.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSONObject;
import com.common.string.LogUtils;
import com.common.sys.ActivityNavigator;
import com.common.utils.StringUtils;
import com.dlg.data.user.model.UserAttributeInfoBean;
import com.dlg.data.user.model.UserIsBindingBean;
import com.dlg.personal.base.BaseFragment;
import com.dlg.personal.home.view.ToastUtils;
import com.dlg.personal.user.activity.ThirdUnitLogin;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.user.IsBindingThirdViewModel;
import com.dlg.viewmodel.user.IsRegisterViewModel;
import com.dlg.viewmodel.user.LogInViewModel;
import com.dlg.viewmodel.user.presenter.IsBindingPresenter;
import com.dlg.viewmodel.user.presenter.IsRegisterPresenter;
import com.dlg.viewmodel.user.presenter.LogInPresenter;
import com.example.umengshare.UmengShareUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.List;
import java.util.Map;

import gongren.com.dlg.R;
import gongren.com.dlg.login.activity.LoginActivity;

/**
 * 作者：小明
 * 主要功能：选择登录方式个人登录
 * 创建时间：2017/7/13 0013 18:01
 */
public class PersonFragment extends BaseFragment implements View.OnClickListener, IsRegisterPresenter, IsBindingPresenter, LogInPresenter {
    EditText phoneText;
    Button btnNext;
    ImageView qq;
    ImageView weiXin;
    ImageView weiBo;
    String phone;
    IsRegisterViewModel viewModel;
    String openId;
    IsBindingThirdViewModel bindViewModel;
    LogInViewModel loginViewModel;
    String type;
    String isBinding = "1";
    private String name;
    private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        phoneText = (EditText) view.findViewById(R.id.phone_text);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        qq = (ImageView) view.findViewById(R.id.qq);
        weiXin = (ImageView) view.findViewById(R.id.weixin);
        weiBo = (ImageView) view.findViewById(R.id.weibo);
        btnNext.setEnabled(false);
        btnNext.setOnClickListener(this);
        weiBo.setOnClickListener(this);
        qq.setOnClickListener(this);
        weiXin.setOnClickListener(this);
        phoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtils.isPhoneNumber(s.toString())) {
                    btnNext.setEnabled(true);
                } else {
                    btnNext.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNext) {   //下一步
            gotoSubmit();
        }

        else if (v.getId() == R.id.weixin) {    //微信
            UmengShareUtil.Builder(mActivity).getPlatformInfo(SHARE_MEDIA.WEIXIN, mUMAuthListener);
        }

        else if (v.getId() == R.id.qq) {        //QQ
            UmengShareUtil.Builder(mActivity).getPlatformInfo(SHARE_MEDIA.QQ, mUMAuthListener);
        }

        else if (v.getId() == R.id.weibo) {     //微博
            UmengShareUtil.Builder(mActivity).getPlatformInfo(SHARE_MEDIA.SINA, mUMAuthListener);
        }
    }
    //友盟三方登录
    private UMAuthListener mUMAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

            if (share_media == SHARE_MEDIA.WEIXIN) {
                type = "3";

                LogUtils.e(JSONObject.toJSONString(map) + "11111111");
            } else if (share_media == SHARE_MEDIA.QQ) {
                type = "4";
                LogUtils.e(JSONObject.toJSONString(map) + "11111111");
            } else if (share_media == SHARE_MEDIA.SINA) {
                type = "5";

            }
            name = map.get("name");
            openId = Base64.encodeToString(map.get("openid").getBytes(), Base64.DEFAULT);
            checkIsBinding();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    //是否绑定
    private void checkIsBinding() {
        if (null == bindViewModel) {
            bindViewModel = new IsBindingThirdViewModel(mContext, this, this);
        }
        bindViewModel.isBinding(openId, type);
    }

    private void gotoSubmit() {
        phone = phoneText.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShort(mContext, "请输入手机号码");
            return;
        }
        if (StringUtils.isPhoneNumber(phone)) {
            viewModel = new IsRegisterViewModel(mContext, this);
            viewModel.IsRegister(phone, "1");
        } else {
            ToastUtils.showShort(mContext, "请输入正确的电话号码");
        }

    }

    //是否注册
    @Override
    public void getIsRegister(boolean isRegist) {
        if (bundle==null){
            bundle = new Bundle();
        }
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        if (isRegist) {

            bundle.putString("phone", phone);
            bundle.putString("inFrom", LoginActivity.TAG_LOGIN);
        } else {
            bundle.putString("phone", phone);
            bundle.putString("inFrom", LoginActivity.TAG_REGIST);
        }
        intent.putExtras(bundle);
        startActivity(intent);
    }


    //是否绑定三方
    @Override
    public void isBinding(List<UserIsBindingBean> result) {
        if (result.get(0).getIsBinding().equals("0")) {         //已经绑定三方的登录
            isBinding = "0";
            if (loginViewModel == null) {
                loginViewModel = new LogInViewModel(mContext, this, this);
            }
            loginViewModel.thirdBindLogIn(openId, type, isBinding);
        } else if (result.get(0).getIsBinding().equals("1")) {  //未绑定三方的登录
            isBinding = "1";
            if (bundle==null){
                bundle = new Bundle();
            }
            bundle.putString("openId", openId);
            bundle.putString("loginType", type);
            bundle.putString("isBinding", openId);
            bundle.putString("name", name);
            ActivityNavigator.navigator().navigateTo(ThirdUnitLogin.class, bundle);
        }
    }

    //登录成功
    @Override
    public void logInUserInfo(UserAttributeInfoBean infoBean) {
        mACache.put(AppKey.CacheKey.MY_USER_ID, infoBean.getUserId());
        mACache.put(AppKey.CacheKey.NAME, infoBean.getName());
        mACache.put(AppKey.CacheKey.USER_LOGO, infoBean.getLogo());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel!=null){
            viewModel.onDestroy();
        }
        if (loginViewModel!=null){
            loginViewModel.onDestroy();
        }
        if (bindViewModel!=null){
            bindViewModel.onDestroy();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (viewModel!=null){
            viewModel.onDestroyView();

        }
        if (loginViewModel!=null){
            loginViewModel.onDestroyView();
        }
        if (bindViewModel!=null){
            bindViewModel.onDestroyView();
        }
    }
}
