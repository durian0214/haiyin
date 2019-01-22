package com.haiyin.gczb.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.haiyin.gczb.R;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created
 * by durian
 * 权限管理工具
 * 2018/5/7.
 */
public class MyPermissions {
    public static final int CAMERA_PERMISSIONS = 0x021;
    public static final int WRITE_PERMISSIONS = 0x022;

    /**
     * 设置相机权限
     *
     * @param mContext
     */
    public static final void setCameraPermissions(Context mContext) {
        EasyPermissions.requestPermissions((Activity) mContext, mContext.getString(R.string.rationale_camera),
                CAMERA_PERMISSIONS, Manifest.permission.CAMERA);
    }

    /**
     * 是否开启相机权限
     * @param mContext
     * @return
     */
    public static final boolean isOpenCamera(Context mContext) {
        return EasyPermissions.hasPermissions(mContext, Manifest.permission.CAMERA);
    }

    /*******************************************************************
     * 设置写入权限
     *
     * @param mContext
     */
    public static final void setWritePermissions(Context mContext) {
        EasyPermissions.requestPermissions((Activity) mContext, mContext.getString(R.string.rationale_write),
                WRITE_PERMISSIONS, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * 是否开启写入权限
     * @param mContext
     * @return
     */
    public static final boolean isOpenWrite(Context mContext) {
        return EasyPermissions.hasPermissions(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


}
