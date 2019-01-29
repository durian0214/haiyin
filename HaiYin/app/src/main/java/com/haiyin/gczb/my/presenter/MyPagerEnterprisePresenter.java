package com.haiyin.gczb.my.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.my.entity.MyPagerEnterpriseEntity;
import com.haiyin.gczb.my.entity.MyPagerProjectEntity;
import com.haiyin.gczb.my.entity.ServiceInformationEntity;
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
public class MyPagerEnterprisePresenter extends BasePresenter<BaseView> {
    public MyPagerEnterprisePresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 企业开票列表
     * @param pageNo
     * @param pageSize
     * @param type 数据类型 1=未开票 2=已开票
     */
    public void invoiceCompanys(int  pageNo,int pageSize ,int type, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo  ", pageNo);
        params.put("pageSize", pageSize);
        params.put("dataType", type);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().invoiceCompanys(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                MyPagerEnterpriseEntity entity = JSON.parseObject(result, MyPagerEnterpriseEntity.class);
                myView.success(ApiConfig.INVOICE_COMPANYS, entity);
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
     * 企业开票项目列表
     * @param pageNo
     * @param pageSize
     * @param type
     * @param companyId
     */
    public void invoiceCompanyProjects(int  pageNo,int pageSize ,int type,String companyId, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo  ", pageNo);
        params.put("pageSize", pageSize);
        params.put("dataType", type);
        params.put("companyId", companyId);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().invoiceCompanyProjects(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                MyPagerProjectEntity entity = JSON.parseObject(result, MyPagerProjectEntity.class);
                myView.success(ApiConfig.INVOICE_COMPANY_PROJECTS, entity);
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
