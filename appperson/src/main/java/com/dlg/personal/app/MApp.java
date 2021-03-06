package com.dlg.personal.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.amap.api.maps.model.LatLng;
import com.dlg.data.model.MyMapLocation;
import com.http.okgo.OkGo;
import com.http.okgo.cache.CacheEntity;
import com.http.okgo.cache.CacheMode;
import com.http.okgo.cookie.store.PersistentCookieStore;
import com.http.okgo.model.HttpHeaders;
import com.http.okgo.model.HttpParams;

import com.iflytek.cloud.SpeechUtility;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.util.logging.Level;

import cn.jpush.android.api.JPushInterface;

/**
 * 作者：wangdakuan
 * 主要功能：
 * 创建时间：2017/6/1 12:01
 */
public class MApp extends MultiDexApplication{

    private MyMapLocation mMapLocation; //当前定位的信息数据
    //application的对象
    private static MApp instance;
    protected static PersistentCookieStore cookieStore;
    public static float dataValueDistance = 1200f;
    {
        //微信
        PlatformConfig.setWeixin("wx68a8f66601847550", "e6c3894850eb395a571b095baebaafc6");
        //QQ
        PlatformConfig.setQQZone("1105693051", "XMrgV1M09LUDYbs5");
        //新浪微博
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        /**
         * 开启debug模式，方便定位错误，具体错误检查方式可以查看
         * http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
         */
        Config.DEBUG = true;
        //初始化友盟分享
        UMShareAPI.get(this);
        initOkHttp();
        //初始化极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        //科大讯飞
        SpeechUtility.createUtility(this, "appid=" + "59367c54");
    }
    /**
     * application的单利
     *
     * @return
     */
    public static synchronized MApp getInstance() {
        return instance;
    }
    /**
     * 出初始化Http请求
     */
    private void initOkHttp(){
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
        //解决初始化线程里 length=conn.getContentLength();返回值一直是-1
        headers.put(HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "identity");
        HttpParams params = new HttpParams();
        params.put("format","json");     //
//        params.put("version", SystemUtil.getVersionName(this)); //app版本
//        params.put("build", SystemUtil.getVersionCode(this)); //app的code
        //必须调用初始化
        OkGo.init(this);
        cookieStore = new PersistentCookieStore();
        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
            OkGo.getInstance()
                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("DLG", Level.INFO, true)
                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(OkGo.DEFAULT_MILLISECONDS)  //全局的连接超时时间
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)     //全局的读取超时时间
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)    //全局的写入超时时间
                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)
                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
                    .setRetryCount(0)
                    //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
//                    .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(cookieStore)        //cookie持久化存储，如果cookie不过期，则一直有效
                    //可以设置https的证书,以下几种方案根据需要自己设置
                    .setCertificates()                                  //方法一：信任所有证书,不安全有风险
//                    .setCertificates(new SafeTrustManager())            //方法二：自定义信任规则，校验服务端证书
//                    .setCertificates(getAssets().open("srca.cer"))      //方法三：使用预埋证书，校验服务端证书（自签名证书）
//                    //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
//                    .setCertificates(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"))//

                    //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
//                    .setHostnameVerifier(new SafeHostnameVerifier())

                    //可以添加全局拦截器，不需要就不要加入，错误写法直接导致任何回调不执行
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        return chain.proceed(chain.request());
//                    }
//                })

                    //这两行同上，不需要就不要加入
                    .addCommonHeaders(headers)  //设置全局公共头
                    .addCommonParams(params);   //设置全局公共参数
            ;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MyMapLocation getMapLocation() {
        return mMapLocation;
    }

    public void setMapLocation(MyMapLocation mMapLocation) {
        this.mMapLocation = mMapLocation;
    }

    /**
     * 获取经纬度
     * @return
     */
    public LatLng getMyLatLng(){
        if(mMapLocation != null){
            double longitude = mMapLocation.getLongitude();
            double latitude = mMapLocation.getLatitude();
            LatLng latLng = new LatLng(latitude, longitude);
            return latLng;
        }
        return null;
    }

    /**
     * 地址
     * @return
     */
    public String getAddress(){
        if(mMapLocation != null){
            String address = mMapLocation.getAddress();
            return address;
        }
        return "";
    }
    public String getCity(){
        if(mMapLocation != null){
            String city = mMapLocation.getCity();
            return city;
        }
        return "";
    }

    public static PersistentCookieStore getCookieStore() {
        return cookieStore;
    }
}
