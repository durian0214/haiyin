package com.haiyin.gczb.user.page;

import android.widget.TextView;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/4.
 */
public class SubmitSucceedActivity extends BaseActivity {
    @BindView(R.id.tv_submit_status)
    TextView tv ;
    @OnClick(R.id.btn_submit_succceed)
    public void to(){
        this.finish();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_submit_succeed;
    }

    @Override
    public void initView() {
        String title = getIntent().getBundleExtra("bundle").getString("title");
        String context = getIntent().getBundleExtra("bundle").getString("context");
        setTitle(title);
        tv.setText(context);
    }
}
