package com.haiyin.gczb.my.adapter;

import android.widget.ImageView;

import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseViewHolder;
import com.durian.lib.glide.GlideUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.my.entity.PaymentCertificateEntity;
import com.haiyin.gczb.utils.UploadHelper;

/**
 * Created
 * by durian
 * 2019/1/26.
 */
public class PaymentCertificateAdapter extends BaseQuickAdapter<PaymentCertificateEntity.DataBean, BaseViewHolder> {
    public PaymentCertificateAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PaymentCertificateEntity.DataBean item) {
        ImageView img = helper.getView(R.id.img_item_payment_certificate);
        GlideUtil.loaderImg(mContext,img, UploadHelper.getInstance().getPriUrl(mContext,item.getProofFile()));
    }
}
