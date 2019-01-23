package com.haiyin.gczb.sendPackage.page;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.durian.lib.base.BaseView;
import com.durian.lib.glide.GlideUtil;
import com.durian.lib.utils.LogUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.sendPackage.adapter.ImgsAdapter;
import com.haiyin.gczb.sendPackage.presenter.SendPackagePresenter;
import com.haiyin.gczb.user.entity.IndustryEntity;
import com.haiyin.gczb.user.presenter.GetDataPresenter;
import com.haiyin.gczb.utils.Arith;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.ImageDisposeUtil;
import com.haiyin.gczb.utils.MyPermissions;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.ObjectKeyUtils;
import com.haiyin.gczb.utils.UploadHelper;
import com.haiyin.gczb.utils.dialog.PopupUtil;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.pic.CropActivity;
import com.haiyin.gczb.utils.pic.OnPictureSelectedListener;
import com.haiyin.gczb.utils.view.MyGridView;
import com.kevin.crop.UCrop;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/22.
 */
public class LaborCompanyActivity extends BaseActivity implements BaseView {
    SendPackagePresenter sendPackagePresenter;
    GetDataPresenter getDataPresenter;
    @BindView(R.id.img_labor_company)
    RoundedImageView img;
    @BindView(R.id.edt_labor_company_name)
    EditText edtName;
    @BindView(R.id.edt_labor_company_detail)
    EditText edtDetail;
    @BindView(R.id.tv_labor_company_starttime)
    TextView tvStartTime;
    @BindView(R.id.tv_labor_company_endtime)
    TextView tvEndTime;
    @BindView(R.id.edt_labor_company_price)
    EditText edtPrice;
    @BindView(R.id.sp_labor_company_industry)
    Spinner spIndustry;
    @BindView(R.id.sp_labor_company_pay)
    Spinner spPay;
    @BindView(R.id.edt_labor_company_address)
    EditText edtAddress;
    @BindView(R.id.tv_labor_company_code)
    TextView tvCode;
    @BindView(R.id.gl_labor_company_framework_contract)
    MyGridView gvFrameworkContract;
    @BindView(R.id.gl_labor_company_order_contract)
    MyGridView gvOrderContract;
    @BindView(R.id.gl_labor_company_project_settlement)
    MyGridView gvProjectSettlement;

    private List<String> framworkContractUrls = new ArrayList<>();
    private List<String> orderContractUrls = new ArrayList<>();
    private List<String> projectSettlementUrls = new ArrayList<>();


    private ImgsAdapter framworkContractAdapter;
    private ImgsAdapter orderContractAdapter ;
    private ImgsAdapter projectSettlementAdapter ;
    String imgUrl;
    private List<IndustryEntity.DataBean> industryList;

    @OnClick(R.id.btn_labor_company_code)
    public void getCode() {
        tvCode.setText(MyUtils.getNumLargeLetter());
    }

    @OnClick(R.id.btn_labor_company)
    public void send() {
        sendData(false);
    }

