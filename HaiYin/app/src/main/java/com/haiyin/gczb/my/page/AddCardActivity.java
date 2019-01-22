package com.haiyin.gczb.my.page;

import com.haiyin.gczb.base.BaseActivity;
import com.durian.lib.base.BaseView;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

/**
 * Created
 * by durian
 * 2019/1/9.
 */
public class AddCardActivity extends BaseActivity implements BaseView {
    @Override
    public void success(int code, Object data) {

    }

    @Override
    public void netError(String msg) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_card;
    }

    @Override
    public void initView() {
        setTitle("添加银行卡");
    }
}
