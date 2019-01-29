package com.haiyin.gczb.home.page;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.home.adapter.MessageAdapter;
import com.haiyin.gczb.home.entity.MessageListsEntity;
import com.haiyin.gczb.home.presenter.MessagePresenter;
import com.haiyin.gczb.utils.MyUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/4.
 */
public class MessageListActivity extends BaseActivity implements BaseView {
    @BindView(R.id.rv_message_list)
    RecyclerView rv;
    private MessageAdapter mAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;

    // 1=系统消息, 2=钱包消息, 3=任务消息
    private int type = 0;
    MessagePresenter messagePresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_list;
    }

    @Override
    public void initView() {
        type = getIntent().getBundleExtra("bundle").getInt("type");
        messagePresenter = new MessagePresenter(this);
        setTitle("消息列表");
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mAdapter = new MessageAdapter(R.layout.item_message);
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        initRefreshLayout();
        getData();
    }

    private void getData() {
        messagePresenter.getMessageLists(type,page,pageNum,mContext);
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
        MessageListsEntity entity = (MessageListsEntity) data;
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
