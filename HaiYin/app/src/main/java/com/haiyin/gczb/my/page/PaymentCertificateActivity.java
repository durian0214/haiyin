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
import com.haiyin.gczb.my.fragment.PaymentCertificateFragment;
import com.haiyin.gczb.order.entity.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/10.
 */
public class PaymentCertificateActivity extends BaseActivity {
    @BindView(R.id.ctl_payment_certificate)
    SlidingTabLayout ctl;
    @BindView(R.id.vp_payment_certificate)
    ViewPager vp;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"本月证明", "历史证明"};


    @Override
    protected int getLayoutId() {
        return R.layout.activity_payment_certificate;
    }

    @Override
    public void initView() {
        setTitle("完税证明");
        mFragments.add(PaymentCertificateFragment.getInstance(1));
        mFragments.add(PaymentCertificateFragment.getInstance(2));
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
