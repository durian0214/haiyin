package com.haiyin.gczb.sendPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.durian.lib.glide.GlideUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.utils.UploadHelper;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * Created
 * by durian
 * 2019/1/22.
 */
public class ImgsAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> urls ;

    private LayoutInflater mInflater;

    public ImgsAdapter(Context mContext,List<String > urls) {
        this.mContext =mContext;
        this.urls = urls;
        mInflater = LayoutInflater.from(mContext);
    }

    public void addImg(String url) {
        urls.add(url);
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ItemViewTag viewTag;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_img_gv, null);
            // construct an item tag
            viewTag = new ItemViewTag((RoundedImageView) convertView.findViewById(R.id.img_item_img_gv),
                    (Button) convertView.findViewById(R.id.btn_item_img_gv));
            convertView.setTag(viewTag);
        } else {
            viewTag = (ItemViewTag) convertView.getTag();
        }

        viewTag.mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeFiles(position);
            }
        });
        GlideUtil.loaderCornersImg(mContext,viewTag.mIcon, UploadHelper.getPriUrl(urls.get(position)));
        return convertView;
    }

    private void removeFiles(int position) {
        urls.remove(position);
        this.notifyDataSetChanged();
    }

    class ItemViewTag {
        protected RoundedImageView mIcon;
        protected Button mClose;


        public ItemViewTag(RoundedImageView icon, Button close) {
            this.mClose = close;
            this.mIcon = icon;
        }
    }

}