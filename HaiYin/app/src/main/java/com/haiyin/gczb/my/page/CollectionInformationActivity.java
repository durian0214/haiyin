package com.haiyin.gczb.my.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.fragment.CollectionInformationFragment;
import com.haiyin.gczb.my.fragment.CumulativeFragment;
import com.haiyin.gczb.my.fragment.DuringMonthFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/28.
 */
public class CollectionInformationActivity extends BaseActivity {
    @BindView(R.id.ctl_collection_information)
    SlidingTabLayout ctl;
    @BindView(R.id.vp_collection_information)
    ViewPager vp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"当月收款", "历史收款"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection_information;
    }

    @Override
    public void initView() {
        setTitle("收款记录");
        mFragments.add(CollectionInformationFragment.getInstance(1));
        mFragments.add(CollectionInformationFragment.getInstance(2));
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
