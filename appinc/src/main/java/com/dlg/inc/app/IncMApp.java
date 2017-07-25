package com.dlg.inc.app;

import com.amap.api.maps.model.LatLng;
import com.dlg.data.model.MyMapLocation;
import com.http.okgo.cookie.store.PersistentCookieStore;

/**
 * 作者：wangdakuan
 * 主要功能：
 * 创建时间：2017/6/1 12:01
 */
public class IncMApp {

    private MyMapLocation mMapLocation; //当前定位的信息数据
    //application的对象
    private static IncMApp instance = new IncMApp();
    protected PersistentCookieStore cookieStore;
    public static float dataValueDistance = 1200f;

    /**
     * application的单利
     *
     * @return
     */
    public static synchronized IncMApp getInstance() {
        return instance;
    }

    public void setCookieStore(PersistentCookieStore cookieStore) {
        this.cookieStore = cookieStore;
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

    public PersistentCookieStore getCookieStore() {
        return cookieStore;
    }
}
