package com.dlg.viewmodel.user;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.data.user.model.UserAttributeInfoBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.server.UserServer;
import com.dlg.viewmodel.user.presenter.LogInPresenter;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：wangdakuan
 * 主要功能：登录ViewModel
 * 创建时间：2017/7/4 16:16
 */
public class LogInViewModel extends BaseViewModel<JsonResponse<UserAttributeInfoBean>> {

    private LogInPresenter mLogInPresenter;
    private BasePresenter mBasePresenter;
    private final UserServer mServer;
    private String mobilPhone ; //用户登录的手机号，目前暂时这样做处理
    private ACache aCache ;

    public LogInViewModel(Context context, BasePresenter basePresenter, LogInPresenter presenter) {
        this.mLogInPresenter = presenter;
        mBasePresenter = basePresenter;
        mServer = new UserServer(context);
        aCache = ACache.get(context);
    }

    /**
     * 登录
     * @param mobile  手机号码,（必填）
     * @param password 密码或验证码, （必填）
     */
    public void logIn(String mobile,String password) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(2)); //用户类型, （必填1个人 2企业）
        map.put("principal", mobile); //手机号码,（必填）
        map.put("credentials", password); // 密码或验证码, （必填）
        map.put("rememberMe", "true");
        //mobilPhone = mobile ;
        mSubscriber = logInSubscriber();
        mServer.userLogIn(mSubscriber, map);
    }
    /**
     * 登录
     * @param mobile  手机号码,（必填）
     * 验证码登录
     */
    public void codeLogIn(String mobile,String code) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(1)); //用户类型, （必填1个人 2企业）
        map.put("principal", mobile); //手机号码,（必填）
        map.put("credentials", code); // 密码或验证码, （必填）
        map.put("rememberMe", "true");
        map.put("sms", "code");
        mSubscriber = logInSubscriber();
        mServer.userLogIn(mSubscriber, map);
    }
    /**
     * 三方登录
     * @param mobile  手机号码,（必填）
     *  未绑定账号登录
     */
    public void thirdUnBindLogIn(String mobile,String code,String openId,String loginType,String isBinding) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(1)); //用户类型, （必填1个人 2企业）
        map.put("principal", mobile); //手机号码,（必填）
        map.put("credentials", code); // 密码或验证码, （必填）
        map.put("openId",openId);
        map.put("loginType",loginType);
        map.put("sms", "code");
        map.put("isBinding",isBinding);
        mSubscriber = logInSubscriber();
        mServer.userLogIn(mSubscriber, map);
    }
    /**
     * 三方登录
     *
     * 已经绑定账号登录
     */
    public void thirdBindLogIn(String openId,String loginType,String isBinding) {
        HashMap<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(1)); //用户类型, （必填1个人 2企业）
        map.put("openId",openId);
        map.put("loginType",loginType);
        map.put("isBinding",isBinding);
        mSubscriber = logInSubscriber();
        mServer.userLogIn(mSubscriber, map);
    }

    private Subscriber<JsonResponse<UserAttributeInfoBean>> logInSubscriber() {
        return new RXSubscriber<JsonResponse<UserAttributeInfoBean>, UserAttributeInfoBean>(mBasePresenter) {
            @Override
            public void requestNext(UserAttributeInfoBean t) {
                if (null != mLogInPresenter) {
                    mLogInPresenter.logInUserInfo(t);
                }
            }
        };
    }
}
