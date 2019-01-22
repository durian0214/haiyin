package com.haiyin.gczb.my.adapter;

import com.haiyin.gczb.my.entity.AccountEntity;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;

import com.haiyin.gczb.my.entity.AccountEntity;


/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class AccountAdapter extends BaseQuickAdapter<AccountEntity, BaseViewHolder> {
    public AccountAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AccountEntity item) {

    }
}
