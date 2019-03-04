package com.haiyin.gczb.my.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.durian.lib.base.BaseView;
import com.durian.lib.glide.GlideUtil;
import com.flyco.tablayout.SlidingTabLayout;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.entity.ContractFilesEntity;
import com.haiyin.gczb.my.entity.SalesContractFilesEntity;
import com.haiyin.gczb.my.fragment.ProjectStatementFragment;
import com.haiyin.gczb.my.presenter.CustomerPresenter;
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
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.UploadHelper;
import com.haiyin.gczb.utils.http.ApiConfig;

/**
 * Created
 * by durian
 * 2019/1/9.
 */
public class ContractDetailActivity extends BaseActivity  implements BaseView{
    MyContractPresenter myContractPresenter;
    CustomerPresenter customerPresenter;
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
        myContractPresenter = new MyContractPresenter(this);
        customerPresenter = new CustomerPresenter(this);
        id = getIntent().getBundleExtra("bundle").getString("id");
        setTitle("合同详情");
        getData();
    }
    private void getData(){
        if(Constant.userType==4){
            customerPresenter.salesContractFiles(id);
        }else {
            myContractPresenter.contractFiles(id,mContext);
        }

    }

    @Override
    public void success(int code, Object data) {
                new Thread(new Runnable() {
            @Override
            public void run() {
                String url =  UploadHelper.getInstance().getPriUrl(mContext, "12");
            }
        }).start();
        if(code ==ApiConfig.GET_MY_CONTRACT_PROJECTS){


            ContractFilesEntity entity = (ContractFilesEntity) data;
            mFragments.add( FrameworkContractFragment.getInstance(entity.getData().getFrameFile()));
            mFragments.add( OrderContractFragment.getInstance(entity.getData().getContractFile()));
            mFragments.add( ProjectStatementFragment.getInstance(entity.getData().getContractFile()));
            vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
            ctl.setViewPager(vp, mTitles, this, mFragments);
            vp.setCurrentItem(0);
        } else if(code== ApiConfig.SALES_CONTRACT_FILES){
            SalesContractFilesEntity entity = (SalesContractFilesEntity) data;
            mFragments.add( FrameworkContractFragment.getInstance(entity.getData().getFrameFile()));
            mFragments.add( OrderContractFragment.getInstance(entity.getData().getContractFile()));
            mFragments.add( ProjectStatementFragment.getInstance(entity.getData().getContractFile()));
            vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
            ctl.setViewPager(vp, mTitles, this, mFragments);
            vp.setCurrentItem(0);
        }

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
