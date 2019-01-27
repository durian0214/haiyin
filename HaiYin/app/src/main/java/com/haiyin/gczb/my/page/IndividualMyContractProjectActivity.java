package com.haiyin.gczb.my.page;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.IndividualMyContractProjectAdapter;
import com.haiyin.gczb.my.adapter.MyContractProjectAdapter;
import com.haiyin.gczb.my.entity.EntityContractsEntity;
import com.haiyin.gczb.my.entity.GetMyContractProjectsEntity;
import com.haiyin.gczb.my.presenter.IndividualPresenter;
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
public class IndividualMyContractProjectActivity extends BaseActivity implements BaseView {
    IndividualPresenter individualPresenter;
    @BindView(R.id.rv_my_contract_project)
    MyRecyclerView rv;
    IndividualMyContractProjectAdapter individualMyContractProjectAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;


    @Override
    public void success(int code, Object data) {
        EntityContractsEntity entity = (EntityContractsEntity) data;
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
        individualMyContractProjectAdapter.addData(entity.getData());
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
        individualPresenter = new IndividualPresenter(this);
        setTitle("我的合同");
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        individualMyContractProjectAdapter = new IndividualMyContractProjectAdapter(R.layout.item_demand_hall);
        rv.setAdapter(individualMyContractProjectAdapter);
        individualMyContractProjectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                EntityContractsEntity.DataBean bean = (EntityContractsEntity.DataBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("frameFile", bean.getFrameFile());
                bundle.putString("contractFile", bean.getContractFile());
                bundle.putString("clearingFile", bean.getClearingFile());
                intentJump(mContext, PdfContractDetailActivity.class, bundle);
            }
        });
        initRefreshLayout();
        getData();
    }

    private void getData() {
        individualPresenter.entityContracts(page, pageNum);
    }

    private void initRefreshLayout() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                srl.setLoadmoreFinished(false);
                individualMyContractProjectAdapter.cleanRV();
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
