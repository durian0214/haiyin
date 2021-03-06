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
import android.widget.TextView;
import android.widget.Toast;

import com.durian.lib.glide.GlideUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.presenter.EnterprisePresenter;
import com.haiyin.gczb.user.entity.IndustryEntity;
import com.haiyin.gczb.user.entity.RegistEntity;
import com.haiyin.gczb.user.entity.SalesEntity;
import com.haiyin.gczb.user.presenter.GetDataPresenter;
import com.haiyin.gczb.user.presenter.RegistPresenter;
import com.haiyin.gczb.utils.ImageDisposeUtil;
import com.haiyin.gczb.utils.MyPermissions;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.ObjectKeyUtils;
import com.haiyin.gczb.utils.UploadHelper;
import com.haiyin.gczb.utils.dialog.PopupUtil;
import com.haiyin.gczb.utils.dialog.PromptDialog;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.pic.CropActivity;
import com.haiyin.gczb.utils.pic.OnPictureSelectedListener;
import com.durian.lib.base.BaseView;
import com.durian.lib.utils.LogUtil;
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
 * 企业用户信息
 */
public class EnterpriseInformationActivity extends BaseActivity implements BaseView {


    RegistPresenter registPresenter;
    GetDataPresenter getDataPresenter;
    EnterprisePresenter enterprisePresenter;

    //头像
    @BindView(R.id.img_enterprise_information_icon)
    RoundedImageView imgIcon;
    //公司名称
    @BindView(R.id.edt_enterprise_information_name)
    EditText edtName;
    //公司联系方式
    @BindView(R.id.edt_enterprise_information_contact)
    EditText edtContact;
    //联系人姓名
    @BindView(R.id.edt_enterprise_information_contact_name)
    EditText edtContactName;
    //联系人电话
    @BindView(R.id.edt_enterprise_information_contact_phone)
    EditText edtContactPhone;
    //职位
    @BindView(R.id.sp_enterprise_information_position)
    Spinner spPosition;
    //业务员
    @BindView(R.id.sp_enterprise_information_salesman)
    Spinner spSalesman;
    //行业
    @BindView(R.id.sp_enterprise_information_industry)
    Spinner spIndustry;
    //收款人姓名
    @BindView(R.id.edt_enterprise_information_collection_name)
    EditText edtCollectionName;
    //银行卡号
    @BindView(R.id.edt_enterprise_information_bank_code)
    EditText edtBankCode;
    //收款人身份证号
    @BindView(R.id.edt_enterprise_information_collection_codeid)
    EditText edtCollectionCodeid;
    //银行名称
    @BindView(R.id.edt_enterprise_information_bank_name)
    EditText edtBankName;
    //营业执照
    @BindView(R.id.img_enterprise_information_business_license)
    RoundedImageView imgBusinessLicense;
    //上传证件
    @BindView(R.id.img_enterprise_information_upload_documents_positive)
    RoundedImageView imgUploadDocumentsPositive;
    @BindView(R.id.img_enterprise_information_upload_documents_back)
    RoundedImageView imgUploadDocumentsBacl;
    //收款人身份证扫描件
    @BindView(R.id.img_enterprise_information_collection_upload_documents_positive)
    RoundedImageView imgCollectionUploadDocumentsPositive;
    @BindView(R.id.img_enterprise_information_collection_upload_documents_back)
    RoundedImageView imgCollectionUploadDocumentsBck;

    int postion;
    String imgIconUrl;
    String imgBusinessLicenseUrl;
    String imgUploadDocumentsPositiveUrl;
    String imgUploadDocumentsBaclUrl;
    String imgCollectionUploadDocumentsPositiveUrl;
    String imgCollectionUploadDocumentsBckUrl;
    //    0 imgIconUrl
//    1 imgBusinessLicenseUrl
//    2 imgUploadDocumentsPositiveUrl
//    3 imgUploadDocumentsBaclUrl
//    4 imgCollectionUploadDocumentsPositiveUrl
//    5 imgCollectionUploadDocumentsBckUrl

