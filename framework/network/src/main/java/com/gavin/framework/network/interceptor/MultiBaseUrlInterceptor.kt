package com.gavin.framework.network.interceptor

import android.content.res.Resources

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author : com.gavin
 * @date 2018/11/7.
 * 支持多个BaseUrl的拦截器
 * 使用方式：
 * 在请求头（HEAD）中加入一个标识：AssignHost，并在后边指定baseUrl或者存储url map的key
 * @sample
 *
 * ```
 * @Headers({"AssignHost:http://v.juhe.cn/"})
 * *@GET("/toutiao/index")
 * Observable<MainItemEntity> getNews(...);
 *
 * *@Headers({"AssignHost:"+HostMap.get(google)"})
 * *@GET("/toutiao/index")
 * Observable<MainItemEntity> getNews(...);
 * ```
 */

class MultiBaseUrlInterceptor(private val multiBaseUrlMap:HashMap<String,String>? =null) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val oriRequest = chain.request()
        val headHostKey = oriRequest.header("AssignHost")
        if (headHostKey.isNullOrEmpty()) {
            return chain.proceed(oriRequest)
        } else {
            val newRequestBuild = oriRequest.newBuilder()
            newRequestBuild.removeHeader("AssignHost")

            val parseUrl = (if(headHostKey.startsWith("http")){
                HttpUrl.parse(headHostKey)
            }else{
                if(multiBaseUrlMap.isNullOrEmpty() || !multiBaseUrlMap.containsKey(headHostKey)){
                    throw Resources.NotFoundException()
                }
                HttpUrl.parse(multiBaseUrlMap[headHostKey]!!)
            }) ?: throw Resources.NotFoundException()

            val newBaseUrl = oriRequest.url().newBuilder()
                    .scheme(parseUrl.scheme())
                    .host(parseUrl.host())
                    .port(parseUrl.port())
                    .build()

            val newRequest = newRequestBuild.url(newBaseUrl).build()
            return chain.proceed(newRequest)
        }
    }

}
