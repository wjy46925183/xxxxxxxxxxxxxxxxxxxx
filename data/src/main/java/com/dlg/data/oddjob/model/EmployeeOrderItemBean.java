package com.dlg.data.oddjob.model;

import com.dlg.data.common.model.ActionButtonsBean;

import java.io.Serializable;

/**
 * 作者：wangdakuan
 * 主要功能：雇员零工列表Item数据
 * 创建时间：2017/7/10 10:52
 */
public class EmployeeOrderItemBean extends BaseOddDetailBean implements Serializable {

    private String compensatoryPayment; ////赔偿金
    private long modifyTime; //修改时间
    private String cancelCause; //取消原因
    private String evaluateLevel; //评价等级
    private String modifyUserId; //修改用户id
    private String jobId; //子任务id
    private int isInProgress; //是否进行中(0.否,1.是)
    private int isEvaluate; //是否评价(0.否,1.是)
    private String postType; // 类型
    //订单状态：8等待雇员同意9雇员拒绝,
    // 10.等待同意,11.拒绝接单,20.等待开工,
    // 21.正在干活中,22.等待验收,30.待收款,40.已完成
    private int status;
    private int isCancel; // 是否取消(0.否,1.是)
    private int meterUnit; //几天或几小时
    private int currentOperateStatus; //当前操作状态

    private ActionButtonsBean buttonsBeen; //订单操作按钮数据

    public String getCompensatoryPayment() {
        return compensatoryPayment;
    }

    public void setCompensatoryPayment(String compensatoryPayment) {
        this.compensatoryPayment = compensatoryPayment;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCancelCause() {
        return cancelCause;
    }

    public void setCancelCause(String cancelCause) {
        this.cancelCause = cancelCause;
    }

    public String getEvaluateLevel() {
        return evaluateLevel;
    }

    public void setEvaluateLevel(String evaluateLevel) {
        this.evaluateLevel = evaluateLevel;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public int getIsInProgress() {
        return isInProgress;
    }

    public void setIsInProgress(int isInProgress) {
        this.isInProgress = isInProgress;
    }

    public int getIsEvaluate() {
        return isEvaluate;
    }

    public void setIsEvaluate(int isEvaluate) {
        this.isEvaluate = isEvaluate;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(int isCancel) {
        this.isCancel = isCancel;
    }

    public int getMeterUnit() {
        return meterUnit;
    }

    public void setMeterUnit(int meterUnit) {
        this.meterUnit = meterUnit;
    }

    public int getCurrentOperateStatus() {
        return currentOperateStatus;
    }

    public void setCurrentOperateStatus(int currentOperateStatus) {
        this.currentOperateStatus = currentOperateStatus;
    }

    public ActionButtonsBean getButtonsBeen() {
        return buttonsBeen;
    }

    public void setButtonsBeen(ActionButtonsBean buttonsBeen) {
        this.buttonsBeen = buttonsBeen;
    }
}
