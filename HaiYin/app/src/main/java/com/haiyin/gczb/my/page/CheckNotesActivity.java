package com.haiyin.gczb.my.page;

import android.Manifest;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.durian.lib.glide.GlideUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created
 * by durian
 * 2019/1/7.
 */
public class CheckNotesActivity extends BaseActivity {

    private final int RC_WRITE_PERM = 120;
    String mImageUrl = "http://pub.oss.jiuniok.com/201901/dododoc1d0tub9hkv416vhb0vrekbpra/dododoc1d0tub9hkv416vhb0vrekbpra.jpeg";

    @OnClick(R.id.btn_checknotes_download)
    public void toDownload() {
        //设置
        if (EasyPermissions.hasPermissions(CheckNotesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            GlideUtil.save(this, mImageUrl);
        } else {
            EasyPermissions.requestPermissions(this, "保存图片需要访问您的读写权限",
                    RC_WRITE_PERM, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    @BindView(R.id.iv_checknotes_img)
    ImageView ivChecknotesImg;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_checknotes;
    }


    @Override
    public void initView() {
        setTitle("查看票据");
        GlideUtil.loaderImg(this, ivChecknotesImg, mImageUrl);

    }


}
