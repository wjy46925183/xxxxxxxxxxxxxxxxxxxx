package com.dlg.viewmodel.oddjob;

import android.content.Context;

import com.common.cache.ACache;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.oddjob.presenter.DeleteOrderPresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：wangdakuan
 * 主要功能：删除订单
 * 创建时间：2017/7/18 10:16
 */
public class DeleteOrderViewModel extends BaseViewModel<JsonResponse<List<String>>> {
    private DeleteOrderPresenter mPresenter;
    private OddServer mOddServer;
    private BasePresenter basePresenter;

    public DeleteOrderViewModel(Context context, BasePresenter basePresenter, DeleteOrderPresenter presenter) {
        mPresenter = presenter;
        mOddServer = new OddServer(context);
        mContext = context;
        this.basePresenter = basePresenter;
    }

    /**
     * 删除订单 雇员
     *
     * @param businessNumber 编号
     * @param userId         雇员id
     */
    public void deleteEmployeeOrder(String businessNumber, String userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("businessNumber", businessNumber);
        map.put("userVo", userId);
        mSubscriber = deleteOrderSub();
        mOddServer.deleteEmployeeOrder(mSubscriber, map);
    }

    /**
     * 删除订单 雇主
     *
     * @param taskId ID
     */
    public void deleteHirerOrder(String taskId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("taskId", taskId);
        map.put("userId", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        mSubscriber = deleteOrderSub();
        mOddServer.deleteHirerOrder(mSubscriber, map);
    }

    private Subscriber<JsonResponse<List<String>>> deleteOrderSub() {
        return new RXSubscriber<JsonResponse<List<String>>, List<String>>(basePresenter) {
            @Override
            public void requestNext(List<String> strings) {
                if (null != mPresenter && null != strings && strings.size() > 0) {
                    if ("0".equals(strings.get(0))) {
                        mPresenter.onDeleteOrderSuccess(true);
                    } else {
                        mPresenter.onDeleteOrderSuccess(false);
                    }
                }
            }
        };
    }
}
