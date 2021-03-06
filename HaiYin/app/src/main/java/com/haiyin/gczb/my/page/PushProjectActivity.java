package com.haiyin.gczb.my.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.CustomerProjectAdapter;
import com.haiyin.gczb.my.entity.SalesCompanyProjectsEntity;
import com.haiyin.gczb.my.entity.SalesContractFilesEntity;
import com.haiyin.gczb.my.presenter.CustomerPresenter;
import com.haiyin.gczb.order.page.OrderDetailActivity;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.view.MyRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/25.
 */
public class PushProjectActivity extends BaseActivity implements BaseView {
    CustomerPresenter customerPresenter;
    @BindView(R.id.rv_push_project)
    MyRecyclerView rv;
    //：1=全部 2=待打款 3=待开票 4=已开票 5=待上传合同项目 6=合作合同项目
    int type;
    String title;
    String id;
    CustomerProjectAdapter mAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;



    private void getData() {
        customerPresenter.salescompanyProjects(page, pageNum, id, type);
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
        if (code== ApiConfig.SALES_COMPANY_PROJECTS) {
            SalesCompanyProjectsEntity entity = (SalesCompanyProjectsEntity) data;
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
            mAdapter.addData(entity.getData());
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_push_project;
    }

    @Override
    public void initView() {
        title = getIntent().getBundleExtra("bundle").getString("title");
        setTitle(title);
        type = getIntent().getBundleExtra("bundle").getInt("type");
        id = getIntent().getBundleExtra("bundle").getString("id");
        customerPresenter = new CustomerPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new CustomerProjectAdapter(R.layout.item_demand_hall);
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SalesCompanyProjectsEntity.DataBean bean = (SalesCompanyProjectsEntity.DataBean) adapter.getData().get(position);


                if(type ==5){
                    //上传合同
                    Intent mIntent = new Intent(mContext, PushProjectDetailActivity.class);
                    Bundle b = new Bundle();
                    b.putString("id", bean.getProjectId());
                    mIntent.putExtra("bundle", b);
                    startActivity(mIntent);
                }else if(type ==6){
                    if(Constant.userType==4){
                        Bundle bundle = new Bundle();
                        bundle.putString("id", bean.getProjectId());
                        intentJump(mContext, ContractDetailActivity.class, bundle);
                    }else {
                        Intent mIntent = new Intent(mContext, OrderDetailActivity.class);
                        Bundle b = new Bundle();
                        b.putString("id", bean.getProjectId());
                        mIntent.putExtra("bundle", b);
                        startActivity(mIntent);
                    }
                }else if(type==7){
                    Intent mIntent = new Intent(mContext, OrderDetailActivity.class);
                    Bundle b = new Bundle();
                    b.putString("id", bean.getProjectId());
                    mIntent.putExtra("bundle", b);
                    startActivity(mIntent);
                }



            }
        });
        initRefreshLayout();
        getData();
    }
}
