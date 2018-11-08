package com.example.core.network;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : gavin
 * @date 2018/11/7.
 * 支持多个BaseUrl的拦截器
 */

public class MutiBaseUrlInterceptor implements Interceptor {

    @Override
    public Response intercept(final Chain chain) throws IOException {

        Observable.create(new ObservableOnSubscribe<Request>() {
            @Override
            public void subscribe(ObservableEmitter<Request> emitter) throws Exception {
                Request oriRequest = chain.request();

                String headHostKey = oriRequest.header("AssignHost");
                if(TextUtils.isEmpty(headHostKey)){
                    emitter.onNext(oriRequest);
                }else{
                    Request.Builder newRequestBuild = oriRequest.newBuilder();
                    newRequestBuild.removeHeader("AssignHost");

                    HttpUrl parseUrl = HttpUrl.parse(headHostKey);
                    HttpUrl newBaseUrl = oriRequest.url().newBuilder()
                            .scheme(parseUrl.scheme())
                            .host(parseUrl.host())
                            .port(parseUrl.port())
                            .build();

                    Request newRequest = newRequestBuild.url(newBaseUrl).build();
                    emitter.onNext(newRequest);
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Request>() {
                    @Override
                    public void accept(Request request) throws Exception {
                        chain.proceed(request);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable,"MutiBaseUrlInterceptor");
                    }
                });

        return null;
    }

}
