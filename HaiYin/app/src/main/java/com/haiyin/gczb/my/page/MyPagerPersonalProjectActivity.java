package com.haiyin.gczb.my.page;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.LinearLayout;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.MyPagerPersonalProjectAdapter;
import com.haiyin.gczb.my.adapter.MyPagerProjectAdapter;
import com.haiyin.gczb.my.entity.MyPagerPersonalProjectEntity;
import com.haiyin.gczb.my.entity.MyPagerProjectEntity;
import com.haiyin.gczb.my.presenter.MyPagerEnterprisePresenter;
import com.haiyin.gczb.my.presenter.MyPagerPersonalProjectPresenter;
import com.haiyin.gczb.order.page.OrderDetailActivity;
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
public class MyPagerPersonalProjectActivity extends BaseActivity implements BaseView {
    MyPagerPersonalProjectPresenter myPagerPersonalProjectPresenter;
    @BindView(R.id.rv_my_pager_personal_project)
    MyRecyclerView rv;
    MyPagerPersonalProjectAdapter myPagerPersonalProjectAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    private int page = 1;
    private int pageNum = 20;
    private int type;

    @Override
    public void success(int code, Object data) {
        MyPagerPersonalProjectEntity entity = (MyPagerPersonalProjectEntity) data;
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
        myPagerPersonalProjectAdapter.addData(entity.getData());
        if (myPagerPersonalProjectAdapter.getData().size()==0){
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
        return R.layout.activity_my_pager_personal_project;
    }

    @Override
    public void initView() {
        type = getIntent().getBundleExtra("bundle").getInt("type");
        String title = getIntent().getBundleExtra("bundle").getString("title");
        myPagerPersonalProjectPresenter = new MyPagerPersonalProjectPresenter(this);
        setTitle(title);
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myPagerPersonalProjectAdapter = new MyPagerPersonalProjectAdapter(R.layout.item_demand_hall);
        rv.setAdapter(myPagerPersonalProjectAdapter);
        myPagerPersonalProjectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyPagerPersonalProjectEntity.DataBean bean = (MyPagerPersonalProjectEntity.DataBean) adapter.getData().get(position);
                if (bean.getProjectStatus() == 6) {
                    //申请开票
                    Bundle bundle = new Bundle();
                    bundle.putString("id", bean.getProjectId());
                    intentJump(mContext, OrderDetailActivity.class, bundle);
                } else if (bean.getProjectStatus() == 8) {
                    //查看发票
                    Bundle bundle = new Bundle();
                    bundle.putString("url", bean.getInvoiceFileEntity());
                    intentJump(mContext, CheckNotesActivity.class, bundle);
                }
            }
        });
        initRefreshLayout();
        getData();
    }

    private void getData() {
        myPagerPersonalProjectPresenter.entityProjects(page, pageNum, type, mContext);
    }

    private void initRefreshLayout() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                srl.setLoadmoreFinished(false);
                myPagerPersonalProjectAdapter.cleanRV();
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
