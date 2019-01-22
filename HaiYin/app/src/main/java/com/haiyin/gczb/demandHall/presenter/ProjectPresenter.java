package com.haiyin.gczb.demandHall.presenter;

import com.alibaba.fastjson.JSON;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.demandHall.entity.ProjectDetailEntity;
import com.haiyin.gczb.demandHall.entity.ProjectListEntity;
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
 * 2019/1/12.
 */
public class ProjectPresenter extends BasePresenter<BaseView> {
    public ProjectPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 项目列表
     *
     * @param cityId
     * @param page
     * @param pageNum
     */
    public void getProjectList(String cityId, int page, int pageNum) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", page);
        params.put("pageSize", pageNum);
        params.put("cityId", cityId);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().getProjectList(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ProjectListEntity entity = JSON.parseObject(result, ProjectListEntity.class);
                myView.success(ApiConfig.PROJECT_LIST, entity);
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
     * 项目详情
     *
     * @param projectId
     */
    public void getProjectDetail(String projectId) {
        Map<String, Object> params = new HashMap<>();
        params.put("projectId", projectId);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().getProjectDetail(MyUtils.encryptString(params));
        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                ProjectDetailEntity entity = JSON.parseObject(result, ProjectDetailEntity.class);
                myView.success(ApiConfig.PROJECT_DETAIL, entity);
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
     * 项目抢单校验
     *
     * @param dataType  抢单类型:1=编码抢单，2=我要抢单
     * @param projectId
     * @param code      项目编码
     */
    public void signProjectCheck(String dataType, String projectId, String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("dataType", dataType);
        params.put("projectId", projectId);
        if (code != null)
            params.put("code", code);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().signProjectCheck(MyUtils.encryptString(params));
        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                BaseEntity entity = JSON.parseObject(result, BaseEntity.class);
                myView.success(ApiConfig.SIGN_PROJECT_CHECK, entity);
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