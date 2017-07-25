package com.dlg.viewmodel.oddjob;

import android.content.Context;
import android.text.TextUtils;

import com.dlg.data.oddjob.model.ReleaseJobBean;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.oddjob.presenter.EditJobPresenter;
import com.dlg.viewmodel.oddjob.presenter.ReleaseJobPresenter;
import com.dlg.viewmodel.server.OddServer;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;

/**
 * 作者：小明
 * 主要功能：发布编辑零工
 * 创建时间：2017/7/10 0010 19:59
 */
public class ReleaseJobViewModel extends BaseViewModel {
    private ReleaseJobPresenter mReleaseJobPresenter;
    private EditJobPresenter mEditJobPresenter;
    private final OddServer mOddServer;
    private Context mContext;

    public ReleaseJobViewModel(Context context, ReleaseJobPresenter presenter,EditJobPresenter editPresenter){
        this.mReleaseJobPresenter = presenter;
        this.mEditJobPresenter=editPresenter;
        mOddServer = new OddServer(context);
        this.mContext = context;
    }

    public void getReleaseJob(ReleaseJobBean bean){

        HashMap<String,String> hashMap = new HashMap<>();
        if (!TextUtils.isEmpty(bean.getId())){
            hashMap.put("id",bean.getId());
        }
        hashMap.put("totalPrice", "");
        hashMap.put("client","ANDROID");
        hashMap.put("type",bean.getType());
        hashMap.put("userId",bean.getUserId());
        hashMap.put("postType",bean.getPostType());
        hashMap.put("postTypeName",bean.getPostTypeName());
        hashMap.put("postName",bean.getPostName());
        hashMap.put("taskTitle",bean.getPostName());
        hashMap.put("taskDescription",bean.getTaskDescription());
        hashMap.put("price",bean.getPrice());
        hashMap.put("recruitNumber",bean.getRecruitNumber());
        hashMap.put("surplusRecruitNumber",bean.getSurplusRecruitNumber());
        hashMap.put("isFarmersInsurance",bean.getIsFarmersInsurance());
        hashMap.put("jobMeterUnit",bean.getJobMeterUnit());
        hashMap.put("jobMeterUnitName",bean.getJobMeterUnitName());
        hashMap.put("demandType",bean.getDemandType());
        hashMap.put("sex","3");
        hashMap.put("areaId",bean.getAreaId());
        hashMap.put("provinceName",bean.getProvinceName());
        hashMap.put("cityName",bean.getCityName());
        hashMap.put("areaName",bean.getAreaName());
        hashMap.put("villageName",bean.getVillageName());
        hashMap.put("workAddress",bean.getWorkAddress());
        hashMap.put("startTime",bean.getStartTime());
        hashMap.put("endTime",bean.getEndTime());
        if (TextUtils.isEmpty(bean.getId())){
            mSubscriber = getReleaseJOb();
            mOddServer.getReleaseJob(mSubscriber,hashMap);
        }else{
            mSubscriber = getEditJOb();
            mOddServer.getEditJob(mSubscriber,hashMap);
        }

    }

    private Subscriber<JsonResponse<List<String>>> getReleaseJOb(){
        return new RXSubscriber<JsonResponse<List<String>>, List<String>>(null) {
            @Override
            public void requestNext(List<String> result) {
                mReleaseJobPresenter.ReleaseJobData(result);
            }
        };
    }
    private Subscriber<JsonResponse<List<String>>> getEditJOb(){
        return new RXSubscriber<JsonResponse<List<String>>, List<String>>(null) {
            @Override
            public void requestNext(List<String> result) {
                mEditJobPresenter.EditJobData(result);
            }
        };
    }
}
