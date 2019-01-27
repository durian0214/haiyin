package com.haiyin.gczb.my.adapter;

import android.widget.ImageView;

import com.durian.lib.glide.GlideUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.FrameworkContractEntity;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;

import com.haiyin.gczb.my.entity.FrameworkContractEntity;
import com.haiyin.gczb.order.entity.DuringMonthEntity;
import com.haiyin.gczb.utils.UploadHelper;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class FrameworkContractAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public FrameworkContractAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView img = helper.getView(R.id.img_item_agreement);
        GlideUtil.loaderImg(mContext,img, UploadHelper.getInstance().getPriUrl(mContext,item));
    }
}
