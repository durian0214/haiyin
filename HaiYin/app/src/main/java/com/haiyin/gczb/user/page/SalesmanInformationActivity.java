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

import com.durian.lib.base.BaseView;
import com.durian.lib.glide.GlideUtil;
import com.durian.lib.utils.LogUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.UserEntity;
import com.haiyin.gczb.my.presenter.UserPresenter;
import com.haiyin.gczb.user.event.SalesDetailInfoEntity;
import com.haiyin.gczb.user.presenter.GetDataPresenter;
import com.haiyin.gczb.user.presenter.RegistPresenter;
import com.haiyin.gczb.user.presenter.SalesPresenter;
import com.haiyin.gczb.utils.ImageDisposeUtil;
import com.haiyin.gczb.utils.MyPermissions;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.ObjectKeyUtils;
import com.haiyin.gczb.utils.UploadHelper;
import com.haiyin.gczb.utils.dialog.PopupUtil;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.pic.CropActivity;
import com.haiyin.gczb.utils.pic.OnPictureSelectedListener;
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
public class SalesmanInformationActivity extends BaseActivity implements BaseView {
    String imgIconUrl;
    String imgUploadDocumentsPositiveUrl;
    String imgUploadDocumentsBaclUrl;
    SalesPresenter salesPresenter;
    @BindView(R.id.img_salesman_information_icon)
    RoundedImageView imgIcon;
    //职位
    @BindView(R.id.sp_salesman_information_position)
    Spinner sp;
    //姓名
    @BindView(R.id.edt_salesman_information_contact_name)
    EditText edtName;
    //联系人电话
    @BindView(R.id.tv_salesman_information_contact_phone)
    TextView tvPhone;
    //现居住地址
    @BindView(R.id.edt_salesman_information_address)
    TextView tvAddress;
    //银行卡号
    @BindView(R.id.edt_salesman_information_code)
    TextView tvCode;
    //开户行
    @BindView(R.id.edt_salesman_information_bank_name)
    TextView tvBankName;
    //上传证件
    @BindView(R.id.img_salesman_information_upload_documents_positive)
    RoundedImageView imgPositive;
    @BindView(R.id.img_salesman_information_upload_documents_back)
    RoundedImageView imgBack;

    @OnClick(R.id.img_salesman_information_icon)
    public void toIcon() {
        pic(0);
    }
    @OnClick(R.id.img_salesman_information_upload_documents_positive)
    public void toCollectionUploadDocumentsPositiveUrl() {
        pic(1);
    }
    @OnClick(R.id.img_salesman_information_upload_documents_back)
    public void toCollectionUploadDocumentsBckUrl() {
        pic(2);
    }

    @OnClick(R.id.btn_salesman_information_submit)
    public void toSubmit() {
        String name = edtName.getText().toString();
        String phone = tvPhone.getText().toString();
        String address = tvAddress.getText().toString();
        String code = tvCode.getText().toString();
        String bankName = tvBankName.getText().toString();
         if(name.isEmpty()||
                 phone.isEmpty()||
                 address.isEmpty()||
                 code.isEmpty()||
                 bankName.isEmpty()||
                 imgIconUrl==null||
                 imgUploadDocumentsPositiveUrl==null||
                 imgUploadDocumentsBaclUrl==null){
             MyUtils.showShort("请完善信息");
        return;
         }
        salesPresenter.salesModifyInfo(phone,null,sp.getSelectedItemPosition(),imgIconUrl,name,address,
                code,bankName,imgUploadDocumentsPositiveUrl,imgUploadDocumentsBaclUrl);
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
        if(code == ApiConfig.SALES_DETAIL_INFO){

            final SalesDetailInfoEntity entity = (SalesDetailInfoEntity) data;
            edtName.setText(entity.getData().getName());
            tvPhone.setText(entity.getData().getMobile());
            tvAddress.setText(entity.getData().getBackAddress());
            tvBankName.setText(entity.getData().getBackName());
            tvCode.setText(entity.getData().getCardNo());
            sp.setSelection(entity.getData().getSalesPosition());
            imgIconUrl = entity.getData().getHeadImg();
            imgUploadDocumentsPositiveUrl = entity.getData().getCardFrontPic();
            imgUploadDocumentsBaclUrl = entity.getData().getCardBackPic();
            new Thread(new Runnable() {
                @Override
                public void run() {
                  final String url =   UploadHelper.getInstance().getPriUrl(mContext,imgUploadDocumentsPositiveUrl);
                  runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          GlideUtil.loaderCornersImg(mContext,imgPositive,url);
                      }
                  });
                }
            }).start();
            GlideUtil.loaderCornersImg(this,imgIcon,imgIconUrl);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String url =   UploadHelper.getInstance().getPriUrl(mContext,imgUploadDocumentsBaclUrl);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GlideUtil.loaderCornersImg(mContext,imgBack,url);
                        }
                    });
                }
            }).start();

      }else if(code ==ApiConfig.SALES_MODIFY_INFO){
            BaseEntity entity = (BaseEntity) data;
            MyUtils.showShort(entity.getEm());
            this.finish();
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_salesman_information;
    }

    @Override
    public void initView() {
        salesPresenter = new SalesPresenter(this);
        setTitle("个人信息");
        salesPresenter.salesDetailInfo();
    }

    public void pic(final int position) {
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

                        String objectKey = "";
                        if (position == 0) {
                            objectKey = ObjectKeyUtils.getIntance().getAvatars();
                        }  else if (position == 1) {
                            objectKey = ObjectKeyUtils.getIntance().getSales();
                        } else if (position == 2) {
                            objectKey = ObjectKeyUtils.getIntance().getSales();
                        }


                        if (position == 0) {

                            UploadHelper.getInstance().upImagePub(mContext, new UploadHelper.OssUpCallback() {
                                @Override
                                public void successImg(String img_url) {
                                    LogUtil.e(img_url);
                                    GlideUtil.loaderCornersImg(mContext, imgIcon, img_url);
                                    imgIconUrl = img_url;
                                }

                                @Override
                                public void successVideo(String video_url) {

                                }

                                @Override
                                public void inProgress(long progress, long zong) {

                                }
                            }, objectKey, ImageDisposeUtil.Bitmap2Bytes(bitmap));
                        } else {
                            UploadHelper.getInstance().upImagePri(mContext, new UploadHelper.OssUpCallback() {
                                @Override
                                public void successImg(final String img_url) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                           if (position == 1) {
                                                GlideUtil.loaderCornersImg(mContext, imgPositive, UploadHelper.getInstance().getPriUrl(mContext,img_url));
                                                imgUploadDocumentsPositiveUrl = img_url;
                                            } else if (position == 2) {
                                                GlideUtil.loaderCornersImg(mContext, imgBack, UploadHelper.getInstance().getPriUrl(mContext,img_url));
                                                imgUploadDocumentsBaclUrl = img_url;
                                            }
                                        }
                                    });

                                }

                                @Override
                                public void successVideo(String video_url) {

                                }

                                @Override
                                public void inProgress(long progress, long zong) {

                                }
                            }, objectKey, ImageDisposeUtil.Bitmap2Bytes(bitmap));
                        }


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
