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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.durian.lib.base.BaseView;
import com.durian.lib.glide.GlideUtil;
import com.durian.lib.utils.LogUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.SalesCompanyDetailEntity;
import com.haiyin.gczb.my.presenter.CustomerPresenter;
import com.haiyin.gczb.user.entity.IndustryEntity;
import com.haiyin.gczb.user.entity.RegistEntity;
import com.haiyin.gczb.user.entity.SalesEntity;
import com.haiyin.gczb.user.presenter.GetDataPresenter;
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
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/4.
 * 完善客户信息
 */
public class ImproveCustomerInformationActivity extends BaseActivity implements BaseView {
    GetDataPresenter getDataPresenter;
    CustomerPresenter customerPresenter;

    //头像
    @BindView(R.id.img_improve_customer_information_icon)
    RoundedImageView imgIcon;

    //公司名称
    @BindView(R.id.edt_improve_customer_information_name)
    EditText edtName;
    //公司联系方式
    @BindView(R.id.edt_improve_customer_information_contact)
    EditText edtContact;
    //联系人姓名
    @BindView(R.id.edt_improve_customer_information_contact_name)
    EditText edtContactName;
    //联系人电话
    @BindView(R.id.edt_improve_customer_information_contact_phone)
    EditText edtContactPhone;
    //职位
    @BindView(R.id.sp_improve_customer_information_position)
    Spinner spPosition;
    //行业
    @BindView(R.id.sp_improve_customer_information_industry)
    Spinner spIndustry;
    //收款人姓名
    @BindView(R.id.edt_improve_customer_information_collection_name)
    EditText edtCollectionName;
    //银行卡号
    @BindView(R.id.edt_improve_customer_information_bank_code)
    EditText edtBankCode;
    //收款人身份证号
    @BindView(R.id.edt_improve_customer_information_collection_codeid)
    EditText edtCollectionCodeid;
    //银行名称
    @BindView(R.id.edt_improve_customer_information_bank_name)
    EditText edtBankName;
    //营业执照
    @BindView(R.id.img_improve_customer_information_business_license)
    RoundedImageView imgBusinessLicense;
    //上传证件
    @BindView(R.id.img_improve_customer_information_upload_documents_positive)
    RoundedImageView imgUploadDocumentsPositive;
    @BindView(R.id.img_improve_customer_information_upload_documents_back)
    RoundedImageView imgUploadDocumentsBacl;
    //收款人身份证扫描件
    @BindView(R.id.img_improve_customer_information_collection_upload_documents_positive)
    RoundedImageView imgCollectionUploadDocumentsPositive;
    @BindView(R.id.img_improve_customer_information_collection_upload_documents_back)
    RoundedImageView imgCollectionUploadDocumentsBck;

    int postion;
    String imgIconUrl;
    String imgBusinessLicenseUrl;
    String imgUploadDocumentsPositiveUrl;
    String imgUploadDocumentsBaclUrl;
    String imgCollectionUploadDocumentsPositiveUrl;
    String imgCollectionUploadDocumentsBckUrl;


    @OnClick(R.id.img_improve_customer_information_business_license)
    public void toBusinessLicenseUrl() {
        pic(1);
    }

    @OnClick(R.id.img_improve_customer_information_upload_documents_positive)
    public void toUploadDocumentsPositiveUrl() {
        pic(2);
    }

    @OnClick(R.id.img_improve_customer_information_upload_documents_back)
    public void toUploadDocumentsBaclUrl() {
        pic(3);
    }

    @OnClick(R.id.img_improve_customer_information_collection_upload_documents_positive)
    public void toCollectionUploadDocumentsPositiveUrl() {
        pic(4);
    }

    @OnClick(R.id.img_improve_customer_information_collection_upload_documents_back)
    public void toCollectionUploadDocumentsBckUrl() {
        pic(5);
    }


    String id;


    private List<SalesEntity.DataBean> salesList;
    private List<IndustryEntity.DataBean> industryList;

