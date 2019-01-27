package com.haiyin.gczb.my.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.fragment.SalemanSendPackageFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/25.
 */
public class SalesmanSendPackageActivity extends BaseActivity{
    @BindView(R.id.ctl_cooperation_plan)
    SlidingTabLayout ctl;
    @BindView(R.id.vp_cooperation_plan)
    ViewPager vp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"当月发包金额", "累计发包金额"};


    private String id;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_cooperation_plan;
    }

    @Override
    public void initView() {
        id = getIntent().getBundleExtra("bundle").getString("id");
        setTitle("收款记录");
        mFragments.add(SalemanSendPackageFragment.getInstance(1,id));
        mFragments.add(SalemanSendPackageFragment.getInstance(2,id));
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
