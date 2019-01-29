package com.haiyin.gczb.my.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.AccountEntity;
import com.haiyin.gczb.my.entity.EnterpriseEntity;
import com.haiyin.gczb.user.entity.RegistEntity;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.http.HttpMethods;
import com.haiyin.gczb.utils.http.OnSuccessAndFaultListener;
import com.haiyin.gczb.utils.http.OnSuccessAndFaultSub;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created
 * by durian
 * 2019/1/23.
 */
public class EnterprisePresenter extends BasePresenter<BaseView> {
    public EnterprisePresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 企业下级企业列表
     */
    public void subCompanys(Context mContext) {
        Map<String, Object> params = new HashMap<>();

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().subCompanys(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                EnterpriseEntity entity = JSON.parseObject(result, EnterpriseEntity.class);
                myView.success(ApiConfig.SUB_COMPANYS, entity);
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
     * 企业下级企业添加
     * @param mobile             手机号
     * @param headImg            头像
     * @param name               名称
     * @param memberPosition     职位： 1=法人, 2=负责人, 3=财务, 4=人事
     * @param salesId            业务员ID
     * @param companyName        公司名称
     * @param companyPhone       公司联系方式
     * @param industryId         行业id
     * @param businessLicensePic 营业执照
     * @param idCardNo           收款人身份证号
     * @param corpCardFront      证件照正面
     * @param corpCardBack       证件照反面
     * @param finaName           收款人
     * @param cardNo             银行卡号
     * @param bankName           开户银行
     * @param finaCardFront      收款人身份证扫描件正面
     * @param finaCardBack       收款人身份证扫描件反面
     */

    public void addSubCompany(@NonNull String mobile, @NonNull String headImg
            , @NonNull String name, @NonNull int memberPosition,  String salesId
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
        params.put("cardNo", cardNo);
        params.put("bankName", bankName);
        params.put("finaCardFront", finaCardFront);
        params.put("corpCardFront", corpCardFront);
        params.put("finaCardBack", finaCardBack);
        params.put("corpCardBack", corpCardBack);
        params.put("finaName", finaName);


        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().addSubCompany(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result, BaseEntity.class);
                myView.success(ApiConfig.ADD_SUB_COMPANY, entity);
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
