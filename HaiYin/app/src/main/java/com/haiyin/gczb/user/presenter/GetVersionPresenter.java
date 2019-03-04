package com.haiyin.gczb.user.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.durian.lib.base.BasePresenter;
import com.durian.lib.base.BaseView;
import com.haiyin.gczb.user.entity.GetVersionEntity;
import com.haiyin.gczb.user.entity.SalesEntity;
import com.haiyin.gczb.utils.Constant;
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
 * 2019/2/15.
 */
public class GetVersionPresenter extends BasePresenter<BaseView> {
    public GetVersionPresenter(BaseView view) {
        attachView(view);
    }

    /**
     * App版本更新
     * @param mContext
     */
    public void getVersion(Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("buildNumber", Constant.appVersionCode);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().appUpdate(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                GetVersionEntity entity = JSON.parseObject(result, GetVersionEntity.class);
                myView.success(ApiConfig.APP_UPDATE, entity);
            }

            @Override
            public void onFault(String errorMsg) {
                //失败
                myView.netError(errorMsg);
            }
        }, mContext);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
}
