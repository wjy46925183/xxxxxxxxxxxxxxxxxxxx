package com.dlg.viewmodel.common;

import android.content.Context;

import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.common.presenter.SuccessObjectPresenter;
import com.dlg.viewmodel.server.CommonServer;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：wangdakuan
 * 主要功能：发送验证码
 * 创建时间：2017/7/14 16:58
 */
public class SendCodeViewModel extends BaseViewModel<JsonResponse<Object>> {
    private SuccessObjectPresenter butPresenter; //与页面交互的接口
    private CommonServer mCommonServer;
    private BasePresenter basePresenter;

    public SendCodeViewModel(Context context, BasePresenter basePresenter, SuccessObjectPresenter presenter) {
        this.butPresenter = presenter;
        this.basePresenter = basePresenter;
        mCommonServer = new CommonServer(context);
        mContext = context;
    }

    /**
     * 发送验证码
     *
     * @param mPhoneNum
     */
    public void sendMsg(String mPhoneNum) {
        HashMap map = new HashMap();
        try {
//            map.put("phone", AESUtil.aesEncryptString(mPhoneNum,AESUtil.KEY_AES));
            map.put("phone", mPhoneNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mSubscriber = getSendSubscriber();
        mCommonServer.getSendMsg(mSubscriber, map);
    }

    /**
     * 得到验证码
     *
     * @param userId
     * @param mPhoneNum
     */
    public void sendMsg(String userId, String mPhoneNum) {
        HashMap map = new HashMap();
        map.put("userId", userId);
        try {
//            map.put("phone", AESUtil.aesEncryptString(mPhoneNum,AESUtil.KEY_AES));
            map.put("phone", mPhoneNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mSubscriber = getSendSubscriber();
        mCommonServer.getSendMsg(mSubscriber, map);
    }

    private Subscriber<JsonResponse<Object>> getSendSubscriber() {
        return new RXSubscriber<JsonResponse<Object>, Object>(basePresenter) {
            @Override
            public void requestNext(Object strings) {
                butPresenter.onSuccess(true);
            }
        };
    }
}
