package com.haiyin.gczb.my.adapter;

import android.view.View;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.EntityContractsEntity;
import com.haiyin.gczb.my.entity.GetMyContractProjectsEntity;
import com.haiyin.gczb.utils.Arith;


/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class IndividualMyContractProjectAdapter extends BaseQuickAdapter<EntityContractsEntity.DataBean, BaseViewHolder> {
    public IndividualMyContractProjectAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, EntityContractsEntity.DataBean item) {
        helper.setText(R.id.tv_item_demand_hall_name,item.getTitle());
        helper.setText(R.id.tv_item_demand_hall_price, item.getAmount()+"元  ");
        helper.setText(R.id.tv_item_demand_hall_time,"项目工期"+item.getDays()+"天");
        helper.setText(R.id.tv_item_demand_hall_classification,item.getSummary());
        helper.setText(R.id.tv_item_demand_hall_address,item.getCityName());
        helper.addOnClickListener(R.id.ll_item_demand_hall);
        helper.getView(R.id.rl_item_demand_hall_status).setVisibility(View.GONE);
    }
}
