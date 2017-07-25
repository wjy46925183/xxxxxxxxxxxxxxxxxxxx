package com.dlg.viewmodel.oddjob;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.dlg.viewmodel.BasePresenter;
import com.dlg.viewmodel.BaseViewModel;
import com.dlg.viewmodel.RXSubscriber;
import com.dlg.viewmodel.oddjob.presenter.ReleaseServicePresenter;
import com.dlg.viewmodel.server.OddServer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Subscriber;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 作者：王进亚
 * 主要功能：
 * 创建时间：2017/7/14 15:17
 */

public class ReleaseServiceViewModel extends BaseViewModel {
    private ReleaseServicePresenter presenter;
    private OddServer oddServer;
    private BasePresenter basePresenter;

    public ReleaseServiceViewModel(Context context, BasePresenter basePresenter, ReleaseServicePresenter presenter) {
        mContext = context;
        oddServer = new OddServer(context);
        this.presenter = presenter;
        this.basePresenter = basePresenter;
    }

    /**
     * 发布零工技能
     *
     * @param id                 id 不编辑时为 null
     * @param demandType         类型
     * @param serviceName        服务名称
     * @param serviceTypeName    服务类型名字
     * @param serviceType        服务类型
     * @param serviceMeterUnit   服务
     * @param price              报酬
     * @param serviceDescription 服务描述
     * @param list            图片网址 编辑用
     * @param imgsBase64         base64编码
     */
    public void releaseService(String id, String demandType, String serviceName, String serviceTypeName
            , String serviceType, String serviceMeterUnit, String price, String serviceDescription
            , List<String> list, String imgsBase64) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("demandType", demandType);
        hashMap.put("serviceName", serviceName);
        hashMap.put("serviceTypeName", serviceTypeName);
        hashMap.put("serviceType", serviceType);

        hashMap.put("serviceMeterUnit", serviceMeterUnit);
        hashMap.put("price", price);

        hashMap.put("serviceDescription", serviceDescription);
        hashMap.put("imagesUrl", getImgUrls(list));//编辑用 url
        hashMap.put("images", imgsBase64);

        mSubscriber = getSub();
        oddServer.releaseService(mSubscriber, hashMap);
    }

    public Subscriber<JsonResponse<String>> getSub() {
        return new RXSubscriber<JsonResponse<String>, String>(basePresenter) {
            @Override
            public void requestNext(String msg) {
                presenter.releaseServiceSuccess(msg);
            }
        };
    }

    /**
     * 得到网址 编辑所用
     *
     * @param paths
     * @return
     */
    private String getImgUrls(List<String> paths) {
        StringBuffer sb = new StringBuffer();
        if (paths.size() > 0) {
            for (int i = 0; i < paths.size(); i++) {
                if (paths.get(i).startsWith("http")) {
                    if (sb.length() == 0) {
                        sb.append(paths.get(i));
                    } else {
                        sb.append(";" + paths.get(i));
                    }
                }
            }
        }
        return sb.toString();
    }

    /**
     * 压缩图片 并base64加密
     *
     * @param releasePicturesCallBack 回调
     * @param paths                   路径
     */
    public void getBase64Imgs(final List<String> paths, final ReleasePicturesCallBack releasePicturesCallBack) {
        if (paths.size() > 0) {
            final StringBuffer imgBase64 = new StringBuffer();
            final List<Integer> integers = new ArrayList<>();
            final List<Integer> integers2 = new ArrayList<>();
            for (int i = 0; i < paths.size(); i++) {
                if (!paths.get(i).startsWith("http:")) {
                    integers2.add(0);
                    Luban.with(mContext).load(new File(paths.get(i))).setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {

                        }

                        @Override
                        public void onSuccess(File file) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream);
                            String img =
                                    Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                            try {
                                imgBase64.append(img + ";");
                                integers.add(0);
                                if (integers.size() == integers2.size()) {
                                    if (releasePicturesCallBack != null) {
                                        releasePicturesCallBack.success(imgBase64.toString());
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    outputStream.close();
                                    bitmap.recycle();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            releasePicturesCallBack.onError(e.toString());
                        }
                    }).launch();
                }
            }
        }
    }

    public interface ReleasePicturesCallBack {
        void success(String base64Imgs);
        void onError(String error);
    }
}
