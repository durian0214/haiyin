package com.haiyin.gczb.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.LinearLayout;

import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.my.adapter.MyPagerPersonalProjectAdapter;
import com.haiyin.gczb.my.entity.MyPagerPersonalProjectEntity;
import com.haiyin.gczb.my.page.CheckNotesActivity;
import com.haiyin.gczb.my.presenter.MyPagerPersonalProjectPresenter;
import com.haiyin.gczb.order.adapter.OrderAdapter;
import com.haiyin.gczb.order.entity.OrderListsEntity;
import com.haiyin.gczb.order.entity.TabEntity;
import com.haiyin.gczb.order.page.OrderDetailActivity;
import com.haiyin.gczb.order.page.UploadDocumentsActivity;
import com.haiyin.gczb.order.presenter.OrderPresenter;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.view.MyRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class OrderFragment extends BaseFragment implements BaseView {
    //    private View mDecorView;
    OrderPresenter orderPresenter;
    MyPagerPersonalProjectPresenter myPagerPersonalProjectPresenter;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabProjectEntities = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabOrderStatusEntities = new ArrayList<>();
    @BindView(R.id.ctl_order)
    CommonTabLayout ctl;
    @BindView(R.id.ctl_order_project)
    CommonTabLayout ctlProject;
    @BindView(R.id.ctl_order_status)
    CommonTabLayout ctlOrderStatus;
    @BindView(R.id.ll_no_data)
    LinearLayout llNoData;
    private final String[] mTitles = {"当月订单", "全部订单"};
    private final String[] mTitlesProject = {"待接单", "待打款", "待开票", "已完成"};
    private final String[] mTitlesOrderStatus = {"全部订单", "待打款", "待开票", "已完成"};
    @BindView(R.id.rv_order)
    MyRecyclerView rv;
    OrderAdapter mAdapter;
    MyPagerPersonalProjectAdapter myPagerPersonalProjectAdapter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout srl;
    private int page = 1;
    private int pageNum = 20;
    //企业 1=是本月 2=非本月订单
    //个体  1=是本月 2=非本月订单
    private int type = 0;
    //1=待接单 2=待打款订单 3=待开票订单 4=已完成订单
    private int orderType = 0;

    private int userType = 0;
    private int removePosition;

    @Override
    public void success(int code, Object data) {
        if (code == ApiConfig.ORDER_LISTS) {
            OrderListsEntity entity = (OrderListsEntity) data;
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
            if (mAdapter.getData().size()==0){
                llNoData.setVisibility(View.VISIBLE);
            }else{
                llNoData.setVisibility(View.GONE);
            }
        } else if (code == ApiConfig.APPLY_INVOICE) {
            mAdapter.remove(removePosition);
        } else if (code == ApiConfig.ENTITY_PROJECTS) {
            MyPagerPersonalProjectEntity entity = (MyPagerPersonalProjectEntity) data;
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
            myPagerPersonalProjectAdapter.addData(entity.getData());
            if (myPagerPersonalProjectAdapter.getData().size()==0){
                llNoData.setVisibility(View.VISIBLE);
            }else{
                llNoData.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int setView() {
        return R.layout.fragment_order;
    }

    @Override
    protected void init(View view) {
        orderPresenter = new OrderPresenter(this);
        rv.setLayoutManager(MyUtils.getVManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        myPagerPersonalProjectPresenter = new MyPagerPersonalProjectPresenter(this);
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], 0, 0));
        }
        for (int i = 0; i < mTitlesProject.length; i++) {
            mTabProjectEntities.add(new TabEntity(mTitlesProject[i], 0, 0));
        }
        for (int i = 0; i < mTitlesOrderStatus.length; i++) {
            mTabOrderStatusEntities.add(new TabEntity(mTitlesOrderStatus[i], 0, 0));
        }
//        mDecorView = getActivity().getWindow().getDecorView();
        ctl.setTabData(mTabEntities);
        ctlProject.setTabData(mTabProjectEntities);
        ctlOrderStatus.setTabData(mTabOrderStatusEntities);
        ctl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                type = position+1;
                orderType = 1;

                ctlProject.setCurrentTab(0);
                cleanData();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        ctlProject.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                orderType = position+1;
                cleanData();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        ctlOrderStatus.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    type = 1;
                } else if (position == 1) {
                    type = 2;
                } else if (position == 2) {
                    type = 3;
                } else if (position == 3) {
                    type = 4;
                }
                cleanData();
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        initRefreshLayout();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        showData();
    }

    private void getData() {
        if (Constant.userType == 1) {
            orderPresenter.getOrderLists(type,orderType, page, pageNum, getActivity());
        } else if (Constant.userType == 2) {
            myPagerPersonalProjectPresenter.entityProjects(page, pageNum, type, getActivity());
        }
    }

    private void cleanData() {
        page = 1;
        srl.setLoadmoreFinished(false);
        if (mAdapter != null) {
            mAdapter.cleanRV();
        }
        if (myPagerPersonalProjectAdapter != null) {
            myPagerPersonalProjectAdapter.cleanRV();
        }
        getData();
    }

    private void initRefreshLayout() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                cleanData();

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

    public void showData() {
        if (userType == Constant.userType) {

        } else {
            userType = Constant.userType;
            if (Constant.userType == 1) {

                ctlOrderStatus.setVisibility(View.GONE);
                ctl.setVisibility(View.VISIBLE);
                ctlProject.setVisibility(View.VISIBLE);
                ctl.setCurrentTab(0);
                type = 1;
                orderType = 1;

                mAdapter = new OrderAdapter(R.layout.item_demand_hall);
                rv.setAdapter(mAdapter);
                mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        OrderListsEntity.DataBean bean = (OrderListsEntity.DataBean) adapter.getData().get(position);
                        removePosition = position;
                        if (view.getId() == R.id.ll_item_demand_hall) {
                            Intent mIntent = new Intent(getActivity(), OrderDetailActivity.class);
                            Bundle b = new Bundle();
//                    b.putSerializable("data", bean);
                            b.putString("id", bean.getProjectId());
                            mIntent.putExtra("bundle", b);
                            startActivity(mIntent);
                        } else if (view.getId() == R.id.btn_item_demand_hall_status) {
                            if (bean.getProjectStatus().equals("6")) {
                                orderPresenter.applyInvoice(bean.getProjectId(), getActivity());
                            } else if (bean.getProjectStatus().equals("8")) {
                                Intent mIntent = new Intent(getActivity(), CheckNotesActivity.class);
                                Bundle b = new Bundle();
//                        b.putSerializable("data", bean);
                                b.putString("url", bean.getInvoiceFileCompany());
                                mIntent.putExtra("bundle", b);
                                startActivity(mIntent);
                            } else if (bean.getProjectStatus().equals("4")) {
                                Intent mIntent = new Intent(getActivity(), UploadDocumentsActivity.class);
                                Bundle b = new Bundle();
                                b.putString("id", bean.getProjectId());
                                mIntent.putExtra("bundle", b);
                                startActivity(mIntent);
                            }

                        }
                    }
                });
            } else if (Constant.userType == 2) {
                ctlOrderStatus.setVisibility(View.VISIBLE);
                ctl.setVisibility(View.GONE);
                ctlProject.setVisibility(View.GONE);
                type = 1;
                myPagerPersonalProjectAdapter = new MyPagerPersonalProjectAdapter(R.layout.item_demand_hall);
                rv.setAdapter(myPagerPersonalProjectAdapter);
                myPagerPersonalProjectAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                        MyPagerPersonalProjectEntity.DataBean bean = (MyPagerPersonalProjectEntity.DataBean) adapter.getData().get(position);
                        removePosition = position;
                        if (view.getId() == R.id.ll_item_demand_hall) {
                            Intent mIntent = new Intent(getActivity(), OrderDetailActivity.class);
                            Bundle b = new Bundle();
//                          b.putSerializable("data", bean);
                            b.putString("id", bean.getProjectId());
                            mIntent.putExtra("bundle", b);
                            startActivity(mIntent);
                        } else if (view.getId() == R.id.btn_item_demand_hall_status) {
                            if (bean.getProjectStatus() == 6) {
                                orderPresenter.applyInvoice(bean.getProjectId(), getActivity());
                            } else if (bean.getProjectStatus() == 8) {
                                Intent mIntent = new Intent(getActivity(), CheckNotesActivity.class);
                                Bundle b = new Bundle();
//                        b.putSerializable("data", bean);
                                b.putString("url", bean.getInvoiceFileEntity());
                                mIntent.putExtra("bundle", b);
                                startActivity(mIntent);
                            } else if (bean.getProjectStatus() == 4) {
                                Intent mIntent = new Intent(getActivity(), UploadDocumentsActivity.class);
                                Bundle b = new Bundle();
                                b.putString("id", bean.getProjectId());
                                mIntent.putExtra("bundle", b);
                                startActivity(mIntent);
                            }

                        }


                    }
                });
            }
            cleanData();
        }


    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            showData();
        }
    }

}
