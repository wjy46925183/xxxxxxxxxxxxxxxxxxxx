package gongren.com.dlg;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.common.cache.ACache;
import com.common.sys.ActivityNavigator;
import com.common.sys.SystemUtil;
import com.common.utils.RxBus;
import com.dlg.data.common.url.CommonUrl;
import com.dlg.inc.app.IncMApp;
import com.dlg.personal.app.MApp;
import com.dlg.personal.jpush.JPushTagAliasCallBack;
import com.dlg.viewmodel.key.AppKey;
import com.umeng.analytics.AnalyticsConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import gongren.com.dlg.login.activity.ChoseLoginActivity;
import gongren.com.dlg.login.activity.LoginActivity;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import spring.update.UpdateConfig;
import spring.update.callback.UpdateCheckCB;
import spring.update.callback.UpdateDownloadCB;
import spring.update.creator.DefaultNeedDownloadCreator;
import spring.update.creator.DefaultNeedInstallCreator;
import spring.update.creator.DefaultNeedUpdateCreator;
import spring.update.model.CheckEntity;
import spring.update.model.HttpMethod;
import spring.update.model.Update;
import spring.update.model.UpdateParser;
import spring.update.strategy.WifiFirstStrategy;

/**
 * 作者：wangdakuan
 * 主要功能：
 * 创建时间：2017/7/20 11:18
 */
public class MyApplication extends MApp {
    @Override
    public void onCreate() {
        super.onCreate();
        UpdateConfig();
        /**
         * 登录和忘记密码
         */
        RxBus.get().register(AppKey.PageRequestCodeKey.LOGIN_RXBUS, String.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
                try {
                    if ("去修改密码".equals(str)) {
                        Intent intent = new Intent();
                        intent.putExtra("inFrom", LoginActivity.TAG_RESET);
                        ActivityNavigator.navigator().navigateTo(ChoseLoginActivity.class, intent, AppKey.PageRequestCodeKey.LOGIN_KEY);
                    } else {
                        ActivityNavigator.navigator().navigateTo(ChoseLoginActivity.class, AppKey.PageRequestCodeKey.LOGIN_KEY);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        /**
         * 登录被踢出
         */
        RxBus.get().register(AppKey.PageRequestCodeKey.LOGIN_ERROR, String.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
                try {
                    JPushInterface.setAlias(getApplicationContext(),"",new JPushTagAliasCallBack());
                    ACache.get(getApplicationContext()).remove(AppKey.CacheKey.MY_USER_ID);
                    ACache.get(getApplicationContext()).remove(AppKey.CacheKey.MY_USER_INFO);
                    ACache.get(getApplicationContext()).remove(AppKey.CacheKey.USER_PHONE);
                    ACache.get(getApplicationContext()).remove(AppKey.CacheKey.NAME);
                    ACache.get(getApplicationContext()).remove(AppKey.CacheKey.USER_LOGO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
		IncMApp.getInstance().setCookieStore(cookieStore);
	}

    /**
     * app更新初始化
     */
    private void UpdateConfig() {
        CheckEntity checkEntity = new CheckEntity();
        checkEntity.setMethod(HttpMethod.POST);
        checkEntity.setUrl(CommonUrl.CHECK_VERSION);
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("appVersionCode", getVersion());
        stringMap.put("channel", AnalyticsConfig.getChannel(this));//渠道号
        stringMap.put("format", "json");
        stringMap.put("sdkVersionCode", SystemUtil.getDeviceSDK());
        checkEntity.setParams(stringMap);
        UpdateConfig.getConfig()
                .checkEntity(checkEntity)
                .jsonParser(new UpdateParser() {
                    @Override
                    public Update parse(String response) {
                        Update update = new Update("");
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if ("0".equals(jsonObject.optString("code"))) {
                                JSONArray data = jsonObject.getJSONArray("data");
                                JSONObject jobt = (JSONObject) data.get(0);
                                boolean isDownload = jobt.getBoolean("isDownload");
                                String downloadUrl = jobt.getString("downloadUrl");
                                int newAppVersionCode = jobt.getInt("newAppVersionCode");
                                boolean newest = jobt.getBoolean("newest");
                                String newVersionRemark = jobt.getString("newVersionRemark");
                                String newVersion = jobt.getString("newVersion ");
                                update.setUpdateTime(System.currentTimeMillis());
                                // 此apk包的下载地址
                                update.setUpdateUrl(downloadUrl);
                                // 此apk包的版本号
                                update.setVersionCode(newAppVersionCode);
                                // 此apk包的版本名称
                                update.setVersionName(newVersion);
                                // 此apk包的更新内容
                                update.setUpdateContent(newVersionRemark);
                                // 此apk包是否为强制更新
                                update.setForced(isDownload);
                                // 是否显示忽略此次版本更新按钮
                                update.setIgnore(false);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return update;
                    }
                })
                .updateDialogCreator(new DefaultNeedUpdateCreator()) //提示更新框
                .downloadDialogCreator(new DefaultNeedDownloadCreator()) //下载框
                .installDialogCreator(new DefaultNeedInstallCreator()) //安装框
                .strategy(new WifiFirstStrategy())  //框逻辑显示
                .checkCB(new UpdateCheckCB() {

                    @Override
                    public void onCheckError(int code, String errorMsg) {
                    }

                    @Override
                    public void onUserCancel() {
                        Toast.makeText(getApplicationContext(), "用户取消更新", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCheckIgnore(Update update) {
                        Toast.makeText(getApplicationContext(), "用户忽略此版本更新", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void hasUpdate(Update update) {
                        Toast.makeText(getApplicationContext(), "检查到有更新", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void noUpdate() {
//                        Toast.makeText(getApplicationContext(), "无更新", Toast.LENGTH_SHORT).show();
                    }
                })
                // apk下载的回调
                .downloadCB(new UpdateDownloadCB() {
                    @Override
                    public void onUpdateStart() {
                        Toast.makeText(getApplicationContext(), "下载开始", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUpdateComplete(File file) {
                        Toast.makeText(getApplicationContext(), "下载完成", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUpdateProgress(long current, long total) {
//                        ToastUtils.showToastShort(getApplicationContext(),current+"::::"+total);
                    }

                    @Override
                    public void onUpdateError(int code, String errorMsg) {
//                        Toast.makeText(getApplicationContext(), "下载失败：code:" + code + ",errorMsg:" + errorMsg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            int version = info.versionCode;
            return version + "";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
