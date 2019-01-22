package com.haiyin.gczb.order.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.haiyin.gczb.R;
import com.haiyin.gczb.order.entity.OrderListsEntity;
import com.haiyin.gczb.utils.Arith;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class OrderAdapter extends BaseQuickAdapter<OrderListsEntity.DataBean, BaseViewHolder> {
    public OrderAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderListsEntity.DataBean item) {
        helper.setText(R.id.tv_item_demand_hall_name,item.getTitle());
        helper.setText(R.id.tv_item_demand_hall_price, Arith.div_text(item.getAmount(),100)+"元  ");
        helper.setText(R.id.tv_item_demand_hall_time,"项目工期"+item.getDays()+"天");
        helper.setText(R.id.tv_item_demand_hall_classification,item.getSummary());
        helper.setText(R.id.tv_item_demand_hall_address,item.getCityName());
        RelativeLayout rl =helper.getView(R.id.rl_item_demand_hall_status);
        Button btn =helper.getView(R.id.btn_item_demand_hall_status);
        helper.addOnClickListener(R.id.ll_item_demand_hall);
        rl  .setVisibility(View.VISIBLE);
        if(item.getProjectStatus().equals("6")){
            btn.setText("申请开票");
        }else if(item.getProjectStatus().equals("8")){
            btn.setText("查看发票");
        }else if(item.getProjectStatus().equals("4")){
            btn.setText("打款");
        }else{
            rl.setVisibility(View.GONE);
        }
        helper.addOnClickListener(R.id.btn_item_demand_hall_status);

    }
}
