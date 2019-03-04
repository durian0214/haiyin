package com.haiyin.gczb.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.alibaba.security.rp.RPSDK;
import com.durian.lib.utils.SystemUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.utils.Constant;

import java.util.ArrayList;

import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.UploadHelper;
import com.haiyin.gczb.utils.UserUtils;

public class BaseApplication extends Application {
    private static BaseApplication instance;
    public static ArrayList<Activity> allActivities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Constant.screenWidth = this.getResources().getDisplayMetrics().widthPixels;
        Constant.appVersionName = UserUtils.getVersionName(this);
        Constant.appVersionCode = UserUtils.getVersionCode(this);
        Constant.deviceBrand = SystemUtil.getDeviceBrand();
        Constant.deviceSysversion = SystemUtil.getSystemVersion();
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder b = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(b.build());
        }
        initRPSDK();
    }

    private void initRPSDK(){
        RPSDK.initialize(this);
    }

    public static Context getAppContext() {
        return instance;
    }


    public static void addActivity(Activity activity) {
        allActivities.add(activity);
    }

    public static void delActivity(Activity activity) {
        allActivities.remove(activity);
    }

}
