package com.dlg.data.home.model;

import java.io.Serializable;

/**
 * 作者：邹前坤
 * 主要功能：代理商 零工市场 列表
 * 创建时间： 2017/7/24  15:43
 */

public class OddJobMarketBean implements Serializable {
	/**
	 "userId": "27544528069374666408514337052",//用户ID
	 "postType": "job.type_10",//岗位类型
	 "postTypeName": "运动健身",//岗位类型名称
	 "postName": "一个人",// 岗位名称
	 "taskTitle": "",//     任务标题
	 "taskDescription": "瑜伽馆招聘瑜伽教练",// 任务描述
	 "price": 2000,//  任务单价(橙子)
	 "totalPrice": 0,//   任务总价
	 "recruitNumber": 1,//   招聘数量
	 "surplusRecruitNumber": 1,// 剩余招聘数量
	 "jobMeterUnit": 1,//   零工计量单位(1.天,2.时,3.单)
	 "jobMeterUnitName": "天", //  零工计量单位(1.天,2.时,3.单)
	 "sex": 3, //  性别(1.男,2女,3男女不限)
	 "xCoordinate": 87.565449, //  x坐标
	 "yCoordinate": 44.368899, // y坐标
	 "provinceId": "650000",  //省Id
	 "provinceName": "新疆维吾尔自治区",//   省名称
	 "cityId": "659000",//  市Id
	 "cityName": "自治区直辖县级行政区划",// 市名称
	 "areaId": "820000",   //  区Id
	 "areaName": "五家渠市", //  区名称
	 "villageId": "513400",  //  乡镇Id
	 "villageName": "凉山彝族自治州",//  乡镇名称
	 "workAddress": "真北路958号", // 工作地址
	 "isAllowAgent": 1, //  是否允许代理商介入(0.否,1.是)
	 "isAgent": 0, //  是否已被代理（0：否，1：是）
	 "isReceive": 1,// 是否接收意向(0.否,1.是)
	 "demandType": 1, // 需求类型(1.工作日,2.双休日,3.计件)
	 "agentLevel": 0, // 代理级别(0.无代理,1.代理,2.市级转发,3.区乡转发)
	 "auditStatus": 1,//审核状态(0.审核不通过,1.审核通过,2.审核中)
	 "status": 10, // 任务状态(0.默认状态,10.有人接单,11.已取消,12.已过期,20.进行中,30.待支付,40.已完成)
	 "userLogo": "… 573/44295308013968229657530.jpg",//用户头像
	 "userName": "黄忠敏",   // 用户姓名
	 "userCreditCount": null, // 诚信值
	 "userPhone": null,// 手机号
	 "startYear": 2017,//开始日期 年
	 "startMonth": 5,//开始日期 月
	 "startDay": 3,//开始日期 日
	 "startHour": 18,//开始日期 时
	 "startMinute": 0,//开始日期 分
	 "startSecond": 1,//开始日期 秒
	 "endYear": 2017,//截止日期 年
	 "endMonth": 5,//截止日期 月
	 "endDay": 13,//截止日期 日
	 "endHour": 18,//截止日期 时
	 "endMinute": 30,//截止日期 分
	 "endSecond": 1,//截止日期 秒

	 */

	private String userId;
	private String postType;
	private String postTypeName;
	private String postName;
	private String taskTitle;
	private String taskDescription;
	private String price;
	private String totalPrice;
	private String recruitNumber;
	private String surplusRecruitNumber;
	private String jobMeterUnit;
	private String jobMeterUnitName;
	private String sex;
	private String xCoordinate;
	private String yCoordinate;
	private String provinceId;
	private String provinceName;
	private String cityId;
	private String cityName;
	private String areaId;
	private String areaName;
	private String villageId;
	private String villageName;
	private String workAddress;
	private String isAllowAgent;
	private String isAgent;
	private String isReceive;
	private String demandType;
	private String agentLevel;
	private String auditStatus;
	private String status;
	private String userLogo;
	private String userName;
	private String userCreditCount;
	private String userPhone;
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
	private int endSecond;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
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

	public String getRecruitNumber() {
		return recruitNumber;
	}

	public void setRecruitNumber(String recruitNumber) {
		this.recruitNumber = recruitNumber;
	}

	public String getSurplusRecruitNumber() {
		return surplusRecruitNumber;
	}

	public void setSurplusRecruitNumber(String surplusRecruitNumber) {
		this.surplusRecruitNumber = surplusRecruitNumber;
	}

	public String getJobMeterUnit() {
		return jobMeterUnit;
	}

	public void setJobMeterUnit(String jobMeterUnit) {
		this.jobMeterUnit = jobMeterUnit;
	}

	public String getJobMeterUnitName() {
		return jobMeterUnitName;
	}

	public void setJobMeterUnitName(String jobMeterUnitName) {
		this.jobMeterUnitName = jobMeterUnitName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getXCoordinate() {
		return xCoordinate;
	}

	public void setXCoordinate(String xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public String getYCoordinate() {
		return yCoordinate;
	}

	public void setYCoordinate(String yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public String getVillageId() {
		return villageId;
	}

	public void setVillageId(String villageId) {
		this.villageId = villageId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getIsAllowAgent() {
		return isAllowAgent;
	}

	public void setIsAllowAgent(String isAllowAgent) {
		this.isAllowAgent = isAllowAgent;
	}

	public String getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(String isAgent) {
		this.isAgent = isAgent;
	}

	public String getIsReceive() {
		return isReceive;
	}

	public void setIsReceive(String isReceive) {
		this.isReceive = isReceive;
	}

	public String getDemandType() {
		return demandType;
	}

	public void setDemandType(String demandType) {
		this.demandType = demandType;
	}

	public String getAgentLevel() {
		return agentLevel;
	}

	public void setAgentLevel(String agentLevel) {
		this.agentLevel = agentLevel;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserLogo() {
		return userLogo;
	}

	public void setUserLogo(String userLogo) {
		this.userLogo = userLogo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCreditCount() {
		return userCreditCount;
	}

	public void setUserCreditCount(String userCreditCount) {
		this.userCreditCount = userCreditCount;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getStartDay() {
		return startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}

	public String getStartSecond() {
		return startSecond;
	}

	public void setStartSecond(String startSecond) {
		this.startSecond = startSecond;
	}

	public String getEndYear() {
		return endYear;
	}

	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getEndDay() {
		return endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(String endMinute) {
		this.endMinute = endMinute;
	}

	public int getEndSecond() {
		return endSecond;
	}

	public void setEndSecond(int endSecond) {
		this.endSecond = endSecond;
	}



}
