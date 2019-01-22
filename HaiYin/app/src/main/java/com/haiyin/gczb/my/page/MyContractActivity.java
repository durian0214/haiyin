package com.haiyin.gczb.my.page;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.MyContractAdapter;
import com.haiyin.gczb.my.entity.MyContractCompanysEntity;
import com.haiyin.gczb.my.presenter.MyContractPresenter;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.view.MyRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/18.
 */
public class MyContractActivity extends BaseActivity implements BaseView {
    MyContractPresenter myContractPresenter;
    @BindView(R.id.rv_my_contract)
    MyRecyclerView rv;
    MyContractAdapter myContractAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;
    @Override
    public void success(int code, Object data) {
        MyContractCompanysEntity entity = (MyContractCompanysEntity) data;
        if (srl != null && srl.isRefreshing()) {
            srl.finishRefresh(200);
        }
        if (srl != null && srl.isLoading()) {
            srl.finishLoadmore(200);
        }
        if (entity.getData().size()< pageNum) {
            //关闭加载更多
            srl.setLoadmoreFinished(true);
        }
        myContractAdapter.addData(entity.getData());
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_contract;
    }

    @Override
    public void initView() {
        myContractPresenter = new MyContractPresenter(this);
        setTitle("我的合同");
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myContractAdapter = new MyContractAdapter(R.layout.item_order_contract);
        rv.setAdapter(myContractAdapter);
        myContractAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyContractCompanysEntity.DataBean bean = (MyContractCompanysEntity.DataBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("id",bean.getCompanyId());
                intentJump(mContext,MyContractProjectActivity.class,bundle);
            }
        });
        initRefreshLayout();
        getData();
    }

    private void getData() {
        myContractPresenter.getMyContractCompanys(page,pageNum);
    }
    private void initRefreshLayout() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                srl.setLoadmoreFinished(false);
                myContractAdapter.cleanRV();
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
}
