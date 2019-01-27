package com.haiyin.gczb.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.home.adapter.SearchNewAdapter;
import com.haiyin.gczb.home.entity.SearchNewsResultsEntity;
import com.haiyin.gczb.home.page.NewsDetailActivity;
import com.haiyin.gczb.home.presenter.SearchPresenter;
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
public class NewsFragment extends BaseFragment implements BaseView {
    SearchPresenter searchPresenter;
    @BindView(R.id.rv_fragment_news)
    MyRecyclerView rv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout srl;
    SearchNewAdapter mAdapter;
    String str ;
    private int page = 1;
    private int pageNum = 20;
    public static NewsFragment getInstance(String str) {
        NewsFragment fragment = new NewsFragment();
        fragment.str = str;
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_news;
    }

    @Override
    protected void init(View view) {
        searchPresenter = new SearchPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        mAdapter = new SearchNewAdapter(R.layout.item_home_news);
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SearchNewsResultsEntity.DataBean bean = (SearchNewsResultsEntity.DataBean) adapter.getData().get(position);
                Intent mIntent = new Intent(getActivity(), NewsDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("id", bean.getNewsId());
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
        searchPresenter.toSearch(1,page,pageNum,str);
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
        SearchNewsResultsEntity entity = (SearchNewsResultsEntity) data;
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
        mAdapter.addData(entity.getData());
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }
}
