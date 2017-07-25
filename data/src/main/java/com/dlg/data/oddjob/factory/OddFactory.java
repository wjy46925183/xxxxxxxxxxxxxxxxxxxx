package com.dlg.data.oddjob.factory;

import android.content.Context;

import com.common.sys.SystemUtil;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.cache.ObjectCacheImpl;
import com.dlg.data.oddjob.OddJobDiaskSource;
import com.dlg.data.oddjob.OddSource;
import com.dlg.data.oddjob.interactor.OddJobInteractor;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/6 15:21
 */

public class OddFactory {
    private ObjectCache objectCache;
    private Context context;

    public OddFactory(Context applicationContext) {
        this(applicationContext, new ObjectCacheImpl(applicationContext));
    }

    public OddFactory(Context context, ObjectCacheImpl objectCache) {
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
    public OddJobInteractor createHomeData(String name) {
        return createHomeData(name, false);
    }

    /**
     * 数据经常更新时每次都会优先取网络
     *
     * @param name   缓存的key
     * @param isWifi 是否判断网络  如果true 判断网络，当前手机没网情况下进行读取本地缓存数据
     * @return
     */
    public OddJobInteractor createHomeData(String name, boolean isWifi) {
        OddJobInteractor dataInteractor = null;
        if (isWifi) {
            if (!SystemUtil.isNetworkAvailable(context)) {
                if (this.objectCache.isCached(name)) {
                    dataInteractor = new OddJobDiaskSource(this.objectCache);
                }
            }
        } else {
            if (this.objectCache.isCached(name)) {
                dataInteractor = new OddJobDiaskSource(this.objectCache);
            }
        }
        if(null == dataInteractor){
            dataInteractor = createOddStore();
        }
        return dataInteractor;
    }

    /**
     * 开启网络请求
     */
    private OddJobInteractor createOddStore() {
        return new OddSource(this.objectCache);
    }
}
