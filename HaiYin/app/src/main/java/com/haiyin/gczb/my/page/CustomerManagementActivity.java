package com.haiyin.gczb.my.page;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/24.
 */
public class CustomerManagementActivity extends BaseActivity{
    @OnClick(R.id.rl_customer_management_paper)
    public void toPaper(){

    }
    @OnClick(R.id.rl_customer_management_customer_info)
    public void toInfo(){
        intentJump(this,CustomerInfoActivity.class,null);
    }
    @OnClick(R.id.rl_customer_management_customer_query)
    public void toQuery(){

    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_management;
    }

    @Override
    public void initView() {
        setTitle("客户管理");
    }
}
