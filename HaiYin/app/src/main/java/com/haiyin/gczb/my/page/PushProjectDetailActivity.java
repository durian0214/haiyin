package com.haiyin.gczb.my.page;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.presenter.CustomerPresenter;
import com.haiyin.gczb.sendPackage.adapter.ImgsAdapter;
import com.haiyin.gczb.user.page.SubmitSucceedActivity;
import com.haiyin.gczb.utils.ImageDisposeUtil;
import com.haiyin.gczb.utils.MyPermissions;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.ObjectKeyUtils;
import com.haiyin.gczb.utils.UploadHelper;
import com.haiyin.gczb.utils.dialog.PopupUtil;
import com.haiyin.gczb.utils.pic.CropActivity;
import com.haiyin.gczb.utils.pic.OnPictureSelectedListener;
import com.haiyin.gczb.utils.view.MyGridView;
import com.kevin.crop.UCrop;

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
 * 2019/1/25.
 */
public class PushProjectDetailActivity extends BaseActivity implements BaseView {
    String id;
    CustomerPresenter customerPresenter;
    private ImgsAdapter framworkContractAdapter;
    private ImgsAdapter orderContractAdapter;
    private ImgsAdapter projectSettlementAdapter;
    private List<String> framworkContractUrls = new ArrayList<>();
    private List<String> orderContractUrls = new ArrayList<>();
    private List<String> projectSettlementUrls = new ArrayList<>();


    @BindView(R.id.gl_push_detail_framework_contract)
    MyGridView gvFrameworkContract;
    @BindView(R.id.gl_push_detail_order_contract)
    MyGridView gvOrderContract;
    @BindView(R.id.gl_push_detail_project_settlement)
    MyGridView gvProjectSettlement;


    @OnClick(R.id.tv_push_detail_framework_contract)
    public void addFrameworkContractImg() {
        //框架合同
        pic(0);
    }

    @OnClick(R.id.tv_push_detail_order_contract)
    public void addOrderContractImg() {
        //订单合同
        pic(1);
    }

    @OnClick(R.id.tv_push_detail_project_settlement)
    public void addProjectSettlementImg() {
        //项目结算
        pic(2);
    }

    @OnClick(R.id.btn_push_detail)
    public void send() {
        String framFiles = null;
        String contractFiles = null;
        String clearingFiles = null;
        if (framworkContractUrls.size() != 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < framworkContractUrls.size(); i++) {
                sb.append(framworkContractUrls.get(i));
                if (framworkContractUrls.size() != i - 1) {
                    sb.append(",");
                }
            }
            framFiles = sb.toString();
        }
        if (orderContractUrls.size() != 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < orderContractUrls.size(); i++) {
                sb.append(orderContractUrls.get(i));
                if (orderContractUrls.size() != i - 1) {
                    sb.append(",");
                }
            }
            contractFiles = sb.toString();
        }
        if (projectSettlementUrls.size() != 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < projectSettlementUrls.size(); i++) {
                sb.append(projectSettlementUrls.get(i));
                if (projectSettlementUrls.size() != i - 1) {
                    sb.append(",");
                }
            }
            clearingFiles = sb.toString();
        }
        if (framFiles == null
                || contractFiles == null
                || clearingFiles == null) {
            MyUtils.showShort("完善信息");
            return;
        }
        customerPresenter.salescompleteProject(id, framFiles, contractFiles, clearingFiles);
    }

    @Override
    public void success(int code, Object data) {
        Bundle bundle = new Bundle();
        bundle.putString("title", "上传协议");
        bundle.putString("context", "提交成功");
        intentJump(this, SubmitSucceedActivity.class, bundle);
        this.finish();
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_push_detail;
    }

    @Override
    public void initView() {
        customerPresenter = new CustomerPresenter(this);
        setTitle("上传协议");
        id = getIntent().getBundleExtra("bundle").getString("id");
        framworkContractAdapter = new ImgsAdapter(this, framworkContractUrls);
        orderContractAdapter = new ImgsAdapter(this, orderContractUrls);
        projectSettlementAdapter = new ImgsAdapter(this, projectSettlementUrls);
        gvFrameworkContract.setAdapter(framworkContractAdapter);
        gvOrderContract.setAdapter(orderContractAdapter);
        gvProjectSettlement.setAdapter(projectSettlementAdapter);
    }


    public void pic(final int position) {
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
