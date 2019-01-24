package com.haiyin.gczb.my.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.durian.lib.base.BaseView;
import com.flyco.tablayout.SlidingTabLayout;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.fragment.CustomerFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/24.
 */
public class CustomerActivity extends BaseActivity implements BaseView {
    @BindView(R.id.ctl_customer)
    SlidingTabLayout ctl;
    @BindView(R.id.vp_customer)
    ViewPager vp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"待完善客户信息", "已完善客户信息"};

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
        setTitle("客户列表");
        mFragments.add(CustomerFragment.getInstance(2));
        mFragments.add(CustomerFragment.getInstance(3));
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
