package com.dlg.viewmodel.key;

/**
 * 作者：wangdakuan
 * 主要功能：整个项目中的key值
 * 创建时间：2017/6/22 14:24
 */
public class AppKey {

    /**
     * 缓存的key值
     */
    public static class CacheKey{
        public static final String NO_FIRST_ENTER = "noFirstEnter"; //是否第一次进入app
        public static final String KEY_HOT = "KEY_HOT"; //历史数据（热门关键字）
        public static final String FROM_SEARCH = "FROM_SEARCH"; //从搜索界面而来

        /**
         * 清楚缓存时不需要（如果是退出成功需要清楚用户信息）
         */
        public static final String MAP_LOCATION_LATLNG = "map_location_latlng"; //当前位置经纬度
        public static final String MY_USER_ID = "my_user_id"; //当前登录者的ID
        public static final String MY_USER_INFO = "my_user_info"; //当前登录者的基本信息
        public static final String USER_ROLE = "user_role"; //用户角色 1 雇员 2 雇主 3 企业 4 为代理商
        public static final String USER_PHONE = "user_phone" ;  //用户登录的手机号
        public static final String NAME = "name" ;  //真实姓名
        public static final String USER_LOGO = "user_logo" ;  //头像
        public static final String HAS_PAY_PSD = "has_pay_psd" ;  //是否有支付密码
        public static final String VERIFY_STATE = "verify_state" ;  //(0.未审核,1.审核中,2.审核通过,3.审核失败)'
        public static final String NAME_NUMBER = "name_number";//身份证号
        public static final String SIGN_STATES = "name_number";//身份状态

    }

    /**
     * 页面返回刷新
     */
    public static class PageRequestCodeKey{
        public static final int CANCLE_KEY = 4001; //取消返回刷新
        public static final int LOGIN_KEY = 4002; //登录页面
        public static final int SINGLE_KEY = 4003; //抢单返回刷新
        public static final String LOGIN_RXBUS = "LOGIN_RXBUS"; //调起登录页面
        public static final String LOGIN_ERROR = "logIn_Error"; //登录异常 账户被踢出

    }
}
