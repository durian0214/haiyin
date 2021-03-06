package com.haiyin.gczb.my.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.AccountEntity;
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
 * 2019/1/23.
 */
public class AccountPresenter  extends BasePresenter<BaseView> {
    public AccountPresenter(BaseView view) {
        attachView(view);
    }

    /**
     *  企业本部账号列表
     */
    public void accountList( Context mContext) {
        Map<String, Object> params = new HashMap<>();

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().accountList(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                AccountEntity entity = JSON.parseObject(result, AccountEntity.class);
                myView.success(ApiConfig.ACCOUNT_LIST, entity);
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
     * 企业本部账号添加
     * @param name
     * @param phone
     */
    public void addAccount(String name,String phone, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("name",name);
        params.put("phone",phone);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().addAccount(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result, BaseEntity.class);
                myView.success(ApiConfig.ADD_ACCOUNT, entity);
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
