package com.haiyin.gczb.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.haiyin.gczb.MainActivity;
import com.haiyin.gczb.home.adapter.HomeNewAdapter;
import com.haiyin.gczb.home.adapter.IconAdapter;
import com.haiyin.gczb.home.entity.GetCityEntity;
import com.haiyin.gczb.home.page.CityActivity;
import com.haiyin.gczb.home.page.NewsActivity;
import com.haiyin.gczb.home.page.NewsDetailActivity;
import com.haiyin.gczb.home.presenter.CityPresenter;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.view.MyGridView;
import com.haiyin.gczb.utils.view.MyRecyclerView;
import com.durian.lib.banner.BannerView;
import com.durian.lib.base.BaseView;
import com.durian.lib.baserRecyclerViewAdapterHelper.BaseQuickAdapter;
import com.durian.lib.glide.GlideUtil;
import com.makeramen.roundedimageview.RoundedImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.demandHall.adapter.DemandHanllAdapter;
import com.haiyin.gczb.demandHall.entity.ProjectListEntity;
import com.haiyin.gczb.demandHall.page.DemandDetailActivity;
import com.haiyin.gczb.demandHall.presenter.ProjectPresenter;
import com.haiyin.gczb.home.entity.GetPicsEntity;
import com.haiyin.gczb.home.entity.MessageCountEntity;
import com.haiyin.gczb.home.entity.NewsListEntity;
import com.haiyin.gczb.home.page.MessageActivity;
import com.haiyin.gczb.home.page.SearchActivity;
import com.haiyin.gczb.home.page.WebActivity;
import com.haiyin.gczb.home.presenter.MessagePresenter;
import com.haiyin.gczb.home.presenter.NewsPresenter;
import com.haiyin.gczb.home.presenter.PicsPresenter;
import com.haiyin.gczb.utils.UserUtils;


/**
 * Created
 * by durian
 * 2018/12/20.
 */
public class HomeFragment extends BaseFragment implements BaseView {

    private HomeNewAdapter homeNewAdapter;
    private DemandHanllAdapter demandHanllAdapter;

    @BindView(R.id.rv_home_crowdsourcing)
    MyRecyclerView rvCrowdsourcing;
    @BindView(R.id.rv_home_news)
    MyRecyclerView rvNews;
    @BindView(R.id.banner_home)
    BannerView banner;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout srl;
    @BindView(R.id.gv_home)
    MyGridView gv;
    MessagePresenter messagePresenter;
    NewsPresenter newsPresenter;
    PicsPresenter picsPresenter;
    ProjectPresenter projectPresenter;
    private List<View> viewList = new ArrayList<>();
    @BindView(R.id.tv_home_positioning)
    TextView tvPositioning;

    @OnClick(R.id.imgb_home_message)
    public void toMessage() {
        if (UserUtils.isLoginToLogin()) {
            Intent mIntent = new Intent(getActivity(), MessageActivity.class);
            startActivity(mIntent);
        }

    }

    @OnClick(R.id.tv_home_positioning)
    public void toPositioning() {
        Intent mIntent = new Intent(getActivity(), CityActivity.class);
        startActivity(mIntent);

    }

    @OnClick(R.id.tv_home_search)
    public void toSearch() {
        Intent mIntent = new Intent(getActivity(), SearchActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.tv_home_crowdsourcing_more)
    public void toCrowdsourcingMore() {
//        Intent mIntent = new Intent(getActivity(), RepairActivity.class);
//        startActivity(mIntent);
        MainActivity.getInstance().selDemand();

    }

    @OnClick(R.id.tv_home_news_more)
    public void toNewsMore() {
        Intent mIntent = new Intent(getActivity(), NewsActivity.class);
        startActivity(mIntent);

    }


    @Override
    protected int setView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init(View view) {
        messagePresenter = new MessagePresenter(this);
        picsPresenter = new PicsPresenter(this);
        newsPresenter = new NewsPresenter(this);
        projectPresenter = new ProjectPresenter(this);

        rvNews.setLayoutManager(MyUtils.getVManager(getActivity()));
        rvNews.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        homeNewAdapter = new HomeNewAdapter(R.layout.item_home_news);
        rvNews.setAdapter(homeNewAdapter);

        rvCrowdsourcing.setLayoutManager(MyUtils.getVManager(getActivity()));
        rvCrowdsourcing.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        demandHanllAdapter = new DemandHanllAdapter(R.layout.item_demand_hall);
        rvCrowdsourcing.setAdapter(demandHanllAdapter);


        demandHanllAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ProjectListEntity.DataBean bean = (ProjectListEntity.DataBean) adapter.getData().get(position);
                Intent mIntent = new Intent(getActivity(), DemandDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("id", bean.getProjectId());
                mIntent.putExtra("bundle", b);
                startActivity(mIntent);

            }
        });

        homeNewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NewsListEntity.DataBean bean = (NewsListEntity.DataBean) adapter.getData().get(position);
                Intent mIntent = new Intent(getActivity(), NewsDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("id", bean.getNewsId());
                mIntent.putExtra("bundle", b);
                startActivity(mIntent);

            }
        });
        initRefreshLayout();


    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getData();
    }

    private void getData() {
        if (UserUtils.isLogin()) {
            messagePresenter.getMessageCount();
        }
        picsPresenter.getPics(2);
        picsPresenter.getIcon();
        newsPresenter.getNewsList(1, 2);
        projectPresenter.getProjectList(Constant.cityId, 1, 3);
    }


    @Override
    public void success(int code, Object data) {
        srl.finishRefresh(200);
        if (code == ApiConfig.MESSAGE_COUNT) {
            MessageCountEntity entity = (MessageCountEntity) data;
        } else if (code == ApiConfig.GET_PICS) {
            GetPicsEntity entity = (GetPicsEntity) data;
            //banner
            for (int i = 0; i < entity.getData().size(); i++) {
                RoundedImageView image = new RoundedImageView(getActivity());
                image.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                //设置显示格式
                image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                image.setCornerRadius(10);
                GlideUtil.loaderImg(getActivity(), image, entity.getData().get(i).getPicPath());
                final String url = entity.getData().get(i).getUrlPath();
                final String name = entity.getData().get(i).getTilte();
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        Bundle b = new Bundle();
                        b.putString("url", url);
                        b.putString("title", name);
                        intent.putExtra("bundle", b);
                        getActivity().startActivity(intent);
                    }
                });
                viewList.add(image);
            }

            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) banner.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
            linearParams.height = (int) (Constant.screenWidth / 2.5);
            banner.setLayoutParams(linearParams);
            banner.setViewList(viewList);
            banner.startLoop(true);
        } else if (code == ApiConfig.NEWS_LIST) {
            NewsListEntity entity = (NewsListEntity) data;
            homeNewAdapter.addData(entity.getData());
        } else if (code == ApiConfig.PROJECT_LIST) {
            ProjectListEntity entity = (ProjectListEntity) data;
            demandHanllAdapter.addData(entity.getData());
        } else if (code == ApiConfig.GET_ICON) {
            GetPicsEntity entity = (GetPicsEntity) data;
            IconAdapter iconAdapter = new IconAdapter(getActivity(), R.layout.item_home_gv, entity.getData());
            gv.setAdapter(iconAdapter);

        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    private void initRefreshLayout() {
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                homeNewAdapter.cleanRV();
                demandHanllAdapter.cleanRV();
                viewList.clear();
                getData();

            }
        });

    }
}
