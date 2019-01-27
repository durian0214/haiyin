package com.haiyin.gczb.my.presenter;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.UserEntity;
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
public class UserPresenter  extends BasePresenter<BaseView> {
    public UserPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 用户资料详情
     */
    public void getDetailInfo() {
        Map<String, Object> params = new HashMap<>();
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().getDetailInfo(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                UserEntity entity = JSON.parseObject(result, UserEntity.class);
                myView.success(ApiConfig.GET_DETAIL_INFO, entity);
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
     * 用户资料修改
     *
     * @param mobile
     * @param headImg
     * @param name
     * @param memberPosition
     * @param salesId
     * @param companyName
     * @param companyPhone
     * @param industryId
     * @param businessLicensePic
     * @param idCardNo
     * @param corpCardFront
     * @param corpCardBack
     * @param finaName
     * @param cardNo
     * @param bankName
     * @param finaCardFront
     * @param finaCardBack
     */
    public void modifyInfo(String mobile,String headImg
            , String name, int memberPosition, String salesId
            , String companyName, String companyPhone, String industryId
            , String businessLicensePic, String idCardNo, String corpCardFront, String corpCardBack, String finaName
            , String cardNo, String bankName, String finaCardFront, String finaCardBack) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("headImg", headImg);
        params.put("name", name);
        params.put("memberPosition", memberPosition);
        params.put("salesId", salesId);
        params.put("companyName", companyName);
        params.put("companyPhone", companyPhone);
        params.put("industryId", industryId);
        params.put("businessLicensePic", businessLicensePic);
        params.put("idCardNo", idCardNo);
        params.put("corpCardFront", corpCardFront);
        params.put("corpCardBack", corpCardBack);
        params.put("cardNo", cardNo);
        params.put("finaCardBack", finaCardBack);
        params.put("bankName", bankName);
        params.put("finaCardFront", finaCardFront);
        params.put("finaName", finaName);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().modifyInfo(MyUtils.encryptString(params));
        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result, BaseEntity.class);
                myView.success(ApiConfig.MODIFY_INFO, entity);
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
