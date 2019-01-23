package com.haiyin.gczb.user.page;

import android.os.Bundle;
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

    @OnClick(R.id.btn_login)
    public void toLogin() {
        loginPresenter.doLogin(edtAccount.getText().toString(), edtPassword.getText().toString());
    }

    @OnClick(R.id.tv_login_send)
    public void sendCode() {
        sendCodePresenter.sendCode(edtAccount.getText().toString(),1);
    }

    @Override
    public void success(int code, Object data) {
        if (code == ApiConfig.LOGIN) {
            LoginEntity entity = (LoginEntity) data;
            SharedPreferencesUtils.put(this, SharedPreferencesVar.TOKEN, entity.getData().getToken());
            int type = entity.getData().getRoleType();
            SharedPreferencesUtils.put(this, SharedPreferencesVar.userType,type);
            this.finish();
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
        //企业
        edtAccount.setText("13000000001");
        edtPassword.setText("03930");
        //个体户
//        edtAccount.setText("13000000003");
//        edtPassword.setText("51835");
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
}
