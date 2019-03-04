package com.haiyin.gczb.my.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

/**
 * Created
 * by durian
 * 2019/1/10.
 */
public class FrameworkContractFragment extends BaseFragment {
    @BindView(R.id.rv_framework_contract)
    RecyclerView rv;

    List<String> lists;
    FrameworkContractAdapter mAdapter;
    public static FrameworkContractFragment getInstance(List<String> lists) {
        FrameworkContractFragment fragment = new FrameworkContractFragment();
        fragment.lists = lists;
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_framework_contract;
    }

    @Override
    protected void init(View view) {

        rv.setLayoutManager(MyUtils.getVManager(getActivity()));
        mAdapter = new FrameworkContractAdapter(R.layout.item_agreement);
        rv.setAdapter(mAdapter);
        mAdapter.addData(lists);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getData();
    }

    private void getData() {
    }



}
