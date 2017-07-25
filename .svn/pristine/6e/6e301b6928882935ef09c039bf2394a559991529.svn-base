package com.dlg.viewmodel;

import com.common.utils.RxBus;
import com.dlg.viewmodel.key.AppKey;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/14 11:57
 */
public abstract class BaseSubscrlber<T, M> extends Subscriber<T> {

    private BasePresenter presenter;

    public BaseSubscrlber(BasePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onCompleted() {
        if (null != presenter) {
            presenter.requestComplete();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (null != e && null != e.getMessage() && e.getMessage().contains("登录失败")) {
            RxBus.get().post(AppKey.PageRequestCodeKey.LOGIN_ERROR,"登录失败,踢出登录");
            if (null != presenter) {
                presenter.logInError();
            }
        }
        if (null != presenter) {
            presenter.requestError(null != e ? e.getMessage() : "网络异常");
            presenter.requestComplete();
        }
    }

    @Override
    public void onNext(T t) {
        if (t instanceof JsonResponse) {
            JsonResponse jsonResponse = (JsonResponse) t;
            requestNext((M) jsonResponse.getData());
            requestNextCode(jsonResponse.getCode());
            if (null != presenter) {
                presenter.requestNext(jsonResponse.getMsg());
            }
        } else {
            requestNext((M) t);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (null != presenter) {
            presenter.requestStart();
        }
    }
    public abstract void requestNext(M m);
    public abstract void requestNextCode(Object m);
}
