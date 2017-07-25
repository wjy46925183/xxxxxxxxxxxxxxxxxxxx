package com.dlg.data.wallet.model;

import java.io.Serializable;

/**
 * 作者：邹前坤
 * 主要功能：钱包流水列表的单条详情bean
 * 创建时间： 2017/7/14  14:00
 */

public class WalletListDetailBean implements Serializable{


	/**
	 * {
	 "billId":123456789789456123,//账单id
	 "billBusinessNumber":123456789,//账单业务编号
	 "subBillBusinessNumber":123123123,//子账单业务编号
	 "orderBusinessNumber":123123123,//订单业务编号
	 "orderId":123123123,//订单编号
	 "payerId":1111111111,//付款方id
	 "payeeId":2222222222,//收款方id
	 "amount":500,//金额
	 "tradeType":3,//交易类型
	 "tradeId":null,//交易流水id
	 "status":2,//状态(0.待支付,1.进行中,2.成功,3.失败)
	 "errorMessage":null,//错误信息
	 "createTime": null,//创建时间
	 "name": "李凯凯",//名称
	 "logo": "…/922512/s27526821140839383213591176060.jpg",//头像logo
	 "remark": null,//备注说明
	 "modifyTime": nul//更新时间
	 }
	 */

	private String billId;
	private String billBusinessNumber;
	private String subBillBusinessNumber;
	private String orderBusinessNumber;
	private String orderId;
	private String payerId;
	private String payeeId;
	private double amount;
	private int tradeType;
	private String tradeId;
	private int status;
	private String errorMessage;
	private String createTime;
	private String name;
	private String logo;
	private String remark;
	private String modifyTime;
	private String paymentType;

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
}
