package com.haiyin.gczb.my.presenter;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.EnterpriseEntity;
import com.haiyin.gczb.my.entity.SalesCompanyListEntity;
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
 * 2019/1/24.
 */
public class CustomerPresenter extends BasePresenter<BaseView> {
    public CustomerPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 业务员客户列表
     *
     * @param pageNo
     * @param pageSize
     * @param dataType 数据类型：1=负责客户列表 2=待完善客户 3=已完善客户 4=公司众包客户列表 5=待上传合同协议客户列表 6=待开票客户列表 7= 已开票客户列表 8=订单合同客户列表(找公司合同)
     */
    public void salescompanyList(int pageNo, int pageSize, int dataType) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("dataType", dataType);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().salescompanyList(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SalesCompanyListEntity entity = JSON.parseObject(result, SalesCompanyListEntity.class);
                myView.success(ApiConfig.SALES_COMPANY_LIST, entity);
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
     * 企业下级企业添加
     *
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
            , @NonNull String name, @NonNull int memberPosition, String salesId
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