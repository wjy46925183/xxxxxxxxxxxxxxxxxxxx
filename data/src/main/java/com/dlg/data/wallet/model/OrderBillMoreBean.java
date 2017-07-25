package com.dlg.data.wallet.model;

import java.io.Serializable;

/**
 * 作者：邹前坤
 * 主要功能：
 * 创建时间： 2017/7/13  10:11
 */

public class OrderBillMoreBean implements Serializable{
	private String id;
	private String taxpayerDistinguishNumber;//纳税人识别号
	private String companyAddress;//地址
	private String companyPhone;//电话
	private String bankAccount;//开户行
	private String bankAccountNumber;//开户账号
	private String remarksExplain;//备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTaxpayerDistinguishNumber() {
		return taxpayerDistinguishNumber;
	}

	public void setTaxpayerDistinguishNumber(String taxpayerDistinguishNumber) {
		this.taxpayerDistinguishNumber = taxpayerDistinguishNumber;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyPhone() {
		return companyPhone;
	}

	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getRemarksExplain() {
		return remarksExplain;
	}

	public void setRemarksExplain(String remarksExplain) {
		this.remarksExplain = remarksExplain;
	}
}
