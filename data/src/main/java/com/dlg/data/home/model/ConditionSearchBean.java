package com.dlg.data.home.model;

import java.io.Serializable;

/**
 * 作者：小明
 * 主要功能：
 * 创建时间：2017/7/6 0006 17:11
 */
public class ConditionSearchBean implements Serializable {
    /*  "id":27501847187295293324954543702,//任务id
        "postName":"Post名字",//岗位名称
        "price":200,// 单价
        "jobMeterUnit":1,// 计量单位类型
        "jobMeterUnitName":"单",// 计量单位名称
        "demandType":1,// 雇主需求类型(1.工作日,2.双休日,3.计件)
        "startYear":null,//开始年
        "startMonth":null, //开始月
        "startDay":null, //开始日
        "startHour":null, //开始时
        "startMinute":null, //开始分
        "startSecond":null, //开始秒
        "endYear":null, //结束年
        "endMonth":null, //结束月
        "endDay":null, //结束日
        "endHour":null, //结束时
        "endMinute":null, //结束分
        "endSecond":null, //结束秒
        "distance":10033.35,//距离KM
        "provinceName": "上海市",
        "cityName": "上海市",
        "areaName": "普陀区",
        "villageName": "普陀镇",
        "workAddress": "星光耀广场",//工作地址
        "userCreditCount": null,//诚信值
        "userLogo": ".. /592593/s836785654913437696.jpeg" //用户头像*/




    private String id;
    private String postName;
    private String price;
    private String jobMeterUnit;
    private String jobMeterUnitName;
    private String demandType;
    private String startYear;
    private String startMonth;
    private String startDay;
    private String startHour;
    private String startMinute;
    private String startSecond;
    private String endYear;
    private String endMonth;
    private String endDay;
    private String endHour;
    private String endMinute;
    private String endSecond;
    private String distance;
    private String provinceName;
    private String cityName;
    private String areaName;
    private String villageName;
    private String workAddress;
    private String userCreditCount;
    private String userLogo;

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

    public String getUserLogo() {

        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    public String getUserCreditCount() {

        return userCreditCount;
    }

    public void setUserCreditCount(String userCreditCount) {
        this.userCreditCount = userCreditCount;
    }

    public String getStartYear() {

        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getStartSecond() {

        return startSecond;
    }

    public void setStartSecond(String startSecond) {
        this.startSecond = startSecond;
    }

    public String getStartMonth() {

        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getStartMinute() {

        return startMinute;
    }

    public void setStartMinute(String startMinute) {
        this.startMinute = startMinute;
    }

    public String getStartHour() {

        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getStartDay() {

        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getProvinceName() {

        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getPrice() {

        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPostName() {

        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
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

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndYear() {

        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getEndSecond() {

        return endSecond;
    }

    public void setEndSecond(String endSecond) {
        this.endSecond = endSecond;
    }

    public String getEndMonth() {

        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getEndMinute() {

        return endMinute;
    }

    public void setEndMinute(String endMinute) {
        this.endMinute = endMinute;
    }

    public String getEndHour() {

        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public String getEndDay() {

        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getDistance() {

        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDemandType() {

        return demandType;
    }

    public void setDemandType(String demandType) {
        this.demandType = demandType;
    }

    public String getCityName() {

        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {

        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }






}
