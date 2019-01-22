package com.haiyin.gczb.my.adapter;

import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.ProjectCooperateEntity;
import com.haiyin.gczb.order.entity.DuringMonthEntity;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;

import com.haiyin.gczb.order.entity.DuringMonthEntity;
import com.haiyin.gczb.utils.Arith;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class CumulativeAdapter  extends BaseQuickAdapter<ProjectCooperateEntity.DataBean.DataListBean, BaseViewHolder> {
    public CumulativeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectCooperateEntity.DataBean.DataListBean item) {
        helper.setText(R.id.tv_item_payment_detail_name,item.getTitle());
        helper.setText(R.id.tv_item_payment_detail_price,item.getCreateDate());
        helper.setText(R.id.tv_item_payment_detail_type,"+"+ Arith.div_text(item.getAmount(),100));

    }
}
