package com.gavin.common

import com.gavin.framework.network.AbsNetManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * @author lyg
 * @data 2019-10-22 20:29
 */
class NetManager:AbsNetManager() {

    companion object{
        @JvmStatic
        val mInstance :NetManager by lazy (mode =LazyThreadSafetyMode.SYNCHRONIZED){ NetManager() }
    }

    override fun configBaseUrl(): String {
        return "http://v.juhe.cn/"
    }

    override fun createHttpClientBuild(): OkHttpClient.Builder {
        return super.createHttpClientBuild()
    }

    override fun createRetrofitBuild(okHttpClient: OkHttpClient, baseUrl: String): Retrofit.Builder {
        return super.createRetrofitBuild(okHttpClient, baseUrl)
    }
}