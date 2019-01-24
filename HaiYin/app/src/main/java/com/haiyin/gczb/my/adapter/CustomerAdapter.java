package com.haiyin.gczb.my.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.ProjectCooperateEntity;
import com.haiyin.gczb.my.entity.SalesCompanyListEntity;
import com.haiyin.gczb.user.page.ImproveCustomerInformationActivity;
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
    protected void convert(BaseViewHolder helper, final SalesCompanyListEntity.DataBean item) {
        helper.setText(R.id.tv_item_customer_title, item.getCompanyName());
        helper.setText(R.id.tv_item_customer_time, item.getCreateDate());
        helper.setText(R.id.tv_item_customer_phone, "联系人电话：" + item.getContactsPhone());
        helper.setText(R.id.tv_item_customer_name, "联系人姓名：" + item.getContactsName());

        Button btn = helper.getView(R.id.btn_item_customer_status);
        TextView tv = helper.getView(R.id.tv_item_customer_status);
        //[客户状态 见常量 1=待完善, 2=待审核, 3=审核通过 ]
        if (item.getStatus() == 1) {
            btn.setText("去完善");
            btn.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent m  = new Intent(mContext, ImproveCustomerInformationActivity.class);
                    Bundle  b  = new Bundle();
                    b.putString("id",item.getCompanyId());
                    m.putExtra("bundle",b);
                    mContext.startActivity(m);
                }
            });
        }else if(item.getStatus() == 2){
            btn.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
            tv.setText("已提交");
        }else if(item.getStatus() == 3){
            btn.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
            tv.setText("已完成");
        }



    }
}
