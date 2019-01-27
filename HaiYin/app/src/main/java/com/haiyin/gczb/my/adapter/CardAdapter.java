package com.haiyin.gczb.my.adapter;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.AccountEntity;
import com.haiyin.gczb.my.entity.CardsEntity;


/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class CardAdapter extends BaseQuickAdapter<CardsEntity.DataBean, BaseViewHolder> {
    public CardAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardsEntity.DataBean item) {
        RelativeLayout rl =helper.getView(R.id.rl_item_card);
        TextView tv = helper.getView(R.id.tv_item_card);
        if(item.getBankName().contains("中国工商银行")){
            rl.setBackgroundResource(R.mipmap.bank_gs);
        }else if(item.getBankName().contains("中国农业银行")){
            rl.setBackgroundResource(R.mipmap.bank_ny);
        }else if(item.getBankName().contains("中国建设银行")){
            rl.setBackgroundResource(R.mipmap.bank_jh);
        }else if(item.getBankName().contains("中国银行")){
            rl.setBackgroundResource(R.mipmap.bank_zg);
        }else if(item.getBankName().contains("中国交通银行")){
            rl.setBackgroundResource(R.mipmap.bank_jt);
        }else {
            rl.setBackgroundResource(R.color.white);
        }
        int i = item.getCardNo().length();
        tv.setText(item.getCardNo().substring(i-5));
    }
}
