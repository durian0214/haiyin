package com.haiyin.gczb.my.page;

import android.support.v4.app.Fragment;

import com.durian.lib.base.BaseView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.order.entity.TabEntity;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/10.
 */
public class PaymentCertificateActivity extends BaseActivity implements BaseView {
    @BindView(R.id.ctl_payment_certificate)
    CommonTabLayout ctl;

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"本月证明", "历史证明"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();


    private int type;
    @Override
    public void success(int code, Object data) {

    }

    @Override
    public void netError(String msg) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_payment_certificate;
    }

    @Override
    public void initView() {
        type = getIntent().getBundleExtra("bundle").getInt("type");
        setTitle("完税证明");

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        ctl.setTabData(mTabEntities);
        ctl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
