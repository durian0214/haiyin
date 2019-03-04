package com.haiyin.gczb.welcome.page;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.haiyin.gczb.MainActivity;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.welcome.adapter.GuideAdapter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by durian on 2017/11/2.
 * 引导页
 */

public class GuideActivity extends BaseActivity {
    private static final String TAG = GuideActivity.class.getSimpleName();
    private GuideAdapter mAdapter;
    private int[] imgs = {R.mipmap.guide_1};
    private List<View> lists = new ArrayList<>();

    @BindView(R.id.vp_guide)
    ViewPager vp;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView() {
        isShowTitle(false);
        setTitle("引导页");
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                LogUtil.i("---", "1");
            }

            @Override
            public void onPageSelected(int position) {

//                LogUtil.i("---", "2");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

//                LogUtil.i("---", "3");
            }
        });
        setData();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setData() {
        LayoutInflater layoutInflater = getLayoutInflater();

        for (int i = 0; i < imgs.length; i++) {
            View view = layoutInflater.inflate(R.layout.item_guide, null);

            ImageView img = view.findViewById(R.id.img_item_guide);
            img.setBackground(ContextCompat.getDrawable(this, imgs[i]));
            if (i == imgs.length - 1) {
                Button imgIn = view.findViewById(R.id.btn_guide_in);
                imgIn.setVisibility(View.VISIBLE);
                imgIn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intentJump(mContext, MainActivity.class, null);
                        mContext.finish();
                    }
                });
            }
            lists.add(view);
        }
        mAdapter = new GuideAdapter(lists);
        vp.setAdapter(mAdapter);
    }


}