    @OnClick(R.id.img_enterprise_information_icon)
    public void toIcon() {
        pic(0);
    }

    @OnClick(R.id.img_enterprise_information_business_license)
    public void toBusinessLicenseUrl() {
        pic(1);
    }

    @OnClick(R.id.img_enterprise_information_upload_documents_positive)
    public void toUploadDocumentsPositiveUrl() {
        pic(2);
    }

    @OnClick(R.id.img_enterprise_information_upload_documents_back)
    public void toUploadDocumentsBaclUrl() {
        pic(3);
    }

    @OnClick(R.id.img_enterprise_information_collection_upload_documents_positive)
    public void toCollectionUploadDocumentsPositiveUrl() {
        pic(4);
    }

    @OnClick(R.id.img_enterprise_information_collection_upload_documents_back)
    public void toCollectionUploadDocumentsBckUrl() {
        pic(5);
    }


    String phone;
    String code;
    int roleType;
    int doType;//1:注册 2:添加

    private List<SalesEntity.DataBean> salesList;
    private List<IndustryEntity.DataBean> industryList;

    //提交
    @OnClick(R.id.btn_enterprise_information)
    public void toSend() {
        if (doType == 1) {
            sendData(false);
        } else if (doType == 2) {
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
            if (spSalesman.getSelectedItemPosition() == 0) {
                MyUtils.showShort("请选择业务员");
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

            enterprisePresenter.addSubCompany(contactPhone,
                    imgIconUrl,
                    contactName,
                    spPosition.getSelectedItemPosition(),
                    salesList.get(spPosition.getSelectedItemPosition() - 1).getSalesId(),
                    name,
                    contact,
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

    }

    boolean b;

    public void sendData(boolean type) {
        b = type;
        String name = edtName.getText().toString();
        String contact = edtContact.getText().toString();
        String contactName = edtContactName.getText().toString();
        String contactPhone = edtContactPhone.getText().toString();
        String collectionName = edtCollectionName.getText().toString();
        String bankCode = edtBankCode.getText().toString();
        String bankName = edtBankName.getText().toString();
        String collectionCodeid = edtCollectionCodeid.getText().toString();
        if (type) {

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
            if (spSalesman.getSelectedItemPosition() == 0) {
                MyUtils.showShort("请选择业务员");
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

            registPresenter.regist(contactPhone,
                    code,
                    imgIconUrl,
                    contactName,
                    spPosition.getSelectedItemPosition(),
                    roleType,
                    salesList.get(spSalesman.getSelectedItemPosition() - 1).getSalesId(),
                    type,
                    name,
                    contact,
                    industryList.get(spIndustry.getSelectedItemPosition() - 1).getIndustryId(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null, mContext);
        } else {
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
            if (spSalesman.getSelectedItemPosition() == 0) {
                MyUtils.showShort("请选择业务员");
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


            registPresenter.regist(contactPhone,
                    code,
                    imgIconUrl,
                    contactName,
                    spPosition.getSelectedItemPosition(),
                    roleType,
                    salesList.get(spPosition.getSelectedItemPosition() - 1).getSalesId(),
                    type,
                    name,
                    contact,
                    industryList.get(spIndustry.getSelectedItemPosition() - 1).getIndustryId(),
                    imgBusinessLicenseUrl,
                    collectionCodeid,
                    imgUploadDocumentsPositiveUrl,
                    imgUploadDocumentsBaclUrl,
                    collectionName,
                    bankCode,
                    bankName,
                    imgCollectionUploadDocumentsPositiveUrl,
                    imgCollectionUploadDocumentsBckUrl, mContext);
        }

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
        if (code == ApiConfig.SALES) {
            SalesEntity entity = (SalesEntity) data;
            salesList = entity.getData();
            List<String> dataList = new ArrayList<>();
            dataList.add("选择业务员");
            for (int i = 0; i < salesList.size(); i++) {
                dataList.add(salesList.get(i).getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, R.layout.item_sp,
                    dataList);
            spSalesman.setAdapter(adapter);
        } else if (code == ApiConfig.INDUSTRY) {
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
        } else if (code == ApiConfig.REGIST) {
            RegistEntity entity = (RegistEntity) data;
            //注册成功
            if (b) {
                PromptDialog.getIntance().showPromptDialog(mContext, "已帮您通知了业务员",
                        "业务员将在第一时间帮您填写资料您也可以拨打电话联系您的业务员", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString("title", "");
                                bundle.putString("context", "审核通过后会在系统消息给您发送通过消息\n" +
                                        "\n" +
                                        "请耐心等待");
                                intentJump(mContext, SubmitSucceedActivity.class, bundle);
                                mContext.finish();
                            }
                        });
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("title", "");
                bundle.putString("context", "审核通过后会在系统消息给您发送通过消息\n" +
                        "\n" +
                        "请耐心等待");
                intentJump(this, SubmitSucceedActivity.class, bundle);
                this.finish();
            }


        } else if (code == ApiConfig.ADD_SUB_COMPANY) {
            //添加成功
            BaseEntity entity = (BaseEntity) data;
            MyUtils.showShort("添加成功");
            setResult(120);
            this.finish();
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enterprise_information;
    }

    @Override
    public void initView() {
        registPresenter = new RegistPresenter(this);
        getDataPresenter = new GetDataPresenter(this);
        enterprisePresenter = new EnterprisePresenter(this);
        try {
            doType = 1;
            Bundle b = getIntent().getBundleExtra("bundle");
            phone = b.getString("phone");
            code = b.getString("code");
            roleType = b.getInt("roleType");
            if (roleType == 1) {
               setTitle("企业用户");
            } else if (roleType == 2) {
                setTitle("个体户用户");
            }
            edtContactPhone.setText(phone);
            setTvRight("业务员帮忙填写", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendData(true);
                }
            });
        } catch (NullPointerException e) {
            doType = 2;
        }
        setTitle("");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.item_sp,
                getResources().getStringArray(R.array.member));
        spPosition.setAdapter(adapter);
        getDataPresenter.sales(mContext);
        getDataPresenter.industry();
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
                        new Handler().post(new Runnable() {
                            public void run() {
                                MyUtils.photoUtil(mContext, position, mTempPhotoPath);
                            }
                        });
                    }
                });

                mOnPictureSelectedListener = new OnPictureSelectedListener() {
                    @Override
                    public void onPictureSelected(Uri fileUri, final Bitmap bitmap) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
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
                                                        GlideUtil.loaderCornersImg(mContext, imgBusinessLicense, UploadHelper.getInstance().getPriUrl(mContext, img_url));
                                                        imgBusinessLicenseUrl = img_url;
                                                    } else if (position == 2) {
                                                        GlideUtil.loaderCornersImg(mContext, imgUploadDocumentsPositive, UploadHelper.getInstance().getPriUrl(mContext, img_url));
                                                        imgUploadDocumentsPositiveUrl = img_url;
                                                    } else if (position == 3) {
                                                        GlideUtil.loaderCornersImg(mContext, imgUploadDocumentsBacl, UploadHelper.getInstance().getPriUrl(mContext, img_url));
                                                        imgUploadDocumentsBaclUrl = img_url;
                                                    } else if (position == 4) {
                                                        GlideUtil.loaderCornersImg(mContext, imgCollectionUploadDocumentsPositive, UploadHelper.getInstance().getPriUrl(mContext, img_url));
                                                        imgCollectionUploadDocumentsPositiveUrl = img_url;
                                                    } else if (position == 5) {
                                                        GlideUtil.loaderCornersImg(mContext, imgCollectionUploadDocumentsBck, UploadHelper.getInstance().getPriUrl(mContext, img_url));
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
                        }).start();


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
