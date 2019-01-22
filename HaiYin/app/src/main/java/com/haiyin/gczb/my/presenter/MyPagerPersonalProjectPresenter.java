package com.haiyin.gczb.my.presenter;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.my.entity.MyPagerEnterpriseEntity;
import com.haiyin.gczb.my.entity.MyPagerProjectEntity;
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
 * 2019/1/20.
 */
public class MyPagerPersonalProjectPresenter  extends BasePresenter<BaseView> {
    public MyPagerPersonalProjectPresenter(BaseView view) {
        attachView(view);
    }


    /**
     * 个体户项目列表
     * @param pageNo
     * @param pageSize
     * @param type  数据类型：1=全部 2=待打款 3=待开票 4=已开票
     */
    public void entityProjects(int  pageNo,int pageSize ,int type) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo  ", pageNo);
        params.put("pageSize", pageSize);
        params.put("dataType", type);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().entityProjects(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                MyPagerProjectEntity entity = JSON.parseObject(result, MyPagerProjectEntity.class);
                myView.success(ApiConfig.INVOICE_COMPANY_PROJECTS, entity);
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