package com.haiyin.gczb.my.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.my.adapter.FrameworkContractAdapter;
import com.haiyin.gczb.my.adapter.ProjectStatementAdapter;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.view.MyRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/10.
 * 合同详情
 */
public class ProjectStatementFragment   extends BaseFragment  {
    @BindView(R.id.rv_fragment_project_statement)
    RecyclerView rv;



    List<String> lists;
    ProjectStatementAdapter mAdapter;
    public static ProjectStatementFragment getInstance(List<String> lists) {
        ProjectStatementFragment fragment = new ProjectStatementFragment();
        fragment.lists = lists;
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_project_statement;
    }

    @Override
    protected void init(View view) {
        rv.setLayoutManager(MyUtils.getVManager(getActivity()));
        mAdapter = new ProjectStatementAdapter(R.layout.item_agreement);
        rv.setAdapter(mAdapter);
        mAdapter.addData(lists);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }


}