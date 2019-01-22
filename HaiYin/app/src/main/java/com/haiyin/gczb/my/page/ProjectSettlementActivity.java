package com.haiyin.gczb.my.page;

import android.support.v4.app.Fragment;

import com.durian.lib.base.BaseView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.fragment.CumulativeFragment;
import com.haiyin.gczb.my.fragment.DuringMonthFragment;
import com.haiyin.gczb.my.presenter.ProgressQueryPresenter;
import com.haiyin.gczb.order.entity.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/18.
 */
public class ProjectSettlementActivity  extends BaseActivity implements BaseView {

    @BindView(R.id.ctl_cooperation_plan)
    CommonTabLayout ctl;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"当月金额", "累计金额"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private int type = 2;
    @Override
    public void success(int code, Object data) {

    }

    @Override
    public void netError(String msg) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cooperation_plan;
    }

    @Override
    public void initView() {

        setTitle("合作方案");
        mFragments.add( DuringMonthFragment.getInstance(type));
        mFragments.add( CumulativeFragment.getInstance(type));
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        ctl .setTabData(mTabEntities, this, R.id.vp_cooperation_plan, mFragments);
    }
}