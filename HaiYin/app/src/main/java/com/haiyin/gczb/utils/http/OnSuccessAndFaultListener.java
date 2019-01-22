package com.haiyin.gczb.utils.http;

/**
 * Created
 * by durian
 * 2018/12/26.
 */
public interface OnSuccessAndFaultListener {
    void onSuccess(String result);

    void onFault(String errorMsg);
}
