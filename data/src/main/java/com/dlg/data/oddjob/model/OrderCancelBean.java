package com.dlg.data.oddjob.model;

/**
 * 作者：wangdakuan
 * 主要功能：订单取消数据对象
 * 创建时间：2017/7/13 13:10
 */
public class OrderCancelBean {

    /**
     * orderBusinessNumber : ..808/1491574977018
     * compensatoryPayment : 111
     * cancelCause : buhao
     * postName :
     * startDate : 1476957600000
     * endDate : 1476961200000
     * price : 1
     * totalPrice : 10
     * meterUnit : 1
     * meterUnitName : null
     */

    private String orderBusinessNumber; //订单编号
    private double compensatoryPayment; //补偿付款
    private String cancelCause; //取消原因
    private String postName; //名称
    private long startDate; //开始时间
    private long endDate; //结束时间
    private double price; //单价
    private double totalPrice; //总价
    private int meterUnit;
    private String meterUnitName; //单位

    public String getOrderBusinessNumber() {
        return orderBusinessNumber;
    }

    public void setOrderBusinessNumber(String orderBusinessNumber) {
        this.orderBusinessNumber = orderBusinessNumber;
    }

    public double getCompensatoryPayment() {
        return compensatoryPayment;
    }

    public void setCompensatoryPayment(double compensatoryPayment) {
        this.compensatoryPayment = compensatoryPayment;
    }

    public String getCancelCause() {
        return cancelCause;
    }

    public void setCancelCause(String cancelCause) {
        this.cancelCause = cancelCause;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
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

    public int getMeterUnit() {
        return meterUnit;
    }

    public void setMeterUnit(int meterUnit) {
        this.meterUnit = meterUnit;
    }

    public String getMeterUnitName() {
        return meterUnitName;
    }

    public void setMeterUnitName(String meterUnitName) {
        this.meterUnitName = meterUnitName;
    }
}
