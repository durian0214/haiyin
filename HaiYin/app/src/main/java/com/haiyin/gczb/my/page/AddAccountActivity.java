package com.haiyin.gczb.my.page;

import android.widget.EditText;

import com.haiyin.gczb.base.BaseActivity;
import com.durian.lib.base.BaseView;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.presenter.AccountPresenter;
import com.haiyin.gczb.utils.MyUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/9.
 */
public class AddAccountActivity extends BaseActivity implements BaseView {
    AccountPresenter accountPresenter;
    @BindView(R.id.edt_add_account_name)
    EditText edtName;

    @BindView(R.id.edt_add_account_phone)
    EditText edtPhone;

    @OnClick(R.id.btn_add_account)
    public void add() {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        if (name.isEmpty() || phone.isEmpty()) {
            MyUtils.showShort("请完善资料");
            return;
        }
        accountPresenter.addAccount(name, phone,mContext);
    }

    @Override
    public void success(int code, Object data) {
        BaseEntity entity = (BaseEntity) data;
        MyUtils.showShort("添加成功");
            setResult(120);
        this.finish();
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_account;
    }

    @Override
    public void initView() {
        setTitle("添加用户");
        accountPresenter = new AccountPresenter(this);
    }
}
