package com.haiyin.gczb.my.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.durian.lib.base.BaseView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.fragment.CustomerProjectFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/24.
 */
public class DemandHallPojectActivity extends BaseActivity  {
    @BindView(R.id.ctl_demandhall_project)
    SlidingTabLayout ctl;
    @BindView(R.id.vp_demandhall_project)
    ViewPager vp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"全部订单", "待打款","待开票", "已完成"};

    private String id;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_demandhall_projeck;
    }

    @Override
    public void initView() {
        setTitle("公司众包列表");
        id = getIntent().getBundleExtra("bundle").getString("id");
        mFragments.add(CustomerProjectFragment.getInstance(id,1));
        mFragments.add(CustomerProjectFragment.getInstance(id,2));
        mFragments.add(CustomerProjectFragment.getInstance(id,3));
        mFragments.add(CustomerProjectFragment.getInstance(id,4));
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
