package com.haiyin.gczb.my.page;

import android.os.Bundle;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/25.
 */
public class CustomerQueryActivity extends BaseActivity {
    @OnClick(R.id.rl_customer_query_contract)
    public void toContract() {
        //合作合同
        Bundle bundle = new Bundle();
        bundle.putString("title","合作合同");
        bundle.putInt("type",8);
        intentJump(this,CustomerListsActivity.class,bundle);
    }

    @OnClick(R.id.rl_customer_query_customer_info)
    public void toCustomerInfo() {
        //客户信息
        Bundle bundle = new Bundle();
        bundle.putString("title","客户信息");
        bundle.putInt("type",1);
        intentJump(this,CustomerListsActivity.class,bundle);
    }

    @OnClick(R.id.rl_customer_query_contract_send_package)
    public void toSendPackage() {
        //发包金额
        Bundle bundle = new Bundle();
        bundle.putString("title","发包金额");
        bundle.putInt("type",9);
        intentJump(this,CustomerListsActivity.class,bundle);
    }

    @OnClick(R.id.rl_customer_query_record)
    public void toRecord() {
        //开票记录和状态
        intentJump(this,MyPagerActivity.class,null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_query;
    }

    @Override
    public void initView() {
        setTitle("客户查询");
    }
}
