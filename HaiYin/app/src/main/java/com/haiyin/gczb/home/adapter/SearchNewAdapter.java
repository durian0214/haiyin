package com.haiyin.gczb.home.adapter;

import com.haiyin.gczb.home.entity.SearchNewsResultsEntity;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.durian.lib.glide.GlideUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import com.haiyin.gczb.R;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class SearchNewAdapter extends BaseQuickAdapter<SearchNewsResultsEntity.DataBean, BaseViewHolder> {
    public SearchNewAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchNewsResultsEntity.DataBean item) {
        RoundedImageView img =   helper.getView(R.id.img_item_home_news);
        GlideUtil.loaderCornersImg(mContext,img,item.getPic());
        helper.setText(R.id.tv_item_home_news_time,item.getCreateDate());
        helper.setText(R.id.tv_item_home_news_context,item.getSummary());
    }
}
