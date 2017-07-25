package com.dlg.data.home.interactor;

import com.dlg.data.home.model.BossListBean;
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
import com.dlg.data.oddjob.model.ServiceListDataBean;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Observable;

/**
 * 作者：wangdakuan
 * 主要功能：首页模块接口
 * 创建时间：2017/6/23 09:57
 */
public interface HomeInteractor {
    /**
     * 雇员列表接口
     * @param hashMap
     * @return
     */
    Observable<JsonResponse<List<EmployeeListBean>>> getListData(HashMap<String,String> hashMap);


    /**
     * 雇员是否有进行中的订单
     * @param hashMap
     * @return
     */
    Observable<Boolean> isHasDoingTask(HashMap<String,String> hashMap);

    /**
     * 雇主是否有进行中的订单
     * @param hashMap
     * @return
     */
    Observable<Boolean> isHasDoingTaskBoss(HashMap<String,String> hashMap);

    /**
     * 雇员进行中的订单数据
     * @param hashMap
     * @return
     */
    Observable<JsonResponse<List<DoingTaskOrderDetailBean>>> getDoingTaskDetailList(HashMap<String,String> hashMap);

    /**
     * 雇员首页地图数据
     * @param hashMap
     * @return
     */
    Observable<JsonResponse<List<HomeMapListBean>>> getHomeWorkMapList(HashMap<String,String> hashMap);

    /**
     * 雇主首页地图数据
     * @param hashMap
     * @return
     */
    Observable<JsonResponse<List<HomeMapBossListBean>>> getHomeBossMapList(HashMap<String,String> hashMap);

    /**
     * 雇主进行中订单
     * @param hashMap
     * @return
     */
    Observable<JsonResponse<List<DataBean>>> getBossTaskingList(HashMap<String,String> hashMap);

    /**
     * 雇主列表数据
     * @param hashMap
     * @return
     */
    Observable<JsonResponse<List<BossListBean>>> getBossDtaList(HashMap<String,String> hashMap);

    /**
     * 字典//零工类型 热门
     * @param hashMap
     * @return
     */
    Observable<List<DictionaryBean>> getDictionaryList(HashMap<String,String> hashMap);

    /**
     *地图首页 卡片
     */
    Observable<JsonResponse<List<WorkCardBean>>> getWorkCard(HashMap<String,String> hashMap);

    /**
     *历史搜索记录
     */
    Observable<JsonResponse<List<HistoryBean>>> getHistoryList(HashMap<String,String> hashMap);

    /**
     *新增搜索历史
     */
    Observable<JsonResponse<List<String>>> getAddJobList(HashMap<String,String> hashMap);

    /**
     *條件搜索
     */
    Observable<JsonResponse<List<EmployeeListBean>>> getSearchConditionList(HashMap<String,String> hashMap);
    /**
     *删除历史
     */
    Observable<JsonResponse<List<String>>> deletHistory(HashMap<String,String> hashMap);
    /**
     * 可接单列表
     */
    Observable<JsonResponse<List<JobOrdersInfo>>> jobOrdersInfo(HashMap<String,String> hashMap);
    /**
     * 选择需求订单
     */
    Observable<JsonResponse<String>> selectOrder(HashMap<String,String> hashMap);

    /**
     * 预约
     * @param hashMap
     * @return
     */
    Observable<JsonResponse<String>> subscribeService(HashMap<String,String> hashMap);
    /**
     * 服务详情
     */
    Observable<JsonResponse<List<ServiceListDataBean>>> serviceDetail(HashMap<String,String> hashMap);
    /**
     * 服务分享
     */
    Observable<JsonResponse<List<ServiceShareBean>>> shareService(HashMap<String,String> hashMap);

    /**
     * 代理商的 零工市场
     * @param hashMap
     * @return
     */
    Observable<JsonResponse<List<OddJobMarketBean>>> getOddJobMarket(HashMap<String,String> hashMap);

}
