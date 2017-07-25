package com.dlg.viewmodel.oddjob;

import android.content.Context;
import android.text.TextUtils;

import com.common.cache.ACache;
import com.dlg.data.common.model.ActionButtonsBean;
import com.dlg.data.oddjob.model.EmployeeOrderItemBean;
import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.key.AppKey;
import com.dlg.viewmodel.oddjob.presenter.EmployeeOddPresenter;
import com.dlg.viewmodel.server.CommonServer;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 作者：wangdakuan
 * 主要功能：雇员零工请求
 * 创建时间：2017/7/6 15:19
 */

public class OddEmployeeViewModel extends BaseViewModel<JsonResponse<List<EmployeeOrderItemBean>>> {
    private EmployeeOddPresenter mEmployeeOddPresenter;
    private BasePresenter basePresenter;
    private final OddServer mOddServer;
    private CommonServer mCommonServer;
    protected Subscriber<ActionButtonsBean> mActionButtonsBeanSubscriber;
    private Context mContext;

    int num = 0; //请求数据的下标
    private EmployeeOrderItemBean orderItemBean; //当前请求操作按钮的对象

    public OddEmployeeViewModel(Context context, BasePresenter basePresenter, EmployeeOddPresenter presenter) {
        this.mEmployeeOddPresenter = presenter;
        this.basePresenter = basePresenter;
        mOddServer = new OddServer(context);
        mCommonServer = new CommonServer(context);
        this.mContext = context;
    }

    /**
     * 请求接口
     *
     * @param status    状态
     * @param pageIndex 页数
     */
    public void getOddEmployeeList(String status, int pageIndex) {
        num = 0;
        orderItemBean = null;
        HashMap<String, String> map = new HashMap<>();
        map.put("id", ACache.get(mContext).getAsString(AppKey.CacheKey.MY_USER_ID));
        map.put("status", status);
        map.put("pageIndex", pageIndex + "");
        map.put("pagesize", 8 + "");
        mSubscriber = getSub();
        mOddServer.getOddEmployeeList(mSubscriber, map);
    }

    private Subscriber<JsonResponse<List<EmployeeOrderItemBean>>> getSub() {
        return new RXSubscriber<JsonResponse<List<EmployeeOrderItemBean>>, List<EmployeeOrderItemBean>>(basePresenter) {
            @Override
            public void requestNext(List<EmployeeOrderItemBean> beanList) {
                if (null != beanList && beanList.size() > 0) {
                    getEmployeeOddDataOrBut(beanList);
                } else {
                    mEmployeeOddPresenter.getEmployeeOddList(beanList);
                }
            }
        };
    }

    /**
     * 根据加载的数据并获取订单按钮数据填充
     */
    private void getEmployeeOddDataOrBut(final List<EmployeeOrderItemBean> beanList) {
        num = 0;
        rx.Observable.from(beanList)
                .flatMap(new Func1<EmployeeOrderItemBean, rx.Observable<ActionButtonsBean>>() {
                    @Override
                    public rx.Observable<ActionButtonsBean> call(EmployeeOrderItemBean employeeOrderItemBean) {
                        orderItemBean = employeeOrderItemBean;
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("businessNumber", orderItemBean.getBusinessNumber());
                        return mCommonServer.getActionButtonsList(hashMap);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mActionButtonsBeanSubscriber = new RXSubscriber<ActionButtonsBean, ActionButtonsBean>(null) {
                    @Override
                    public void requestNext(ActionButtonsBean buttonsBean) {
                        AddDataBut(buttonsBean, beanList);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mActionButtonsBeanSubscriber) {
            mActionButtonsBeanSubscriber.unsubscribe();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mActionButtonsBeanSubscriber) {
            mActionButtonsBeanSubscriber.unsubscribe();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mActionButtonsBeanSubscriber) {
            mActionButtonsBeanSubscriber.unsubscribe();
        }
    }

    /**
     * 整合操作按钮到订单中
     *
     * @param buttonsBean
     */
    private synchronized void AddDataBut(ActionButtonsBean buttonsBean, List<EmployeeOrderItemBean> beanList) {
        if (null != buttonsBean) {
            for (EmployeeOrderItemBean employeeOrderItemBean : beanList) {
                if (TextUtils.equals(buttonsBean.getBusinessNumber(), employeeOrderItemBean.getBusinessNumber())) {
                    employeeOrderItemBean.setButtonsBeen(buttonsBean);
                    num++;
                }
            }
        }
        if (num >= beanList.size()) {
            mEmployeeOddPresenter.getEmployeeOddList(beanList);
        }
    }
}
