package com.haiyin.gczb.home.presenter;

import com.alibaba.fastjson.JSON;
import com.haiyin.gczb.home.entity.GetPicsEntity;
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
public class PicsPresenter extends BasePresenter<BaseView> {
    public PicsPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 配置图片位置
     *
     * @param picType 1=引导页, 2=首页banner, 3=首页icon图
     */
    public void getPics(int picType) {
        Map<String, Object> params = new HashMap<>();
        params.put("picType", picType);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().basePics(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                GetPicsEntity entity = JSON.parseObject(result, GetPicsEntity.class);
                myView.success(ApiConfig.GET_PICS, entity);
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
