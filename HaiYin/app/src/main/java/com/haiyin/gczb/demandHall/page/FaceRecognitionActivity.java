package com.haiyin.gczb.demandHall.page;

import android.app.Activity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.haiyin.gczb.base.ActivityManager;
import com.haiyin.gczb.base.BaseActivity;

import com.haiyin.gczb.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/4.
 */
public class FaceRecognitionActivity extends BaseActivity {
    @BindView(R.id.img_face_recognition)
    ImageView img;
    @BindView(R.id.tv_face_recognition)
    TextView tv;

    @OnClick(R.id.btn_face_recognition)
    public void to() {
        this.finish();
    }

    @OnClick(R.id.btn_face_recognition_back)
    public void toOut() {
        this.finish();
    }

    @BindView(R.id.btn_face_recognition)
    Button btn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_face_recognition;
    }

    @Override
    public void initView() {
        String str = getIntent().getBundleExtra("bundle").getString("str");
        int type = getIntent().getBundleExtra("bundle").getInt("type");
        tv.setText(str);
        if (type == 0) {
            //成功
            img.setBackgroundResource(R.mipmap.face_recognition_successful);
            btn.setText("查看订单");
        } else {
            img.setBackgroundResource(R.mipmap.face_recognition_failure);
        }
        setTitle("人脸识别");

    }
}
