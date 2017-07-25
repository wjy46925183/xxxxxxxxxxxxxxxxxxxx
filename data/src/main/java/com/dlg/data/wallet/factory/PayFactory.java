package com.dlg.data.wallet.factory;

import android.content.Context;

import com.common.sys.SystemUtil;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.cache.ObjectCacheImpl;
import com.dlg.data.wallet.PayDiaskSource;
import com.dlg.data.wallet.PaySource;
import com.dlg.data.wallet.interactor.PayInteractor;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/14 10:15
 */
public class PayFactory {
    private ObjectCache objectCache;
    private Context context;

    public PayFactory(Context applicationContext) {
        this(applicationContext, new ObjectCacheImpl(applicationContext));
    }

    public PayFactory(Context context, ObjectCacheImpl objectCache) {
        if (null == context || objectCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context;
        this.objectCache = objectCache;
    }

    /**
     * 数据不经常更新时每次都会优先取缓存
     *
     * @param name 缓存时的KEY
     * @return
     */
    public PayInteractor createHomeData(String name) {
        return createHomeData(name, false);
    }

    /**
     * 数据经常更新时每次都会优先取网络
     *
     * @param name   缓存的key
     * @param isWifi 是否判断网络  如果true 判断网络，当前手机没网情况下进行读取本地缓存数据
     * @return
     */
    public PayInteractor createHomeData(String name, boolean isWifi) {
        PayInteractor dataInteractor = null;
        if (isWifi) {
            if (!SystemUtil.isNetworkAvailable(context)) {
                if (this.objectCache.isCached(name)) {
                    dataInteractor = new PayDiaskSource(this.objectCache);
                }
            }
        } else {
            if (this.objectCache.isCached(name)) {
                dataInteractor = new PayDiaskSource(this.objectCache);
            }
        }
        if(null == dataInteractor){
            dataInteractor = createHomeStore();
        }
        return dataInteractor;
    }

    /**
     * 开启网络请求
     */
    private PayInteractor createHomeStore() {
        return new PaySource(this.objectCache);
    }
}
