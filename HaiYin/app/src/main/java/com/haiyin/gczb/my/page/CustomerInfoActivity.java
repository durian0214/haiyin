package com.haiyin.gczb.my.page;

import android.view.View;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/24.
 */
public class CustomerInfoActivity extends BaseActivity {
    @OnClick(R.id.rl_customer_info_customer)
    public void toCustomer() {
        intentJump(this,CustomerActivity.class,null);
    }

    @OnClick(R.id.rl_customer_info_demand_hall)
    public void toDemandHall() {

    }

    @OnClick(R.id.rl_customer_info_push)
    public void toPush() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_info;
    }

    @Override
    public void initView() {
        setTitle("客户信息");
        setTvRight("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
