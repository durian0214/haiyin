package com.haiyin.gczb.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.haiyin.gczb.utils.Constant;

import java.util.ArrayList;

import com.haiyin.gczb.utils.Constant;

public class BaseApplication extends Application {
    private static BaseApplication instance;
    public static ArrayList<Activity> allActivities = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Constant.screenWidth = this.getResources().getDisplayMetrics().widthPixels;

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
