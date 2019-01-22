package com.haiyin.gczb.my.page;

import android.os.Bundle;

import com.haiyin.gczb.base.BaseActivity;

import butterknife.OnClick;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

/**
 * Created
 * by durian
 * 2019/1/7.
 */
public class ProgressQueryActivity extends BaseActivity {
    @OnClick(R.id.rl_progress_query_cooperation_plan)
    public void   toCooperationPlan(){
        Bundle b = new Bundle();
        intentJump(this,CooperationPlanActivity.class,null);
    }
    @OnClick(R.id.rl_progress_query_project_settlement)
    public void   toProjectSettlement(){
        intentJump(this,ProjectSettlementActivity.class,null);
    }
    @OnClick(R.id.rl_progress_query_payment_certificate)
    public void  toPaymentCertificate(){
        intentJump(this,PaymentCertificateActivity.class,null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_progress_query;
    }

    @Override
    public void initView() {
        setTitle("进度查询");
    }
}
