package com.haiyin.gczb.my.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.durian.lib.base.BaseView;
import com.flyco.tablayout.SlidingTabLayout;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.entity.ContractFilesEntity;
import com.haiyin.gczb.my.fragment.FrameworkContractFragment;
import com.haiyin.gczb.my.fragment.OrderContractFragment;
import com.haiyin.gczb.my.fragment.PDFFragment;
import com.haiyin.gczb.my.fragment.ProjectStatementFragment;
import com.haiyin.gczb.my.presenter.MyContractPresenter;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.UploadHelper;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/9.
 */
public class PdfContractDetailActivity extends BaseActivity implements BaseView {
    @BindView(R.id.ctl_my_contract)
    SlidingTabLayout ctl;
    @BindView(R.id.vp_my_contract)
    ViewPager vp;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"框架合同", "订单合同", "项目结算单"};

    private String id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_contract_detail;
    }

    @Override
    public void initView() {
        setTitle("合同详情");
        getData();
    }

    private void getData() {
        String frameFile = getIntent().getBundleExtra("bundle").getString("frameFile");
        String contractFile = getIntent().getBundleExtra("bundle").getString("contractFile");
        String clearingFile = getIntent().getBundleExtra("bundle").getString("clearingFile");
        mFragments.add(PDFFragment.getInstance(UploadHelper.getInstance().getPriUrl(this, frameFile)));
        mFragments.add(PDFFragment.getInstance(UploadHelper.getInstance().getPriUrl(this, contractFile)));
        mFragments.add(PDFFragment.getInstance(UploadHelper.getInstance().getPriUrl(this, clearingFile)));
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        ctl.setViewPager(vp, mTitles, this, mFragments);
        vp.setCurrentItem(0);
    }

    @Override
    public void success(int code, Object data) {
        ContractFilesEntity entity = (ContractFilesEntity) data;
        mFragments.add(FrameworkContractFragment.getInstance(entity.getData().getFrameFile()));
        mFragments.add(OrderContractFragment.getInstance(entity.getData().getContractFile()));
        mFragments.add(ProjectStatementFragment.getInstance(entity.getData().getContractFile()));
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        ctl.setViewPager(vp, mTitles, this, mFragments);
        vp.setCurrentItem(0);
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
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
