package com.haiyin.gczb.my.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.my.entity.ProjectCooperateEntity;
import com.haiyin.gczb.my.presenter.ProgressQueryPresenter;
import com.haiyin.gczb.utils.Arith;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.view.MyRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.my.adapter.CumulativeAdapter;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.view.MyRecyclerView;

/**
 * Created
 * by durian
 * 2019/1/7.
 */
public class CumulativeFragment extends BaseFragment implements BaseView{
     ProgressQueryPresenter progressQueryPresenter;
    @BindView(R.id.rv_cumulative)
    RecyclerView rv;
    @BindView(R.id.tv_cumulative_title)
    TextView tvTitle;
    @BindView(R.id.tv_cumulative_price)
    TextView tvPrice;
    int type;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;
    CumulativeAdapter mAdapter;
    public static CumulativeFragment getInstance(int type) {
        CumulativeFragment fragment = new CumulativeFragment();
        fragment.type = type;
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_cumulative;
    }

    @Override
    protected void init(View view) {
        progressQueryPresenter = new ProgressQueryPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

//        mAdapter = new CumulativeAdapter(R.layout.item_cumulative);
        mAdapter = new CumulativeAdapter(R.layout.item_during_month);
        rv.setAdapter(mAdapter);
        initRefreshLayout();

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getData();
    }

    private void getData() {
        if(type==1){
            progressQueryPresenter.projectCooperate(page,pageNum,2,getActivity());
        }else if(type==2){
            progressQueryPresenter.projectClearing(page,pageNum,2,getActivity());
        }
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

        ProjectCooperateEntity entity = (ProjectCooperateEntity) data;
        tvPrice.setText(Arith.div_text(entity.getData().getTotalAmount(),100)+"元");
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
