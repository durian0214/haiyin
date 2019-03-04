package com.haiyin.gczb.sendPackage.page;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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
public class LookTeamActivity extends BaseActivity implements BaseView {
    SendPackagePresenter sendPackagePresenter;
    GetDataPresenter getDataPresenter;
    @BindView(R.id.img_look_team)
    RoundedImageView img;
    @BindView(R.id.edt_look_team_name)
    EditText edtName;
    @BindView(R.id.edt_look_team_detail)
    EditText edtDetail;
    @BindView(R.id.tv_look_team_starttime)
    TextView tvStartTime;
    @BindView(R.id.tv_look_team_endtime)
    TextView tvEndTime;
    @BindView(R.id.edt_look_team_price)
    EditText edtPrice;
    @BindView(R.id.sp_look_team_industry)
    Spinner spIndustry;
    @BindView(R.id.sp_look_team_pay)
    Spinner spPay;
    @BindView(R.id.edt_look_team_address)
    EditText edtAddress;
    @BindView(R.id.edt_look_team_code)
    EditText edtCode;
    @BindView(R.id.cb_look_team)
    CheckBox cb;
    String imgUrl;
    private List<IndustryEntity.DataBean> industryList;

//    @OnClick(R.id.btn_look_team_code)
//    public void getCode() {
//        tvCode.setText(MyUtils.getNumLargeLetter());
//    }

    @OnClick(R.id.btn_look_team)
    public void send() {
        String title = edtName.getText().toString();
        String summary = edtDetail.getText().toString();
        String beginTime = tvStartTime.getText().toString();
        String endTime = tvEndTime.getText().toString();
        String price = edtPrice.getText().toString();
        String address = edtAddress.getText().toString();
        String code = edtCode.getText().toString();
        if ( price.isEmpty()
                ) {
            MyUtils.showShort("请输入金额");
            return;
        }
        if ( spIndustry.getSelectedItemPosition()==0
                ) {
            MyUtils.showShort("请选择行业");
            return;
        }
//        if (title.isEmpty()
//                || beginTime.isEmpty()
//                || endTime.isEmpty()
//                || price.isEmpty()
//                || address.isEmpty()) {
//            MyUtils.showShort("完善信息");
//            return;
//        }

        if (!cb.isChecked()) {
            MyUtils.showShort("是否同意协议");
            return;
        }
        sendPackagePresenter.publishProject(2,
                imgUrl,
                title,
                summary,
                beginTime,
                endTime,
                Arith.mul(Integer.valueOf(price), 100),
                industryList.get(spIndustry.getSelectedItemPosition() - 1).getIndustryId(),
                spPay.getSelectedItemPosition(),
                Constant.cityName,
                address,
                code,
                false,
                null,
                null,
                null,
                null
                ,mContext
        );
    }

    @OnClick(R.id.tv_look_team_starttime)
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

    @OnClick(R.id.tv_look_team_endtime)
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

    @OnClick(R.id.img_look_team)
    public void getPic() {
        if (MyPermissions.isOpenWrite(this)) {
            if (MyPermissions.isOpenCamera(this)) {
                final String imgName = "cropImage" + MyUtils.getNowTime() + ".jpeg";
                mDestinationUri = Uri.fromFile(new File(this.getCacheDir(), imgName));

                PopupUtil.getInstence().showCamera(this, new PopupUtil.OnSelectedListener() {
                    @Override
                    public void OnSelected(View v, final int position) { new Handler().post(new Runnable(){
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

                        objectKey = ObjectKeyUtils.getIntance().getProjects();


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
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_look_team;
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
        tvStartTime.setText(MyUtils.getTimeTo1());
        tvEndTime.setText(MyUtils.getTimeTo30());
        setTitle("找团队");
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
