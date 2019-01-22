package com.haiyin.gczb.user.presenter;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
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
 * 2019/1/20.
 */
public class GetInfomationPresenter  extends BasePresenter<BaseView> {
    public GetInfomationPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * @param mobile             手机号
     * @param code               手机验证码
     * @param headImg            头像
     * @param name               名称
     * @param memberPosition     职位： 1=法人, 2=负责人, 3=财务, 4=人事
     * @param roleType           角色类型：见常量定义   1=企业用户, 2=个体用户, 3=个人用户,4=业务员
     * @param salesId            业务员ID
     * @param isSalesPerfect     是否业务员代写： 1=代写 2=不代写
     * @param companyName        公司名称
     * @param companyPhone       公司联系方式
     * @param industryId         行业id
     * @param businessLicensePic 营业执照
     * @param idCardNo           收款人身份证号
     */
    public void salesModifyInfo(@NonNull String mobile, @NonNull String code, @NonNull String headImg
            , @NonNull String name, @NonNull int memberPosition, @NonNull int roleType, String salesId
            , int isSalesPerfect, String companyName, String companyPhone, String industryId
            , String businessLicensePic, String idCardNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("code", code);
        params.put("headImg", headImg);
        params.put("name", name);
        params.put("memberPosition", memberPosition);
        params.put("roleType", roleType);
        if(salesId!=null)
            params.put("salesId", salesId);
        if(isSalesPerfect!=0)
            params.put("isSalesPerfect", isSalesPerfect);
        if(companyName!=null)
            params.put("companyName", companyName);
        if(companyPhone!=null)
            params.put("companyPhone", companyPhone);
        if(industryId!=null)
            params.put("industryId", industryId);
        if(businessLicensePic!=null)
            params.put("businessLicensePic", businessLicensePic);
        if(idCardNo!=null)
            params.put("idCardNo", idCardNo);


        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().salesModifyInfo(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                RegistEntity entity = JSON.parseObject(result, RegistEntity.class);
                myView.success(ApiConfig.REGIST, entity);
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
