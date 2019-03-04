package com.haiyin.gczb.my.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.my.adapter.CollectionInformationAdapter;
import com.haiyin.gczb.my.adapter.CumulativeAdapter;
import com.haiyin.gczb.my.entity.CollectionInformationEntity;
import com.haiyin.gczb.my.entity.ProjectCooperateEntity;
import com.haiyin.gczb.my.presenter.MyWalletPresenter;
import com.haiyin.gczb.my.presenter.ProgressQueryPresenter;
import com.haiyin.gczb.utils.Arith;
import com.haiyin.gczb.utils.MyUtils;
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
public class CollectionInformationFragment extends BaseFragment implements BaseView{
    MyWalletPresenter myWalletPresenter;
    @BindView(R.id.rv_cumulative)
    RecyclerView rv;
    @BindView(R.id.tv_cumulative_title)
    TextView tvTitle;
    @BindView(R.id.tv_cumulative_price)
    TextView tvPrice;
    //数据类型：1=当月 2=历史
    int type;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;
    CollectionInformationAdapter mAdapter;
    public static CollectionInformationFragment getInstance(int type) {
        CollectionInformationFragment fragment = new CollectionInformationFragment();
        fragment.type = type;
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_cumulative;
    }

    @Override
    protected void init(View view) {
        myWalletPresenter = new MyWalletPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

//        mAdapter = new CumulativeAdapter(R.layout.item_cumulative);
        mAdapter = new CollectionInformationAdapter(R.layout.item_during_month);
        rv.setAdapter(mAdapter);
        initRefreshLayout();

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getData();
    }

    private void getData() {
            myWalletPresenter.entityReceivedPays(page,pageNum,type,getActivity());

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

        CollectionInformationEntity entity = (CollectionInformationEntity) data;
        tvPrice.setText(entity.getData().getReceivedAmount()+"元");
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
