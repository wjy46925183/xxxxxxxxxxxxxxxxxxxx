package com.dlg.data.wallet.model;

/**
 * 作者：关蕤
 * 主要功能：绑定账号的列表实体对象
 * 创建时间：2017/7/12 13:59
 */
public class BindBean {

    /**
     * id : 27614761012991354112306561994
     * userId : 27614709103853530693628313858
     * idCard : null
     * payType : 1
     * payAccount : 13321917860
     * payAccounthouseholder : 梁文明
     * payAccountType : null
     * bankCode : null
     * depositBank : null
     * bankCardType : null
     * bankCardNature : null
     * reservedPhone : 13321917860
     * depositBankProvince : null
     * depositBankCity : null
     * status : 1
     * createTime : 1496999195000
     * bindId : 201706091706348915867292225
     * bankIconUrl : null
     */

    private String id;
    private String userId;
    private String idCard;
    private int payType;
    private String payAccount;
    private String payAccounthouseholder;
    private String payAccountType;
    private String bankCode;
    private String depositBank;
    private String bankCardType;
    private String bankCardNature;
    private String reservedPhone;
    private String depositBankProvince;
    private String depositBankCity;
    private int status;
    private long createTime;
    private String bindId;
    private String bankIconUrl;

    public BindBean() {
    }

    public BindBean(int payType, String payAccount) {
        this.payType = payType;
        this.payAccount = payAccount;
    }

    public BindBean(String id, String userId, String idCard, int payType, String payAccount, String payAccounthouseholder, String payAccountType, String bankCode, String depositBank, String bankCardType, String bankCardNature, String reservedPhone, String depositBankProvince, String depositBankCity, int status, long createTime, String bindId, String bankIconUrl) {
        this.id = id;
        this.userId = userId;
        this.idCard = idCard;
        this.payType = payType;
        this.payAccount = payAccount;
        this.payAccounthouseholder = payAccounthouseholder;
        this.payAccountType = payAccountType;
        this.bankCode = bankCode;
        this.depositBank = depositBank;
        this.bankCardType = bankCardType;
        this.bankCardNature = bankCardNature;
        this.reservedPhone = reservedPhone;
        this.depositBankProvince = depositBankProvince;
        this.depositBankCity = depositBankCity;
        this.status = status;
        this.createTime = createTime;
        this.bindId = bindId;
        this.bankIconUrl = bankIconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getPayAccounthouseholder() {
        return payAccounthouseholder;
    }

    public void setPayAccounthouseholder(String payAccounthouseholder) {
        this.payAccounthouseholder = payAccounthouseholder;
    }

    public String getPayAccountType() {
        return payAccountType;
    }

    public void setPayAccountType(String payAccountType) {
        this.payAccountType = payAccountType;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public String getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(String bankCardType) {
        this.bankCardType = bankCardType;
    }

    public String getBankCardNature() {
        return bankCardNature;
    }

    public void setBankCardNature(String bankCardNature) {
        this.bankCardNature = bankCardNature;
    }

    public String getReservedPhone() {
        return reservedPhone;
    }

    public void setReservedPhone(String reservedPhone) {
        this.reservedPhone = reservedPhone;
    }

    public String getDepositBankProvince() {
        return depositBankProvince;
    }

    public void setDepositBankProvince(String depositBankProvince) {
        this.depositBankProvince = depositBankProvince;
    }

    public String getDepositBankCity() {
        return depositBankCity;
    }

    public void setDepositBankCity(String depositBankCity) {
        this.depositBankCity = depositBankCity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getBankIconUrl() {
        return bankIconUrl;
    }

    public void setBankIconUrl(String bankIconUrl) {
        this.bankIconUrl = bankIconUrl;
    }
}
