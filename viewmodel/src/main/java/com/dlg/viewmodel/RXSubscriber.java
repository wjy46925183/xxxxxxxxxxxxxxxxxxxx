package com.dlg.viewmodel;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：wangdakuan
 * 主要功能：订阅者监听
 * 创建时间：2016/12/26 17:02
 */
public  class RXSubscriber<T, M> extends BaseSubscrlber<T, M>{
    public RXSubscriber(BasePresenter presenter) {
        super(presenter);
    }
    @Override
    public void requestNext(M m) {

    }

    @Override
    public void requestNextCode(Object m) {

    }
}
