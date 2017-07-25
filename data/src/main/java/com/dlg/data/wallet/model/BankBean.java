package com.dlg.data.wallet.model;

import java.io.Serializable;

/**
 * 作者：邹前坤
 * 主要功能：
 * 创建时间： 2017/7/21  17:22
 */

public class BankBean implements Serializable{

	/**
	 * id : 1
	 * active : 1
	 * version : 1
	 * createTime : null
	 * createUserId : null
	 * modifyTime : null
	 * modifyUserId : null
	 * bankName : 招商银行
	 * bankCode : 308
	 * bankIcon : http://s.gongren.com/dds/img/oddjob/bankIcon/zhaoshang.png
	 */

	private String id;
	private String active;
	private String version;
	private String createTime;
	private String createUserId;
	private String modifyTime;
	private String modifyUserId;
	private String bankName;
	private String bankCode;
	private String bankIcon;

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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankIcon() {
		return bankIcon;
	}

	public void setBankIcon(String bankIcon) {
		this.bankIcon = bankIcon;
	}
}
