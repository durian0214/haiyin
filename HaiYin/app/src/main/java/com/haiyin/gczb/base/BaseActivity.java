package com.haiyin.gczb.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiyin.gczb.R;
import com.haiyin.gczb.utils.MyPermissions;
import com.haiyin.gczb.utils.MyUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created
 * by durian
 * 2018/12/19.
 */
public abstract class BaseActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    public   Activity mContext;
    protected Unbinder mBinder;
    TextView tvTitle;
    RelativeLayout rlTitle;
    TextView tvRight;
    ImageButton imgBack;
    LinearLayout llBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(getLayoutId());
        tvTitle = findViewById(R.id.tv_base_title);
        imgBack = findViewById(R.id.imgb_base_back);

        llBack = findViewById(R.id.ll_back);
        rlTitle = findViewById(R.id.rl_base_title);
        tvRight = findViewById(R.id.tv_base_right);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.finish();
            }
        });
        //绑定初始化ButterKnife
        mBinder= ButterKnife.bind(this);
        mContext =this;

        initView();
    }

    /**
     * 是否显示title
     *
     * @param isShow true：显示
     *               false：不显示
     */
    protected void isShowTitle(boolean isShow) {
        if (isShow) {
            rlTitle.setVisibility(View.VISIBLE);
        } else {
            rlTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右边文字以及监听
     *
     * @param name
     * @param listener
     */
    protected void setTvRight(String name, View.OnClickListener listener) {
        if (!TextUtils.isEmpty(name)) {
            tvRight.setText(name);
        }
        if (listener != null) {
            tvRight.setOnClickListener(listener);
        }
    }

    /**
     * 设置title
     *
     * @param title
     */
    protected void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.getActivityManager().popActivity(this);

    }
    /**
     * 获取layout ID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化view
     */
    public abstract void initView();
    /**
     * 跳转
     *
     * @param context
     * @param activity
     * @param bundle
     */
    public void intentJump(Activity context, Class<?> activity, Bundle bundle) {
        Intent intent = new Intent(context, activity);
        if (bundle != null) {
            intent.putExtra("bundle", bundle);
        }
        startActivity(intent);
    }
    //权限回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            switch (requestCode) {
                case MyPermissions.CAMERA_PERMISSIONS:
                    MyUtils.showShort("该功能需要在设置中打开相机权限");
                    break;
                case MyPermissions.WRITE_PERMISSIONS:
                    MyUtils.showShort("该功能需要在设置中打开读写权限");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityManager.getActivityManager().pushActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
