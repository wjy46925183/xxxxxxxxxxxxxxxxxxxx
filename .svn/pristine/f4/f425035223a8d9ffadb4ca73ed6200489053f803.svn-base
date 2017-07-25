package com.dlg.data.wallet.interactor;

import com.dlg.data.wallet.model.BankBean;

import java.util.HashMap;
import java.util.List;

import okhttp.rx.JsonResponse;
import rx.Observable;

/**
 * 作者：关蕤
 * 主要功能：
 * 创建时间：2017/7/12 10:11
 */
public interface WithDrawalInteractor {
    Observable getFrequency(HashMap<String, String> map);

    Observable<JsonResponse<List<String>>> getBindWeChat(HashMap map);
    Observable<JsonResponse<List<String>>> getIncBindWeChat(HashMap map);
    Observable<JsonResponse<List<String>>> exchange(HashMap map);

    Observable<JsonResponse<List<String>>> setPayPwd(HashMap map);

    Observable<JsonResponse<List<BankBean>>> getBank(HashMap map);
}
