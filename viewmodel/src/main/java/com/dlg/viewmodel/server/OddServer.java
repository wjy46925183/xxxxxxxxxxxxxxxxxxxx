package com.dlg.viewmodel.server;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dlg.data.oddjob.factory.OddFactory;
import com.dlg.data.oddjob.interactor.OddJobInteractor;
import com.dlg.data.oddjob.url.OddUrl;

import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/6 15:20
 */

public class OddServer {

    OddFactory oddFactory;

    public OddServer(Context appContext) {
        this(new OddFactory(appContext));
    }

    public OddServer(OddFactory oddFactory) {
        this.oddFactory = oddFactory;
    }

    /**
     * 雇主零工列表数据
     *
     * @param subscriber
     * @param hashMap
     */
    public void getOddHirerList(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.ODD_HIRER + JSON.toJSONString(hashMap), true);
        dataInteractor.getOddHirerList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 编辑发布零工界面
     * <p>
     * 发布零工界面
     *
     * @param subscriber
     * @param hashMap
     */
    public void getReleaseJob(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.RELEASE_JOB + JSON.toJSONString(hashMap), true);
        dataInteractor.getReleaseJOb(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 编辑零工界面
     *
     * @param subscriber
     * @param hashMap
     */
    public void getEditJob(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.EDIT_RELEASE_JOB + JSON.toJSONString(hashMap), true);
        dataInteractor.getEditJOb(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇主零工列表数据
     *
     * @param subscriber
     * @param hashMap
     */
    public void getOddEmployeeList(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.ODD_EMPLOYEE_LIST + JSON.toJSONString(hashMap), true);
        dataInteractor.getOddEmployeeList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇主零工地图
     *
     * @param subscriber
     * @param hashMap
     */
    public void getOddHirerMapList(Subscriber subscriber, HashMap<String, String> hashMap) {

        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.ODD_MAP + JSON.toJSONString(hashMap), true);
        dataInteractor.getHirerMapList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 批量修改订单状态
     *
     * @param subscriber
     * @param hashMap
     */
    public void getOddHirerLots(Subscriber subscriber, HashMap<String, String> hashMap) {

        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.ODD_LOTS + JSON.toJSONString(hashMap), true);
        dataInteractor.lotsOrders(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇主零工地图详情
     *
     * @param subscriber
     * @param hashMap
     */
    public void getJobtaskInfo(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.GET_JOBTASK_INFO + JSON.toJSONString(hashMap), true);
        dataInteractor.getJobtaskInfo(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 查询服务列表
     *
     * @param subscriber
     * @param hashMap
     */
    public void getJobServiceList(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.GET_JOB_SERVICE_LIST + JSON.toJSONString(hashMap), true);
        dataInteractor.getJobServiceList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 查询订单统计数据
     *
     * @param subscriber
     * @param hashMap
     */
    public void queryReleaseJoinData(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.QUERY_RELEASE_JOIN_DATA + JSON.toJSONString(hashMap), true);
        dataInteractor.queryReleaseJoinData(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇主和雇员评价
     *
     * @param subscriber
     * @param hashMap
     */
    public void evaluateItems(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.HIRER_EVALUATE + JSON.toJSONString(hashMap), true);
        dataInteractor.getEvaluateItems(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇主和雇员评价
     *
     * @param subscriber
     * @param hashMap
     */
    public void evaluateClick(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.EVALUATE_CLICK + JSON.toJSONString(hashMap), true);
        dataInteractor.goToEvaluate(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇主和雇员评价
     *
     * @param subscriber
     * @param hashMap
     */
    public void getTruskAndMoney(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.TRUANDMONEY + JSON.toJSONString(hashMap), true);
        dataInteractor.getTruAndMoney(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇主和雇员评价
     *
     * @param subscriber
     * @param hashMap
     */
    public void goToCancleOrder(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.CANCELORDER + JSON.toJSONString(hashMap), true);
        dataInteractor.goToCancleOrder(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 抢单
     *
     * @param subscriber
     * @param hashMap
     */
    public void grabSingleOrder(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor dataInteractor = oddFactory.createHomeData(OddUrl.GRAB_SINGLE_ORDER + JSON.toJSONString(hashMap), true);
        dataInteractor.grabSingleOrder(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 服务列表查询
     *
     * @param subscriber
     * @param hashMap
     */
    public void getServiceList(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor oddJobInteractor = oddFactory.createHomeData(OddUrl.SEVICE_LIST + JSON.toJSONString(hashMap), true);
        oddJobInteractor.getServiceList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 发布零工服务
     *
     * @param subscriber
     * @param hashMap
     */
    public void releaseService(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor oddJobInteractor = oddFactory.createHomeData(OddUrl.RELEASE_SERVICE + JSON.toJSONString(hashMap), true);
        oddJobInteractor.releaseService(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 删除订单 雇主
     *
     * @param subscriber
     * @param hashMap
     */
    public void deleteHirerOrder(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor oddJobInteractor = oddFactory.createHomeData(OddUrl.DELETE_HIRER_ORDER + JSON.toJSONString(hashMap), true);
        oddJobInteractor.deleteHirerOrder(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 删除零工 雇员
     *
     * @param subscriber
     * @param hashMap
     */
    public void deleteEmployeeOrder(Subscriber subscriber, HashMap<String, String> hashMap) {
        OddJobInteractor oddJobInteractor = oddFactory.createHomeData(OddUrl.DELETE_EMPLOYEE_ORDER + JSON.toJSONString(hashMap), true);
        oddJobInteractor.deleteEmployeeOrder(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
