package com.dlg.data.wallet.model;

import java.io.Serializable;

/**
 * 作者：邹前坤
 * 主要功能： 这个是 消费历史的bean
 * 创建时间： 2017/7/10  18:29
 */

public class InvoiceListBean implements Serializable{
	/**
	 "createTime": 1498843663000,
	 "modifyTime": 1498843663000,
	 "paymentType": 0,


	 * billId : 123456789789456123
	 * billBusinessNumber : 123456789
	 * subBillBusinessNumber : 123123123
	 * orderBusinessNumber : 123123123
	 * orderId : 123123123
	 * payerId : 1111111111
	 * payeeId : 2222222222
	 * amount : 500
	 * tradeType : 3
	 * tradeId : null
	 * status : 2
	 * errorMessage : null
	 */

	private String billId; // 账单id 或订单id
	private String billBusinessNumber; // 订单号
	private String subBillBusinessNumber;//替补订单号
	private String orderBusinessNumber;//订货单号
	private String orderId;//订货id
	private String payerId;//付款人ID
	private String payeeId;//收款人id
	private double amount;//交易金额
	private int tradeType;
	private String tradeId;
	private int status;//
	private String errorMessage;//错误信息

	private String createTime;//创建时间
	private String modifyTime;//更新时间
	private String paymentType;//交易类型

	private boolean isSelect; //自己加的字段 判断是否选中
	private String showDate; //显示日期字段

	public String getShowDate() {
		return showDate;
	}

	public void setShowDate(String showDate) {
		this.showDate = showDate;
	}



	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}



	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getBillBusinessNumber() {
		return billBusinessNumber;
	}

	public void setBillBusinessNumber(String billBusinessNumber) {
		this.billBusinessNumber = billBusinessNumber;
	}

	public String getSubBillBusinessNumber() {
		return subBillBusinessNumber;
	}

	public void setSubBillBusinessNumber(String subBillBusinessNumber) {
		this.subBillBusinessNumber = subBillBusinessNumber;
	}

	public String getOrderBusinessNumber() {
		return orderBusinessNumber;
	}

	public void setOrderBusinessNumber(String orderBusinessNumber) {
		this.orderBusinessNumber = orderBusinessNumber;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getTradeType() {
		return tradeType;
	}

	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean getSelect() {
		return isSelect;
	}

	public void setSelect(boolean select) {
		isSelect = select;
	}



}
