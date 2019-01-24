package com.haiyin.gczb.my.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.SalesCompanyListEntity;
import com.haiyin.gczb.user.page.ImproveCustomerInformationActivity;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class CustomerListsAdapter extends BaseQuickAdapter<SalesCompanyListEntity.DataBean, BaseViewHolder> {
    public CustomerListsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, final SalesCompanyListEntity.DataBean item) {
        helper.setText(R.id.tv_item_customer_title, item.getCompanyName());
        helper.setText(R.id.tv_item_customer_time, item.getCreateDate());
        helper.setText(R.id.tv_item_customer_phone, "联系人电话：" + item.getContactsPhone());
        helper.setText(R.id.tv_item_customer_name, "联系人姓名：" + item.getContactsName());
        helper.addOnClickListener(R.id.rl_item_customer);
        Button btn = helper.getView(R.id.btn_item_customer_status);
        TextView tv = helper.getView(R.id.tv_item_customer_status);
        tv.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
    }
}
