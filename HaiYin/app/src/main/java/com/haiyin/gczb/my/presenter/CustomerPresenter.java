package com.haiyin.gczb.my.presenter;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.EnterpriseEntity;
import com.haiyin.gczb.my.entity.SalesCompanyDetailEntity;
import com.haiyin.gczb.my.entity.SalesCompanyListEntity;
import com.haiyin.gczb.my.entity.SalesCompanyProjectsEntity;
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
     * 业务员客户项目列表
     *
     * @param pageNo
     * @param pageSize
     * @param dataType 数据类型：1=全部 2=待打款 3=待开票 4=已开票 5=待上传合同项目 6=合作合同项目
     * @param companyId
     */
    public void salescompanyProjects(int pageNo, int pageSize, String companyId,int dataType) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("companyId", companyId);
        params.put("dataType", dataType);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().salescompanyProjects(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SalesCompanyProjectsEntity entity = JSON.parseObject(result, SalesCompanyProjectsEntity.class);
                myView.success(ApiConfig.SALES_COMPANY_PROJECTS, entity);
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
     * 业务员客户信息详情
     * @param companyId
     */

    public void salescompanyDetail(String companyId) {
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyId);

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().salescompanyDetail(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SalesCompanyDetailEntity entity = JSON.parseObject(result, SalesCompanyDetailEntity.class);
                myView.success(ApiConfig.SALES_COMPANY_DETAIL, entity);
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
     * 业务员客户信息完善
     * @param companyId  客户公司ID 新加时为
     * @param companyName 公司名称
     * @param headImg 头像
     * @param companyPhone 公司联系方式
     * @param name 联系人名称
     * @param mobile 联系人电话
     * @param memberPosition 职位：见常量定义
     * @param industryId 行业id
     * @param businessLicensePic 营业执照
     * @param corpCardFront 法人证件正面照
     * @param corpCardBack 法人证件反面照
     * @param finaName 收款人
     * @param cardNo 银行卡号
     * @param bankName 开户银行
     * @param finaCardFront 	收款人身份证扫描件正面
     * @param finaCardBack 收款人身份证扫描件反面
     * @param idCardNo 收款人身份证号
     */

    public void saleseditCompany(String companyId,
                                 String companyName,
                                 String headImg,
                                 String companyPhone,
                                 String name,
                                 String mobile,
                                 int memberPosition,
                                 String industryId,
                                 String businessLicensePic,
                                 String corpCardFront,
                                 String corpCardBack,
                                 String finaName,
                                 String cardNo,
                                 String bankName,
                                 String finaCardFront,
                                 String finaCardBack,
                                 String idCardNo
                                 ) {
        Map<String, Object> params = new HashMap<>();
        params.put("companyId", companyId);
        params.put("companyName", companyName);
        params.put("headImg", headImg);
        params.put("companyPhone", companyPhone);
        params.put("name", name);
        params.put("mobile", mobile);
        params.put("memberPosition", memberPosition);
        params.put("industryId", industryId);
        params.put("businessLicensePic", businessLicensePic);
        params.put("corpCardFront", corpCardFront);
        params.put("corpCardBack", corpCardBack);
        params.put("finaName", finaName);
        params.put("cardNo", cardNo);
        params.put("bankName", bankName);
        params.put("finaCardFront", finaCardFront);
        params.put("finaCardBack", finaCardBack);
        params.put("idCardNo", idCardNo);

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().saleseditCompany(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result, BaseEntity.class);
                myView.success(ApiConfig.SALES_EDIT_COMPANY, entity);
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