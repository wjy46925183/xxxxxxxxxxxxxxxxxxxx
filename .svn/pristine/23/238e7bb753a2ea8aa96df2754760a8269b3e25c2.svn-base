package com.dlg.viewmodel.home;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.home.presenter.SubscribePresenter;
import com.dlg.viewmodel.server.HomeServer;

import java.util.HashMap;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/18 16:18
 */

public class SubscribeViewModel extends BaseViewModel {
    private final HomeServer mHomeServer;
    private SubscribePresenter mSubscribePresenter;
    public SubscribeViewModel(Context context, SubscribePresenter presenter){
        mContext = context;
        this.mSubscribePresenter = presenter;
        mHomeServer = new HomeServer(context);
    }

    public void subscribeService(CharSequence date,String sbDate,CharSequence time,
                                 String jobServiceId,String areaId,CharSequence workAddress){

        if (TextUtils.isEmpty(date)) {
            Toast.makeText(mContext, "日期不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(time)) {
            Toast.makeText(mContext, "时间不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(workAddress)) {
            Toast.makeText(mContext, "地址不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String[] dates = sbDate.split("--");
        String[] times = time.toString().split("--");

        String startDate = dates[0].replaceAll("[年月]", "-").replaceAll("日", "") + " " + times[0] + ":01";
        String endDate;
        if (dates.length == 1) {
            if (times.length == 1) {
                endDate = dates[0].replaceAll("[年月]", "-").replaceAll("日", "") + " " + times[0] + ":01";
            } else {
                endDate = dates[0].replaceAll("[年月]", "-").replaceAll("日", "") + " " + times[1] + ":01";
            }
        } else {
            if (times.length == 1) {
                endDate = dates[1].replaceAll("[年月]", "-").replaceAll("日", "") + " " + times[0] + ":01";
            } else {
                endDate = dates[1].replaceAll("[年月]", "-").replaceAll("日", "") + " " + times[1] + ":01";
            }
        }
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("jobServiceId", jobServiceId);
        hashMap.put("provinceId", "");
        hashMap.put("provinceName", "");
        hashMap.put("cityId", "");
        hashMap.put("cityName", "");
        hashMap.put("areaId", areaId);
        hashMap.put("areaName", "");
        hashMap.put("villageId", "");
        hashMap.put("villageName", "");
        hashMap.put("workAddress", workAddress.toString());
        hashMap.put("startTime", startDate);
        hashMap.put("endTime", endDate);
        hashMap.put("client", "ANDROID");
        mSubscriber = getSub();
        mHomeServer.subscribeService(mSubscriber,hashMap);
    }

    public Subscriber<JsonResponse<String>> getSub() {
        return new RXSubscriber<JsonResponse<String>,String>(null){
            @Override
            public void requestNext(String s) {
                if(mSubscribePresenter != null){
                    mSubscribePresenter.subscribeService(s);
                }
            }

            @Override
            public void onError(Throwable e) {
                if(mSubscribePresenter != null){
                    mSubscribePresenter.error(e.getMessage());
                }
            }

            @Override
            public void onNext(JsonResponse<String> stringJsonResponse) {
                super.onNext(stringJsonResponse);
            }
        };
    }
}
