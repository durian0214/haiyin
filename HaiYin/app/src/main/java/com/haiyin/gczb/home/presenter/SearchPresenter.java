package com.haiyin.gczb.home.presenter;

import com.alibaba.fastjson.JSON;
import com.haiyin.gczb.home.entity.SearchResultsEntity;
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
public class SearchPresenter extends BasePresenter<BaseView> {
    public SearchPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 首页搜索
     * @param type 搜索数据类型 1=新闻 2=众包列表
     * @param page
     * @param pageNum
     * @param keywords
     */
    public void toSearch(int type,int page ,int pageNum,String keywords) {
        Map<String, Object> params = new HashMap<>();
        params.put("dataType", type);
        params.put("pageNo", page);
        params.put("pageSize", pageNum);
        params.put("keywords", keywords);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().toSearch(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SearchResultsEntity entity = JSON.parseObject(result, SearchResultsEntity.class);
                myView.success(ApiConfig.SEARCH, entity);
            }

            @Override
            public void onFault(String errorMsg) {
                //失败
                myView.netError(errorMsg);
            }
        });
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

}
