package com.haiyin.gczb.my.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.GetPayPwdStatusEntity;
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
 * 2019/1/14.
 */
public class PayPasswordPresenter  extends BasePresenter<BaseView> {
    public PayPasswordPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 企业交易密码设置
     * @param payPwd
     */
    public void setPayPwd(String  payPwd, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("payPwd", payPwd);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().setPayPwd(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result, BaseEntity.class);
                myView.success(ApiConfig.SET_PAY_PWD, entity);
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
     * 企业交易密码修改
     * @param mobile
     * @param code
     * @param payPwd
     * @param payPwd2
     */
    public void modifyPayPwd(String mobile,String code,String  payPwd,String payPwd2, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("code", code);
        params.put("payPwd", payPwd);
        params.put("payPwd2", payPwd2);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().modifyPayPwd(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result, BaseEntity.class);
                myView.success(ApiConfig.MODIFY_PAY_PWD, entity);
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
     * 交易密码设置状态获取
     */
    public void getPayPwdStatus( Context mContext) {
        Map<String, Object> params = new HashMap<>();
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().getPayPwdStatus(MyUtils.encryptString(params));
        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                GetPayPwdStatusEntity entity = JSON.parseObject(result, GetPayPwdStatusEntity.class);
                myView.success(ApiConfig.GET_PAY_PWD_STATUS, entity);
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
