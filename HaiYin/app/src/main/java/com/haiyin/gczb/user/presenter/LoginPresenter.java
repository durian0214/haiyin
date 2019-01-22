package com.haiyin.gczb.user.presenter;

import com.alibaba.fastjson.JSON;
import com.haiyin.gczb.user.entity.LoginEntity;
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
public class LoginPresenter  extends BasePresenter<BaseView> {
    public LoginPresenter(BaseView view) {
        attachView(view);
    }


    public void doLogin(String mobile ,String code) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("code", code);


        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().doLogin(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                LoginEntity entity = JSON.parseObject(result,LoginEntity.class);
                myView.success(ApiConfig.LOGIN,entity);
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
