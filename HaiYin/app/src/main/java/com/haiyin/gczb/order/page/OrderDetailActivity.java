package com.haiyin.gczb.order.page;

import android.view.View;
import android.widget.TextView;

import com.durian.lib.base.BaseView;

import butterknife.BindView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.demandHall.entity.ProjectDetailEntity;
import com.haiyin.gczb.demandHall.presenter.ProjectPresenter;
import com.haiyin.gczb.order.entity.OrderListsEntity;
import com.haiyin.gczb.order.presenter.OrderPresenter;

/**
 * Created
 * by durian
 * 2019/1/6.
 * 订单详情
 */
public class OrderDetailActivity extends BaseActivity implements BaseView {
    OrderPresenter orderPresenter;
    ProjectPresenter projectPresenter;
    private   OrderListsEntity.DataBean data;
    @BindView(R.id.tv_order_detail_name)
    TextView tvName;
    @BindView(R.id.tv_order_detail_describe)
    TextView tvDescribe;
    @BindView(R.id.tv_order_detail_starte_time)
    TextView tvStartTime;
    @BindView(R.id.tv_order_detail_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_order_detail_projec_amount)
    TextView tvAmount;
    @BindView(R.id.tv_order_detail_industry_type)
    TextView tvType;
    @BindView(R.id.tv_order_detail_code)
    TextView tvCode;
    @BindView(R.id.tv_order_detail_industry_address)
    TextView tvAddress;
    @BindView(R.id.btn_order_detail)
    TextView btn;

    @Override
    public void success(int code, Object data) {

        ProjectDetailEntity entity = (ProjectDetailEntity) data;
        if(entity.getData().getProjectStatus()==6){
            orderPresenter.applyInvoice(entity.getData().getProjectId());
            btn.setText("申请开票");
        }else if(entity.getData().getProjectStatus()==8){
            btn.setVisibility(View.GONE);
        }else if(entity.getData().getProjectStatus()==4){
            btn.setText("打款");
        }
        tvName.setText("项目名称:"+entity.getData().getTitle());
        tvDescribe.setText("项目描述:"+entity.getData().getSummary());
        tvStartTime.setText("开工时间:"+entity.getData().getSummary());
        tvEndTime.setText("完工时间:"+entity.getData().getTitle());
        tvAmount.setText("项目金额:"+entity.getData().getTitle());
        tvType.setText("行业类型:"+entity.getData().getTitle());
        tvCode.setText("生成编码:"+entity.getData().getTitle());
        tvAddress.setText("项目位置:"+entity.getData().getTitle());
    }

    @Override
    public void netError(String msg) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {
        orderPresenter = new OrderPresenter(this);
        projectPresenter = new ProjectPresenter(this);

        setTitle("详情");
        btn.setVisibility(View.VISIBLE);
        String id = getIntent().getBundleExtra("bundle").getString("id");
        projectPresenter.getProjectDetail(id);
    }
}
