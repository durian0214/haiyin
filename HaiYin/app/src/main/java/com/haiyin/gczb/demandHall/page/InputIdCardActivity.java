package com.haiyin.gczb.demandHall.page;

import com.haiyin.gczb.base.BaseActivity;
import com.durian.lib.base.BaseView;

import com.haiyin.gczb.R;

/**
 * Created
 * by durian
 * 2019/1/4.
 */
public class InputIdCardActivity  extends BaseActivity implements BaseView{
    @Override
    public void success(int code, Object data) {

    }

    @Override
    public void netError(String msg) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_input_id_card;
    }

    @Override
    public void initView() {
        setTitle("输入身份");
    }
}
