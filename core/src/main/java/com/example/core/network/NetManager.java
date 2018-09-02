package com.example.core.network;


import android.util.Log;

import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * @author : Gavin
 * @date 2018-08-29
 * 网络管理类
 */
public class NetManager {
    private static final String CACHE_CONTROL = "Cache_Control";
    private static final String TAG = NetManager.class.getName();
    private static NetManager instance;

    private int outOfNetCacheTime = 60 * 60 * 24 * 7;
    private static long maxCache = 1024 * 1024 * 10;
    private int netWorkCacheTime = 60;
    private static final int TIME_OUT = 10;

    private static String mBaseHostUrl;

    private Retrofit mRetrofit;

    private static void initNetConfig(String hostUrl){
        mBaseHostUrl = hostUrl;
    }

    public static NetManager getInstance() {
        if (instance == null) {
            synchronized (NetManager.class){
                if(instance == null){
                    instance = new NetManager();
                }
            }
        }
        return instance;
    }

    private NetManager() {
        OkHttpClient okHttpClient = buildHttpClient();
        initRetrofit(okHttpClient,mBaseHostUrl);

    }

    /**
     * 构建HttpClient
     * @return
     */
    private OkHttpClient buildHttpClient(){
        OkHttpClient.Builder builder =  new OkHttpClient.Builder()
                .addInterceptor(new CommonParamsInterceptor())
                .connectionPool(new ConnectionPool(20,10,TimeUnit.SECONDS))
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);
        return builder.build();
    }

    /**
     * 初始化Retrofit
     * @param okHttpClient
     * @param baseUrl
     */
    private void initRetrofit(OkHttpClient okHttpClient,String baseUrl){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public <T> T getApi(Class<T> api){
        return mRetrofit.create(api);
    }


    /**
     * 创建缓存拦截器
     * @return
     */
    private Interceptor createCacheInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                if (NetworkUtils.isConnected()) {
                    return response.newBuilder().removeHeader("program").removeHeader(CACHE_CONTROL)
                            .addHeader(CACHE_CONTROL, "public, max-age=" + netWorkCacheTime).build();
                } else {
                    return response.newBuilder().removeHeader("program").removeHeader(CACHE_CONTROL)
                            .addHeader(CACHE_CONTROL, "public, only-if-cached, max-stale=" + outOfNetCacheTime).build();
                }
            }
        };
    }

    /**
     * 创建日志拦截器
     * @return
     */
    private Interceptor createLoggerInterceptor(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (request.url() != null) {
                    Log.e(TAG, "请求的url" + request.url().toString()
                    );
                } else {
                    Log.e(TAG, "请求的url为空");
                }
                RequestBody requestBody = request.body();
                Buffer buffer = new Buffer();
                if (requestBody != null) {
                    requestBody.writeTo(buffer);
                    Log.e(TAG, parseContent(requestBody, buffer));
                } else {
                    Log.e(TAG, "request_body is null");
                }
                return chain.proceed(request);
            }
        };
    }

    private String parseContent(RequestBody requestBody, Buffer buffer) {
        try {
            if (requestBody.contentType() != null && requestBody.contentType().toString().equals("multipart")) {
                return URLDecoder.decode(buffer.readUtf8(), "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {

        }
        return "";
    }
}
