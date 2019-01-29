package com.haiyin.gczb.my.page;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.haiyin.gczb.my.entity.AccountEntity;
import com.haiyin.gczb.my.presenter.AccountPresenter;
import com.haiyin.gczb.utils.MyUtils;
import com.durian.lib.base.BaseView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;

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
    AccountPresenter accountPresenter;
    @BindView(R.id.rv_add_account)
    RecyclerView rv;
    private AccountAdapter mAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.ll_add_account_new)
    LinearLayout ll;
    @OnClick(R.id.btn_add_account)
    public void addAccount(){
        intentJump(this,AddAccountActivity.class,null);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_account_lists;
    }
    @Override
    public void initView() {
        setTitle("添加本部账号");
        setTvRight("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentJump(mContext,AddAccountActivity.class,null);
            }
        });
        accountPresenter = new AccountPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new AccountAdapter(R.layout.item_account);
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        initRefreshLayout();
        getData();
    }

    private void getData() {
        accountPresenter.accountList(mContext);
    }

    private void initRefreshLayout() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                srl.setLoadmoreFinished(false);
                mAdapter.cleanRV();
                getData();
            }
        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                getData();
            }
        });
    }

    @Override
    public void success(int code, Object data) {
        AccountEntity entity = (AccountEntity) data;
        if (srl != null && srl.isRefreshing()) {
            srl.finishRefresh(200);
        }
        if (srl != null && srl.isLoading()) {
            srl.finishLoadmore(200);
        }
        if (entity.getData().size() < 1000) {
            //关闭加载更多
            srl.setLoadmoreFinished(true);
        }
        if(entity.getData().size()==0){
            ll.setVisibility(View.VISIBLE);
        }else {
            ll.setVisibility(View.GONE);
            mAdapter.addData(entity.getData());
        }

    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }
}
