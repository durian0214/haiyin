package com.haiyin.gczb.my.presenter;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.my.entity.ContractFilesEntity;
import com.haiyin.gczb.my.entity.GetMyContractProjectsEntity;
import com.haiyin.gczb.my.entity.MyContractCompanysEntity;
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
public class ServiceInformationPresenter extends BasePresenter<BaseView> {
    public ServiceInformationPresenter(BaseView view) {
        attachView(view);
    }

    /**
     *  企业服务商列表
     * @param pageNo
     * @param pageSize
     */
    public void companyList(int  pageNo,int pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo  ", pageNo);
        params.put("pageSize", pageSize);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().companyList(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ServiceInformationEntity entity = JSON.parseObject(result, ServiceInformationEntity.class);
                myView.success(ApiConfig.COMPANY_LIST, entity);
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
