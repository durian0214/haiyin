package com.haiyin.gczb.utils.http;

import com.haiyin.gczb.utils.UserUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created
 * by durian
 * 2018/12/28.
 */
public class TokenInterceptord  implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取原先的请求
        Request originalRequest = chain.request();
        //重新构建url
        HttpUrl.Builder builder = originalRequest.url().newBuilder();
        //公共参数
        builder.addQueryParameter("access_token", UserUtils.getToken());
        //新的url
        HttpUrl httpUrl = builder.build();
        Request request = originalRequest.newBuilder()
                .method(originalRequest.method(),originalRequest.body())
                .url(httpUrl).build();
        return chain.proceed(request);
    }
}