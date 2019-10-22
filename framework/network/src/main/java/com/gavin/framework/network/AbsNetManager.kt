package com.gavin.framework.network

import java.util.concurrent.TimeUnit

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author : Gavin
 * @date 2018-08-29
 * 网络管理类
 * 尽量保证程序中仅有一个实现，保证子类单例实现，尽量减少没有必要的额外初始化操作
 * 默认配置：
 * 1. 常用公共参数
 * 2. 多baseUrl支持 @see [CommonParamsInterceptor]
 * 3.
 *
 * TODO
 * 1. 网络接口缓存管理
 * 2. 重试管理
 */
abstract class AbsNetManager {

    private val outOfNetCacheTime = 60 * 60 * 24 * 7
    private val netWorkCacheTime = 60

    private var mBaseHostUrl: String? = null
    private var mRetrofit: Retrofit

    companion object {
        private val CACHE_CONTROL = "cache_control"
        private val TAG = AbsNetManager::class.java.name
        private var mInstance: AbsNetManager? = null
        private val maxCache = (1024 * 1024 * 10).toLong()
        private const val TIME_OUT = 10
    }

    abstract fun configBaseUrl():String

    init {
        mBaseHostUrl = configBaseUrl()
        val oriBuild = createHttpClientBuild()
        val httpClientBuild  = addBaseBuildParams(oriBuild)
        mRetrofit =  createRetrofitBuild(httpClientBuild.build(), mBaseHostUrl?:throw NullPointerException("must implement configBaseUrl method")).build()
    }

    /**
     * 构建HttpClient
     * @return
     */
    open fun createHttpClientBuild(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
                .addInterceptor(CommonParamsInterceptor())
                .connectionPool(ConnectionPool(20, 10, TimeUnit.SECONDS))
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
    }



    /**
     * 初始化Retrofit
     * @param okHttpClient
     * @param baseUrl
     */
    open fun createRetrofitBuild(okHttpClient: OkHttpClient, baseUrl: String):Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
    }


    fun <T> getApi(api: Class<T>): T {
        return mRetrofit.create(api)
    }



    private fun addBaseBuildParams(oriBuild:OkHttpClient.Builder):OkHttpClient.Builder{
        return oriBuild.addInterceptor(MutiBaseUrlInterceptor())
    }

}
