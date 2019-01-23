package com.haiyin.gczb.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.durian.lib.glide.GlideUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.home.entity.GetPicsEntity;
import com.haiyin.gczb.home.page.WebActivity;

import java.util.List;

public class IconAdapter extends ArrayAdapter<GetPicsEntity.DataBean> {

    private int resource;
    private Context mContext;
    private List<GetPicsEntity.DataBean> lists;
    public IconAdapter(Context context, int textViewResourceId, List<GetPicsEntity.DataBean> objects) {
        super(context, textViewResourceId, objects);
        resource = textViewResourceId;
        this.mContext = context;
        this.lists = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        RelativeLayout layout = null;
        if (convertView == null) {
            layout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(resource, null);
        } else {
            layout = (RelativeLayout) convertView;
        }
        ImageView img =  layout.findViewById(R.id.img_item_home_gv);

        TextView tv =  layout.findViewById(R.id.tv_item_home_gv);
        tv.setText(lists.get(position).getTilte());
        GlideUtil.loaderImg(mContext,img,lists.get(position).getPicPath());
        layout.findViewById(R.id.rl_item_home_gv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(mContext,WebActivity.class);
                Bundle b = new Bundle();
                b.putString("title",lists.get(position).getTilte());
                b.putString("url",lists.get(position).getUrlPath());
                mIntent.putExtra("bundle",b);
                mContext.startActivity(mIntent);
            }
        });
        return layout;
    }
}
