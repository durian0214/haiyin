package com.haiyin.gczb.home.page;

import android.os.Bundle;
import android.widget.EditText;

import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.view.MyRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.view.MyRecyclerView;

/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class SearchActivity extends BaseActivity {
    @BindView(R.id.edt_search)
    EditText edt;
    @OnClick(R.id.ll_search_back)
    public void tofinish(){
        this.finish();
    }
    @OnClick(R.id.tv_search)
    public void toSearch() {
        String str = edt.getText().toString();
        if (str.isEmpty()) {
            MyUtils.showShort("请输入搜索关键字");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("str", str);
        intentJump(this, SearchResultsActivity.class, bundle);
    }

    @BindView(R.id.rv_search)
    MyRecyclerView rv;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        isShowTitle(false);
    }
}
