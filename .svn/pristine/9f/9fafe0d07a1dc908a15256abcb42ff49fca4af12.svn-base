package com.dlg.data.wallet.url;

import com.dlg.data.UrlNet;

/**
 * 作者：曹伟
 * 主要功能：钱包接口链接
 * 创建时间：2017/7/7 10:35
 */

public class WalletUrl {
    /**
     * 零钱查询
     */
    public static final String WALLET_BALANCE = UrlNet.getName() + "/api/userAccountRest/findOrangeAccount";
    /**
     *消费列表
     */
    public static final String INVOICE_LIST_BALANCE = UrlNet.getName() + "/api/billDetailRest/findBillDetailPageByUserIdAndStatus";

    /**
     * 充值（支付宝，微信）
     */
    public static final String WALLET_PAY = UrlNet.getName() + "/api/userAccountRest/recharge" ;
    /**
     * 查询可提现次数
     */
    public static final String FREQUENCY_NUM = UrlNet.getName() + "/api/billDetailRest/findUserMonthWithdrawalsNumber";

    /**
     * 查询提现绑定列表
     */
    public static final String  PAY_BIND_LIST= UrlNet.getName() + "/api/payBindRest/queryList";

    /**
     * 添加绑定
     */
    public static final String PAY_BIND_ADD = UrlNet.getName()+"/api/payBindRest/add";
    /**
     * 发票历史列表
     */
    public static final String INVOICE_BILL_LIST = UrlNet.getName() + "/api/invoiceRest/queryInvoice";

    /**
     * 查看发票详情  "/api/invoiceRest/findById"
     *
     * 发票详情 下部
     */
    public static final String INVOICE_BILL_DETAILS = UrlNet.getName() +"/api/invoiceRest/queryByInvoiceId";

    /**
     * 发票模板
     */
    public static final String ORDER_BILL_TEMPLATE = UrlNet.getName() +"/api/invoiceInformationRest/queryList";
    /**
     * 新建发票
     */
    public static final String ORDER_BILL_NEW = UrlNet.getName() +"/api/invoiceRest/addInvoice";

    /**
     * 发送验证码
     */
    public static final String SEND_VERIFY_CODE = UrlNet.getName() + "/api/smsRest/sendVerifyCode";
    /**
     *  发送验证码 这个需要加密使用
     */
//    public static final String SEND_VERIFY_CODE = UrlNet.getName() + "/api/smsRest/sendVerifyCodeByApp";

    /**
     * 提现
     */
    public static final String GET_CASH = UrlNet.getName() + "/api/userAccountRest/withDraw";

    /**
     * 接触绑定
     */
    public static final String UNBIND = UrlNet.getName() + "/api/payBindRest/delete" ;

    /**
     * 兑换
     */
    public static final String EXCHANGE = UrlNet.getName() + "/api/exchangeCodeRest/exchangeCodeNumber" ;
    /**
     * 钱包详情列表
     */
    public static final String WALLET_DETAILS_LIST = UrlNet.getName() + "/api/billDetailRest/findBillDetailPageByUserIdAndStatus" ;
    /**
     * 钱包详情列表单个详情
     */
    public static final String WALLET_DETAILS_LIST_DETAIL = UrlNet.getName() + "/api/billDetailRest/findBillDetailInfoByNumber" ;

    /**
     * 判断是否设置过支付密码
     */
    public static final String HAS_SET_PAY_PWD = UrlNet.getName() + "/api/payPasswordRest/checkSet";

    /**
     * 新增支付密码
     */
    public static final String SET_PAY_PWD = UrlNet.getName() + "/api/payPasswordRest/add" ;

    /**
     * 验证验证码
     */
    public static final String RESET_PWD_CHECK_VERIFYCODE = UrlNet.getName() + "/api/smsRest/checkVerifyCode";
    /**
     * 重置密码
     */
    public static final String RESET_PWD_UPDATE_PWD = UrlNet.getName() + "/api/payPasswordRest/update";
    /**
     * 充值失败
     */
    public static final String RECHARGE_ERROE = UrlNet.getName() + "/api/payRest/cancel";

    public static final String PAYMENT = UrlNet.getName() + "/api/userAccountRest/orderSettlement";

    /**
     * 企业绑定银行卡
     */
    public static final String INC_PAY_BIND_ADD = UrlNet.getName()+"/api/payBindRest/addBank";
    /**
     * 得到银行集合
     */
    public static final String GET_BANK = UrlNet.getName()+"/api/payBindRest/findBankInforByName";
}
