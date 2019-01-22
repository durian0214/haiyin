package com.haiyin.gczb.my.page;

import android.support.v7.widget.DividerItemDecoration;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.ServiceInformationAdapter;
import com.haiyin.gczb.my.entity.ServiceInformationEntity;
import com.haiyin.gczb.my.presenter.ServiceInformationPresenter;
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
 * 2019/1/20.
 */
public class ServiceInformationActivity  extends BaseActivity implements BaseView{
    ServiceInformationPresenter serviceInformationPresenter;
    @BindView(R.id.rv_service_information)
    MyRecyclerView rv;
    ServiceInformationAdapter myContractAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;
    @Override
    public void success(int code, Object data) {
        ServiceInformationEntity entity = (ServiceInformationEntity) data;
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
        return R.layout.activity_service_information;
    }

    @Override
    public void initView() {
        serviceInformationPresenter = new ServiceInformationPresenter(this);
        setTitle("服务信息");
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        myContractAdapter = new ServiceInformationAdapter(R.layout.item_order_contract);
        rv.setAdapter(myContractAdapter);
        initRefreshLayout();
        getData();
    }

    private void getData() {
        serviceInformationPresenter.companyList(page,pageNum);
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
