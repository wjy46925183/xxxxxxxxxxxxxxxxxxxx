package com.dlg.data.oddjob.model;

import java.io.Serializable;

/**
 * 作者：小明
 * 主要功能：发布零工的的实体传递类
 * 创建时间：2017/7/11 0011 10:07
 */
public class ReleaseJobBean implements Serializable{
    /**type",1 //零工类型：1招聘 2服务'
    postType", "job.type_12"//岗位类型
    postTypeName", "RestTest"//岗位类型名称
    postName", "Post名字"//岗位名称
    taskTitle", "Rest主题"//任务标题
    taskDescription", "Rest描述"//任务描述
    price", "8"//单价
    recruitNumber", "20"//招聘数量
    jobMeterUnit", "3"//零工计量单位(1.天,2.时,3.单)
    jobMeterUnitName", "单"//零工计量单位名称
    demandType”,”1”//需求类型(1.工作日,2.双休日,3.计件)
    sex", "3"//性别(1.男,2女,3男女不限)
    provinceId", "101"
    provinceName", "上海市"
    cityId", "101")
    cityName", "上海市"
    areaId", "101"
    areaName", "普陀区"
    villageId", "101"
    villageName", "普陀镇"
    workAddress", "星光耀广场"
    startTime", "2017-03-30 09:00:01"//开始日期
    endTime", "2017-04-28 18:00:01"//结束日期
    parentJobId”,””//父任务id（针对代理商业务）
    isAllowAgent”:0, //是否允许代理商介入(0.否,1.是),
    auditPermission”:1, //雇员审批权限方 1自己  2代理商'
    client:” ANDROID”//客户端类型 如：ANDROID、IOS、H5、PC
    isFarmersInsurance://是否购买保险 0否 1是*/
    private String id;
    private String  type;
    private String postType;
    private String postTypeName;
    private String postName;
    private String taskTitle;
    private String taskDescription;
    private String price;
    private String recruitNumber;
    private String jobMeterUnit;
    private String jobMeterUnitName;
    private String demandType;
    private String surplusRecruitNumber;
    private String sex;
    private String provinceId;
    private String provinceName;
    private String cityId;
    private String cityName;
    private String areaId;
    private String areaName;
    private String villageId;
    private String villageName;
    private String workAddress;
    private String startTime;
    private String endTime;
    private String parentJobId;
    private String isAllowAgent;
    private String auditPermission;
    private String client;
    private String isFarmersInsurance;
    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getVillageName() {

        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageId() {

        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaskTitle() {

        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {

        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getStartTime() {

        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSex() {

        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRecruitNumber() {

        return recruitNumber;
    }

    public void setRecruitNumber(String recruitNumber) {
        this.recruitNumber = recruitNumber;
    }

    public String getProvinceName() {

        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceId() {

        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPostTypeName() {

        return postTypeName;
    }

    public void setPostTypeName(String postTypeName) {
        this.postTypeName = postTypeName;
    }

    public String getPostType() {

        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostName() {

        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getParentJobId() {

        return parentJobId;
    }

    public void setParentJobId(String parentJobId) {
        this.parentJobId = parentJobId;
    }

    public String getJobMeterUnitName() {

        return jobMeterUnitName;
    }

    public void setJobMeterUnitName(String jobMeterUnitName) {
        this.jobMeterUnitName = jobMeterUnitName;
    }

    public String getJobMeterUnit() {

        return jobMeterUnit;
    }

    public void setJobMeterUnit(String jobMeterUnit) {
        this.jobMeterUnit = jobMeterUnit;
    }

    public String getIsFarmersInsurance() {

        return isFarmersInsurance;
    }

    public void setIsFarmersInsurance(String isFarmersInsurance) {
        this.isFarmersInsurance = isFarmersInsurance;
    }

    public String getIsAllowAgent() {

        return isAllowAgent;
    }

    public void setIsAllowAgent(String isAllowAgent) {
        this.isAllowAgent = isAllowAgent;
    }

    public String getEndTime() {

        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDemandType() {

        return demandType;
    }

    public void setDemandType(String demandType) {
        this.demandType = demandType;
    }

    public String getClient() {

        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCityName() {

        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityId() {

        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAuditPermission() {

        return auditPermission;
    }

    public void setAuditPermission(String auditPermission) {
        this.auditPermission = auditPermission;
    }

    public String getSurplusRecruitNumber() {
        return surplusRecruitNumber;
    }

    public void setSurplusRecruitNumber(String surplusRecruitNumber) {
        this.surplusRecruitNumber = surplusRecruitNumber;
    }

    @Override
    public String toString() {
        return "ReleaseJobBean{" +
                "areaId='" + areaId + '\'' +
                ", type='" + type + '\'' +
                ", postType='" + postType + '\'' +
                ", postTypeName='" + postTypeName + '\'' +
                ", postName='" + postName + '\'' +
                ", taskTitle='" + taskTitle + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", price='" + price + '\'' +
                ", recruitNumber='" + recruitNumber + '\'' +
                ", jobMeterUnit='" + jobMeterUnit + '\'' +
                ", jobMeterUnitName='" + jobMeterUnitName + '\'' +
                ", demandType='" + demandType + '\'' +
                ", surplusRecruitNumber='" + surplusRecruitNumber + '\'' +
                ", sex='" + sex + '\'' +
                ", provinceId='" + provinceId + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityId='" + cityId + '\'' +
                ", cityName='" + cityName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", villageId='" + villageId + '\'' +
                ", villageName='" + villageName + '\'' +
                ", workAddress='" + workAddress + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", parentJobId='" + parentJobId + '\'' +
                ", isAllowAgent='" + isAllowAgent + '\'' +
                ", auditPermission='" + auditPermission + '\'' +
                ", client='" + client + '\'' +
                ", isFarmersInsurance='" + isFarmersInsurance + '\'' +
                '}';
    }
}
