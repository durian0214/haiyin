package com.haiyin.gczb.home.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.haiyin.gczb.home.entity.NewsDetailEntity;
import com.haiyin.gczb.home.entity.NewsListEntity;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.http.HttpMethods;
import com.haiyin.gczb.utils.http.OnSuccessAndFaultListener;
import com.haiyin.gczb.utils.http.OnSuccessAndFaultSub;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class NewsPresenter   extends BasePresenter<BaseView> {
    public NewsPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 新闻列表
     * @param page
     * @param pageNum
     */
    public void getNewsList(int page ,int pageNum, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo",page);
        params.put("pageSize",pageNum);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().getNewsList(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                NewsListEntity entity = JSON.parseObject(result,NewsListEntity.class);
                myView.success(ApiConfig.NEWS_LIST,entity);
            }

            @Override
            public void onFault(String errorMsg) {
                //失败
                myView.netError(errorMsg);
            }
        },mContext);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 新闻详情
     * @param newsId
     */
    public void getNewsDetail(String newsId, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("newsId",newsId);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().getNewsDetail(MyUtils.encryptString(params));
        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                NewsDetailEntity entity = JSON.parseObject(result,NewsDetailEntity.class);
                myView.success(ApiConfig.NEWS_DETAIL,entity);
            }

            @Override
            public void onFault(String errorMsg) {
                //失败
                myView.netError(errorMsg);
            }
        },mContext);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
}
