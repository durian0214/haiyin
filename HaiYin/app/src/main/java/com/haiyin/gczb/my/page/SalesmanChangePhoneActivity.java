package com.haiyin.gczb.my.page;

import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.EditText;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.user.entity.SendCodeEntity;
import com.haiyin.gczb.user.page.LoginActivity;
import com.haiyin.gczb.user.presenter.SalesPresenter;
import com.haiyin.gczb.user.presenter.SendCodePresenter;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.UserUtils;
import com.haiyin.gczb.utils.http.ApiConfig;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/24.
 */
public class SalesmanChangePhoneActivity extends BaseActivity implements BaseView {
    SalesPresenter salesPresenter;
    SendCodePresenter sendCodePresenter;
    @BindView(R.id.edt_salesman_change_phone_phone)
    EditText edtPhone;
    @BindView(R.id.edt_salesman_change_phone_code)
    EditText edtCode;
    @BindView(R.id.edt_salesman_change_phone_new_phone)
    EditText edtNewPhone;
    @BindView(R.id.btn_salesman_change_phone_getcode)
    Button btnGetyzm;
    @OnClick(R.id.btn_salesman_change_phone_getcode)
    public void toGetCode() {
        String phone = edtPhone.getText().toString();
        if (phone.isEmpty()) {
            MyUtils.showShort("请输入手机号");
            return;
        }
        sendCodePresenter.sendCode(phone, 2,mContext);
    }

    @OnClick(R.id.btn_salesman_information_submit)
    public void toSubmit() {
        String newPhone = edtNewPhone .getText().toString();
        String code = edtCode .getText().toString();
        if(newPhone.isEmpty()){
            MyUtils.showShort("请输入新手机号");
            return;
        }
        if(code.isEmpty()){
            MyUtils.showShort("请输入验证码");
            return;
        }
        salesPresenter.salesModifyInfo(newPhone,code,0,null
                ,null,null,null,null,null,null,mContext);
    }

    @Override
    public void success(int code, Object data) {
        if(code == ApiConfig.SEND_CODE){
            SendCodeEntity entity = (SendCodeEntity) data;
            MyUtils.showShort("发送成功");
            countDown();
        }else if(code == ApiConfig.SALES_MODIFY_INFO){
            BaseEntity entity = (BaseEntity) data;
            MyUtils.showShort("修改成功");
            this.finish();
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_salesman_change_phone;
    }

    @Override
    public void initView() {
        setTitle("修改手机号");
        edtPhone.setText(UserUtils.getMobile());
        sendCodePresenter = new SendCodePresenter(this);
        salesPresenter = new SalesPresenter(this);

    }
    //倒计时
    public void countDown() {
        new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btnGetyzm.setText("重新发送(" + millisUntilFinished / 1000 + ")");
                btnGetyzm.setEnabled(false);//按钮处于禁用状态
                btnGetyzm.setClickable(false);//不能监听
                btnGetyzm.setTextColor(ContextCompat.getColor(SalesmanChangePhoneActivity.this, R.color.color_444444));
            }

            @Override
            public void onFinish() {
                btnGetyzm.setTextColor(ContextCompat.getColor(SalesmanChangePhoneActivity.this, R.color.color_00C1B6));
                btnGetyzm.setEnabled(true);
                btnGetyzm.setClickable(true);
                btnGetyzm.setText("获取验证码");
            }
        }.start();
    }
}
