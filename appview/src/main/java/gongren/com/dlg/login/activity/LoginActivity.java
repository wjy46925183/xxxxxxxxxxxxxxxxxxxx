package gongren.com.dlg.login.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.dlg.personal.base.BaseActivity;

import gongren.com.dlg.R;
import gongren.com.dlg.login.fragment.PersonCertificateFragment;
import gongren.com.dlg.login.fragment.PersonLoginFragment;
import gongren.com.dlg.login.fragment.PersonRegisterFragment;
import gongren.com.dlg.login.fragment.ResetPsdFragment;

/**
 * 作者：小明
 * 主要功能：登录主选择页面
 * 创建时间：2017/7/14 0014 09:34
 */
public class LoginActivity extends BaseActivity {

    public static final String TAG_REGIST = "TAG_REGIST";
    public static final String TAG_LOGIN = "TAG_LOGIN";
    public static final String TAG_RESET = "TAG_RESET";
    public static final String TAG_VERYCODE = "TAG_VERYCODE";
    Bundle bundle;
    PersonRegisterFragment registFragment;
    PersonLoginFragment personLoginFragment;
    ResetPsdFragment resetPsdFragment;
    PersonCertificateFragment certificateFragment;
    FrameLayout frameLayout;
    String from;//从哪个页面来的、TAG_REGIST注册、2登录、3忘记密码、4、验证码登录

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login);
        initView();
    }

    private void initView() {
        bundle = new Bundle();
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        from = getIntent().getStringExtra("inFrom");
        switch (from){
            case TAG_REGIST:
                mToolBarHelper.setToolbarTitle("用户注册");
                registFragment = new PersonRegisterFragment();
                registFragment.setArguments(getIntent().getExtras());
                addFragment(R.id.frameLayout, registFragment, TAG_REGIST);
                break;
            case TAG_LOGIN:
                mToolBarHelper.setToolbarTitle("个人登录");
                personLoginFragment = new PersonLoginFragment();
                personLoginFragment.setArguments(getIntent().getExtras());
                addFragment(R.id.frameLayout, personLoginFragment, TAG_LOGIN);
                break;
            case TAG_RESET:
                mToolBarHelper.setToolbarTitle("找回密码");
                resetPsdFragment = new ResetPsdFragment();
                resetPsdFragment.setArguments(getIntent().getExtras());
                addFragment(R.id.frameLayout, resetPsdFragment, TAG_RESET);
                break;
            case TAG_VERYCODE:
                mToolBarHelper.setToolbarTitle("验证码登录");
                certificateFragment = new PersonCertificateFragment();
                certificateFragment.setArguments(getIntent().getExtras());
                addFragment(R.id.frameLayout, certificateFragment, TAG_VERYCODE);
                break;

        }

    }

}
