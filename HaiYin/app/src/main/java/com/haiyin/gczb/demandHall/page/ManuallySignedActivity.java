package com.haiyin.gczb.demandHall.page;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.durian.lib.base.BaseView;
import com.github.gcacace.signaturepad.views.SignaturePad;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.demandHall.entity.SignProjectEntity;
import com.haiyin.gczb.demandHall.presenter.FaceIdPresenter;
import com.haiyin.gczb.utils.ImageDisposeUtil;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.ObjectKeyUtils;
import com.haiyin.gczb.utils.UploadHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/25.
 */
public class ManuallySignedActivity extends BaseActivity implements BaseView{
    FaceIdPresenter faceIdPresenter;
    @BindView(R.id.spad_manually_signed)
    SignaturePad spad;
    String token;
    String id;
    @OnClick(R.id.btn_manually_signed_submit)
    public void toSubmit(){
        Bitmap b = spad.getSignatureBitmap();
       String objectKey = ObjectKeyUtils.getIntance().getSignname();

        UploadHelper.getInstance().upImagePri(mContext, new UploadHelper.OssUpCallback() {
            @Override
            public void successImg(final String img_url) {
                url = img_url;
                faceIdPresenter.signProject(token,id,url,mContext);
            }

            @Override
            public void successVideo(String video_url) {

            }

            @Override
            public void inProgress(long progress, long zong) {

            }
        }, objectKey, ImageDisposeUtil.Bitmap2Bytes(b));
    }
    @OnClick(R.id.btn_manually_signed_clean)
    public void toClean(){
        spad.clear();
    }

    String url;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_manually_signed;
    }

    @Override
    public void initView() {
        faceIdPresenter = new FaceIdPresenter(this);
        token = getIntent().getBundleExtra("bundle").getString("token");
        id = getIntent().getBundleExtra("bundle").getString("id");
        setTitle("网签");
    }

    @Override
    public void success(int code, Object data) {
        SignProjectEntity entity = (SignProjectEntity) data;
        if(entity.getData()==null){
            Bundle bundle = new Bundle();
            bundle.putString("str",entity.getEm());
            bundle.putInt("type",0);
            intentJump(this,FaceRecognitionActivity.class,bundle);
        }else {
            Bundle bundle = new Bundle();
            List<String> lists = new ArrayList<>();

            lists.add(UploadHelper.getInstance().getPriUrl(this, entity.getData().getFrameSignPdf()));
            lists.add(UploadHelper.getInstance().getPriUrl(this, entity.getData().getOrderClearSignPdf()));
            lists.add(UploadHelper.getInstance().getPriUrl(this, entity.getData().getOrderSignPdf()));

            bundle.putStringArrayList("lists", (ArrayList<String>) lists);
            intentJump(this,PDFActivity.class,bundle);
        }
        this.finish();
    }

    @Override
    public void netError(String msg) {
        Bundle bundle = new Bundle();
                bundle.putString("str",msg);
                bundle.putInt("type",1);
        intentJump(this,FaceRecognitionActivity.class,bundle);
        this.finish();
    }
}
