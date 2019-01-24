package com.haiyin.gczb.my.adapter;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.ProjectCooperateEntity;
import com.haiyin.gczb.my.entity.SalesCompanyListEntity;
import com.haiyin.gczb.utils.Arith;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
    public class CustomerAdapter extends BaseQuickAdapter<SalesCompanyListEntity.DataBean, BaseViewHolder> {
    public CustomerAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SalesCompanyListEntity.DataBean item) {
        helper.setText(R.id.tv_item_customer_title,item.getCompanyName());
        helper.setText(R.id.tv_item_customer_time,item.getCreateDate());
        helper.setText(R.id.tv_item_customer_phone,"联系人电话："+ item.getContactsPhone());
        helper.setText(R.id.tv_item_customer_name,"联系人姓名："+ item.getContactsName());

    }
}
