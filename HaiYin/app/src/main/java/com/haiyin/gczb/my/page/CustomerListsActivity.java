package com.haiyin.gczb.my.page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.adapter.CustomerListsAdapter;
import com.haiyin.gczb.my.entity.SalesCompanyListEntity;
import com.haiyin.gczb.my.presenter.CustomerPresenter;
import com.haiyin.gczb.user.page.CustomerInformationActivity;
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
 * 2019/1/25.
 */
public class CustomerListsActivity extends BaseActivity implements BaseView {
    CustomerPresenter customerPresenter;
    @BindView(R.id.rv_customer_lists)
    MyRecyclerView rv;
    //1=负责客户列表 2=待完善客户 3=已完善客户 4=公司众包客户列表
    // 5=待上传合同协议客户列表 6=待开票客户列表 7= 已开票客户列表 8=订单合同客户列表(找公司合同)
    int type ;
    CustomerListsAdapter mAdapter;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;
    String title ;

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

        SalesCompanyListEntity entity = (SalesCompanyListEntity) data;
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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_lists;
    }

    @Override
    public void initView() {
        type = getIntent().getBundleExtra("bundle").getInt("type");
         title = getIntent().getBundleExtra("bundle").getString("title");
        setTitle(title);
        customerPresenter = new CustomerPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new CustomerListsAdapter(R.layout.item_customer);
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SalesCompanyListEntity.DataBean bean = (SalesCompanyListEntity.DataBean) adapter.getData().get(position);
                if(type==4){
                    Intent mIntent = new Intent(mContext, DemandHallPojectActivity.class);
                    Bundle b = new Bundle();
                    b.putString("id", bean.getCompanyId());
                    mIntent.putExtra("bundle", b);
                    startActivity(mIntent);
                }else if(type==5){
                    Intent mIntent = new Intent(mContext, PushProjectActivity.class);
                    Bundle b = new Bundle();
                    b.putString("id", bean.getCompanyId());
                    b.putInt("type", 5);
                    b.putString("title", title);
                    mIntent.putExtra("bundle", b);
                    startActivity(mIntent);
                }else if(type==6){
                    //待开票
                    Intent mIntent = new Intent(mContext, PushProjectActivity.class);
                    Bundle b = new Bundle();
                    b.putString("id", bean.getCompanyId());
                    b.putInt("type", 3);
                    b.putString("title", title);
                    mIntent.putExtra("bundle", b);
                    startActivity(mIntent);
                }else if(type==7){
                    //已开票
                    Intent mIntent = new Intent(mContext, PushProjectActivity.class);
                    Bundle b = new Bundle();
                    b.putString("id", bean.getCompanyId());
                    b.putInt("type", 4);
                    b.putString("title", title);
                    mIntent.putExtra("bundle", b);
                    startActivity(mIntent);
                }else if(type==8){
                    //合作合同
                    Intent mIntent = new Intent(mContext, PushProjectActivity.class);
                    Bundle b = new Bundle();
                    b.putString("id", bean.getCompanyId());
                    b.putInt("type", 6);
                    b.putString("title", title);
                    mIntent.putExtra("bundle", b);
                    startActivity(mIntent);
                }else if(type ==1){
                        //客户列表
                    Intent mIntent = new Intent(mContext, CustomerInformationActivity.class);
                    Bundle b = new Bundle();
                    b.putString("id", bean.getCompanyId());
                    mIntent.putExtra("bundle", b);
                    startActivity(mIntent);
                }
                else if(type ==9){
                    //发包金额
                    Intent mIntent = new Intent(mContext, SalesmanSendPackageActivity.class);
                    Bundle b = new Bundle();
                    b.putString("id", bean.getCompanyId());
                    mIntent.putExtra("bundle", b);
                    startActivity(mIntent);
                }
            }
        });
        initRefreshLayout();
        getData();
    }
    private void getData() {
        customerPresenter.salescompanyList(page,pageNum,type,mContext);
    }
}
