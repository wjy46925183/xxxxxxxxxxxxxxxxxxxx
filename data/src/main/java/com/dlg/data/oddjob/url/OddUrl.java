package com.dlg.data.oddjob.url;

import com.dlg.data.UrlNet;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/6 15:44
 */

public class OddUrl {
    /**
     * 雇主零工列表 接口
     */
    public static final String ODD_HIRER = UrlNet.getName() + "/api/jobTaskRest/getJobTaskOfMasterPage";
    /**
     * 发布零工
     */
    public static final String RELEASE_JOB = UrlNet.getName() + "/api/jobTaskRest/saveJobTask";
    /**
     * 编辑零工
     */
    public static final String EDIT_RELEASE_JOB = UrlNet.getName() + "/api/jobTaskRest/updateJobTaskById";
    /**
     * 雇员零工列表数据接口
     */
    public static final String ODD_EMPLOYEE_LIST = UrlNet.getName() + "/api/orderRest/findPageListByUserVoAndStatus";
    /**
     * 雇主零工地图卡片信息
     */
    public static final String ODD_MAP = UrlNet.getName() + "/api/orderRest/findFirstOrder";
    /**
     * 雇主批量处理
     */
    public static final String ODD_LOTS = UrlNet.getName() + "/api/orderRest/updateOrderOperaStatusBatch";
    /**
     * 雇员零工地图详情
     */
    public static final String GET_JOBTASK_INFO = UrlNet.getName() + "/api/jobTaskRest/getJobTaskById";
    /**
     * 零工服务列表数据
     */
    public static final String GET_JOB_SERVICE_LIST = UrlNet.getName() + "/api/jobServiceRest/findByUserId";
    /**
     * 用户发单、接单、迟到、取消记录
     */
    public static final String QUERY_RELEASE_JOIN_DATA = UrlNet.getName() + "/api/userRest/queryReleaseJoinLateCancel";
    /**
     * 雇主和雇员评价标签
     */
    public static final String HIRER_EVALUATE = UrlNet.getName() + "/api/dictionaryRest/queryGroup";
    /**
     * 雇主和雇员评价
     */
    public static final String EVALUATE_CLICK = UrlNet.getName() + "/api/orderEvaluateRest/addOrderEvaluate";
    /**
     * 取消订单获取诚信值和赔偿
     */
    public static final String TRUANDMONEY = UrlNet.getName() + "/api/orderCancelRest/orderCancelFare";
    /**
     * 取消订单
     */
    public static final String CANCELORDER = UrlNet.getName() + "/api/orderCancelRest/cancel";
    /**
     * 抢单
     */
    public static final String GRAB_SINGLE_ORDER = UrlNet.getName() + "/api/orderRest/saveOrder";
    /**
     * 服务查询列表
     */
    public static final String SEVICE_LIST = UrlNet.getName() + "/api/jobServiceRest/findByUserId";
    /**
     * 发布零工服务
     */
    public static final String RELEASE_SERVICE = UrlNet.getName() + "/api/jobServiceRest/saveOrUpdateJobService";
    /**
     * 删除订单任务 雇主
     */
    public static final String DELETE_HIRER_ORDER = UrlNet.getName() + "/api/jobTaskRest/deleteJobTaskById";
    /**
     * 删除订单 雇员
     */
    public static final String DELETE_EMPLOYEE_ORDER = UrlNet.getName() + "/api/orderRest/deleteOrderByBusiness";
}
