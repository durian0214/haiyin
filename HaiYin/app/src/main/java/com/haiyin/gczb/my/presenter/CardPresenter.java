package com.haiyin.gczb.my.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.AccountEntity;
import com.haiyin.gczb.my.entity.CardsEntity;
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
public class CardPresenter   extends BasePresenter<BaseView> {
    public CardPresenter(BaseView view) {
        attachView(view);
    }

    /**
     *  企业银行卡列表
     */
    public void bankCards(Context mContext) {
        Map<String, Object> params = new HashMap<>();

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().bankCards(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                CardsEntity entity = JSON.parseObject(result, CardsEntity.class);
                myView.success(ApiConfig.BANK_CARDS, entity);
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
     * 企业银行卡添加
     * @param taxNo
     * @param cardNo
     * @param bankName
     * @param phone
     * @param bankAddress
     */
    public void addBankCard(String taxNo,String cardNo,String bankName,String phone,String bankAddress, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("taxNo",taxNo);
        params.put("cardNo",cardNo);
        params.put("bankName",bankName);
        params.put("bankAddress",bankAddress);
        params.put("phone",phone);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().addBankCard(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result, BaseEntity.class);
                myView.success(ApiConfig.ADD_BANK_CARD, entity);
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