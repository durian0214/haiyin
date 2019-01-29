package com.haiyin.gczb.my.page;

import android.os.Bundle;
import android.widget.EditText;

import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.presenter.PayPasswordPresenter;
import com.haiyin.gczb.user.page.SubmitSucceedActivity;
import com.haiyin.gczb.user.presenter.SendCodePresenter;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.durian.lib.base.BaseView;

import butterknife.BindView;
import butterknife.OnClick;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.presenter.PayPasswordPresenter;
import com.haiyin.gczb.user.page.SubmitSucceedActivity;
import com.haiyin.gczb.user.presenter.SendCodePresenter;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.http.ApiConfig;

/**
 * Created
 * by durian
 * 2019/1/9.
 */
public class ChangePayPasswordActivity extends BaseActivity implements BaseView {
    PayPasswordPresenter payPasswordPresenter;
    SendCodePresenter sendCodePresenter;
    @BindView(R.id.edt_change_pay_password_phone)
    EditText edtPhone;
    @BindView(R.id.edt_change_pay_password_code)
    EditText edtCode;
    @BindView(R.id.edt_change_pay_password_new)
    EditText edtNew;
    @BindView(R.id.edt_change_pay_password_confirm)
    EditText edtConfirm;

    @OnClick(R.id.edt_change_pay_password_phone)
    public void toSend() {
        if(edtPhone.getText().toString().length()!=11){
            MyUtils.showShort("请输入手机号");
            return;
        }
        sendCodePresenter.sendCode(edtPhone.getText().toString(),2,mContext);
    }

    @OnClick(R.id.btn_change_pay_password_submit)
    public void toSubmit() {
        if(edtPhone.getText().toString().length()!=11){
            MyUtils.showShort("请输入手机号");
            return;
        }
        payPasswordPresenter.modifyPayPwd(edtPhone.getText().toString(),
                edtCode.getText().toString(),
                edtNew.getText().toString(),
                edtConfirm.getText().toString(),mContext);
    }

    @Override
    public void success(int code, Object data) {
        if(code == ApiConfig.SEND_CODE){
            MyUtils.showShort("短信已发送");
        }else if(code==ApiConfig.MODIFY_PAY_PWD){
            Bundle bundle = new Bundle();
            bundle.putString("title","修改交易密码");
            bundle.putString("context","密码修改成功");
            intentJump(this,SubmitSucceedActivity.class,bundle);
            this.finish();
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_pay_password;
    }

    @Override
    public void initView() {
        setTitle("修改交易密码");
        payPasswordPresenter = new PayPasswordPresenter(this);
        sendCodePresenter = new SendCodePresenter(this);
    }
}
