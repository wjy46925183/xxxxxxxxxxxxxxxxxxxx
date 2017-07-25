package com.dlg.data.wallet.model;

import java.io.Serializable;

/**
 * 作者：曹伟
 * 主要功能：
 * 创建时间：2017/7/6 17:26
 */

public class WalletBean implements Serializable{

    /**
     * originSystem : 打零工
     * accountId : 27614709103853530693628313858
     * accountName : null
     * amount : 10.0
     * frozen : 0.0
     * noWithdrawCashAmount : 0.0
     * withdrawCashAmount : 10.0
     * status : 1
     * createTime : 1496996381000
     * remark : null
     */

    private String originSystem;
    private String accountId;
    private Object accountName;
    private double amount;
    private double frozen;
    private double noWithdrawCashAmount;
    private double withdrawCashAmount;
    private int status;
    private long createTime;
    private Object remark;

    public String getOriginSystem() {
        return originSystem;
    }

    public void setOriginSystem(String originSystem) {
        this.originSystem = originSystem;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Object getAccountName() {
        return accountName;
    }

    public void setAccountName(Object accountName) {
        this.accountName = accountName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFrozen() {
        return frozen;
    }

    public void setFrozen(double frozen) {
        this.frozen = frozen;
    }

    public double getNoWithdrawCashAmount() {
        return noWithdrawCashAmount;
    }

    public void setNoWithdrawCashAmount(double noWithdrawCashAmount) {
        this.noWithdrawCashAmount = noWithdrawCashAmount;
    }

    public double getWithdrawCashAmount() {
        return withdrawCashAmount;
    }

    public void setWithdrawCashAmount(double withdrawCashAmount) {
        this.withdrawCashAmount = withdrawCashAmount;
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

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }
}
