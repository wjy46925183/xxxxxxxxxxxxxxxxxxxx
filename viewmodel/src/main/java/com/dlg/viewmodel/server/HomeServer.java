package com.dlg.viewmodel.server;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.dlg.data.home.factory.HomeFactory;
import com.dlg.data.home.interactor.HomeInteractor;
import com.dlg.data.home.url.HomeUrl;

import java.util.HashMap;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：wangdakuan
 * 主要功能：首页模块请求服务
 * 创建时间：2017/6/1 14:31
 */
public class HomeServer {

    HomeFactory homeFactory;

    public HomeServer(Context appContext) {
        this(new HomeFactory(appContext));
    }

    public HomeServer(HomeFactory homeFactory) {
        this.homeFactory = homeFactory;
    }

    /**
     * 雇员列表数据
     *
     * @param subscriber
     * @param hashMap
     */
    public void getList(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.EMPPLOYEELIST_URL + JSON.toJSONString(hashMap), true);
        dataInteractor.getListData(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 雇员是否有进行中的订单
     *
     * @param subscriber
     * @param hashMap
     */
    public void isHasDoingTask(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.WORKER_IS_TASKING + JSON.toJSONString(hashMap));
        dataInteractor.isHasDoingTask(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇员进行中的订单
     *
     * @param subscriber
     * @param hashMap
     */
    public void getDoingTaskDetailList(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.DOING_TASK_DETAIL_LIST + JSON.toJSONString(hashMap), true);
        dataInteractor.getDoingTaskDetailList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇员首页地图数据
     *
     * @param subscriber
     * @param hashMap
     */
    public void getHomeWorkMapList(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.HOME_WORK_MAP_LIST + JSON.toJSONString(hashMap), true);
        dataInteractor.getHomeWorkMapList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇主首页地图数据
     *
     * @param subscriber
     * @param hashMap
     */
    public void getHomeBossMapList(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.HOME_BOSS_MAP_LIST + JSON.toJSONString(hashMap), true);
        dataInteractor.getHomeBossMapList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇员是否有进行中的订单
     *
     * @param subscriber
     * @param hashMap
     */
    public void isHasDoingTaskBoss(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.BOSS_IS_TASKING + JSON.toJSONString(hashMap));
        dataInteractor.isHasDoingTaskBoss(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 雇主进行中订单
     * @param subscriber
     * @param hashMap
     */
    public void getBossTaskingList(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.BOSS_IS_TASKING_LIST + JSON.toJSONString(hashMap));
        dataInteractor.getBossTaskingList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    /**
     * 雇主列表数据
     * @param subscriber
     * @param hashMap
     */
    public void getBossDtaList(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.BOSS_DATA_LIST + JSON.toJSONString(hashMap),true);
        dataInteractor.getBossDtaList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 零工词典
     * //热门 零工类型
     * @param subscriber
     * @param hashMap
     * */
    public void getDictionaryData(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.DICTIONARY + JSON.toJSONString(hashMap));
        dataInteractor.getDictionaryList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 零工类型
     * @param subscriber
     * @param hashMap
     * */
    public void getWorkCard(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.EMPLOYEE_CARD,true);
        dataInteractor.getWorkCard(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 历史搜索记录
     * @param subscriber
     * @param hashMap
     * */
    public void getHistory(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.SEARCH_HISTORY,true);
        dataInteractor.getHistoryList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 新增搜索条件
     * @param subscriber
     * @param hashMap
     * */
    public void getAddJobData(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.ADD_JOB_CONDITION,true);
        dataInteractor.getAddJobList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 條件搜索
     * @param subscriber
     * @param hashMap
     * */
    public void getSearchData(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.GET_JOB_BY_CONDITION,true);
        dataInteractor.getSearchConditionList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 删除历史搜索记录
     * @param subscriber
     * @param hashMap
     * */
    public void deletHistory(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.DELEET_SERACH_HISTORY,false);
        dataInteractor.deletHistory(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 可接单
     * @param subscriber
     * @param hashMap
     * */
    public void jobOrderNum(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.ORDERED_NUM,false);
        dataInteractor.jobOrdersInfo(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 选择需求
     */
    public void selectOrder(Subscriber subscriber, HashMap<String, String> hashMap) {
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.SELECT_ORDER,false);
        dataInteractor.selectOrder(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 预约
     */
    public void subscribeService(Subscriber subscriber, HashMap<String, String> hashMap){
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.SUBSCRIBE_ORDER,false);
        dataInteractor.subscribeService(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 服务详情
     * @param subscriber
     * @param hashMap
     */
    public void serviceDetail(Subscriber subscriber, HashMap<String, String> hashMap){
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.SERVICE_DETAIL,false);
        dataInteractor.serviceDetail(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    /**
     * 服务分享
     * @param subscriber
     * @param hashMap
     */
    public void shareService(Subscriber subscriber, HashMap<String, String> hashMap){
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.SHARE_SERVICE,false);
        dataInteractor.shareService(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void getOddJobMarket(Subscriber subscriber, HashMap<String, String> hashMap){
        HomeInteractor dataInteractor = homeFactory.createHomeData(HomeUrl.ODD_JOB_MARKET,false);
        dataInteractor.getOddJobMarket(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
