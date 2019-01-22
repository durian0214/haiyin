package com.haiyin.gczb.utils;

import com.durian.lib.bus.RxBus;
import com.haiyin.gczb.base.BaseApplication;
import com.haiyin.gczb.user.event.LoginOutEvent;
import com.haiyin.gczb.utils.var.SharedPreferencesVar;

/**
 * Created
 * by durian
 * 2019/1/10.
 */
public class UserUtils {

    /**
     * 用户token
     */
    public static String getToken() {
        return SharedPreferencesUtils.get(BaseApplication.getAppContext(), SharedPreferencesVar.TOKEN, "").toString();
    }

    /**
     * 是否登录
     *
     * @return true  false
     */
    public static boolean isLogin() {
        if (SharedPreferencesUtils.get(BaseApplication.getAppContext(), SharedPreferencesVar.TOKEN, "").toString().isEmpty()) {

            return false;
        }
        Constant.userType = (int) SharedPreferencesUtils.get(BaseApplication.getAppContext(), SharedPreferencesVar.userType, 0);
        return true;
    }

    /**
     * 是否登录 并登录
     *
     * @return
     */
    public static boolean isLoginToLogin() {
        if (SharedPreferencesUtils.get(BaseApplication.getAppContext(), SharedPreferencesVar.TOKEN, "").toString().isEmpty()) {
            RxBus.getInstance().send(new LoginOutEvent());
            return false;
        }
        Constant.userType = (int) SharedPreferencesUtils.get(BaseApplication.getAppContext(), SharedPreferencesVar.userType, 0);
        return true;

    }

    /**
     * 退出登录
     */
    public static void outLogin() {
        RxBus.getInstance().send(new LoginOutEvent());
        SharedPreferencesUtils.clear(BaseApplication.getAppContext());
    }
}