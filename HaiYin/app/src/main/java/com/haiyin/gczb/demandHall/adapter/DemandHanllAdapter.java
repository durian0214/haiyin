package com.haiyin.gczb.demandHall.adapter;

import android.view.View;

import com.haiyin.gczb.demandHall.entity.ProjectListEntity;
import com.haiyin.gczb.utils.Arith;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;

import com.haiyin.gczb.R;


/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class DemandHanllAdapter extends BaseQuickAdapter<ProjectListEntity.DataBean, BaseViewHolder> {
    public DemandHanllAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectListEntity.DataBean item) {
        helper.setText(R.id.tv_item_demand_hall_name,item.getTitle());
        helper.setText(R.id.tv_item_demand_hall_price, item.getAmount()+"元  ");
        helper.setText(R.id.tv_item_demand_hall_time,"项目工期"+item.getDays()+"天");
        helper.setText(R.id.tv_item_demand_hall_classification,item.getSummary());
        helper.setText(R.id.tv_item_demand_hall_address,item.getAddress());
        helper.addOnClickListener(R.id.ll_item_demand_hall);
        helper.getView(R.id.rl_item_demand_hall_status).setVisibility(View.GONE);
    }
}
