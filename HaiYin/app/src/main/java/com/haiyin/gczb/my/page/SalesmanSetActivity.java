package com.haiyin.gczb.my.page;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.user.page.SalesmanInformationActivity;

import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/24.
 */
public class SalesmanSetActivity  extends BaseActivity  {
   @Override
    protected int getLayoutId() {
        return R.layout.activity_salesman_set;
    }
    @OnClick(R.id.rl_salesman_set_change_phone)
    public void toChangePhone(){
        intentJump(this,SalesmanChangePhoneActivity.class,null);
    }
    @OnClick(R.id.rl_salesman_set_change_info)
    public void toChangeInfo(){
        intentJump(this,SalesmanInformationActivity.class,null);
    }



    @Override
    public void initView() {
        setTitle("修改信息");
    }


}
