package com.haiyin.gczb.user.page;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.utils.MyUtils;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/2/23.
 */
public class AgreementActivity extends BaseActivity {
    @BindView(R.id.tv_agreement)
    TextView tv;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_agreement;
    }

    @Override
    public void initView() {
        setTitle("谷仓众包用户协议");
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv.setText(MyUtils.readTxt(mContext, "谷仓众包用户协议.txt"));
    }
}
