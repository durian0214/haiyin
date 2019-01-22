package com.haiyin.gczb.home.page;

import android.support.v4.app.Fragment;

import com.haiyin.gczb.home.fragment.NewsFragment;
import com.haiyin.gczb.home.fragment.ProjectFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.home.entity.SearchResultsEntity;
import com.haiyin.gczb.home.fragment.NewsFragment;
import com.haiyin.gczb.home.fragment.ProjectFragment;
import com.haiyin.gczb.order.entity.TabEntity;

/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class SearchResultsActivity  extends BaseActivity{
    @BindView(R.id.ctl_cooperation_plan)
    CommonTabLayout ctl;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"新闻列表", "众包列表"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();



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
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        ctl .setTabData(mTabEntities, this, R.id.vp_cooperation_plan, mFragments);

    }
}
