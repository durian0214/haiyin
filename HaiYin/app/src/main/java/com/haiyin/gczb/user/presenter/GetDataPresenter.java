package com.haiyin.gczb.user.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.user.entity.IndustryEntity;
import com.haiyin.gczb.user.entity.SalesEntity;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.http.HttpMethods;
import com.haiyin.gczb.utils.http.OnSuccessAndFaultListener;
import com.haiyin.gczb.utils.http.OnSuccessAndFaultSub;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created
 * by durian
 * 2019/1/21.
 */
public class GetDataPresenter   extends BasePresenter<BaseView> {
    public GetDataPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 业务员列表
     */
    public void sales(Context mContext) {
        Map<String, Object> params = new HashMap<>();

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().sales(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SalesEntity entity = JSON.parseObject(result,SalesEntity.class);
                myView.success(ApiConfig.SALES,entity);
            }

            @Override
            public void onFault(String errorMsg) {
                //失败
                myView.netError(errorMsg);
            }
        },  mContext);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    /**
     * 行业列表
     */
    public void industry() {
        Map<String, Object> params = new HashMap<>();

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().industry(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                IndustryEntity entity = JSON.parseObject(result,IndustryEntity.class);
                myView.success(ApiConfig.INDUSTRY,entity);
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
