package com.dlg.data.wallet.interactor;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Observable;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/14 10:17
 */
public interface PayInteractor {
    Observable<JsonResponse<List<Boolean>>> judgePwd(HashMap<String, String> hashMap);
}
