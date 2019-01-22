package com.haiyin.gczb.my.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.view.MyRecyclerView;
import com.durian.lib.base.BaseView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.my.adapter.FrameworkContractAdapter;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.view.MyRecyclerView;

/**
 * Created
 * by durian
 * 2019/1/10.
 */
public class FrameworkContractFragment extends BaseFragment {
    @BindView(R.id.rv_framework_contract)
    MyRecyclerView rv;

    int type;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;
    FrameworkContractAdapter mAdapter;
    public static FrameworkContractFragment getInstance(int type) {
        FrameworkContractFragment fragment = new FrameworkContractFragment();
        fragment.type = type;
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_cumulative;
    }

    @Override
    protected void init(View view) {
        rv.setLayoutManager(MyUtils.getTableManager(getActivity(),2));

        mAdapter = new FrameworkContractAdapter(R.layout.item_framework_contract);
        rv.setAdapter(mAdapter);
        initRefreshLayout();
        getData();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    private void getData() {
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
