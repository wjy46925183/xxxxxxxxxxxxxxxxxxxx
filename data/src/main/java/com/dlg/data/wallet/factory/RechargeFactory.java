package com.dlg.data.wallet.factory;

import android.content.Context;

import com.common.sys.SystemUtil;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.cache.ObjectCacheImpl;
import com.dlg.data.wallet.RechargeDiaskSource;
import com.dlg.data.wallet.RechargeSource;
import com.dlg.data.wallet.interactor.RechargeInteractor;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/11 10:33
 */
public class RechargeFactory {
    private ObjectCache objectCache;
    private Context context;

    public RechargeFactory(Context applicationContext) {
        this(applicationContext, new ObjectCacheImpl(applicationContext));
    }

    public RechargeFactory(Context context, ObjectCacheImpl objectCache) {
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
    public RechargeInteractor createWalletData(String name) {
        return createRechargeData(name, false);
    }

    /**
     * 数据经常更新时每次都会优先取网络
     *
     * @param name   缓存的key
     * @param isWifi 是否判断网络  如果true 判断网络，当前手机没网情况下进行读取本地缓存数据
     * @return
     */
    public RechargeInteractor createRechargeData(String name, boolean isWifi) {
        RechargeInteractor dataInteractor = null;
        if (isWifi) {
            if (!SystemUtil.isNetworkAvailable(context)) {
                if (this.objectCache.isCached(name)) {
                    dataInteractor = new RechargeDiaskSource(this.objectCache);
                }
            }
        } else {
            if (this.objectCache.isCached(name)) {
                dataInteractor = new RechargeDiaskSource(this.objectCache);
            }
        }
        if(null == dataInteractor){
            dataInteractor = createRechargeStore();
        }
        return dataInteractor;
    }


    /**
     * 充值
     * @param s
     * @param b
     * @return
     */
    public RechargeInteractor getWalletBalance(String s, boolean b) {
        RechargeInteractor rechargeInteractor = null;
        if (b) {
            if (!SystemUtil.isNetworkAvailable(context)) {
                if (this.objectCache.isCached(s)) {
                    rechargeInteractor = new RechargeDiaskSource(this.objectCache);
                }
            }
        } else {
            if (this.objectCache.isCached(s)) {
                rechargeInteractor = new RechargeDiaskSource(this.objectCache);
            }
        }
        if(null == rechargeInteractor){
            rechargeInteractor = createRechargeStore();
        }
        return rechargeInteractor;
    }

    /**
     * 开启网络请求
     */
    private RechargeInteractor createRechargeStore() {
        return new RechargeSource(this.objectCache);
    }
}
