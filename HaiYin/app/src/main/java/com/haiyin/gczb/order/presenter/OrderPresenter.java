package com.haiyin.gczb.order.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.order.entity.OrderListsEntity;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.http.HttpMethods;
import com.haiyin.gczb.utils.http.OnSuccessAndFaultListener;
import com.haiyin.gczb.utils.http.OnSuccessAndFaultSub;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;

/**
 * Created
 * by durian
 * 2019/1/14.
 */
public class OrderPresenter extends BasePresenter<BaseView> {
    public OrderPresenter(BaseView view) {
        attachView(view);
    }

    /**
     *企业订单列表
     * @param type ：1=全部订单 2=待打款订单 3=待开票订单 4=已完成订单 5=本月项目 6=历史项目
     * @param page
     * @param pageNum
     */
    public void getOrderLists(int type ,int page,int pageNum, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", page);
        params.put("pageSize", pageNum);
        params.put("projectType", type);

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().getOrder(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                OrderListsEntity entity = JSON.parseObject(result,OrderListsEntity.class);
                MyUtils.showShort(entity.getEm());
                myView.success(ApiConfig.ORDER_LISTS,entity);
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
     * 企业项目开票申请
     * @param projectId
     */

    public void applyInvoice(String projectId , Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().applyInvoice(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result,BaseEntity.class);
                MyUtils.showShort(entity.getEm());
                myView.success(ApiConfig.APPLY_INVOICE,entity);
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
     * 企业项目打款凭证保存
     * @param projectId
     * @param payFile
     */

    public void payFileSave(String projectId ,String payFile, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        params.put("payFile", payFile);

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().payFileSave(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result,BaseEntity.class);
                MyUtils.showShort(entity.getEm());
                myView.success(ApiConfig.PAY_FILE_SAVE,entity);
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
