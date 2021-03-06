package com.haiyin.gczb.my.adapter;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.PaysEntity;
import com.haiyin.gczb.my.entity.ProjectCooperateEntity;
import com.haiyin.gczb.utils.Arith;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
    public class PaysAdapter extends BaseQuickAdapter<PaysEntity.DataBean.DataListBean, BaseViewHolder> {
    public PaysAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PaysEntity.DataBean.DataListBean item) {
        helper.setText(R.id.tv_item_payment_detail_name,item.getTitle());
        helper.setText(R.id.tv_item_payment_detail_price,item.getCreateDate());
        helper.setText(R.id.tv_item_payment_detail_type,"+"+ item.getAmount());

    }
}
