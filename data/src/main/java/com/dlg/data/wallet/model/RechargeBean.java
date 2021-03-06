package com.dlg.data.wallet.model;

import java.io.Serializable;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/11 10:28
 */
public class RechargeBean implements Serializable{

    /**
     * returnRul : http://pay.itrusty.com/wchatNotify/pay
     * billBusinessNumber : null
     * sign : 3E9F5CFFCD0C71DD55ADE4FB92E1CED6
     * nonceStr : PEFSOW7opzIzp9bU
     * mchId : 1401108902
     * time : 1499744541
     * appId : wx68a8f66601847550
     * prepayId : wx20170711114245296f1770450315232378
     * tradeType : APP
     * outTradeNo : 7f2811ea20264b14a8989dc7d3a4ff25
     * oddPackage : Sign=WXPay
     * jsonRquestData : null
     * payUrl : null
     * totalFee : null
     * qrCode : null
     * prePayForm : null
     */

    private String returnRul;
    private Object billBusinessNumber;
    private String sign;
    private String nonceStr;
    private String mchId;
    private String time;
    private String appId;
    private String prepayId;
    private String tradeType;
    private String outTradeNo;
    private String oddPackage;
    private Object jsonRquestData;
    private Object payUrl;
    private Object totalFee;
    private Object qrCode;
    private Object prePayForm;

    public String getReturnRul() {
        return returnRul;
    }

    public void setReturnRul(String returnRul) {
        this.returnRul = returnRul;
    }

    public Object getBillBusinessNumber() {
        return billBusinessNumber;
    }

    public void setBillBusinessNumber(Object billBusinessNumber) {
        this.billBusinessNumber = billBusinessNumber;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOddPackage() {
        return oddPackage;
    }

    public void setOddPackage(String oddPackage) {
        this.oddPackage = oddPackage;
    }

    public Object getJsonRquestData() {
        return jsonRquestData;
    }

    public void setJsonRquestData(Object jsonRquestData) {
        this.jsonRquestData = jsonRquestData;
    }

    public Object getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(Object payUrl) {
        this.payUrl = payUrl;
    }

    public Object getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Object totalFee) {
        this.totalFee = totalFee;
    }

    public Object getQrCode() {
        return qrCode;
    }

    public void setQrCode(Object qrCode) {
        this.qrCode = qrCode;
    }

    public Object getPrePayForm() {
        return prePayForm;
    }

    public void setPrePayForm(Object prePayForm) {
        this.prePayForm = prePayForm;
    }
}
