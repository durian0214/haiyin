package com.haiyin.gczb.my.presenter;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.PaymentCertificateEntity;
import com.haiyin.gczb.my.entity.ProjectCooperateEntity;
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
 * 2019/1/18.
 */
public class ProgressQueryPresenter  extends BasePresenter<BaseView> {
    public ProgressQueryPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 企业合同列表
     * @param pageNo
     * @param pageSize
     * @param dateType
     */
    public void projectCooperate(int pageNo,int pageSize,int  dateType) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("dateType", dateType);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().projectCooperate(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ProjectCooperateEntity entity = JSON.parseObject(result, ProjectCooperateEntity.class);
                myView.success(ApiConfig.PROJECT_COOPERATE, entity);
            }

            @Override
            public void onFault(String errorMsg) {
                //失败
                myView.netError(errorMsg);
            }
        });
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     * 企业项目结算
     * @param pageNo
     * @param pageSize
     * @param dateType
     */
    public void projectClearing(int pageNo,int pageSize,int  dateType) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("dateType", dateType);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().projectClearing(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ProjectCooperateEntity entity = JSON.parseObject(result, ProjectCooperateEntity.class);
                myView.success(ApiConfig.PROJECT_CLEARING, entity);
            }

            @Override
            public void onFault(String errorMsg) {
                //失败
                myView.netError(errorMsg);
            }
        });
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
    /**
     * 企业完税证明文件列表
     * @param pageNo
     * @param pageSize
     * @param dateType
     */
    public void taxProofs(int pageNo,int pageSize,int  dateType) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("dateType", dateType);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().taxProofs(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                PaymentCertificateEntity entity = JSON.parseObject(result, PaymentCertificateEntity.class);
                myView.success(ApiConfig.PROJECT_COOPERATE, entity);
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
