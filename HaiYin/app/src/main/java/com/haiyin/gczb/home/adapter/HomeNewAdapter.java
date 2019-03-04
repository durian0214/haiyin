package com.haiyin.gczb.home.adapter;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.durian.lib.glide.GlideUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import com.haiyin.gczb.R;
import com.haiyin.gczb.home.entity.NewsListEntity;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class HomeNewAdapter extends BaseQuickAdapter<NewsListEntity.DataBean, BaseViewHolder> {
    public HomeNewAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsListEntity.DataBean item) {
        RoundedImageView img =   helper.getView(R.id.img_item_home_news);
        GlideUtil.loaderCornersImg(mContext,img,item.getPic());
        helper.setText(R.id.tv_item_home_news_title,item.getTitle());
        helper.setText(R.id.tv_item_home_news_context,item.getSummary());
        helper.setText(R.id.tv_item_home_news_time,item.getCreateDate());
        helper.addOnClickListener(R.id.rl_home_news);
    }
}
