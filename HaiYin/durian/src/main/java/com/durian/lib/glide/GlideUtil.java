package com.durian.lib.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.durian.lib.R;
import com.durian.lib.utils.ImageUtil;

/**
 * Created
 * by durian
 * 2018/3/29.
 * glide图片加载工具类
 */
public class GlideUtil {
    private static int IMG_ERROR = R.mipmap.ic_launcher;
    private static int IMG_WAIT = R.mipmap.ic_launcher;
    ;

    /**
     * 加载图片
     *
     * @param mContext
     * @param v
     * @param url
     */
    public static void loaderImg(Context mContext, ImageView v, String url) {
        RequestOptions option = new RequestOptions().placeholder(IMG_WAIT).error(IMG_ERROR).diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(mContext)
                .load(url)
                .transition(new DrawableTransitionOptions().crossFade(300))
                .apply(option)
                .into(v);
    }

    /**
     * 加载圆角图片
     *
     * @param mContext
     * @param v
     * @param url
     */
    public static void loaderCornersImg(Context mContext, ImageView v, String url) {

        RequestOptions option = new RequestOptions().placeholder(IMG_WAIT).error(IMG_WAIT).diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(mContext)
                .asBitmap()
                .load(url)
                .apply(option)
                .into(v);

//                Glide.with(BaseApplication.getAppContext()).asBitmap().load("url").into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//               // mBitmap = resource;
//            }
//        });
    }

    /**
     * 加载图片返回bitmap
     */
    public static void save(final Context mContext, final String mImageUrl) {
        Glide.with(mContext).asBitmap().load(mImageUrl).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                String[] fileNameArr = mImageUrl.substring(mImageUrl.lastIndexOf("/") + 1).split("\\.");
                ImageUtil.saveImage(mContext, mImageUrl,resource , fileNameArr[0]);
            }
        });
    }


    //清理磁盘缓存
    public static void GuideClearDiskCache(Context mContext) {
        //理磁盘缓存 需要在子线程中执行
        Glide.get(mContext).clearDiskCache();
    }

    //清理内存缓存
    public static void GuideClearMemory(Context mContext) {
        //清理内存缓存  可以在UI主线程中进行
        Glide.get(mContext).clearMemory();
    }
}
