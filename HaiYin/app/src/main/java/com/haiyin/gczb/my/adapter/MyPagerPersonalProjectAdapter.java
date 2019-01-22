package com.haiyin.gczb.my.adapter;

import android.view.View;
import android.widget.Button;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.MyPagerPersonalProjectEntity;
import com.haiyin.gczb.my.entity.MyPagerProjectEntity;
import com.haiyin.gczb.utils.Arith;


/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class MyPagerPersonalProjectAdapter extends BaseQuickAdapter<MyPagerPersonalProjectEntity.DataBean, BaseViewHolder> {
    public MyPagerPersonalProjectAdapter(int layoutResId ) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyPagerPersonalProjectEntity.DataBean item) {
        helper.setText(R.id.tv_item_demand_hall_name,item.getTitle());
        helper.setText(R.id.tv_item_demand_hall_price, Arith.div_text(item.getAmount(),100)+"元  ");
        helper.setText(R.id.tv_item_demand_hall_time,"项目工期"+item.getDays()+"天");
        helper.setText(R.id.tv_item_demand_hall_classification,item.getSummary());
        helper.setText(R.id.tv_item_demand_hall_address,item.getCityName());
        helper.addOnClickListener(R.id.ll_item_demand_hall);
        Button  b = helper.getView(R.id.rl_item_demand_hall_status);
       b .setVisibility(View.VISIBLE);

    }
}
