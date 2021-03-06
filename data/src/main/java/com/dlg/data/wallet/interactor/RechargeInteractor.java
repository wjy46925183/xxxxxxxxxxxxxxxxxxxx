package com.dlg.data.wallet.interactor;

import com.dlg.data.wallet.model.RechargeBean;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Observable;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/11 10:36
 */
public interface RechargeInteractor {
    Observable<JsonResponse<List<RechargeBean>>> getPay(HashMap<String, String> map);
    Observable<JsonResponse<List<String>>> rechargeError(HashMap<String, String> map);
}
