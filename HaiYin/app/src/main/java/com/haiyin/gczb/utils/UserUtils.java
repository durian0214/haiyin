package com.haiyin.gczb.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.durian.lib.bus.RxBus;
import com.haiyin.gczb.MainActivity;
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
     * 用户MOBILE
     */
    public static String getMobile() {
        return SharedPreferencesUtils.get(BaseApplication.getAppContext(), SharedPreferencesVar.MOBILE, "").toString();
    }
    /**
     * userid
     */
    public static String getUserId() {
        return SharedPreferencesUtils.get(BaseApplication.getAppContext(), SharedPreferencesVar.USER_ID, "").toString();
    }
    /**
     * 是否登录
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
            RxBus.getInstance().post(new LoginOutEvent());
            return false;
        }
        Constant.userType = (int) SharedPreferencesUtils.get(BaseApplication.getAppContext(), SharedPreferencesVar.userType, 0);
        return true;

    }

    /**
     * 退出登录
     */
    public static void outLogin() {
        Constant.userType = 0;
        SharedPreferencesUtils.clear(BaseApplication.getAppContext());
        if (MainActivity.getInstance() != null) {
            MainActivity.getInstance().selMainhome();
        }
        RxBus.getInstance().post(new LoginOutEvent());


    }

    /**
     * token错误
     */
    public static void tokenerror() {
        SharedPreferencesUtils.clear(BaseApplication.getAppContext());
        RxBus.getInstance().post(new LoginOutEvent());


    }


    /**
     * 获取build  name
     *
     * @param mContext
     * @return
     */
    public static final String getVersionName(Context mContext) {
        PackageManager manager = mContext.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionName;
    }

    /**
     * 获取build  code
     *
     * @param mContext
     * @return
     */
    public static final int getVersionCode(Context mContext) {
        PackageManager manager = mContext.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionCode;
    }
}
