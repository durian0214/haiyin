package com.haiyin.gczb.my.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.TextView;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.my.adapter.DuringMonthAdapter;
import com.haiyin.gczb.my.adapter.PaymentCertificateAdapter;
import com.haiyin.gczb.my.entity.PaymentCertificateEntity;
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

/**
 * Created
 * by durian
 * 2019/1/26.
 */
public class PaymentCertificateFragment  extends BaseFragment implements BaseView {
    ProgressQueryPresenter progressQueryPresenter;
    @BindView(R.id.rv_payment_certificate)
    MyRecyclerView rv;
    int type;
    PaymentCertificateAdapter mAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;

    public static PaymentCertificateFragment getInstance(int type) {
        PaymentCertificateFragment fragment = new PaymentCertificateFragment();
        fragment.type = type;
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_payment_certificate;
    }

    @Override
    protected void init(View view) {
        progressQueryPresenter = new ProgressQueryPresenter(this);
        rv.setLayoutManager(MyUtils.getTableManager(getActivity(),2));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new PaymentCertificateAdapter(R.layout.item_payment_certificate);
        rv.setAdapter(mAdapter);
        initRefreshLayout();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getData();
    }

    private void getData() {
            progressQueryPresenter.taxProofs(page, pageNum, type,getActivity());
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
        PaymentCertificateEntity entity = (PaymentCertificateEntity) data;
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