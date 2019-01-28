package com.haiyin.gczb.user.page;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.user.entity.LoginEntity;
import com.haiyin.gczb.user.event.RegisterUserEvent;
import com.haiyin.gczb.user.presenter.LoginPresenter;
import com.haiyin.gczb.user.presenter.SendCodePresenter;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.SharedPreferencesUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.var.SharedPreferencesVar;
import com.durian.lib.base.BaseView;
import com.durian.lib.bus.RxBus;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Created
 * by durian
 * 2019/1/4.
 * 登录
 */
public class LoginActivity extends BaseActivity implements BaseView {
    private LoginPresenter loginPresenter;
    private SendCodePresenter sendCodePresenter;
    @BindView(R.id.edt_login_account)
    EditText edtAccount;
    @BindView(R.id.edt_login_password)
    EditText edtPassword;
    @BindView(R.id.btn_login_send)
    Button btnGetyzm;

    @OnClick(R.id.btn_login)
    public void toLogin() {
        loginPresenter.doLogin(edtAccount.getText().toString(), edtPassword.getText().toString());
    }

    @OnClick(R.id.btn_login_send)
    public void sendCode() {

            sendCodePresenter.sendCode(edtAccount.getText().toString(), 1);

    }

    @Override
    public void success(int code, Object data) {
        if (code == ApiConfig.LOGIN) {
            MyUtils.showShort("登录成功");
            LoginEntity entity = (LoginEntity) data;
            SharedPreferencesUtils.put(this, SharedPreferencesVar.TOKEN, entity.getData().getToken());
            SharedPreferencesUtils.put(this, SharedPreferencesVar.USER_ID, entity.getData().getUserId());
            int type = entity.getData().getRoleType();
            SharedPreferencesUtils.put(this, SharedPreferencesVar.userType,type);
            this.finish();
        }if (code == ApiConfig.SEND_CODE) {
            countDown();
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        isShowTitle(false);
        sendCodePresenter = new SendCodePresenter(this);
        loginPresenter = new LoginPresenter(this);

        RxBus.getInstance().subscribe(RegisterUserEvent.class, new Consumer<RegisterUserEvent>() {
            @Override
            public void accept(RegisterUserEvent event) {
                toChooseUserType();
            }
        });
    }

    private void toChooseUserType(){
        Bundle b  = new Bundle();
        b.putString("phone",edtAccount.getText().toString());
        b.putString("code",edtPassword.getText().toString());
        intentJump(this,ChooseUserTypeActivity.class,b);
    }


    //倒计时
    public void countDown() {
        new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnGetyzm.setText("重新发送(" + millisUntilFinished / 1000 + ")");
                btnGetyzm.setEnabled(false);//按钮处于禁用状态
                btnGetyzm.setClickable(false);//不能监听
                btnGetyzm.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.color_444444));
            }

            @Override
            public void onFinish() {
                btnGetyzm.setTextColor(ContextCompat.getColor(LoginActivity.this, R.color.color_00C1B6));
                btnGetyzm.setEnabled(true);
                btnGetyzm.setClickable(true);
                btnGetyzm.setText("获取验证码");
            }
        }.start();
    }


}
