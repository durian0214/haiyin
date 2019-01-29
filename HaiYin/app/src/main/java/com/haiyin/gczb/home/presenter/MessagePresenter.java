package com.haiyin.gczb.home.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.haiyin.gczb.home.entity.MessageCountEntity;
import com.haiyin.gczb.home.entity.MessageListsEntity;
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
public class MessagePresenter extends BasePresenter<BaseView> {
    public MessagePresenter(BaseView view) {
        attachView(view);
    }

    /**
     * 获取未读消息列表
     */
    public void getMessageCount( Context mContext) {
        Map<String, Object> params = new HashMap<>();

        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().unreadMsgCount(MyUtils.encryptString(params));

        DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                MessageCountEntity entity = JSON.parseObject(result,MessageCountEntity.class);
                myView.success(ApiConfig.MESSAGE_COUNT,entity);
            }

            @Override
            public void onFault(String errorMsg) {
                //失败
                myView.netError(errorMsg);
            }
    },mContext);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }

    /**
     *  获取消息列表
     * @param type 消息类型: 1=系统消息, 2=钱包消息, 3=任务消息
     * @param page
     * @param pageNum
     */
    public void getMessageLists(int type,int page ,int pageNum, Context mContext) {
        Map<String, Object> params = new HashMap<>();
        params.put("msgType",type);
        params.put("pageNo",page);
        params.put("pageSize",pageNum);
        Observable<ResponseBody>
                observable = HttpMethods.getInstance().getHttpApi().getMsgLists(MyUtils.encryptString(params));
         DisposableObserver<ResponseBody> subscriber = new OnSuccessAndFaultSub(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(String result) {
                MessageListsEntity entity = JSON.parseObject(result,MessageListsEntity.class);
                myView.success(ApiConfig.MESSAGE_LISTS,entity);
            }

            @Override
            public void onFault(String errorMsg) {
                //失败
                myView.netError(errorMsg);
            }
    },mContext);
        HttpMethods.getInstance().toSubscribe(observable, subscriber);
    }
}
