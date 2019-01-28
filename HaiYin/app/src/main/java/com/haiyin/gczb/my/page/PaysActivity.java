package com.haiyin.gczb.my.page;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.PaysAdapter;
import com.haiyin.gczb.my.entity.PaysEntity;
import com.haiyin.gczb.my.presenter.MyWalletPresenter;
import com.haiyin.gczb.utils.Arith;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/28.
 */
public class PaysActivity extends BaseActivity implements BaseView {
    private MyWalletPresenter myWalletPresenter;


    PaysAdapter mAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;
    @BindView(R.id.tv_pay_record_amount)
    TextView tvAmount;
    @BindView(R.id.tv_pay_record_label)
    TextView tvLabel;
    @BindView(R.id.tv_pay_record_carry_acount)
    TextView tvCarryAcount;
    @BindView(R.id.tv_pay_record_carry)
    TextView tvCarry;
    @BindView(R.id.tv_pay_record_uncarry_acount)
    TextView tvUncarryAcount;
    @BindView(R.id.tv_pay_record_uncarry)
    TextView tvUncarry;
    @BindView(R.id.btn_pay_record)
    Button btn;
    @BindView(R.id.rv_pay_record)
    RecyclerView rv;

    @OnClick(R.id.btn_pay_record)
    public void to() {
        //提现
        MyUtils.showShort("未开放");
    }

    int type; //1:线上 2：线下

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_record;
    }

    @Override
    public void initView() {

        myWalletPresenter = new MyWalletPresenter(this);
        type = getIntent().getBundleExtra("bundle").getInt("type");
        if (type == 1) {
            tvLabel.setText("可提现金额");
            tvCarry.setText("线上总成交额");
            tvUncarry.setText("不可提现金额");
            btn.setVisibility(View.VISIBLE);
        } else {
            tvLabel.setText("总成交额");
            tvCarry.setText("已到账");
            tvUncarry.setText("未到账");
            btn.setVisibility(View.INVISIBLE);
        }
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new PaysAdapter(R.layout.item_during_month);
        rv.setAdapter(mAdapter);
        initRefreshLayout();
        getData();
    }


    private void getData() {
        if (type==1) {
            myWalletPresenter.entityOnlinePays(page, pageNum);
        }else if(type ==2){
            myWalletPresenter.entityOfflinePays(page, pageNum);
        }
    }

    @Override
    public void success(int code, Object data) {

        if(code == ApiConfig.ENTITY_ONLINE_PAYS){
            PaysEntity entity = (PaysEntity) data;
            tvAmount.setText(Arith.div_text(((PaysEntity) data).getData().getTotalAmount(),100));
            tvCarryAcount.setText(Arith.div_text(((PaysEntity) data).getData().getArrivalAmount(),100)+"元");
            tvUncarryAcount.setText(Arith.div_text(((PaysEntity) data).getData().getUnArrivalAmount(),100)+"元");
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
        }else if(code == ApiConfig.ENTITY_OFFLINE_PAYS){
            tvAmount.setText(Arith.div_text(((PaysEntity) data).getData().getTotalAmount(),100));
            tvCarryAcount.setText(Arith.div_text(((PaysEntity) data).getData().getArrivalAmount(),100));
            tvUncarryAcount.setText(Arith.div_text(((PaysEntity) data).getData().getUnArrivalAmount(),100));
            PaysEntity entity = (PaysEntity) data;
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
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
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
