package com.haiyin.gczb.utils.pic;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created
 * by durian
 * 2019/1/1.
 */
public interface OnPictureSelectedListener {
    /**
     * 图片选择的监听回调
     *
     * @param fileUri
     * @param bitmap
     */
    void onPictureSelected(Uri fileUri, Bitmap bitmap);
}
