package com.haiyin.gczb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.haiyin.gczb.base.BaseApplication;
import com.haiyin.gczb.utils.dialog.PopupUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created
 * by durian
 * 2018/12/25.
 */
public class MyUtils {
    public static void showLong(String msg) {
        Toast mToast = Toast.makeText(BaseApplication.getAppContext(), msg, Toast.LENGTH_LONG);
        mToast.show();
    }

    public static void showShort(String msg) {
        Toast mToast = Toast.makeText(BaseApplication.getAppContext(), msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    /**
     * RV水平管理
     *
     * @param mContext
     * @return
     */
    public static LinearLayoutManager getHManager(Context mContext) {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        return manager;
    }

    /**
     * RV垂直管理
     *
     * @param mContext
     * @return
     */

    public static LinearLayoutManager getVManager(Context mContext) {
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }

    /**
     * 表格佈局
     *
     * @param mContext
     * @param size     数量
     * @return
     */
    public static GridLayoutManager getTableManager(Context mContext, int size) {
        GridLayoutManager glm = new GridLayoutManager(mContext, size);
        return glm;
    }

    /**
     * 时间转换
     *
     * @param time
     * @return
     */
    public static String getTime(String time) {
        String str = "";
        if (!time.isEmpty()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  hh:mm");
            Date curDate = new Date();//获取当前时间
            curDate.setTime(Long.parseLong(time));
            str = formatter.format(curDate);
        }

        return str;

    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static long getNowTime() {
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间

        return curDate.getTime();

    }

    /**
     * 获取随机数
     * @return
     */
    public static String getRandomnum() {

        String strRand="" ;
        for(int i=0;i<5;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }

        return strRand;
    }
    /**
     * 数字与大写字母混编字符串
     * @return
     */
    public static String getNumLargeLetter(){
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for(int i=0; i<6;i++){
            if(random.nextInt(2) % 2 == 0){//字母
                buffer.append((char) (random.nextInt(26) + 'A'));
            }else{//数字
                buffer.append(random.nextInt(10));
            }
        }
        return buffer.toString();
    }

    /**
     * 相机弹出框监听
     */
    public static final int CAMERA_REQUEST_CODE = 1;
    public static final int GALLERY_REQUEST_CODE = 2;

    public static void photoUtil(Activity context, int position, String mTempPhotoPath) {
        if (MyPermissions.isOpenWrite(context)) {
            if (MyPermissions.isOpenCamera(context)) {
                if (position == 0) {
                    PopupUtil.getInstence().disPopup();
                    Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //下面这句指定调用相机拍照后的照片存储的路径
                    takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mTempPhotoPath)));
                    context.startActivityForResult(takeIntent, CAMERA_REQUEST_CODE);
                } else if (position == 1) {
                    // "从相册选择"按钮被点击了
                    PopupUtil.getInstence().disPopup();
                    Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                    // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
                    pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    context.startActivityForResult(pickIntent, GALLERY_REQUEST_CODE);
                }
            } else {
                MyPermissions.setCameraPermissions(context);
            }
        } else {
            MyPermissions.setWritePermissions(context);
        }

    }

    /**
     * 加密
     *
     * @param params
     * @return
     */
    public static String encryptString(Map<String, Object> params) {
        String data = "";
        if (!UserUtils.getToken().isEmpty()) {
            params.put("token", UserUtils.getToken());
        }
        try {
            data = AESUtil.encrypt(JSON.toJSONString(params));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 是否是合法的手机号
     */
    public static boolean isMobileNO(String mobiles) {
//        Pattern p = Pattern
//                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        //手机号段随时可能增加，这是最保险的方法
        Pattern p = Pattern.compile("[1]\\d{10}");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

}
