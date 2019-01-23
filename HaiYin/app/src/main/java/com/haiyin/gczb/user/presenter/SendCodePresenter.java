package com.haiyin.gczb.user.presenter;

import com.alibaba.fastjson.JSON;
import com.haiyin.gczb.user.entity.SendCodeEntity;
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
 * 2019/1/10.
 */
public class SendCodePresenter extends BasePresenter<BaseView> {
    public SendCodePresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 发送短信验证码
     * @param mobile
     * @param dataType 发送验证码类型：1=登录验证码 2=其他身份验证码
     */
    public void sendCode(String mobile ,int dataType) {
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("dataType", dataType);

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().sendCode(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                SendCodeEntity entity = JSON.parseObject(result,SendCodeEntity.class);
                MyUtils.showShort(entity.getEm());
               myView.success(ApiConfig.SEND_CODE,entity);
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
