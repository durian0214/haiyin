package com.haiyin.gczb.my.page;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.EnterpriseAdapter;
import com.haiyin.gczb.my.entity.EnterpriseEntity;
import com.haiyin.gczb.my.presenter.EnterprisePresenter;
import com.haiyin.gczb.user.page.EnterpriseInformationActivity;
import com.haiyin.gczb.utils.MyUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/23.
 */
public class AddEnterpriseListsActivity   extends BaseActivity implements BaseView {
    EnterprisePresenter enterprisePresenter;
    @BindView(R.id.rv_add_enterprise)
    RecyclerView rv;
    private EnterpriseAdapter mAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.ll_add_enterprise_new)
    LinearLayout ll;
    @OnClick(R.id.btn_add_enterprise)
    public void addEnterprise(){
        intentJump(this,EnterpriseInformationActivity.class,null);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_enterprise_lists;
    }
    @Override
    public void initView() {
        setTitle("添加下级企业");
        setTvRight("添加", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentJump(mContext,EnterpriseInformationActivity.class,null);
            }
        });
        enterprisePresenter = new EnterprisePresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new EnterpriseAdapter(R.layout.item_order_contract);
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        initRefreshLayout();
        getData();
    }

    private void getData() {
        enterprisePresenter.subCompanys();
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
        EnterpriseEntity entity = (EnterpriseEntity) data;
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