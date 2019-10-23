package com.gavin.common

import com.gavin.framework.network.AbsNetManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * @author lyg
 * @data 2019-10-22 20:29
 */
class NetManager:AbsNetManager(hostUrl = "http://www.baidu.com/",multiHostUrlMap = multiUrlMap) {

    companion object{
        @JvmStatic
        val mInstance :NetManager by lazy (mode =LazyThreadSafetyMode.SYNCHRONIZED){ NetManager() }

        const val HOST_JUHE = "host_juhe"
        const val HOST_OTHER = BuildConfig.FLAVOR

        val multiUrlMap = HashMap<String,String>().apply {
            put(HOST_JUHE,"http://v.juhe.cn/")
        }
    }

}