package com.dlg.data.wallet.factory;

import android.content.Context;

import com.common.sys.SystemUtil;
import com.dlg.data.cache.ObjectCache;
import com.dlg.data.cache.ObjectCacheImpl;
import com.dlg.data.wallet.WalletDiaskSource;
import com.dlg.data.wallet.WalletSource;
import com.dlg.data.wallet.interactor.WalletInteractor;

/**
 * 作者：曹伟
 * 主要功能：零钱模块工厂接口实现类
 * 创建时间：2017/7/6 17:40
 */

public class WalletFactory {
    private ObjectCache objectCache;
    private Context context;

    public WalletFactory(Context applicationContext) {
        this(applicationContext, new ObjectCacheImpl(applicationContext));
    }

    public WalletFactory(Context context, ObjectCacheImpl objectCache) {
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
    public WalletInteractor createWalletData(String name) {
        return createWalletData(name, false);
    }

    /**
     * 数据经常更新时每次都会优先取网络
     *
     * @param name   缓存的key
     * @param isWifi 是否判断网络  如果true 判断网络，当前手机没网情况下进行读取本地缓存数据
     * @return
     */
    public WalletInteractor createWalletData(String name, boolean isWifi) {
        WalletInteractor dataInteractor = null;
        if (isWifi) {
            if (!SystemUtil.isNetworkAvailable(context)) {
                if (this.objectCache.isCached(name)) {
                    dataInteractor = new WalletDiaskSource(this.objectCache);
                }
            }
        } else {
            if (this.objectCache.isCached(name)) {
                dataInteractor = new WalletDiaskSource(this.objectCache);
            }
        }
        if(null == dataInteractor){
            dataInteractor = createWalletStore();
        }
        return dataInteractor;
    }


    /**
     * 查询用户零钱
     * @param s
     * @param b
     * @return
     */
    public WalletInteractor getWalletBalance(String s, boolean b) {
        WalletInteractor walletInteractor = null;
        if (b) {
            if (!SystemUtil.isNetworkAvailable(context)) {
                if (this.objectCache.isCached(s)) {
                    walletInteractor = new WalletDiaskSource(this.objectCache);
                }
            }
        } else {
            if (this.objectCache.isCached(s)) {
                walletInteractor = new WalletDiaskSource(this.objectCache);
            }
        }
        if(null == walletInteractor){
            walletInteractor = createWalletStore();
        }
        return walletInteractor;
    }

    /**
     * 开启网络请求
     */
    private WalletInteractor createWalletStore() {
        return new WalletSource(this.objectCache);
    }
}
