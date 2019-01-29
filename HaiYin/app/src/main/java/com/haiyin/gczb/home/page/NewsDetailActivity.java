package com.haiyin.gczb.home.page;

import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.durian.lib.base.BaseView;
import com.durian.lib.glide.GlideUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.home.entity.NewsDetailEntity;
import com.haiyin.gczb.home.presenter.NewsPresenter;
import com.haiyin.gczb.utils.MyUtils;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/3.
 */
public class NewsDetailActivity extends BaseActivity implements BaseView {
    NewsPresenter newsPresenter;
    @BindView(R.id.tv_news_detail_title)
    TextView tvTitle;
    @BindView(R.id.tv_news_detail_time)
    TextView tvTime;
    @BindView(R.id.tv_news_detail_content)
    TextView tvContent;
    @BindView(R.id.img_news_detail)
    ImageView img;
    @Override
    public void success(int code, Object data) {
        NewsDetailEntity entity = (NewsDetailEntity) data;
        GlideUtil.loaderImg(this,img,entity.getData().getPic());
        tvTime.setText(entity.getData().getCreateDate());
        tvTitle.setText(entity.getData().getTitle());
        tvContent.setText(Html.fromHtml(entity.getData().getContent()));
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_detail;
    }

    @Override
    public void initView() {
        setTitle("新闻详情");
        String id = getIntent().getBundleExtra("bundle").getString("id");
        newsPresenter = new NewsPresenter(this);
        newsPresenter.getNewsDetail(id,mContext);
    }
}
