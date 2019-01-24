package com.haiyin.gczb.user.presenter;

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
    public void salesDetailInfo() {
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
        });
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
            , String cardNo, String bankName, String cardFrontPic, String cardBackPic) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("code", code);
        params.put("salesPosition", salesPosition);
        params.put("headImg", headImg);
        params.put("name", name);
        params.put("address", address);
        params.put("cardNo", cardNo);
        params.put("bankName", bankName);
        params.put("cardFrontPic", cardFrontPic);
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
        });
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
}