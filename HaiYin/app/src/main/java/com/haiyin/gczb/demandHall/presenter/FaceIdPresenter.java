package com.haiyin.gczb.demandHall.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.haiyin.gczb.demandHall.entity.DetectInfoEntity;
import com.haiyin.gczb.demandHall.entity.GetBiztokenEntity;
import com.haiyin.gczb.demandHall.entity.SignProjectEntity;
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
 * 2019/1/17.
 */
public class FaceIdPresenter extends BasePresenter<BaseView> {
    public FaceIdPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 获取 biztoken
     *
     * @param projectId
     */
    public void getBiztoken(String projectId, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().getBiztoken(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                GetBiztokenEntity entity = JSON.parseObject(result, GetBiztokenEntity.class);
                myView.success(ApiConfig.GET_BIZTOKEN, entity);
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
     * 获取人脸核身结果
     *
     * @param bizToken
     */
    public void detectInfo(String bizToken, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("bizToken", bizToken);

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().detectInfo(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                DetectInfoEntity entity = JSON.parseObject(result, DetectInfoEntity.class);
                myView.success(ApiConfig.DETECT_INFO, entity);
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
     * 项目抢单
     *
     * @param bizToken
     */
    public void signProject(String bizToken, String projectId, String signNameUrl, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("bizToken", bizToken);
        params.put("projectId", projectId);
        if (signNameUrl != null)
            params.put("signNameUrl", signNameUrl);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().signProject(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SignProjectEntity entity = JSON.parseObject(result, SignProjectEntity.class);
                myView.success(ApiConfig.SIGN_PROJECT, entity);
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
