package com.dlg.data.oddjob.model;

/**
 * 作者：wangdakuan
 * 主要功能：迟到数据对象
 * 创建时间：2017/7/13 13:07
 */
public class OrderOperateBean {

    /**
     * orderBusinessNumber : ..0753857536/1491574976286
     * status : 40
     * orderId : 111
     * description :
     * isLate : 1
     * exceptionTime : 1
     * minId : null
     * pageSize : null
     * pageNumber : null
     * postName : 测试1
     * startDate : 1476954000000
     * endDate : 1476957600000
     * price : 1
     * totalPrice : 10
     * meterUnit : 1
     * meterUnitName : null
     */

    private String orderBusinessNumber; //订单编号
    private Integer status; //订单状态
    private String orderId; //订单ID
    private String description; //描述
    private int isLate; //迟到多少
    private int exceptionTime; //时间
    private String minId;
    private String pageSize;
    private String pageNumber;
    private String postName; //名称
    private long startDate; //开始时间
    private long endDate; //结束时间
    private double price; //单价
    private double totalPrice; //总价
    private int meterUnit; //
    private String meterUnitName; //单位

    public String getOrderBusinessNumber() {
        return orderBusinessNumber;
    }

    public void setOrderBusinessNumber(String orderBusinessNumber) {
        this.orderBusinessNumber = orderBusinessNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsLate() {
        return isLate;
    }

    public void setIsLate(int isLate) {
        this.isLate = isLate;
    }

    public int getExceptionTime() {
        return exceptionTime;
    }

    public void setExceptionTime(int exceptionTime) {
        this.exceptionTime = exceptionTime;
    }

    public String getMinId() {
        return minId;
    }

    public void setMinId(String minId) {
        this.minId = minId;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
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
