package com.dlg.data.wallet.factory;

import android.content.Context;

import com.common.sys.SystemUtil;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.cache.ObjectCacheImpl;
import com.dlg.data.wallet.WithDrawalDiaskSource;
import com.dlg.data.wallet.WithDrawalSource;
import com.dlg.data.wallet.interactor.WithDrawalInteractor;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/12 10:08
 */
public class WithDrawalFactory {
    private ObjectCache objectCache;
    private Context context;

    public WithDrawalFactory(Context applicationContext) {
        this(applicationContext, new ObjectCacheImpl(applicationContext));
    }

    public WithDrawalFactory(Context context, ObjectCacheImpl objectCache) {
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
    public WithDrawalInteractor createWithDrawalData(String name) {
        return createWithDrawalData(name, false);
    }

    /**
     * 数据经常更新时每次都会优先取网络
     *
     * @param name   缓存的key
     * @param isWifi 是否判断网络  如果true 判断网络，当前手机没网情况下进行读取本地缓存数据
     * @return
     */
    public WithDrawalInteractor createWithDrawalData(String name, boolean isWifi) {
        WithDrawalInteractor dataInteractor = null;
        if (isWifi) {
            if (!SystemUtil.isNetworkAvailable(context)) {
                if (this.objectCache.isCached(name)) {
                    dataInteractor = new WithDrawalDiaskSource(this.objectCache);
                }
            }
        } else {
            if (this.objectCache.isCached(name)) {
                dataInteractor = new WithDrawalDiaskSource(this.objectCache);
            }
        }
        if(null == dataInteractor){
            dataInteractor = createWithDrawalStore();
        }
        return dataInteractor;
    }

    public WithDrawalInteractor getFrequency(String s, boolean b) {
        WithDrawalInteractor interactor = null;
        if (b) {
            if (!SystemUtil.isNetworkAvailable(context)) {
                if (this.objectCache.isCached(s)) {
                    interactor = new WithDrawalDiaskSource(this.objectCache);
                }
            }
        } else {
            if (this.objectCache.isCached(s)) {
                interactor = new WithDrawalDiaskSource(this.objectCache);
            }
        }
        if(null == interactor){
            interactor = createWithDrawalStore();
        }
        return interactor;
    }

    /**
     * 开启网络请求
     */
    private WithDrawalInteractor createWithDrawalStore() {
        return new WithDrawalSource(this.objectCache);
    }

    public WithDrawalInteractor getBindWeChat(String s, boolean b) {
        WithDrawalInteractor interactor = null;
        if (b) {
            if (!SystemUtil.isNetworkAvailable(context)) {
                if (this.objectCache.isCached(s)) {
                    interactor = new WithDrawalDiaskSource(this.objectCache);
                }
            }
        } else {
            if (this.objectCache.isCached(s)) {
                interactor = new WithDrawalDiaskSource(this.objectCache);
            }
        }
        if(null == interactor){
            interactor = createWithDrawalStore();
        }
        return interactor;
    }

    public WithDrawalInteractor setPayPwd(String s, boolean b) {
        WithDrawalInteractor interactor = null;
        if (b) {
            if (!SystemUtil.isNetworkAvailable(context)) {
                if (this.objectCache.isCached(s)) {
                    interactor = new WithDrawalDiaskSource(this.objectCache);
                }
            }
        } else {
            if (this.objectCache.isCached(s)) {
                interactor = new WithDrawalDiaskSource(this.objectCache);
            }
        }
        if(null == interactor){
            interactor = createWithDrawalStore();
        }
        return interactor;
    }
}