    //提交
    @OnClick(R.id.btn_improve_customer_information)
    public void toSend() {

        String name = edtName.getText().toString();
        String contact = edtContact.getText().toString();
        String contactName = edtContactName.getText().toString();
        String contactPhone = edtContactPhone.getText().toString();
        String collectionName = edtCollectionName.getText().toString();
        String bankCode = edtBankCode.getText().toString();
        String bankName = edtBankName.getText().toString();
        String collectionCodeid = edtCollectionCodeid.getText().toString();
        if (contactPhone.isEmpty()) {
            MyUtils.showShort("请输入联系人电话");
            return;
        }
        if (imgIconUrl == null) {
            MyUtils.showShort("请选择头像");
            return;
        }
        if (contactName.isEmpty()) {
            MyUtils.showShort("请输入联系人姓名");
            return;
        }
        if (spPosition.getSelectedItemPosition() == 0) {
            MyUtils.showShort("请选择职位");
            return;
        }

        if (name.isEmpty()) {
            MyUtils.showShort("请输入公司名称");
            return;
        }
        if (contact.isEmpty()) {
            MyUtils.showShort("请输入公司联系方式");
            return;
        }
        if (spIndustry.getSelectedItemPosition() == 0) {
            MyUtils.showShort("请选择行业");
            return;
        }
        if (imgBusinessLicenseUrl == null ||
                imgUploadDocumentsPositiveUrl == null ||
                imgUploadDocumentsBaclUrl == null || imgCollectionUploadDocumentsPositiveUrl == null ||
                imgCollectionUploadDocumentsBckUrl == null) {
            MyUtils.showShort("请上传图片");
            return;
        }
        if (collectionCodeid.isEmpty()) {
            MyUtils.showShort("请输入收款人身份证号");
            return;
        }
        if (collectionName.isEmpty()) {
            MyUtils.showShort("请输入收款人姓名");
            return;
        }
        if (bankCode.isEmpty()) {
            MyUtils.showShort("请输入银行卡号");
            return;
        }
        if (bankName.isEmpty()) {
            MyUtils.showShort("请输入银行名称");
            return;
        }

        customerPresenter.saleseditCompany(id,contactPhone,
                imgIconUrl,
                contactName,
                name,
                contact,
                spPosition.getSelectedItemPosition(),
                industryList.get(spIndustry.getSelectedItemPosition() - 1).getIndustryId(),
                imgBusinessLicenseUrl,
                collectionCodeid,
                imgUploadDocumentsPositiveUrl,
                imgUploadDocumentsBaclUrl,
                collectionName,
                bankCode,
                bankName,
                imgCollectionUploadDocumentsPositiveUrl,
                imgCollectionUploadDocumentsBckUrl);

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
        if (code == ApiConfig.INDUSTRY) {
            IndustryEntity entity = (IndustryEntity) data;
            industryList = entity.getData();
            List<String> dataList = new ArrayList<>();
            dataList.add("选择行业");
            for (int i = 0; i < industryList.size(); i++) {
                dataList.add(industryList.get(i).getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, R.layout.item_sp,
                    dataList);
            spIndustry.setAdapter(adapter);
        } else if (code == ApiConfig.SALES_EDIT_COMPANY) {
            //完善成功
            BaseEntity entity = (BaseEntity) data;
            MyUtils.showShort("完善成功");
            this.finish();
        }else if(code ==ApiConfig.SALES_COMPANY_DETAIL){
            SalesCompanyDetailEntity entity = (SalesCompanyDetailEntity) data;
            GlideUtil.loaderCornersImg(this,imgIcon,entity.getData().getHeadImg());
            edtName.setText(entity.getData().getCompanyName());
            edtContact.setText(entity.getData().getCompanyPhone());
            edtContactName.setText(entity.getData().getContactsName());
            edtContactPhone.setText(entity.getData().getContactsPhone());
            edtBankCode.setText(entity.getData().getCardNo());
            edtCollectionCodeid.setText(entity.getData().getIdCardNo());
            edtCollectionName.setText(entity.getData().getFinaName());
            edtBankName.setText(entity.getData().getBankName());
            if(!entity.getData().getBusinessLicensePic().isEmpty()){
                GlideUtil.loaderCornersImg(this,imgBusinessLicense,UploadHelper.getInstance().getPriUrl(mContext,entity.getData().getBusinessLicensePic()));
            }
            if(!entity.getData().getCorpCardFront().isEmpty()){
                GlideUtil.loaderCornersImg(this,imgUploadDocumentsPositive,UploadHelper.getInstance().getPriUrl(mContext,entity.getData().getCorpCardFront()));
            }
            if(!entity.getData().getCorpCardBack().isEmpty()){
                GlideUtil.loaderCornersImg(this,imgUploadDocumentsBacl,UploadHelper.getInstance().getPriUrl(mContext,entity.getData().getCorpCardBack()));
            }
            if(!entity.getData().getFinaCardFront().isEmpty()){
                GlideUtil.loaderCornersImg(this,imgCollectionUploadDocumentsPositive,UploadHelper.getInstance().getPriUrl(mContext,entity.getData().getFinaCardFront()));
            }
            if(!entity.getData().getFinaCardBack().isEmpty()){
                GlideUtil.loaderCornersImg(this,imgCollectionUploadDocumentsBck,UploadHelper.getInstance().getPriUrl(mContext,entity.getData().getFinaCardBack()));
            }
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_improve_customer_information;
    }

    @Override
    public void initView() {
        getDataPresenter = new GetDataPresenter(this);
        customerPresenter = new CustomerPresenter(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.item_sp,
                getResources().getStringArray(R.array.member));
        spPosition.setAdapter(adapter);
        getDataPresenter.industry();
        try {
            Bundle b = getIntent().getBundleExtra("bundle");
            id = b.getString("id");
            setTitle("完善客户信息");
            customerPresenter.salescompanyDetail(id);
        }catch (NullPointerException e){
            id = "";
            setTitle("添加客户信息");
        }
    }

    public void pic(final int position) {
        postion =position;
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
                        } else if (position == 1) {
                            objectKey = ObjectKeyUtils.getIntance().getCompanysLicense();
                        } else if (position == 2) {
                            objectKey = ObjectKeyUtils.getIntance().getCompanyCorporation();
                        } else if (position == 3) {
                            objectKey = ObjectKeyUtils.getIntance().getCompanyCorporation();
                        } else if (position == 4) {
                            objectKey = ObjectKeyUtils.getIntance().getCompanyPayee();
                        } else if (position == 5) {
                            objectKey = ObjectKeyUtils.getIntance().getCompanyPayee();
                        }
                        if (position == 0) {

                            UploadHelper.getInstance().upImagePub(mContext, new UploadHelper.OssUpCallback() {
                                @Override
                                public void successImg(final String img_url) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            LogUtil.e(img_url);
                                            GlideUtil.loaderCornersImg(mContext, imgIcon, img_url);
                                            imgIconUrl = img_url;
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
                        } else {
                            UploadHelper.getInstance().upImagePri(mContext, new UploadHelper.OssUpCallback() {
                                @Override
                                public void successImg(final String img_url) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (position == 1) {
                                                GlideUtil.loaderCornersImg(mContext, imgBusinessLicense, UploadHelper.getInstance().getPriUrl(mContext,img_url));
                                                imgBusinessLicenseUrl = img_url;
                                            } else if (position == 2) {
                                                GlideUtil.loaderCornersImg(mContext, imgUploadDocumentsPositive, UploadHelper.getInstance().getPriUrl(mContext,img_url));
                                                imgUploadDocumentsPositiveUrl = img_url;
                                            } else if (position == 3) {
                                                GlideUtil.loaderCornersImg(mContext, imgUploadDocumentsBacl, UploadHelper.getInstance().getPriUrl(mContext,img_url));
                                                imgUploadDocumentsBaclUrl = img_url;
                                            } else if (position == 4) {
                                                GlideUtil.loaderCornersImg(mContext, imgCollectionUploadDocumentsPositive, UploadHelper.getInstance().getPriUrl(mContext,img_url));
                                                imgCollectionUploadDocumentsPositiveUrl = img_url;
                                            } else if (position == 5) {
                                                GlideUtil.loaderCornersImg(mContext, imgCollectionUploadDocumentsBck, UploadHelper.getInstance().getPriUrl(mContext,img_url));
                                                imgCollectionUploadDocumentsBckUrl = img_url;
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
        if(postion ==1){
            UCrop.of(uri, mDestinationUri)
                    .withMaxResultSize(10000, 10000)
                    .withTargetActivity(CropActivity.class)
                    .start(this);
        }else {
            UCrop.of(uri, mDestinationUri)
                    .withAspectRatio(1, 1)
                    .withMaxResultSize(512, 512)
                    .withTargetActivity(CropActivity.class)
                    .start(this);
        }

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
