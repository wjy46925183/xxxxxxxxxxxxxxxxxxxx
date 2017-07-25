package com.dlg.viewmodel.server;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dlg.data.user.factory.UserFactory;
import com.dlg.data.user.interactor.UserInteractor;
import com.dlg.data.user.url.UserUrl;

import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：wangdakuan
 * 主要功能：用户接口服务类
 * 创建时间：2017/6/23 19:19
 */
public class UserServer {

    UserFactory userFactory;

    public UserServer(Context appContext) {
        this(new UserFactory(appContext));
    }

    public UserServer(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    /**
     * 根据ID获取用户信息
     * @param subscriber
     * @param hashMap
     */
    public void queryUserDetail(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.QUERY_USER_DETAIL + JSON.toJSONString(hashMap), true);
        dataInteractor.queryUserDetail(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 用户登录
     * @param subscriber
     * @param hashMap
     */
    public void userLogIn(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.USER_LOGIN + JSON.toJSONString(hashMap), true);
        dataInteractor.userLogIn(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 判断是否注册
     * @param subscriber
     * @param hashMap
     */
    public void getRegist(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.CHECK_IS_REGIST + JSON.toJSONString(hashMap), true);
        dataInteractor.IsRegist(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 注册
     * @param subscriber
     * @param hashMap
     */
    public void getRegistResult(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.SYSTEM_REGISTER + JSON.toJSONString(hashMap), true);
        dataInteractor.getCode(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
        /**
         * 更新个人信息
         * @param subscriber
         * @param hashMap
         */
    public void upUserInfo(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.UPDATE_USER_INFORMATION + JSON.toJSONString(hashMap), true);
        dataInteractor.upUserInfo(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 更新个人信息主表
     * @param subscriber
     * @param hashMap
     */
    public void upUserInfoMain(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.UPDATE_USER_INFORMATION_MAIN + JSON.toJSONString(hashMap), true);
        dataInteractor.upUserInfoMain(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 重置密码
     * @param subscriber
     * @param hashMap
     */
    public void getResetResult(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.FORGET_PWD + JSON.toJSONString(hashMap), true);
        dataInteractor.resetPsd(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 是否绑定三方
     * @param subscriber
     * @param hashMap
     */
    public void isBindingThird(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.IS_BINDING_THIRD + JSON.toJSONString(hashMap), true);
        dataInteractor.isBindingThird(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 图片检测身份证信息
     * @param subscriber
     * @param hashMap
     */
    public void getVerify(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.NAME_VERIFY_BY_PHOTO + JSON.toJSONString(hashMap), true);
        dataInteractor.isVerify(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 退出登录
     * @param subscriber
     * @param hashMap
     */
    public void logOut(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.LOGOUT + JSON.toJSONString(hashMap), true);
        dataInteractor.logOut(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 退出登录
     * @param subscriber
     * @param hashMap
     */
    public void getVerifyResult(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.NAME_VERIFY + JSON.toJSONString(hashMap), true);
        dataInteractor.logOut(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 更新头像
     * @param subscriber
     * @param hashMap
     */
    public void upLoadHead(Subscriber subscriber, HashMap<String, String> hashMap) {
        UserInteractor dataInteractor = userFactory.createUserData(UserUrl.UPDATE_USER_HEAD + JSON.toJSONString(hashMap), true);
        dataInteractor.upLoadHead(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
