package com.haiyin.gczb.my.adapter;

import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.AccountEntity;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;


/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class AccountAdapter extends BaseQuickAdapter<AccountEntity.DataBean, BaseViewHolder> {
    public AccountAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountEntity.DataBean item) {
        helper.setText(R.id.tv_item_account_name,"姓名："+item.getName());
        helper.setText(R.id.tv_item_account_phone,"手机号："+item.getMobile());
    }
}
