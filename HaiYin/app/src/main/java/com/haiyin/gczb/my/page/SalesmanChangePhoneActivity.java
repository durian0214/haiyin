package com.haiyin.gczb.my.page;

import android.widget.EditText;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.user.entity.SendCodeEntity;
import com.haiyin.gczb.user.presenter.SalesPresenter;
import com.haiyin.gczb.user.presenter.SendCodePresenter;
import com.haiyin.gczb.utils.MyUtils;
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
            MyUtils.showShort(entity.getEm());
        }else if(code == ApiConfig.SEND_CODE){
            BaseEntity entity = (BaseEntity) data;
            MyUtils.showShort(entity.getEm());
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
        sendCodePresenter = new SendCodePresenter(this);
        salesPresenter = new SalesPresenter(this);

    }
}
