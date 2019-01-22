package com.haiyin.gczb.user.page;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

/**
 * Created
 * by durian
 * 2019/1/4.
 */
public class SubmitSucceedActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_succeed;
    }

    @Override
    public void initView() {
        String title = getIntent().getBundleExtra("bundle").getString("title");
        String context = getIntent().getBundleExtra("bundle").getString("context");

        setTitle(title);

    }
}
