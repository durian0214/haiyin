package com.haiyin.gczb.my.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.my.entity.ContractFilesEntity;
import com.haiyin.gczb.my.entity.GetMyContractProjectsEntity;
import com.haiyin.gczb.my.entity.MyContractCompanysEntity;
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
public class MyContractPresenter extends BasePresenter<BaseView> {
    public MyContractPresenter(BaseView view) {
        attachView(view);
    }

    /**
     *  企业合同列表
     * @param pageNo
     * @param pageSize
     */
    public void getMyContractCompanys(int  pageNo,int pageSize, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo  ", pageNo);
        params.put("pageSize", pageSize);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().getMyContractCompanys(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                MyContractCompanysEntity entity = JSON.parseObject(result, MyContractCompanysEntity.class);
                myView.success(ApiConfig.GET_MY_CONTRACT_COMPANYS, entity);
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
     * 企业我的合同
     * @param pageNo
     * @param pageSize
     * @param companyId
     */

    public void myContractProjects(int  pageNo,int pageSize,String companyId, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo  ", pageNo);
        params.put("pageSize", pageSize);
        params.put("companyId", companyId);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().myContractProjects(MyUtils.encryptString(params));
        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                GetMyContractProjectsEntity entity = JSON.parseObject(result, GetMyContractProjectsEntity.class);
                myView.success(ApiConfig.GET_MY_CONTRACT_PROJECTS, entity);
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
     * 企业协议列表
     * @param projectId
     */

    public void contractFiles(String projectId, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().contractFiles(MyUtils.encryptString(params));
        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ContractFilesEntity entity = JSON.parseObject(result, ContractFilesEntity.class);
                myView.success(ApiConfig.GET_MY_CONTRACT_PROJECTS, entity);
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
