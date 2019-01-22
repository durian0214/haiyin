package com.haiyin.gczb.home.adapter;

import com.haiyin.gczb.home.entity.SearchResultsEntity;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;

import com.haiyin.gczb.R;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class SearchProjectAdapter extends BaseQuickAdapter<SearchResultsEntity.DataBean.ProjectListBean, BaseViewHolder> {
    public SearchProjectAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchResultsEntity.DataBean.ProjectListBean item) {
        helper.setText(R.id.tv_item_demand_hall_name,item.getTitle());
        helper.setText(R.id.tv_item_demand_hall_price,item.getAmount());
        helper.setText(R.id.tv_item_demand_hall_time,item.getDays());
        helper.setText(R.id.tv_item_demand_hall_classification,item.getSummary());
        helper.setText(R.id.tv_item_demand_hall_address,item.getCityName());
        helper.addOnClickListener(R.id.ll_item_demand_hall);
    }
}
