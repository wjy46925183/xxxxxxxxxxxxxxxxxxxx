package com.dlg.data.home;

import com.alibaba.fastjson.JSON;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.home.interactor.HomeInteractor;
import com.dlg.data.home.model.BossListBean;
import com.dlg.data.home.model.ConditionSearchBean;
import com.dlg.data.home.model.DataBean;
import com.dlg.data.home.model.DictionaryBean;
import com.dlg.data.home.model.DoingTaskOrderDetailBean;
import com.dlg.data.home.model.EmployeeListBean;
import com.dlg.data.home.model.HistoryBean;
import com.dlg.data.home.model.HomeMapBossListBean;
import com.dlg.data.home.model.HomeMapListBean;
import com.dlg.data.home.model.JobOrdersInfo;
import com.dlg.data.home.model.OddJobMarketBean;
import com.dlg.data.home.model.ServiceShareBean;
import com.dlg.data.home.model.WorkCardBean;
import com.dlg.data.home.url.HomeUrl;
import com.dlg.data.oddjob.model.ServiceListDataBean;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Observable;

/**
 * 作者：wangdakuan
 * 主要功能：缓存实现接口
 * 创建时间：2017/6/23 10:34
 */
public class HomeDiaskSource implements HomeInteractor {

    private final ObjectCache objectCache;

    public HomeDiaskSource(ObjectCache objectCache) {
        this.objectCache = objectCache;
    }


    @Override
    public Observable<JsonResponse<List<EmployeeListBean>>> getListData(HashMap<String, String> hashMap) {
        return this.objectCache.getList(HomeUrl.EMPPLOYEELIST_URL + JSON.toJSONString(hashMap), JsonResponse.class, EmployeeListBean.class);
    }


    @Override
    public Observable<Boolean> isHasDoingTask(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<JsonResponse<List<DoingTaskOrderDetailBean>>> getDoingTaskDetailList(HashMap<String, String> hashMap) {
        return this.objectCache.getList(HomeUrl.DOING_TASK_DETAIL_LIST + JSON.toJSONString(hashMap), JsonResponse.class, DoingTaskOrderDetailBean.class);
    }

    @Override
    public Observable<JsonResponse<List<HomeMapListBean>>> getHomeWorkMapList(HashMap<String, String> hashMap) {
        return this.objectCache.getList(HomeUrl.HOME_WORK_MAP_LIST + JSON.toJSONString(hashMap), JsonResponse.class, HomeMapListBean.class);
    }

    @Override
    public Observable<JsonResponse<List<HomeMapBossListBean>>> getHomeBossMapList(HashMap<String, String> hashMap) {
        return this.objectCache.getList(HomeUrl.HOME_BOSS_MAP_LIST + JSON.toJSONString(hashMap), JsonResponse.class, HomeMapBossListBean.class);
    }

    @Override
    public Observable<Boolean> isHasDoingTaskBoss(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<JsonResponse<List<DataBean>>> getBossTaskingList(HashMap<String, String> hashMap) {
        return this.objectCache.getList(HomeUrl.BOSS_IS_TASKING_LIST + JSON.toJSONString(hashMap), JsonResponse.class, DataBean.class);
    }

    @Override
    public Observable<JsonResponse<List<BossListBean>>> getBossDtaList(HashMap<String, String> hashMap) {
        return this.objectCache.getList(HomeUrl.BOSS_DATA_LIST + JSON.toJSONString(hashMap), JsonResponse.class, BossListBean.class);
    }

    @Override
    public Observable<List<DictionaryBean>> getDictionaryList(HashMap<String, String> hashMap) {
        return this.objectCache.getList(HomeUrl.DICTIONARY + JSON.toJSONString(hashMap), DictionaryBean.class);
    }

    @Override
    public Observable<JsonResponse<List<WorkCardBean>>> getWorkCard(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<JsonResponse<List<HistoryBean>>> getHistoryList(HashMap<String, String> hashMap) {
        return this.objectCache.getList(HomeUrl.SEARCH_HISTORY + JSON.toJSONString(hashMap), JsonResponse.class, HistoryBean.class);
    }

    @Override
    public Observable<JsonResponse<List<String>>> getAddJobList(HashMap<String, String> hashMap) {
        return this.objectCache.getList(HomeUrl.ADD_JOB_CONDITION + JSON.toJSONString(hashMap), JsonResponse.class, String.class);
    }

    @Override
    public Observable<JsonResponse<List<EmployeeListBean>>> getSearchConditionList(HashMap<String, String> hashMap) {
        return this.objectCache.getList(HomeUrl.GET_JOB_BY_CONDITION + JSON.toJSONString(hashMap), JsonResponse.class, ConditionSearchBean.class);
    }

    @Override
    public Observable<JsonResponse<List<String>>> deletHistory(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<JsonResponse<List<JobOrdersInfo>>> jobOrdersInfo(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<JsonResponse<String>> selectOrder(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<JsonResponse<String>> subscribeService(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<JsonResponse<List<ServiceListDataBean>>> serviceDetail(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<JsonResponse<List<ServiceShareBean>>> shareService(HashMap<String, String> hashMap) {
        return null;
    }

    @Override
    public Observable<JsonResponse<List<OddJobMarketBean>>> getOddJobMarket(HashMap<String, String> hashMap) {
        return this.objectCache.getList(HomeUrl.ODD_JOB_MARKET + JSON.toJSONString(hashMap), JsonResponse.class, OddJobMarketBean.class);
    }
}
