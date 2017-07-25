package com.dlg.data.oddjob.model;

import com.dlg.data.home.model.XYCoordinateBean;

import java.io.Serializable;

/**
 * 作者：王进亚
 * 主要功能：雇主零工地图详情
 * 创建时间：2017/7/11 09:19
 */

public class HirerMapBean implements Serializable {

    /**
     * id : 123321321123123
     * active : 1
     * version : 9
     * createTime : 1492148975000
     * createUserId : 788909895532548100
     * modifyTime : 1492501135000
     * modifyUserId : null
     * employeeId : 788909895532548100
     * employerId : 789009063844384800
     * status : 21
     * demandType : 3
     * businessNumber : 1235321345312
     * isInProgress : 0
     * currentOperateStatus : 21
     * jobId : 158158158
     * jobTitle : null
     * postName : ces
     * postType : 21
     * postTypeName : 其他
     * name : 刘奎
     * phone : 18321271116
     * logo : http://s.gongren.com/dds/img/oddjob/logo/922512/s27526821140839383213591176060.jpg
     * releaseCount : 44
     * joinCount : 5
     * scoreCount : 4
     * creditCount : null
     * distance : jd.scope_2
     * description : 江苏诚信聘人力资源有限公司。人才中介；职业中介；计算机领域内的技术开发、技术咨询、技术转让、技术服务；网络工程；计算机系统集成；计算机软件开发与销售；计算机及配件的上门安装、上门维修；电脑图文设计、制作。（依法须经批准的项目，经相关部门批准后方可开展经营活动）\r\n\r\n\r\n（依法须经批准的项目，经相关部门批准后方可开展经营活动）。核心团队包括：葛猛任监事;董亮任执行董事兼总经理;本公司主要股东包括：董亮;赵腾达;我公司地址为昆山市花桥镇徐公桥路2号352号房，欢迎前来洽谈合作业务。
     * xyCoordinateVo : {"jobXCoordinate":121.392577,"jobYCoordinate":31.242348,"userXCoordinate":11,"userYCoordinate":22,"distanceToJob":12243.73}
     * startDate : null
     * endDate : null
     * price : 150
     * totalPrice : null
     * meterUnit : 1
     * meterUnitName : 天
     */

    public String id;
    public String active;
    public String version;
    public String createTime;
    public String createUserId;
    public String modifyTime;
    public String modifyUserId;
    public String employeeId;
    public String employerId;
    public int status;
    public String demandType;
    public String businessNumber;
    public String isInProgress;
    public String currentOperateStatus;
    public String jobId;
    public String jobTitle;
    public String postName;
    public int identity;
    public String postType;
    public String postTypeName;
    public String name;
    public String phone;
    public String logo;
    public String releaseCount;
    public String joinCount;
    public String scoreCount;
    public String creditCount;
    public String distance;
    public String description;
    public XYCoordinateBean xyCoordinateVo;
    public String startDate;
    public String endDate;
    public String price;
    public String totalPrice;
    public String meterUnit;
    public String meterUnitName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDemandType() {
        return demandType;
    }

    public void setDemandType(String demandType) {
        this.demandType = demandType;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getIsInProgress() {
        return isInProgress;
    }

    public void setIsInProgress(String isInProgress) {
        this.isInProgress = isInProgress;
    }

    public String getCurrentOperateStatus() {
        return currentOperateStatus;
    }

    public void setCurrentOperateStatus(String currentOperateStatus) {
        this.currentOperateStatus = currentOperateStatus;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getPostTypeName() {
        return postTypeName;
    }

    public void setPostTypeName(String postTypeName) {
        this.postTypeName = postTypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getReleaseCount() {
        return releaseCount;
    }

    public void setReleaseCount(String releaseCount) {
        this.releaseCount = releaseCount;
    }

    public String getJoinCount() {
        return joinCount;
    }

    public void setJoinCount(String joinCount) {
        this.joinCount = joinCount;
    }

    public String getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(String scoreCount) {
        this.scoreCount = scoreCount;
    }

    public String getCreditCount() {
        return creditCount;
    }

    public void setCreditCount(String creditCount) {
        this.creditCount = creditCount;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public XYCoordinateBean getXyCoordinateVo() {
        return xyCoordinateVo;
    }

    public void setXyCoordinateVo(XYCoordinateBean xyCoordinateVo) {
        this.xyCoordinateVo = xyCoordinateVo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMeterUnit() {
        return meterUnit;
    }

    public void setMeterUnit(String meterUnit) {
        this.meterUnit = meterUnit;
    }

    public String getMeterUnitName() {
        return meterUnitName;
    }

    public void setMeterUnitName(String meterUnitName) {
        this.meterUnitName = meterUnitName;
    }
}
