package com.haiyin.gczb.my.page;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.haiyin.gczb.utils.MyUtils;
import com.durian.lib.base.BaseView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.AccountAdapter;
import com.haiyin.gczb.utils.MyUtils;

/**
 * Created
 * by durian
 * 2019/1/9.
 */
public class AddAccountListsActivity extends BaseActivity implements BaseView {
    @BindView(R.id.rv_add_account)
    RecyclerView rv;
    private AccountAdapter mAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_account_lists;
    }

    @Override
    public void initView() {
        setTitle("添加本部账号");

        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mAdapter = new AccountAdapter(R.layout.item_account);
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        initRefreshLayout();
        getData();
    }

    private void getData() {
    }

    private void initRefreshLayout() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                srl.setLoadmoreFinished(false);
                mAdapter.cleanRV();
                getData();

            }
        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                page++;
                getData();
            }
        });
    }

    @Override
    public void success(int code, Object data) {
//        MessageListsEntity entity = (MessageListsEntity) data;
//        if (srl != null && srl.isRefreshing()) {
//            srl.finishRefresh(200);
//        }
//        if (srl != null && srl.isLoading()) {
//            srl.finishLoadmore(200);
//        }
//        if (entity.getResponse_data().getCount() < pageNum) {
//            //关闭加载更多
//            srl.setLoadmoreFinished(true);
//        }
//        mAdapter.addData(entity.getResponse_data().getLists());
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }
}
