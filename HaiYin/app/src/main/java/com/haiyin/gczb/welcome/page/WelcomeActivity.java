package com.haiyin.gczb.welcome.page;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.MainActivity;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.user.entity.GetVersionEntity;
import com.haiyin.gczb.user.presenter.GetVersionPresenter;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.SharedPreferencesUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.var.SharedPreferencesVar;


/**
 * Created by durian on 2017/11/2
 * 欢迎界面
 */

public class WelcomeActivity extends BaseActivity implements BaseView {
    private GetVersionPresenter getVersionPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        isShowTitle(false);
        getVersionPresenter = new GetVersionPresenter(this);
        getVersionPresenter.getVersion(mContext);
    }


    @Override
    public void success(int code, Object data) {
        if (code == ApiConfig.APP_UPDATE) {
            final GetVersionEntity entity = (GetVersionEntity) data;
            if (entity.getData().getAppPackage() != null) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                if (entity.getData().getUpdateType() == 1) {
                    alertDialogBuilder
                            .setTitle(entity.getData().getVersionName())
                            .setMessage(entity.getData().getConfigInfo())
                            .setPositiveButton("更新", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Uri uri = Uri.parse(entity.getData().getAppPackage());
//                                    Uri uri = Uri.parse("https://www.baidu.com");
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    isJump();
                                }
                            })
                            .create();
                } else if (entity.getData().getUpdateType() == 3) {
                    alertDialogBuilder
                            .setTitle(entity.getData().getVersionName())
                            .setMessage(entity.getData().getConfigInfo())
                            .setPositiveButton("更新", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Uri uri = Uri.parse(entity.getData().getAppPackage());
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    System.exit(0);
                                }
                            })
                            .create();
                }
                alertDialogBuilder.show();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        isJump();
                    }
                }).start();
            }
        }
    }

    public void isJump() {
        if ((boolean) SharedPreferencesUtils.get(mContext, SharedPreferencesVar.FIRST_LOGIN, true)) {
            SharedPreferencesUtils.put(mContext, SharedPreferencesVar.FIRST_LOGIN, false);
            intentJump(mContext, GuideActivity.class, null);
        } else {
            intentJump(mContext, MainActivity.class, null);
        }
        mContext.finish();
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }
}
