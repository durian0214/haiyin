package com.haiyin.gczb.home.adapter;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;

import com.haiyin.gczb.R;
import com.haiyin.gczb.home.entity.MessageListsEntity;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class MessageAdapter extends BaseQuickAdapter<MessageListsEntity.DataBean, BaseViewHolder> {
    public MessageAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageListsEntity.DataBean item) {
        helper.setText(R.id.tv_item_message_time,item.getCreateDate());
        helper.setText(R.id.tv_item_message_context,item.getContent());
        helper.setText(R.id.tv_item_message_title,item.getTitle());

    }
}
