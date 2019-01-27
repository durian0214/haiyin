package com.haiyin.gczb.order.page;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.durian.lib.base.BaseView;

import butterknife.BindView;

import com.durian.lib.glide.GlideUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.demandHall.entity.ProjectDetailEntity;
import com.haiyin.gczb.demandHall.presenter.ProjectPresenter;
import com.haiyin.gczb.order.entity.OrderListsEntity;
import com.haiyin.gczb.order.presenter.OrderPresenter;
import com.haiyin.gczb.utils.Arith;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.MyUtils;

/**
 * Created
 * by durian
 * 2019/1/6.
 * 订单详情
 */
public class OrderDetailActivity extends BaseActivity implements BaseView {
    OrderPresenter orderPresenter;
    ProjectPresenter projectPresenter;
    @BindView(R.id.tv_order_detail_name)
    TextView tvName;
    @BindView(R.id.img_order_detail)
    ImageView img;
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
    Button btn;
    @BindView(R.id.tv_order_detail_status)
    TextView tvStatus;

    @Override
    public void success(int code, Object data) {
        final ProjectDetailEntity entity = (ProjectDetailEntity) data;
        if (entity.getData().getProjectStatus() == 6) {
            if (Constant.userType == 4) {
                btn.setVisibility(View.GONE);
                tvStatus.setText("待开票");
            } else {
                tvStatus.setVisibility(View.GONE);
                btn.setText("申请开票");
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        orderPresenter.applyInvoice(entity.getData().getProjectId());
                    }
                });
            }
        } else if (entity.getData().getProjectStatus() == 8) {
            if (Constant.userType == 4) {
                tvStatus.setVisibility(View.VISIBLE);
                btn.setVisibility(View.GONE);
                tvStatus.setText("已开票");
            } else {
                tvStatus.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);
            }

        } else if (entity.getData().getProjectStatus() == 4) {
            tvStatus.setVisibility(View.GONE);
            btn.setText("打款");
        }else {
            tvStatus.setVisibility(View.GONE);
            btn.setVisibility(View.GONE);
        }
        tvName.setText("项目名称:" + entity.getData().getTitle());
        tvDescribe.setText("项目描述:" + entity.getData().getSummary());
        tvStartTime.setText("开工时间:" + entity.getData().getBeginDate());
        tvEndTime.setText("完工时间:" + entity.getData().getEndDate());
        tvAmount.setText("项目金额:" + Arith.div_text(entity.getData().getAmount(),100));
        tvType.setText("行业类型:" + entity.getData().getIndustryName());
        tvCode.setText("生成编码:" );
        tvAddress.setText("项目位置:" + entity.getData().getAddress());
        GlideUtil.loaderImg(this,img,entity.getData().getPic());
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
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
