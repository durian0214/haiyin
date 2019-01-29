package com.haiyin.gczb.my.page;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.CardAdapter;
import com.haiyin.gczb.my.entity.CardsEntity;
import com.haiyin.gczb.my.presenter.CardPresenter;
import com.haiyin.gczb.utils.MyUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/9.
 */
public class AddCardListsActivity extends BaseActivity implements BaseView {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_card_lists;
    }



    CardPresenter cardPresenter;
    @BindView(R.id.rv_add_card)
    RecyclerView rv;
    private CardAdapter mAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    @BindView(R.id.ll_add_card_new)
    LinearLayout ll;
    @OnClick(R.id.btn_add_card)
    public void addAccount(){
        intentJump(this,AddCardActivity.class,null);
    }
    @OnClick(R.id.tv_add_card)
    public void addAccounttv(){
        intentJump(this,AddCardActivity.class,null);
    }

    @Override
    public void initView() {
        setTitle("添加银行卡");
        cardPresenter = new CardPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new CardAdapter(R.layout.item_card);
        rv.setAdapter(mAdapter);
        rv.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));

        initRefreshLayout();
        getData();
    }

    private void getData() {
        cardPresenter.bankCards(mContext);
    }

    private void initRefreshLayout() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                srl.setLoadmoreFinished(false);
                mAdapter.cleanRV();
                getData();
            }
        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                getData();
            }
        });
    }

    @Override
    public void success(int code, Object data) {
        CardsEntity entity = (CardsEntity) data;
        if (srl != null && srl.isRefreshing()) {
            srl.finishRefresh(200);
        }
        if (srl != null && srl.isLoading()) {
            srl.finishLoadmore(200);
        }
        if (entity.getData().size() < 1000) {
            //关闭加载更多
            srl.setLoadmoreFinished(true);
        }
        if(entity.getData().size()==0){
            ll.setVisibility(View.VISIBLE);
        }else {
            ll.setVisibility(View.GONE);
            mAdapter.addData(entity.getData());
        }

    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }
}
