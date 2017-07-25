package com.dlg.viewmodel.home;

import android.content.Context;

import com.dlg.data.home.model.DictionaryBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.home.presenter.DictionaryPresenter;
import com.dlg.viewmodel.server.HomeServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：小明
 * 主要功能：词典
 * 创建时间：2017/6/28 0028 16:04
 */
public class DictionaryViewModel extends BaseViewModel<JsonResponse<List<DictionaryBean>>> {
    private BasePresenter basePresenter;
    private final HomeServer mServer;
    private DictionaryPresenter dictionaryPresenter;

    public DictionaryViewModel(Context context, BasePresenter basePresenter, DictionaryPresenter dictionaryPresenter) {
        this.basePresenter = basePresenter;
        mServer = new HomeServer(context);
        this.dictionaryPresenter = dictionaryPresenter;
    }

    public void getDictionaryData(String type, String sign) {
        HashMap<String, String> map = new HashMap<>();
        map.put("groupCode", type);
        if (sign.equals("1")) {
            map.put("sign", sign);
        }

        mSubscriber = getDictionarySubscriber();
        mServer.getDictionaryData(mSubscriber, map);
    }

    private Subscriber<JsonResponse<List<DictionaryBean>>> getDictionarySubscriber() {
        return new RXSubscriber<JsonResponse<List<DictionaryBean>>, List<DictionaryBean>>(basePresenter) {
            @Override
            public void requestNext(List<DictionaryBean> dictionaryBeen) {
                dictionaryPresenter.getDictionary(dictionaryBeen);
            }
        };
    }
}
