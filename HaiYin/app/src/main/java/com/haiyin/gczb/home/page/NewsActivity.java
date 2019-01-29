package com.haiyin.gczb.home.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.home.adapter.HomeNewAdapter;
import com.haiyin.gczb.home.entity.NewsListEntity;
import com.haiyin.gczb.home.presenter.NewsPresenter;
import com.haiyin.gczb.utils.MyUtils;
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
public class NewsActivity extends BaseActivity implements BaseView {
    NewsPresenter newsPresenter;
    @BindView(R.id.rv_news)
    RecyclerView rv;
    private HomeNewAdapter mAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    public void initView() {
        setTitle("新闻资讯");
        newsPresenter = new NewsPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mAdapter = new HomeNewAdapter(R.layout.item_home_news);
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NewsListEntity.DataBean bean = (NewsListEntity.DataBean) adapter.getData().get(position);
                Intent mIntent = new Intent(mContext, NewsDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("id", bean.getNewsId());
                mIntent.putExtra("bundle", b);
                startActivity(mIntent);

            }
        });
        initRefreshLayout();
        getData();
    }

    private void getData() {
        newsPresenter.getNewsList(page, pageNum,mContext);
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
        NewsListEntity entity = (NewsListEntity) data;
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
}