    private void sendData(boolean b) {
        String title = edtName.getText().toString();
        String summary = edtDetail.getText().toString();
        String beginTime = tvStartTime.getText().toString();
        String endTime = tvEndTime.getText().toString();
        String price = edtPrice.getText().toString();
        String address = edtAddress.getText().toString();
        String code = tvCode.getText().toString();
        String framFiles = null;
        String contractFiles = null;
        String clearingFiles = null;
        if (b) {
            if (title.isEmpty() || summary.isEmpty()
                    || beginTime.isEmpty()
                    || endTime.isEmpty()
                    || price.isEmpty()
                    || address.isEmpty()
                    || imgUrl == null) {
                MyUtils.showShort("完善信息");
                return;
            }
        } else {
            if (framworkContractUrls.size() != 0) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < framworkContractUrls.size(); i++) {
                    sb.append(framworkContractUrls.get(i));
                    if (framworkContractUrls.size() != i-1) {
                        sb.append(",");
                    }
                }
                framFiles = sb.toString();
            }
            if (orderContractUrls.size() != 0) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < orderContractUrls.size(); i++) {
                    sb.append(orderContractUrls.get(i));
                    if (orderContractUrls.size() != i-1) {
                        sb.append(",");
                    }
                }
                contractFiles = sb.toString();
            }
            if (projectSettlementUrls.size() != 0) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < projectSettlementUrls.size(); i++) {
                    sb.append(projectSettlementUrls.get(i));
                    if (projectSettlementUrls.size() != i-1) {
                        sb.append(",");
                    }
                }
                clearingFiles = sb.toString();
            }
            if (title.isEmpty() || summary.isEmpty()
                    || beginTime.isEmpty()
                    || endTime.isEmpty()
                    || price.isEmpty()
                    || address.isEmpty()
                    || imgUrl == null
                    || framFiles == null
                    || contractFiles == null
                    || clearingFiles == null) {
                MyUtils.showShort("完善信息");
                return;
            }
        }
        sendPackagePresenter.publishProject(1,
                imgUrl,
                title,
                summary,
                beginTime,
                endTime,
                Arith.mul(Integer.valueOf(price), 100),
                industryList.get(spIndustry.getSelectedItemPosition()-1).getIndustryId()
                , spPay.getSelectedItemPosition(),
                Constant.cityName,
                address,
                code,
                b,
                framFiles,
                contractFiles,
                clearingFiles
        );
    }

    @OnClick(R.id.btn_labor_company_help_fill_in)
    public void helpFillIn() {
        //帮填
        sendData(true);
    }

    @OnClick(R.id.tv_labor_company_framework_contract)
    public void addFrameworkContractImg() {
        //框架合同
        pic(0);
    }

    @OnClick(R.id.tv_labor_company_order_contract)
    public void addOrderContractImg() {
        //订单合同
        pic(1);
    }

    @OnClick(R.id.tv_labor_company_project_settlement)
    public void addProjectSettlementImg() {
        //项目结算
        pic(2);
    }

    @OnClick(R.id.tv_labor_company_starttime)
    public void getStartTime() {
        Calendar calendar = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tvStartTime.setText(year + "." + (monthOfYear + 1) + "." + dayOfMonth);

            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    public void pic(final int position) {
        if (MyPermissions.isOpenWrite(this)) {
            if (MyPermissions.isOpenCamera(this)) {
                final String imgName = "cropImage" + MyUtils.getNowTime() + ".jpeg";
                mDestinationUri = Uri.fromFile(new File(this.getCacheDir(), imgName));

                PopupUtil.getInstence().showCamera(this, new PopupUtil.OnSelectedListener() {
                    @Override
                    public void OnSelected(View v, int position) {
                        MyUtils.photoUtil(mContext, position, mTempPhotoPath);
                    }
                });

                mOnPictureSelectedListener = new OnPictureSelectedListener() {
                    @Override
                    public void onPictureSelected(Uri fileUri, Bitmap bitmap) {

                        String objectKey = "";
                        if (position == 0) {
                            objectKey = ObjectKeyUtils.getIntance().getProjectFrame();
                        } else if (position == 1) {
                            objectKey = ObjectKeyUtils.getIntance().getProjectContract();
                        } else if (position == 2) {
                            objectKey = ObjectKeyUtils.getIntance().getProjectSettlement();
                        }
                        UploadHelper.getInstance().upImagePri(mContext, new UploadHelper.OssUpCallback() {
                            @Override
                            public void successImg(final String img_url) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (position == 0) {
                                            framworkContractAdapter.addImg(img_url);
                                        } else if (position == 1) {
                                            orderContractAdapter.addImg(img_url);
                                        } else if (position == 2) {
                                            projectSettlementAdapter.addImg(img_url);
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
                };
            } else {
                MyPermissions.setCameraPermissions(this);
            }
        } else {
            MyPermissions.setWritePermissions(this);
        }
    }

    @OnClick(R.id.tv_labor_company_endtime)
    public void getEndTime() {
        Calendar calendar = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tvEndTime.setText(year + "." + (monthOfYear + 1) + "." + dayOfMonth);

            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    @OnClick(R.id.img_labor_company)
    public void getPic() {
        if (MyPermissions.isOpenWrite(this)) {
            if (MyPermissions.isOpenCamera(this)) {
                final String imgName = "cropImage" + MyUtils.getNowTime() + ".jpeg";
                mDestinationUri = Uri.fromFile(new File(this.getCacheDir(), imgName));

                PopupUtil.getInstence().showCamera(this, new PopupUtil.OnSelectedListener() {
                    @Override
                    public void OnSelected(View v, int position) {
                        MyUtils.photoUtil(mContext, position, mTempPhotoPath);
                    }
                });

                mOnPictureSelectedListener = new OnPictureSelectedListener() {
                    @Override
                    public void onPictureSelected(Uri fileUri, Bitmap bitmap) {

                        String objectKey = ObjectKeyUtils.getIntance().getProjects();


                        UploadHelper.getInstance().upImagePub(mContext, new UploadHelper.OssUpCallback() {
                            @Override
                            public void successImg(final String img_url) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        LogUtil.e(img_url);
                                        GlideUtil.loaderCornersImg(mContext, img, img_url);
                                        imgUrl = img_url;
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
                };
            } else {
                MyPermissions.setCameraPermissions(this);
            }
        } else {
            MyPermissions.setWritePermissions(this);
        }
    }

    @Override
    public void success(int code, Object data) {
        if (code == ApiConfig.PUBLISH_PROJECT) {
            MyUtils.showShort("发布成功");
            this.finish();
        } else if (code == ApiConfig.INDUSTRY) {
            IndustryEntity entity = (IndustryEntity) data;
            industryList =entity.getData();
            List<String> dataList = new ArrayList<>();
            dataList.add("选择行业");
            for (int i = 0; i < industryList.size(); i++) {
                dataList.add(industryList.get(i).getName());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, R.layout.item_sp,
                    dataList);
            spIndustry.setAdapter(adapter);
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_labor_company;
    }

    @Override
    public void initView() {
        getDataPresenter = new GetDataPresenter(this);
        sendPackagePresenter = new SendPackagePresenter(this);
        List<String> dataList = Arrays.asList(getResources().getStringArray(R.array.pay));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, R.layout.item_sp,
                dataList);
        spPay.setAdapter(adapter);


        framworkContractAdapter = new ImgsAdapter(this, framworkContractUrls);
        orderContractAdapter = new ImgsAdapter(this, orderContractUrls);
        projectSettlementAdapter = new ImgsAdapter(this, projectSettlementUrls);

        gvFrameworkContract.setAdapter(framworkContractAdapter);
        gvOrderContract.setAdapter(orderContractAdapter);
        gvProjectSettlement.setAdapter(projectSettlementAdapter);
        setTitle("劳务公司");
        getDataPresenter.industry();
    }

    /**
     * 图片选择的监听回调
     */
    private OnPictureSelectedListener mOnPictureSelectedListener;
    // 剪切后图像文件
    private Uri mDestinationUri;
    String mTempPhotoPath = Environment.getExternalStorageDirectory() + File.separator + "photo.jpeg";

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