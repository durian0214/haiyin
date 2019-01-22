package com.haiyin.gczb.sendPackage.page;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/22.
 */
public class SendPackageActivity extends BaseActivity{
    @OnClick(R.id.rl_send_package_team)
    public void toTeam(){
        intentJump(this,LookTeamActivity.class,null);
    }
    @OnClick(R.id.rl_send_package_company)
    public void toCompany(){
        intentJump(this,LaborCompanyActivity.class,null);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_send_package;
    }

    @Override
    public void initView() {
        setTitle("需求大厅");
    }
}
