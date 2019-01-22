package com.haiyin.gczb.home.page;

import android.os.Bundle;

import butterknife.OnClick;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

/**
 * Created
 * by durian
 * 2019/1/4.
 */
public class MessageActivity extends BaseActivity {
    @OnClick(R.id.rl_message_system)
    public void toSystem() {
        Bundle bundle = new Bundle();
        bundle.putInt("type",1);
        intentJump(this,MessageListActivity.class,bundle);
    }
    @OnClick(R.id.rl_message_wallet)
    public void toWallet() {
        Bundle bundle = new Bundle();
        bundle.putInt("type",2);
        intentJump(this,MessageListActivity.class,bundle);
    }
    @OnClick(R.id.rl_message_task)
    public void toTask() {
        Bundle bundle = new Bundle();
        bundle.putInt("type",3);
        intentJump(this,MessageListActivity.class,bundle);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void initView() {
        setTitle("消息");
    }
}
