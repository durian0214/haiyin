package com.haiyin.gczb.my.adapter;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.MyContractCompanysEntity;

/**
 * Created
 * by durian
 * 2019/1/18.
 */
public class MyContractAdapter   extends BaseQuickAdapter<MyContractCompanysEntity.DataBean, BaseViewHolder> {
    public MyContractAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyContractCompanysEntity.DataBean item) {
            helper.setText(R.id.tv_item_order_contract_title,item.getCompanyName());
            helper.setText(R.id.tv_item_order_contract_name,"联系人姓名："+item.getContactsName());
            helper.setText(R.id.tv_item_order_contract_time,item.getCreateDate());
            helper.setText(R.id.tv_item_order_contract_phone,"联系人电话："+item.getContactsPhone());
            helper.addOnClickListener(R.id.rl_item_order_contract);
    }
}
