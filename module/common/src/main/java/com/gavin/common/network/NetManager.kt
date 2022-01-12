package com.gavin.common.network

import com.gavin.framework.network.BaseNetManager

/**
 * @author lyg
 * @data 2019-10-22 20:29
 */
class NetManager:BaseNetManager(hostUrl = "http://www.baidu.com/",multiHostUrlMap = multiUrlMap) {

    companion object{
        @JvmStatic
        val mInstance : NetManager by lazy (mode =LazyThreadSafetyMode.SYNCHRONIZED){ NetManager() }

        const val HOST_KEY_JUHE = "host_key_juhe"

        val multiUrlMap = HashMap<String,String>().apply {
            put(HOST_KEY_JUHE,"http://v.juhe.cn/")
        }
    }

}