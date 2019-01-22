package com.durian.lib.base;

/**
 * Created by durian on 2017/11/2.
 */

public interface BaseView {
    void success(int code,Object data);
    void netError(String msg);

}
