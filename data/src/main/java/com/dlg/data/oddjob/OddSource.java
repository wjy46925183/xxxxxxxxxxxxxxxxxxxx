package com.dlg.data.oddjob;

import com.alibaba.fastjson.JSON;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.oddjob.interactor.OddJobInteractor;
import com.dlg.data.oddjob.model.CancleBean;
import com.dlg.data.oddjob.model.EmployeeOrderItemBean;
import com.dlg.data.oddjob.model.EvaluateItemsBean;
import com.dlg.data.oddjob.model.HirerMapBean;
import com.dlg.data.oddjob.model.OddHirerBean;
import com.dlg.data.oddjob.model.OddServiceItemBean;
import com.dlg.data.oddjob.model.OrderStatisticalBean;
import com.dlg.data.oddjob.model.ServiceListDataBean;
import com.dlg.data.oddjob.url.OddUrl;
import com.http.okgo.OkGo;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonConvert;
import okhttp.rx.JsonResponse;
import okhttp.rx.RxAdapter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/6 15:43
 */

public class OddSource implements OddJobInteractor {

    private final ObjectCache objectCache;

    /**
     */
    public OddSource(ObjectCache objectCache) {
        this.objectCache = objectCache;
    }

    private Action1<Object> saveToCacheAction(final String key) {
        return new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o != null) {
                    OddSource.this.objectCache.put(o, key);
                }
            }
        };
    }

    /**
     * 雇主零工列表
     *
     * @param hashMap
     * @return
     */
    @Override
    public Observable<JsonResponse<List<OddHirerBean>>> getOddHirerList(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.ODD_HIRER)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<OddHirerBean>>>() {
                }, RxAdapter.<JsonResponse<List<OddHirerBean>>>create())
//                .doOnNext(saveToCacheAction(OddUrl.ODD_HIRER + JSON.toJSONString(hashMap)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<EmployeeOrderItemBean>>> getOddEmployeeList(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.ODD_EMPLOYEE_LIST)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<EmployeeOrderItemBean>>>() {
                }, RxAdapter.<JsonResponse<List<EmployeeOrderItemBean>>>create())
//                .doOnNext(saveToCacheAction(OddUrl.ODD_HIRER + JSON.toJSONString(hashMap)))
                .observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Observable<JsonResponse<List<HirerMapBean>>> getHirerMapList(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.ODD_MAP)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<HirerMapBean>>>() {
                }, RxAdapter.<JsonResponse<List<HirerMapBean>>>create())
//                .doOnNext(saveToCacheAction(OddUrl.ODD_HIRER + JSON.toJSONString(hashMap)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 发布编辑零工
     *
     * @param hashMap
     * @return
     */
    @Override
    public Observable<JsonResponse<List<String>>> getReleaseJOb(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.RELEASE_JOB)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<String>>>() {
                }, RxAdapter.<JsonResponse<List<String>>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 编辑零工
     *
     * @param hashMap
     * @return
     */
    @Override
    public Observable<JsonResponse<List<String>>> getEditJOb(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.EDIT_RELEASE_JOB)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<String>>>() {
                }, RxAdapter.<JsonResponse<List<String>>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 雇主批量
     *
     * @param hashMap
     * @return
     */
    @Override
    public Observable<JsonResponse<String>> lotsOrders(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.ODD_LOTS)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<String>>() {
                }, RxAdapter.<JsonResponse<String>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 雇主零工地图详情
     *
     * @param hashMap
     * @return
     */
    @Override
    public Observable<JsonResponse<List<OddHirerBean>>> getJobtaskInfo(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.GET_JOBTASK_INFO)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<OddHirerBean>>>() {
                }, RxAdapter.<JsonResponse<List<OddHirerBean>>>create())
                .doOnNext(saveToCacheAction(OddUrl.GET_JOBTASK_INFO + JSON.toJSONString(hashMap)))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<OddServiceItemBean>>> getJobServiceList(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.GET_JOB_SERVICE_LIST)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<OddServiceItemBean>>>() {
                }, RxAdapter.<JsonResponse<List<OddServiceItemBean>>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<OrderStatisticalBean>>> queryReleaseJoinData(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.QUERY_RELEASE_JOIN_DATA)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<OrderStatisticalBean>>>() {
                }, RxAdapter.<JsonResponse<List<OrderStatisticalBean>>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 雇员和雇主评价标签
     *
     * @param hashMap
     * @return
     */
    @Override
    public Observable<JsonResponse<List<EvaluateItemsBean>>> getEvaluateItems(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.HIRER_EVALUATE)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<EvaluateItemsBean>>>() {
                }, RxAdapter.<JsonResponse<List<EvaluateItemsBean>>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<Object>> goToEvaluate(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.EVALUATE_CLICK)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<Object>>>() {
                }, RxAdapter.<JsonResponse<Object>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<CancleBean>>> getTruAndMoney(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.TRUANDMONEY)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<CancleBean>>>() {
                }, RxAdapter.<JsonResponse<List<CancleBean>>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<Object>> goToCancleOrder(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.CANCELORDER)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<Object>>() {
                }, RxAdapter.<JsonResponse<Object>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<String>>> grabSingleOrder(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.GRAB_SINGLE_ORDER)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<String>>>() {
                }, RxAdapter.<JsonResponse<List<String>>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 服务列表查询
     *
     * @param hashMap
     * @return
     */
    @Override
    public Observable<JsonResponse<List<ServiceListDataBean>>> getServiceList(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.SEVICE_LIST)//
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<ServiceListDataBean>>>() {
                }, RxAdapter.<JsonResponse<List<ServiceListDataBean>>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 发布零工服务
     *
     * @param hashMap
     * @return
     */
    @Override
    public Observable<JsonResponse<String>> releaseService(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.RELEASE_SERVICE)
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<String>>() {
                }, RxAdapter.<JsonResponse<String>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<String>>> deleteHirerOrder(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.DELETE_HIRER_ORDER)
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<String>>>() {
                }, RxAdapter.<JsonResponse<List<String>>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<JsonResponse<List<String>>> deleteEmployeeOrder(HashMap<String, String> hashMap) {
        return OkGo.post(OddUrl.DELETE_EMPLOYEE_ORDER)
                .params(hashMap)
                .getCall(new JsonConvert<JsonResponse<List<String>>>() {
                }, RxAdapter.<JsonResponse<List<String>>>create())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
