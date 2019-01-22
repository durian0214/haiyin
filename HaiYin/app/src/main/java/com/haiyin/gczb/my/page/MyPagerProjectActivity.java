package com.haiyin.gczb.my.page;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.MyContractProjectAdapter;
import com.haiyin.gczb.my.adapter.MyPagerProjectAdapter;
import com.haiyin.gczb.my.entity.GetMyContractProjectsEntity;
import com.haiyin.gczb.my.entity.MyPagerProjectEntity;
import com.haiyin.gczb.my.presenter.MyContractPresenter;
import com.haiyin.gczb.my.presenter.MyPagerEnterprisePresenter;
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
public class MyPagerProjectActivity extends BaseActivity implements BaseView {
    MyPagerEnterprisePresenter myPagerEnterprisePresenter;
    @BindView(R.id.rv_my_pager_project)
    MyRecyclerView rv;
    MyPagerProjectAdapter myPagerProjectAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;
    private String id;
    private int type;
    @Override
    public void success(int code, Object data) {
        MyPagerProjectEntity entity = (MyPagerProjectEntity) data;
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
        myPagerProjectAdapter.addData(entity.getData());
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_pager_project;
    }

    @Override
    public void initView() {
        id = getIntent().getBundleExtra("bundle").getString("id");
        type = getIntent().getBundleExtra("bundle").getInt("type");
       String title = getIntent().getBundleExtra("bundle").getString("title");
        myPagerEnterprisePresenter = new MyPagerEnterprisePresenter(this);
        setTitle(title);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myPagerProjectAdapter = new MyPagerProjectAdapter(R.layout.item_demand_hall);
        rv.setAdapter(myPagerProjectAdapter);
        myPagerProjectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyPagerProjectEntity.DataBean bean = (MyPagerProjectEntity.DataBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("id", bean.getProjectId());
                intentJump(mContext, ContractDetailActivity.class, bundle);
            }
        });
        initRefreshLayout();
        getData();
    }

    private void getData() {
        myPagerEnterprisePresenter.invoiceCompanyProjects(page, pageNum,type,id);
    }

    private void initRefreshLayout() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                srl.setLoadmoreFinished(false);
                myPagerProjectAdapter.cleanRV();
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
