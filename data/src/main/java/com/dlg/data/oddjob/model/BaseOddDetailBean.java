package com.dlg.data.oddjob.model;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * 作者：wangdakuan
 * 主要功能：零工
 * 创建时间：2017/7/14 10:34
 */
public class BaseOddDetailBean implements Serializable{

    private String employeeId;       //雇员Id
    private String employerId;       //雇主Id
    private String logo;               //用户头像
    private String name;               //任务对接人
    private String scoreCount;        //评分统计
    private String creditCount;       //诚信统计
    private String businessNumber;   //订单编号
    private String phone;
    private String postName;  //名称
    private int demandType; //需求类型(1.工作日,2.双休日,3.计件)
    private int isFarmersInsurance; ////是否购买保险 0否 1是
    private String postTypeName;  //类型名称
    private double price; //单价
    private double totalPrice;  //总价
    private String meterUnitName; //单位
    private OrderTimeBean orderTimeResultRpcVo; //时间对象


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScoreCount() {
        if(TextUtils.isEmpty(scoreCount) || "null".equals(scoreCount)){
            return "0";
        }
        return scoreCount;
    }

    public void setScoreCount(String scoreCount) {
        this.scoreCount = scoreCount;
    }

    public String getCreditCount() {
        if(TextUtils.isEmpty(creditCount) || "null".equals(creditCount)){
            return "36.5";
        }
        return creditCount;
    }

    public void setCreditCount(String creditCount) {
        this.creditCount = creditCount;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public int getDemandType() {
        return demandType;
    }

    public void setDemandType(int demandType) {
        this.demandType = demandType;
    }

    public int getIsFarmersInsurance() {
        return isFarmersInsurance;
    }

    public void setIsFarmersInsurance(int isFarmersInsurance) {
        this.isFarmersInsurance = isFarmersInsurance;
    }

    public String getPostTypeName() {
        return postTypeName;
    }

    public void setPostTypeName(String postTypeName) {
        this.postTypeName = postTypeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderTimeBean getOrderTimeResultRpcVo() {
        return orderTimeResultRpcVo;
    }

    public void setOrderTimeResultRpcVo(OrderTimeBean orderTimeResultRpcVo) {
        this.orderTimeResultRpcVo = orderTimeResultRpcVo;
    }

    public String getMeterUnitName() {
        return meterUnitName;
    }

    public void setMeterUnitName(String meterUnitName) {
        this.meterUnitName = meterUnitName;
    }

    public String getDemandTypeName(){
        switch (this.demandType) {
            case 1:
                return "工作日";
            case 2:
                return "双休日";
            case 3:
                return "计件";
        }
        return "";
    }
}
