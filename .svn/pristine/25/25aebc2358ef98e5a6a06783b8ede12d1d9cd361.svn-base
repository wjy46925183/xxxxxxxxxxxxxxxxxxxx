package com.dlg.viewmodel.common;

import android.content.Context;

import com.dlg.data.common.model.ShareDataBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.common.presenter.SharePresenter;
import com.dlg.viewmodel.server.CommonServer;

import java.util.HashMap;

import rx.Subscriber;

/**
 * 作者：wangdakuan
 * 主要功能：分享
 * 创建时间：2017/7/17 19:45
 */
public class ShareViewModel extends BaseViewModel<ShareDataBean> {
    private SharePresenter sharePresenter; //与页面交互的接口
    private CommonServer mCommonServer;
    private BasePresenter basePresenter;

    public ShareViewModel(Context context, SharePresenter presenter) {
        this.sharePresenter = presenter;
        mCommonServer = new CommonServer(context);
        mContext = context;
    }

    /**
     * 分享接口
     *
     * @param taskId
     */
    public void getShareData(String taskId) {
        HashMap map = new HashMap();
        map.put("taskId", taskId);
        mSubscriber = shareSubscriber();
        mCommonServer.getShareData(mSubscriber, map);
    }

    private Subscriber<ShareDataBean> shareSubscriber() {
        return new RXSubscriber<ShareDataBean, ShareDataBean>(basePresenter) {
            @Override
            public void requestNext(ShareDataBean dataBean) {
                if (null != sharePresenter) {
                    sharePresenter.onShareData(dataBean);
                }
            }
        };
    }
}
