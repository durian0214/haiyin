package com.haiyin.gczb.user.page;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.user.presenter.RegistPresenter;
import com.haiyin.gczb.utils.ImageDisposeUtil;
import com.haiyin.gczb.utils.MyPermissions;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.ObjectKeyUtils;
import com.haiyin.gczb.utils.UploadHelper;
import com.haiyin.gczb.utils.dialog.PopupUtil;
import com.haiyin.gczb.utils.pic.CropActivity;
import com.haiyin.gczb.utils.pic.OnPictureSelectedListener;
import com.durian.lib.base.BaseView;
import com.durian.lib.glide.GlideUtil;
import com.kevin.crop.UCrop;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/4.
 */
public class PersonalInformationActivity extends BaseActivity implements BaseView {
    String phone;
    String code;
    int roleType;
    String imgUrl;
    private RegistPresenter registPresenter;
    @BindView(R.id.img_personal_information_icon)
    RoundedImageView img;
    @BindView(R.id.sp_personal_information_position)
    Spinner sp;
    @BindView(R.id.edt_personal_information_contact_name)
    EditText edtName;
    @BindView(R.id.tv_personal_information_contact_phone)
    TextView tvPhone;

    @OnClick(R.id.img_personal_information_icon)
    public void toIcon() {
        pic();
    }

    @OnClick(R.id.btn_personal_information_submit)
    public void toSubmit() {
        if (edtName.getText().toString().isEmpty()) {
            MyUtils.showShort("请输入姓名");
            return;
        }
        if (imgUrl == null) {
            MyUtils.showShort("请选择头像");
            return;
        }
        if (sp.getSelectedItemPosition() == 0) {
            MyUtils.showShort("请选择职位");
            return;
        }
        registPresenter.regist(phone,
                code,
                imgUrl,
                edtName.getText().toString(),
                sp.getSelectedItemPosition(),
                roleType,
                null,
                false,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,mContext);
    }

    /**
     * 图片选择的监听回调
     */
    private OnPictureSelectedListener mOnPictureSelectedListener;
    // 剪切后图像文件
    private Uri mDestinationUri;
    String mTempPhotoPath = Environment.getExternalStorageDirectory() + File.separator + "photo.jpeg";

    @Override
    public void success(int code, Object data) {

    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_information;
    }

    @Override
    public void initView() {
        registPresenter = new RegistPresenter(this);
        setTitle("个人用户");
        Bundle b = getIntent().getBundleExtra("bundle");
        phone = b.getString("phone");
        code = b.getString("code");
        roleType = b.getInt("roleType");
        tvPhone.setText(phone);
    }

    public void pic() {
        if (MyPermissions.isOpenWrite(this)) {
            if (MyPermissions.isOpenCamera(this)) {
                final String imgName = "cropImage" + MyUtils.getNowTime() + ".jpeg";
                mDestinationUri = Uri.fromFile(new File(this.getCacheDir(), imgName));

                PopupUtil.getInstence().showCamera(this, new PopupUtil.OnSelectedListener() {
                    @Override
                    public void OnSelected(View v, final int position) {
                        new Handler().post(new Runnable(){
                            public void run(){
                                MyUtils.photoUtil(mContext, position, mTempPhotoPath);
                            }
                        });

                    }
                });
                mOnPictureSelectedListener = new OnPictureSelectedListener() {
                    @Override
                    public void onPictureSelected(Uri fileUri, Bitmap bitmap) {
                        UploadHelper.getInstance().upImagePub(mContext, new UploadHelper.OssUpCallback() {
                            @Override
                            public void successImg(final String img_url) {
                                imgUrl = img_url;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        GlideUtil.loaderImg(mContext, img, img_url);
                                    }
                                });
                            }

                            @Override
                            public void successVideo(String video_url) {

                            }

                            @Override
                            public void inProgress(long progress, long zong) {

                            }
                        }, ObjectKeyUtils.getIntance().getAvatars(), ImageDisposeUtil.Bitmap2Bytes(bitmap));
                    }
                };
            } else {
                MyPermissions.setCameraPermissions(this);
            }
        } else {
            MyPermissions.setWritePermissions(this);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == this.RESULT_OK) {
            switch (requestCode) {
                case MyUtils.CAMERA_REQUEST_CODE:   // 调用相机拍照
                    File temp = new File(mTempPhotoPath);
                    startCropActivity(Uri.fromFile(temp));
                    break;
                case MyUtils.GALLERY_REQUEST_CODE:  // 直接从相册获取
                    startCropActivity(data.getData());
                    break;

                case UCrop.REQUEST_CROP:    // 裁剪图片结果
                    handleCropResult(data);
                    break;
                case UCrop.RESULT_ERROR:    // 裁剪图片错误
                    handleCropError(data);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * 处理剪切成功的返回值
     *
     * @param result
     */
    private void handleCropResult(Intent result) {

        deleteTempPhotoFile();
        final Uri resultUri = UCrop.getOutput(result);
        if (null != resultUri && null != mOnPictureSelectedListener) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mOnPictureSelectedListener.onPictureSelected(resultUri, bitmap);
        } else {
            Toast.makeText(this, "无法剪切选择图片", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 处理剪切失败的返回值
     *
     * @param result
     */
    private void handleCropError(Intent result) {
        deleteTempPhotoFile();
        final Throwable cropError = UCrop.getError(result);
        if (cropError != null) {
            Log.e("", "handleCropError: ", cropError);

            Toast.makeText(this, cropError.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "无法剪切选择图片", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startCropActivity(Uri uri) {
        UCrop.of(uri, mDestinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(512, 512)
                .withTargetActivity(CropActivity.class)
                .start(this);
    }

    /**
     * 删除拍照临时文件
     */
    private void deleteTempPhotoFile() {
        File tempFile = new File(mTempPhotoPath);
        if (tempFile.exists() && tempFile.isFile()) {
            tempFile.delete();
        }
    }
}
