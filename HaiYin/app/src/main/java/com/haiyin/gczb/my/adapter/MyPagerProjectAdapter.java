package com.haiyin.gczb.my.adapter;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.GetMyContractProjectsEntity;
import com.haiyin.gczb.my.entity.MyPagerProjectEntity;
import com.haiyin.gczb.utils.Arith;


/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class MyPagerProjectAdapter extends BaseQuickAdapter<MyPagerProjectEntity.DataBean, BaseViewHolder> {
    public MyPagerProjectAdapter(int layoutResId ) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyPagerProjectEntity.DataBean item) {
        helper.setText(R.id.tv_item_demand_hall_name,item.getTitle());
        helper.setText(R.id.tv_item_demand_hall_price, item.getAmount()+"元  ");
        helper.setText(R.id.tv_item_demand_hall_time,"项目工期"+item.getDays()+"天");
        helper.setText(R.id.tv_item_demand_hall_classification,item.getSummary());
        helper.setText(R.id.tv_item_demand_hall_address,item.getCityName());
        helper.addOnClickListener(R.id.btn_item_demand_hall_status);
        RelativeLayout rl = helper.getView(R.id.rl_item_demand_hall_status);
        rl .setVisibility(View.VISIBLE);

        TextView tv =  helper.getView(R.id.tv_item_demand_hall_status);
        Button btn = helper.getView(R.id.btn_item_demand_hall_status);
        if(item.getProjectStatus()==6){
            //待开发票
            btn.setText("申请开票");
            btn.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);

        }else if(item.getProjectStatus()==7){
            //申请开票中
            btn.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
        }else if(item.getProjectStatus()==8){
            //已开票完成
            btn.setText("查看发票");
            btn.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
        }

    }
}
