package com.haiyin.gczb.utils.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.haiyin.gczb.R;

/**
 * Created by durian on 2017/12/13.
 * 相册弹出框
 */

public class PopupUtil {

    private static PopupUtil popupUtil;
    private static PopupWindow popupWindow = null;

    public static PopupUtil getInstence() {
        synchronized (PopupUtil.class) {
            if (popupUtil == null) {
                popupUtil = new PopupUtil();
            }
        }
        return popupUtil;

    }

    /**
     * 选择监听接口
     */
    public interface OnSelectedListener {
        void OnSelected(View v, int position);
    }

    /**
     * 调用照相机 相册
     *
     * @param mContext
     * @param select   0 = 相机  1= 相册
     */
    public void showCamera(Context mContext, final @NonNull OnSelectedListener select) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(R.layout.dialog_camera, null);
        Button takePhotoBtn = mMenuView.findViewById(R.id.picture_selector_take_photo_btn);
        Button pickPictureBtn = mMenuView.findViewById(R.id.picture_selector_pick_picture_btn);
        Button cancelBtn = mMenuView.findViewById(R.id.picture_selector_cancel_btn);
        // 设置按钮监听
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.OnSelected(view, 0);
            }
        });
        pickPictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.OnSelected(view, 1);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disPopup();
            }
        });
        popupWindow = new PopupWindow(mMenuView,    // 添加到popupWindow
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);   // 设置窗口显示的动画效果
        popupWindow.setFocusable(false);                                        // 点击其他地方隐藏键盘 popupWindow
        popupWindow.update();
    }

    /**
     * 调用照相机
     *
     * @param mContext
     * @param select   0 = 相机  1= 相册
     */
    public void showCamera1(Context mContext, final @NonNull OnSelectedListener select) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mMenuView = inflater.inflate(R.layout.dialog_camera, null);
        Button takePhotoBtn = mMenuView.findViewById(R.id.picture_selector_take_photo_btn);
        Button pickPictureBtn = mMenuView.findViewById(R.id.picture_selector_pick_picture_btn);
        pickPictureBtn.setVisibility(View.GONE);
        Button cancelBtn = mMenuView.findViewById(R.id.picture_selector_cancel_btn);
        // 设置按钮监听
        takePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.OnSelected(view, 0);
            }
        });
        pickPictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.OnSelected(view, 1);
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disPopup();
            }
        });
        popupWindow = new PopupWindow(mMenuView,    // 添加到popupWindow
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        popupWindow.setAnimationStyle(android.R.style.Animation_InputMethod);   // 设置窗口显示的动画效果
        popupWindow.setFocusable(false);                                        // 点击其他地方隐藏键盘 popupWindow
        popupWindow.update();
    }

    public void disPopup() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }
}
