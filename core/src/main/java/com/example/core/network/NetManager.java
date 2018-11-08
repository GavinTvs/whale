package com.example.core.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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

    private String mBaseHostUrl;

    private Retrofit mRetrofit;

    public void initNetConfig(String hostUrl){
        mBaseHostUrl = hostUrl;
        OkHttpClient okHttpClient = buildHttpClient();
        initRetrofit(okHttpClient,mBaseHostUrl);
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

    /**
     * 构建HttpClient
     * @return
     */
    private OkHttpClient buildHttpClient(){
        OkHttpClient.Builder builder =  new OkHttpClient.Builder()
                .addInterceptor(new BasicParamsInterceptor())
                .addInterceptor(new MutiBaseUrlInterceptor())
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
    private void initRetrofit(OkHttpClient okHttpClient, @NonNull String baseUrl){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);
        mRetrofit = builder.build();
    }

    public <T> T getApi(Class<T> api){
        return mRetrofit.create(api);
    }

}
