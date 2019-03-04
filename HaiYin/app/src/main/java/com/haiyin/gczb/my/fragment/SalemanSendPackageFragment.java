package com.haiyin.gczb.my.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.TextView;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.my.adapter.DuringMonthAdapter;
import com.haiyin.gczb.my.adapter.SalemanSendPackageAdapter;
import com.haiyin.gczb.my.entity.ProjectCooperateEntity;
import com.haiyin.gczb.my.entity.SalesProjectAmountEntity;
import com.haiyin.gczb.my.presenter.CustomerPresenter;
import com.haiyin.gczb.my.presenter.ProgressQueryPresenter;
import com.haiyin.gczb.utils.Arith;
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
 * 2019/1/7.
 */
public class SalemanSendPackageFragment extends BaseFragment implements BaseView{
    CustomerPresenter customerPresenter;
    @BindView(R.id.rv_during_month)
    MyRecyclerView rv;
    @BindView(R.id.tv_during_month_title)
    TextView tvTitle;
    @BindView(R.id.tv_during_month_price)
    TextView tvPrice;
    int type ;
    SalemanSendPackageAdapter mAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    String id;
    private int pageNum = 20;
    public static SalemanSendPackageFragment getInstance(int type,String id) {
        SalemanSendPackageFragment fragment = new SalemanSendPackageFragment();
        fragment.type = type;
        fragment.id = id;
        return fragment;
    }
    @Override
    protected int setView() {
        return R.layout.fragment_during_month;
    }

    @Override
    protected void init(View view) {
        if(type==1){
            tvTitle.setText("当月收款纪录总和");
        }else {
            tvTitle.setText("累计收款纪录总和");
        }
        customerPresenter = new CustomerPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new SalemanSendPackageAdapter(R.layout.item_during_month);
        rv.setAdapter(mAdapter);
        initRefreshLayout();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getData();
    }

    private void getData() {
            customerPresenter.salesProjectAmount(id,page,pageNum,type);

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

        SalesProjectAmountEntity entity = (SalesProjectAmountEntity) data;
        tvPrice.setText(entity.getData().getTotalAmount()+"元");
        if (srl != null && srl.isRefreshing()) {
            srl.finishRefresh(200);
        }
        if (srl != null && srl.isLoading()) {
            srl.finishLoadmore(200);
        }
        if (entity.getData().getDataList().size()< pageNum) {
            //关闭加载更多
            srl.setLoadmoreFinished(true);
        }
        mAdapter.addData(entity.getData().getDataList());
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

}
