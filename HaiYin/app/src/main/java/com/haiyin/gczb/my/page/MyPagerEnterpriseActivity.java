package com.haiyin.gczb.my.page;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.MyPagerEnterpriseAdapter;
import com.haiyin.gczb.my.entity.MyPagerEnterpriseEntity;
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
public class MyPagerEnterpriseActivity extends BaseActivity implements BaseView {
    MyPagerEnterprisePresenter myPagerEnterprisePresenter;
    @BindView(R.id.rv_my_pager_enterprise)
    MyRecyclerView rv;
    MyPagerEnterpriseAdapter myPagerEnterpriseAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;
    // 1=未开票 2=已开票
    private int type ;
    private String title;
    @Override
    public void success(int code, Object data) {
        MyPagerEnterpriseEntity entity = (MyPagerEnterpriseEntity) data;
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
        myPagerEnterpriseAdapter.addData(entity.getData());
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_pager_enterprise;
    }

    @Override
    public void initView() {
        type  = getIntent().getBundleExtra("bundle").getInt("type");
         title = getIntent().getBundleExtra("bundle").getString("title");
        myPagerEnterprisePresenter = new MyPagerEnterprisePresenter(this);
        setTitle(title);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myPagerEnterpriseAdapter = new MyPagerEnterpriseAdapter(R.layout.item_order_contract);
        rv.setAdapter(myPagerEnterpriseAdapter);
        myPagerEnterpriseAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyPagerEnterpriseEntity.DataBean bean = (MyPagerEnterpriseEntity.DataBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("id",bean.getCompanyId());
                bundle.putInt("type",type);
                bundle.putString("title",title);
                intentJump(mContext,MyPagerProjectActivity.class,bundle);
            }
        });
        initRefreshLayout();
        getData();
    }

    private void getData() {
        myPagerEnterprisePresenter.invoiceCompanys(page,pageNum,type);
    }
    private void initRefreshLayout() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                srl.setLoadmoreFinished(false);
                myPagerEnterpriseAdapter.cleanRV();
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
