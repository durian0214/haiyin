package com.haiyin.gczb.order.page;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.durian.lib.base.BaseView;

import butterknife.BindView;

import com.durian.lib.glide.GlideUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.demandHall.entity.ProjectDetailEntity;
import com.haiyin.gczb.demandHall.presenter.ProjectPresenter;
import com.haiyin.gczb.my.page.CheckNotesActivity;
import com.haiyin.gczb.order.entity.OrderListsEntity;
import com.haiyin.gczb.order.presenter.OrderPresenter;
import com.haiyin.gczb.utils.Arith;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.http.ApiConfig;

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
    @BindView(R.id.ll_order_detail_code)
    LinearLayout llCode;
    @BindView(R.id.tv_order_detail_status)
    TextView tvStatus;

    @Override
    public void success(int code, Object data) {
        if(ApiConfig.PROJECT_DETAIL ==code){
            final ProjectDetailEntity entity = (ProjectDetailEntity) data;
            if (Constant.userType == 1) {
                if (entity.getData().getProjectStatus() == 8) {
                    tvStatus.setVisibility(View.GONE);
                    btn.setText("查看开票");
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent mIntent = new Intent(mContext, CheckNotesActivity.class);
                            Bundle b = new Bundle();
                            if (entity.getData().getNeedType() == 1) {
                                b.putString("url", entity.getData().getInvoiceFileCompany());
                            } else {
                                b.putString("url", entity.getData().getInvoiceFileEntity());
                            }
                            mIntent.putExtra("bundle", b);
                            startActivity(mIntent);
                        }
                    });
                } else if (entity.getData().getProjectStatus() == 7) {
                    tvStatus.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.GONE);
                    tvStatus.setText("申请开票中");
                } else if (entity.getData().getProjectStatus() == 6) {
                    tvStatus.setVisibility(View.GONE);
                    btn.setText("申请开票");
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            orderPresenter.applyInvoice(entity.getData().getProjectId());
                        }
                    });
                } else if (entity.getData().getProjectStatus() == 5) {
                    tvStatus.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.GONE);
                    tvStatus.setText("待审核");
                } else if (entity.getData().getProjectStatus() == 4) {
                    tvStatus.setVisibility(View.GONE);
                    btn.setText("打款");
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent mIntent = new Intent(mContext, UploadDocumentsActivity.class);
                            Bundle b = new Bundle();
                            b.putString("id", entity.getData().getProjectId());
                            mIntent.putExtra("bundle", b);
                            startActivity(mIntent);
                        }
                    });
                } else {
                    tvStatus.setVisibility(View.GONE);
                    btn.setVisibility(View.GONE);
                }
            } else if (Constant.userType == 2) {
                if (entity.getData().getProjectStatus() == 8) {
                    tvStatus.setVisibility(View.GONE);
                    btn.setText("查看开票");
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent mIntent = new Intent(mContext, CheckNotesActivity.class);
                            Bundle b = new Bundle();
                            if (Constant.userType== 1) {
                                b.putString("url", entity.getData().getInvoiceFileCompany());
                            } else if(Constant.userType==2) {
                                b.putString("url", entity.getData().getInvoiceFileEntity());
                            }
                            mIntent.putExtra("bundle", b);
                            startActivity(mIntent);
                        }
                    });
                } else if (entity.getData().getProjectStatus() == 7) {
                    tvStatus.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.GONE);
                    tvStatus.setText("申请开票中");
                } else if (entity.getData().getProjectStatus() == 6) {
                    tvStatus.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.GONE);
                    tvStatus.setText("待开票");
                } else if (entity.getData().getProjectStatus() == 5) {
                    tvStatus.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.GONE);
                    tvStatus.setText("待审核");
                } else if (entity.getData().getProjectStatus() == 4) {
                    tvStatus.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.GONE);
                    tvStatus.setText("待打款");
                } else {
                    tvStatus.setVisibility(View.GONE);
                    btn.setVisibility(View.GONE);
                }
            } else if (Constant.userType == 4) {
                if (entity.getData().getProjectStatus() == 8) {
                    tvStatus.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.GONE);
                    tvStatus.setText("已开票");
                } else if (entity.getData().getProjectStatus() == 7) {
                    btn.setVisibility(View.GONE);
                    tvStatus.setText("申请开票中");
                } else if (entity.getData().getProjectStatus() == 6) {
                    btn.setVisibility(View.GONE);
                    tvStatus.setText("待开票");
                } else if (entity.getData().getProjectStatus() == 5) {
                    btn.setVisibility(View.GONE);
                    tvStatus.setText("待审核");
                } else if (entity.getData().getProjectStatus() == 4) {
                    tvStatus.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.GONE);
                    tvStatus.setText("已开票");
                } else {
                    tvStatus.setVisibility(View.GONE);
                    btn.setVisibility(View.GONE);
                }
            }

            tvName.setText("项目名称:" + entity.getData().getTitle());
            tvDescribe.setText("项目描述:" + entity.getData().getSummary());
            tvStartTime.setText("开工时间:" + entity.getData().getBeginDate());
            tvEndTime.setText("完工时间:" + entity.getData().getEndDate());
            tvAmount.setText("项目金额:" + Arith.div_text(entity.getData().getAmount(), 100));
            tvType.setText("行业类型:" + entity.getData().getIndustryName());
            if(entity.getData().isIsSelfCompany()){
                llCode.setVisibility(View.VISIBLE);
                tvCode.setText("生成编码:"+entity.getData().getCodeNo());
            }else {
                llCode.setVisibility(View.GONE);
            }
            tvAddress.setText("项目位置:" + entity.getData().getAddress());
            GlideUtil.loaderImg(this, img, entity.getData().getPic());
        }else if(ApiConfig.APPLY_INVOICE ==code){
            MyUtils.showShort("申请成功");
            this.finish();
        }
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
