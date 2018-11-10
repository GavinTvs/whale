package com.gavin.core.network;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
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
