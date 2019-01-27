package com.haiyin.gczb.my.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.durian.lib.base.BaseView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
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
    SlidingTabLayout ctl;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"当月金额", "累计金额"};
    @BindView(R.id.vp_cooperation_plan)
    ViewPager vp;
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
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        ctl.setViewPager(vp, mTitles, this, mFragments);
        vp.setCurrentItem(0);
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
