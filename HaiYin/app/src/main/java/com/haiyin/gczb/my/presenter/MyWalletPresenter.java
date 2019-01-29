package com.haiyin.gczb.my.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.CardsEntity;
import com.haiyin.gczb.my.entity.PaysEntity;
import com.haiyin.gczb.my.entity.ReceivedPaysEntity;
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
 * 2019/1/27.
 */
public class MyWalletPresenter extends BasePresenter<BaseView> {
    public MyWalletPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 个体户线上支付记录
     * @param page
     * @param num
     */
    public void entityOnlinePays(int page, int num, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", page);
        params.put("pageSize", num);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().entityOnlinePays(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                PaysEntity entity = JSON.parseObject(result, PaysEntity.class);
                myView.success(ApiConfig.ENTITY_ONLINE_PAYS, entity);
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
     * 个体户线下支付记录
     * @param page
     * @param num
     */
    public void entityOfflinePays(int page, int num, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", page);
        params.put("pageSize", num);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().entityOfflinePays(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                PaysEntity entity = JSON.parseObject(result, PaysEntity.class);
                myView.success(ApiConfig.ENTITY_OFFLINE_PAYS, entity);
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
     * 个体户收款数据
     * @param pageNo
     * @param pageSize
     * @param dataType 数据类型：1=当月 2=历史
     */
    public void entityReceivedPays(int pageNo,int pageSize,int dataType, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("dataType", dataType);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().entityReceivedPays(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ReceivedPaysEntity entity = JSON.parseObject(result, ReceivedPaysEntity.class);
                myView.success(ApiConfig.ENTITY_RECEIVED_PAYS, entity);
            }
            @Override
            public void onFault(String errorMsg) {
                //失败
                myView.netError(errorMsg);
            }
        },  mContext);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
}