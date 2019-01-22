package com.haiyin.gczb.my.page;

import android.os.Bundle;

import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.presenter.PayPasswordPresenter;
import com.haiyin.gczb.user.page.SubmitSucceedActivity;
import com.haiyin.gczb.utils.MyUtils;
import com.durian.lib.base.BaseView;
import com.jungly.gridpasswordview.GridPasswordView;

import butterknife.BindView;
import butterknife.OnClick;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.presenter.PayPasswordPresenter;
import com.haiyin.gczb.user.page.SubmitSucceedActivity;
import com.haiyin.gczb.utils.MyUtils;

/**
 * Created
 * by durian
 * 2019/1/9.
 */
public class SetPayPasswordActivity extends BaseActivity implements BaseView {
    PayPasswordPresenter payPasswordPresenter;
    @BindView(R.id.pswView)
    GridPasswordView pswView;

    @OnClick(R.id.btn_set_pay_password_next)
    public void toNext() {
        if (pswView.getPassWord().length() != 6) {
            MyUtils.showShort("请输入支付密码");
            return;
        }
        payPasswordPresenter.setPayPwd(pswView.getPassWord());
    }

    @Override
    public void success(int code, Object data) {
        Bundle  bundle = new Bundle();
        bundle.putString("title","设置交易密码");
        bundle.putString("context","密码设置成功");
        intentJump(this,SubmitSucceedActivity.class,bundle);
        this.finish();
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_pay_password;
    }

    @Override
    public void initView() {
        setTitle("设置交易密码");
        payPasswordPresenter = new PayPasswordPresenter(this);
    }
}
