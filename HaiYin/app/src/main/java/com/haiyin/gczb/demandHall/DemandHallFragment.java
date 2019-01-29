package com.haiyin.gczb.demandHall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.demandHall.adapter.DemandHanllAdapter;
import com.haiyin.gczb.demandHall.entity.ProjectListEntity;
import com.haiyin.gczb.demandHall.page.DemandDetailActivity;
import com.haiyin.gczb.demandHall.presenter.ProjectPresenter;
import com.haiyin.gczb.utils.Constant;
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
 * 2019/1/3.
 */
public class DemandHallFragment extends BaseFragment implements BaseView {
    ProjectPresenter projectPresenter;
    private DemandHanllAdapter mAdapter;
    @BindView(R.id.rv_demand_hall)
    MyRecyclerView rv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;


    @Override
    public void success(int code, Object data) {
        ProjectListEntity entity = (ProjectListEntity) data;
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
    protected int setView() {
        return R.layout.fragment_demand_hall;
    }

    @Override
    protected void init(View view) {
        projectPresenter = new ProjectPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mAdapter = new DemandHanllAdapter(R.layout.item_demand_hall);
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ProjectListEntity.DataBean bean = (ProjectListEntity.DataBean) adapter.getData().get(position);
                Intent mIntent = new Intent(getActivity(), DemandDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("id", bean.getProjectId());
                mIntent.putExtra("bundle", b);
                startActivity(mIntent);
            }
        });
        initRefreshLayout();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getData();
    }

    private void getData() {
        projectPresenter.getProjectList(Constant.cityId, page, pageNum,getActivity());
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
}
