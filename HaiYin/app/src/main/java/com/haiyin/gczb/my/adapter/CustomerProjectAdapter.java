package com.haiyin.gczb.my.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.SalesCompanyProjectsEntity;
import com.haiyin.gczb.order.entity.OrderListsEntity;
import com.haiyin.gczb.utils.Arith;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class CustomerProjectAdapter extends BaseQuickAdapter<SalesCompanyProjectsEntity.DataBean, BaseViewHolder> {
    public CustomerProjectAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SalesCompanyProjectsEntity.DataBean item) {
        helper.setText(R.id.tv_item_demand_hall_name, item.getTitle());
        helper.setText(R.id.tv_item_demand_hall_price, Arith.div_text(item.getAmount(), 100) + "元  ");
        helper.setText(R.id.tv_item_demand_hall_time, "项目工期" + item.getDays() + "天");
        helper.setText(R.id.tv_item_demand_hall_classification, item.getSummary());
        helper.setText(R.id.tv_item_demand_hall_address, item.getCityName());
        RelativeLayout rl = helper.getView(R.id.rl_item_demand_hall_status);
        Button btn = helper.getView(R.id.btn_item_demand_hall_status);
        TextView tv = helper.getView(R.id.tv_item_demand_hall_status);

        if (item.getProjectStatus() == 4) {
            btn.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            tv.setText("待打款");
        } else if (item.getProjectStatus() == 6) {

            btn.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            tv.setText("待开票");
        } else if (item.getProjectStatus() == 8) {
            btn.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            tv.setText("已完成");
        } else  if (item.getProjectStatus() == 1) {
            //待上传
            rl.setVisibility(View.GONE);
        }else {
            rl.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.ll_item_demand_hall);
    }
}
