package com.dlg.data.wallet.model;

import java.io.Serializable;

/**
 * 作者：邹前坤
 * 主要功能：历史发票列表bean
 * 创建时间： 2017/7/11  17:19
 */

public class BillHistoryBean implements Serializable{
	/**
	 * active : 1
	 * createTime : 1497447162000
	 * createUserId : 835196739882455040
	 * expressCompany : zhongtong
	 * expressOrderNumber : 123456
	 * id : 27623024556175888651315622206
	 * invoiceAmount : 1.0
	 * invoiceInformationId : 27623024556175888651315622205
	 * invoiceInformationRestVo : {"bankAccount":"123456789","bankName":"123456789","checkTakerAddress":"湖南","checkTakerName":"刘嘉雯","checkTakerPhone":"13142114843","companyName":"刘嘉雯","createTime":1497447162000,"id":"27623024556175888651315622205","registerAddress":"湖南","registerPhone":"13142114843","taxpayerIdentificationCode":"123456789","userId":"835196739882455040"}
	 * invoiceTax : 0.1
	 * modifyTime : 1497447328000
	 * modifyUserId : 27576012972159674390963650173
	 * status : 3
	 * version : 3
	 */

	private String active;
	private String createTime;//创建时间
	private String createUserId;//创建人
	private String expressCompany;//快递公司
	private String expressOrderNumber;//快递单号
	private String id;
	private double invoiceAmount;//账单 发票金额
	private String invoiceInformationId;//发票详情id
	/**
	 * bankAccount : 123456789
	 * bankName : 123456789
	 * checkTakerAddress : 湖南
	 * checkTakerName : 刘嘉雯
	 * checkTakerPhone : 13142114843
	 * companyName : 刘嘉雯
	 * createTime : 1497447162000
	 * id : 27623024556175888651315622205
	 * registerAddress : 湖南
	 * registerPhone : 13142114843
	 * taxpayerIdentificationCode : 123456789
	 * userId : 835196739882455040
	 */

	private InvoiceInformationRestVoBean invoiceInformationRestVo;//内部 更多信息bean
	private double invoiceTax;//发票税金
	private String modifyTime;//更新时间
	private String modifyUserId;//更新人
	private int status;
	private int version;

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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

	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	public String getExpressOrderNumber() {
		return expressOrderNumber;
	}

	public void setExpressOrderNumber(String expressOrderNumber) {
		this.expressOrderNumber = expressOrderNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getInvoiceInformationId() {
		return invoiceInformationId;
	}

	public void setInvoiceInformationId(String invoiceInformationId) {
		this.invoiceInformationId = invoiceInformationId;
	}

	public InvoiceInformationRestVoBean getInvoiceInformationRestVo() {
		return invoiceInformationRestVo;
	}

	public void setInvoiceInformationRestVo(InvoiceInformationRestVoBean invoiceInformationRestVo) {
		this.invoiceInformationRestVo = invoiceInformationRestVo;
	}

	public double getInvoiceTax() {
		return invoiceTax;
	}

	public void setInvoiceTax(double invoiceTax) {
		this.invoiceTax = invoiceTax;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}



//	/**
//	 * id : 2.749987132606803E28
//	 * bankAccount :
//	 * bankName :
//	 * checkTakerAddress :
//	 * checkTakerName :
//	 * checkTakerPhone :
//	 * companyName :
//	 * invoiceType :
//	 * registerAddress :
//	 * registerPhone :
//	 * taxpayerIdentificationCode :
//	 * userId : null
//	 */
//
//	private String id;
//	private String bankAccount;
//	private String bankName;
//	private String checkTakerAddress;
//	private String checkTakerName;
//	private String checkTakerPhone;
//	private String companyName;
//	private String invoiceType;
//	private String registerAddress;
//	private String registerPhone;
//	private String taxpayerIdentificationCode;
//	private String userId;
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getBankAccount() {
//		return bankAccount;
//	}
//
//	public void setBankAccount(String bankAccount) {
//		this.bankAccount = bankAccount;
//	}
//
//	public String getBankName() {
//		return bankName;
//	}
//
//	public void setBankName(String bankName) {
//		this.bankName = bankName;
//	}
//
//	public String getCheckTakerAddress() {
//		return checkTakerAddress;
//	}
//
//	public void setCheckTakerAddress(String checkTakerAddress) {
//		this.checkTakerAddress = checkTakerAddress;
//	}
//
//	public String getCheckTakerName() {
//		return checkTakerName;
//	}
//
//	public void setCheckTakerName(String checkTakerName) {
//		this.checkTakerName = checkTakerName;
//	}
//
//	public String getCheckTakerPhone() {
//		return checkTakerPhone;
//	}
//
//	public void setCheckTakerPhone(String checkTakerPhone) {
//		this.checkTakerPhone = checkTakerPhone;
//	}
//
//	public String getCompanyName() {
//		return companyName;
//	}
//
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}
//
//	public String getInvoiceType() {
//		return invoiceType;
//	}
//
//	public void setInvoiceType(String invoiceType) {
//		this.invoiceType = invoiceType;
//	}
//
//	public String getRegisterAddress() {
//		return registerAddress;
//	}
//
//	public void setRegisterAddress(String registerAddress) {
//		this.registerAddress = registerAddress;
//	}
//
//	public String getRegisterPhone() {
//		return registerPhone;
//	}
//
//	public void setRegisterPhone(String registerPhone) {
//		this.registerPhone = registerPhone;
//	}
//
//	public String getTaxpayerIdentificationCode() {
//		return taxpayerIdentificationCode;
//	}
//
//	public void setTaxpayerIdentificationCode(String taxpayerIdentificationCode) {
//		this.taxpayerIdentificationCode = taxpayerIdentificationCode;
//	}
//
//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}

}
