package com.haiyin.gczb.my.page;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.LinearLayout;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.MyContractProjectAdapter;
import com.haiyin.gczb.my.entity.GetMyContractProjectsEntity;
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
public class MyContractProjectActivity extends BaseActivity implements BaseView {
    MyContractPresenter myContractPresenter;
    @BindView(R.id.rv_my_contract_project)
    MyRecyclerView rv;
    MyContractProjectAdapter myContractProjectAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;
    private String id;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;

    @Override
    public void success(int code, Object data) {
        GetMyContractProjectsEntity entity = (GetMyContractProjectsEntity) data;
        if (srl != null && srl.isRefreshing()) {
            srl.finishRefresh(200);
        }
        if (srl != null && srl.isLoading()) {
            srl.finishLoadmore(200);
        }
        if (entity.getData().size() < pageNum) {
            //关闭加载更多
            srl.setLoadmoreFinished(true);
        }
        myContractProjectAdapter.addData(entity.getData());
        if (myContractProjectAdapter.getData().size()==0){
            llNoData.setVisibility(View.VISIBLE);
        }else{
            llNoData.setVisibility(View.GONE);
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_contract_project;
    }

    @Override
    public void initView() {
        id = getIntent().getBundleExtra("bundle").getString("id");


        myContractPresenter = new MyContractPresenter(this);
        setTitle("我的合同");
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myContractProjectAdapter = new MyContractProjectAdapter(R.layout.item_demand_hall);
        rv.setAdapter(myContractProjectAdapter);
        myContractProjectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GetMyContractProjectsEntity.DataBean bean = (GetMyContractProjectsEntity.DataBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("id", bean.getProjectId());
                intentJump(mContext, ContractDetailActivity.class, bundle);
            }
        });
        initRefreshLayout();
        getData();
    }

    private void getData() {
        myContractPresenter.myContractProjects(page, pageNum, id,mContext);
    }

    private void initRefreshLayout() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                srl.setLoadmoreFinished(false);
                myContractProjectAdapter.cleanRV();
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
