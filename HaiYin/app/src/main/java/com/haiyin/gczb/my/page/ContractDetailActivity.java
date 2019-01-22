package com.haiyin.gczb.my.page;

import android.support.v4.app.Fragment;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.fragment.ProjectStatementFragment;
import com.haiyin.gczb.my.presenter.MyContractPresenter;
import com.haiyin.gczb.order.entity.TabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.fragment.CumulativeFragment;
import com.haiyin.gczb.my.fragment.DuringMonthFragment;
import com.haiyin.gczb.my.fragment.FrameworkContractFragment;
import com.haiyin.gczb.my.fragment.OrderContractFragment;
import com.haiyin.gczb.my.fragment.ProjectStatementFragment;
import com.haiyin.gczb.order.entity.TabEntity;

/**
 * Created
 * by durian
 * 2019/1/9.
 */
public class ContractDetailActivity extends BaseActivity  implements BaseView{
    MyContractPresenter myContractPresenter;
    @BindView(R.id.ctl_my_contract)
    CommonTabLayout ctl;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"框架合同", "订单合同", "项目结算单"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private String id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_contract_detail;
    }

    @Override
    public void initView() {
        myContractPresenter = new MyContractPresenter(this);
        id = getIntent().getBundleExtra("bundle").getString("id");
        setTitle("合同详情");
        mFragments.add( FrameworkContractFragment.getInstance(1));
        mFragments.add( OrderContractFragment.getInstance(1));
        mFragments.add( ProjectStatementFragment.getInstance(1));
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        ctl .setTabData(mTabEntities, this, R.id.vp_cooperation_plan, mFragments);
        getData();
    }
    private void getData(){
        myContractPresenter.contractFiles(id);
    }

    @Override
    public void success(int code, Object data) {

    }

    @Override
    public void netError(String msg) {

    }
}
