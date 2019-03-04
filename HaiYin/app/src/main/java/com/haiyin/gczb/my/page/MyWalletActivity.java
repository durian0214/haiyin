package com.haiyin.gczb.my.page;

import android.os.Bundle;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/28.
 */
public class MyWalletActivity extends BaseActivity {
    @OnClick(R.id.rl_my_wallet_online)
    public void toOnline() {
        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        intentJump(this, PaysActivity.class, bundle);
    }

    @OnClick(R.id.rl_my_wallet_offline)
    public void toOffline() {
        Bundle bundle = new Bundle();
        bundle.putInt("type", 2);
        intentJump(this, PaysActivity.class, bundle);
    }

    @OnClick(R.id.rl_my_wallet_collection_information)
    public void toCollectionInformation() {
        intentJump(this, CollectionInformationActivity.class, null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_wallet;
    }

    @Override
    public void initView() {

        setTitle("我的钱包");
    }
}
