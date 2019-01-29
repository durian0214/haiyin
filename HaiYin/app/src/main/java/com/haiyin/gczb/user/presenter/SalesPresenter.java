package com.haiyin.gczb.user.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.UserEntity;
import com.haiyin.gczb.user.event.SalesDetailInfoEntity;
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
 * 2019/1/24.
 */
public class SalesPresenter   extends BasePresenter<BaseView> {
    public SalesPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 业务员个人资料详情
     */
    public void salesDetailInfo(Context mContext) {
        Map<String, Object> params = new HashMap<>();
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().salesDetailInfo(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SalesDetailInfoEntity entity = JSON.parseObject(result, SalesDetailInfoEntity.class);
                myView.success(ApiConfig.SALES_DETAIL_INFO, entity);
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
     * 业务员个人资料修改
     * @param mobile
     * @param code
     * @param salesPosition
     * @param headImg
     * @param name
     * @param address
     * @param cardNo
     * @param bankName
     * @param cardFrontPic
     * @param cardBackPic
     */
    public void salesModifyInfo(String mobile
            , String code, int salesPosition, String headImg
            , String name, String address
            , String cardNo, String bankName, String cardFrontPic, String cardBackPic, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        if(mobile!=null)
        params.put("mobile", mobile);
        if(code!=null)
        params.put("code", code);
        if(salesPosition==0)
        params.put("salesPosition", salesPosition);
        if(headImg!=null)
        params.put("headImg", headImg);
        if(name!=null)
        params.put("name", name);
        if(address!=null)
        params.put("address", address);
        if(cardNo!=null)
        params.put("cardNo", cardNo);
        if(bankName!=null)
        params.put("bankName", bankName);
        if(cardFrontPic!=null)
        params.put("cardFrontPic", cardFrontPic);
        if(cardBackPic!=null)
        params.put("cardBackPic", cardBackPic);

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().salesModifyInfo(MyUtils.encryptString(params));
        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result, BaseEntity.class);
                myView.success(ApiConfig.SALES_MODIFY_INFO, entity);
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