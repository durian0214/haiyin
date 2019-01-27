package com.haiyin.gczb.home.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.haiyin.gczb.home.fragment.NewsFragment;
import com.haiyin.gczb.home.fragment.ProjectFragment;

import java.util.ArrayList;

import butterknife.BindView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class SearchResultsActivity  extends BaseActivity{
    @BindView(R.id.ctl_cooperation_plan)
    SlidingTabLayout ctl;
    @BindView(R.id.vp_cooperation_plan)
    ViewPager vp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"新闻列表", "众包列表"};



    private String str;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cooperation_plan;
    }

    @Override
    public void initView() {
        str = getIntent().getBundleExtra("bundle").getString("str");
        setTitle("搜索结果");
        mFragments.add( NewsFragment.getInstance(str));
        mFragments.add( ProjectFragment.getInstance(str));
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
