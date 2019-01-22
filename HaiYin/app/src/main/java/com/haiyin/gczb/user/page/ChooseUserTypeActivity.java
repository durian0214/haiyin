package com.haiyin.gczb.user.page;

import android.os.Bundle;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/4.
 * 选择用户类型
 */
public class ChooseUserTypeActivity extends BaseActivity {
    Bundle b ;
    @OnClick(R.id.rl_choose_user_type_company)
    public void toCompany(){
        b.putInt("roleType",1);
        intentJump(this,EnterpriseInformationActivity.class,b);

    }
    @OnClick(R.id.rl_choose_user_type_individual)
    public void toIndividual(){
        b.putInt("roleType",2);
        intentJump(this,EnterpriseInformationActivity.class,b);
    }
    @OnClick(R.id.rl_choose_user_type_personal)
    public void toPersonal(){
        b.putInt("roleType",3);
        intentJump(this,PersonalInformationActivity.class,b);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_user_type;
    }

    @Override
    public void initView() {
        b = getIntent().getBundleExtra("bundle");
    }
}
