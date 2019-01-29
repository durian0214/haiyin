package com.haiyin.gczb.sendPackage.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.user.entity.SendCodeEntity;
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
 * 2019/1/22.
 */
public class SendPackagePresenter extends BasePresenter<BaseView> {
    public SendPackagePresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 项目发布
     * @param needType 项目发布对方需求：1=找劳务公司, 2=找团队
     * @param projectPic 项目宣传图
     * @param title 项目标题
     * @param summary 项目描述
     * @param begin 项目开始日期 yyyy.MM.dd
     * @param end 项目结束日期 yyyy.MM.dd
     * @param amount 	项目金额
     * @param industryId 行业Id
     * @param payType 支付类型： 1=线下,2=线上
     * @param cityName 城市名称 如：北京
     * @param address 	详细地址
     * @param code 项目编码
     * @param isSalesPerfect 是否业务员完善
     * @param frameFiles 框架合同文件，多个以“,”分割
     * @param contractFiles 订单合同文件，多个以“,”分割
     * @param clearingFiles 项目结算单文件，多个以“,”分割
     */
    public void publishProject(@NonNull int needType,
                               @NonNull String projectPic,
                               @NonNull  String title,
                               @NonNull   String summary,
                               @NonNull    String begin,
                               @NonNull    String end,
                               @NonNull     Double amount,
                               @NonNull      String industryId,
                               @NonNull       int payType,
                               @NonNull       String cityName,
                               @NonNull       String address,
                               String code,
                               boolean isSalesPerfect,
                               String frameFiles,
                               String contractFiles,
                               String clearingFiles,
                            String labourCompany, Context mContext

    ) {
        Map<String, Object> params = new HashMap<>();
        params.put("needType", needType);
        if(needType==1){
           params.put("labourCompany",labourCompany) ;
        }
        params.put("projectPic", projectPic);
        params.put("title", title);
        params.put("summary", summary);
        params.put("begin", begin);
        params.put("end", end);
        params.put("amount", amount);
        params.put("industryId", industryId);
        params.put("industryId", industryId);
        params.put("payType", payType);
        params.put("cityName", cityName);
        params.put("address", address);
        params.put("code", code);
        params.put("isSalesPerfect", isSalesPerfect);
        params.put("frameFiles", frameFiles);
        params.put("contractFiles", contractFiles);
        params.put("clearingFiles", clearingFiles);

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().publishProject(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result, BaseEntity.class);
                myView.success(ApiConfig.PUBLISH_PROJECT, entity);
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
