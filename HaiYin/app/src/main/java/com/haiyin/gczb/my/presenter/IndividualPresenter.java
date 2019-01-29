package com.haiyin.gczb.my.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.my.entity.EntityContractsEntity;
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
 * 2019/1/23.
 */
public class IndividualPresenter   extends BasePresenter<BaseView> {
    public IndividualPresenter(BaseView view) {
        attachView(view);
    }


    /**
     * 个体户我的合同
     * @param pageNo
     * @param pageSize
     */
    public void entityContracts(int  pageNo,int pageSize , Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo  ", pageNo);
        params.put("pageSize", pageSize);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().entityContracts(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                EntityContractsEntity entity = JSON.parseObject(result, EntityContractsEntity.class);
                myView.success(ApiConfig.ENTITY_CONTRACTS, entity);
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