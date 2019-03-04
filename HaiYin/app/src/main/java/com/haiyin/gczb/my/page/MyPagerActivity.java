package com.haiyin.gczb.my.page;

import android.os.Bundle;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.utils.Constant;

import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/10.
 */
public class MyPagerActivity extends BaseActivity {
    @OnClick(R.id.rl_my_pager_invoice_stay)
    public void toInvoiceStay() {
        Bundle b = new Bundle();
        //待开票
        if(Constant.userType==1){
            // 1=未开票 2=已开票
            b.putInt("type" ,1);
            b.putString("title","待开票");
            intentJump(this,MyPagerEnterpriseActivity.class,b);
        }else if(Constant.userType==2){
            b.putInt("type" ,3);
            b.putString("title","待开票");
            intentJump(this,MyPagerPersonalProjectActivity.class,b);
        }else if(Constant.userType==4){
            b.putInt("type" ,6);
            b.putString("title","待开票");
            intentJump(this,CustomerListsActivity.class,b);
        }
    }

    @OnClick(R.id.rl_my_pager_invoice_has)
    public void toInvoiceHas() {
        //已开票
        Bundle b = new Bundle();
        if(Constant.userType==1){
            // 1=未开票 2=已开票
            b.putInt("type" ,2);
            b.putString("title","已开票");
            intentJump(this,MyPagerEnterpriseActivity.class,b);
        }else if(Constant.userType==2){
            b.putInt("type" ,4);
            b.putString("title","已开票");
            intentJump(this,MyPagerPersonalProjectActivity.class,b);
        }else if(Constant.userType==4){
            // 1=未开票 2=已开票
            b.putInt("type" ,7);
            b.putString("title","已开票");
            intentJump(this,CustomerListsActivity.class,b);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_pager;
    }

    @Override
    public void initView() {
        setTitle("我的票据");
    }
}
