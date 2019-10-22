package com.gavin.framework.network;

import android.text.TextUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : com.gavin
 * @date 2018/11/7.
 * 支持多个BaseUrl的拦截器
 * 使用方式：
 * 在请求头（HEAD）中加入一个标识：AssignHost，并在后边指定baseUrl的地址即可
 * eg:
 * @Headers({"AssignHost:http://v.juhe.cn/"})
 * @GET("/toutiao/index")
 * Observable<MainItemEntity> getNews(...);
 */

public class MutiBaseUrlInterceptor implements Interceptor {

    @Override
    public Response intercept(final Chain chain) throws IOException {
        Request oriRequest = chain.request();
        String headHostKey = oriRequest.header("AssignHost");
        if (TextUtils.isEmpty(headHostKey)) {
            return chain.proceed(oriRequest);
        } else {
            Request.Builder newRequestBuild = oriRequest.newBuilder();
            newRequestBuild.removeHeader("AssignHost");

            HttpUrl parseUrl = HttpUrl.parse(headHostKey);
            HttpUrl newBaseUrl = oriRequest.url().newBuilder()
                    .scheme(parseUrl.scheme())
                    .host(parseUrl.host())
                    .port(parseUrl.port())
                    .build();

            Request newRequest = newRequestBuild.url(newBaseUrl).build();
            return chain.proceed(newRequest);
        }
    }

}
