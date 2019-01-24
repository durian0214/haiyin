package com.haiyin.gczb.my.page;

import android.support.v7.widget.DividerItemDecoration;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.CustomerProjectAdapter;
import com.haiyin.gczb.my.entity.SalesCompanyProjectsEntity;
import com.haiyin.gczb.my.presenter.CustomerPresenter;
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
 * 2019/1/25.
 */
public class PushProjectActivity extends BaseActivity implements BaseView {
    CustomerPresenter customerPresenter;
    @BindView(R.id.rv_customer_project)
    MyRecyclerView rv;
    //：1=全部 2=待打款 3=待开票 4=已开票 5=待上传合同项目 6=合作合同项目
    int type;
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

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.fragment_customer;
    }

    @Override
    public void initView() {
        type = getIntent().getBundleExtra("bundle").getInt("type");
        id = getIntent().getBundleExtra("bundle").getString("id");
        customerPresenter = new CustomerPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new CustomerProjectAdapter(R.layout.item_customer);
        rv.setAdapter(mAdapter);
        initRefreshLayout();
        getData();
    }
}
