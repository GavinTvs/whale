package com.gavin.framework.network.interceptor


import android.os.Build
import android.webkit.WebSettings

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.PhoneUtils
import com.blankj.utilcode.util.TimeUtils
import com.gavin.core.application.BaseAppLogic

import java.io.IOException
import java.util.HashMap

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author : com.gavin
 * @date 2018/8/29.
 */

class CommonParamsInterceptor : Interceptor {

    private var mCommonParamsMap: HashMap<String, String>? = null


    /**
     * 获取默认的参数
     *
     * @return
     */
    private val defaultParams: HashMap<String, String>
        get() {
            val commonParamsMap = HashMap<String, String>()
            commonParamsMap["sdk_version_code"] = DeviceUtils.getSDKVersionCode().toString()
            commonParamsMap["android_id"] = DeviceUtils.getAndroidID()
            commonParamsMap["mac_address"] = DeviceUtils.getMacAddress()
            commonParamsMap["device_manufacturer"] = DeviceUtils.getManufacturer()
            commonParamsMap["device_model"] = DeviceUtils.getModel()
            commonParamsMap["device_abis"] = DeviceUtils.getABIs().toString()

            commonParamsMap["app_version_code"] = AppUtils.getAppVersionCode().toString()
            commonParamsMap["network_is_connected"] = NetworkUtils.isConnected().toString()
            commonParamsMap["network_operator_name"] = NetworkUtils.getNetworkOperatorName()
            commonParamsMap["network_type"] = NetworkUtils.getNetworkType().toString()
            commonParamsMap["network_ip_address"] = NetworkUtils.getIPAddress(true)
            commonParamsMap["network_address_by_wifi"] = NetworkUtils.getIpAddressByWifi()

            commonParamsMap["is_phone"] = PhoneUtils.isPhone().toString()
            commonParamsMap["phone_sim_operator_name"] = PhoneUtils.getSimOperatorName()
            commonParamsMap["phone_sim_operator_by_mnc"] = PhoneUtils.getSimOperatorByMnc()

            commonParamsMap["now_mills"] = TimeUtils.getNowMills().toString()

            return commonParamsMap
        }

    private val userAgent: String
        get() {
            var userAgent: String? = ""
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                try {
                    userAgent = WebSettings.getDefaultUserAgent(BaseAppLogic.getApplication())
                } catch (e: Exception) {
                    userAgent = System.getProperty("http.agent")
                }

            } else {
                userAgent = System.getProperty("http.agent")
            }
            val sb = StringBuffer()
            var i = 0
            val length = userAgent!!.length
            while (i < length) {
                val c = userAgent[i]
                if (c <= '\u001f' || c >= '\u007f') {
                    sb.append(String.format("\\u%04x", c.toInt()))
                } else {
                    sb.append(c)
                }
                i++
            }
            return sb.toString()
        }

    constructor() {
        mCommonParamsMap = defaultParams
    }

    constructor(commonParamsMap: HashMap<String, String>) {
        this.mCommonParamsMap = commonParamsMap
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //获取到request
        val oldRequest = chain.request()

        // 添加新的参数
        val authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host())

        val iterator = mCommonParamsMap!!.keys.iterator()
        while (iterator.hasNext()) {
            val key = iterator.next()
            authorizedUrlBuilder.addQueryParameter(key, mCommonParamsMap!![key])
        }

        // 新的请求
        val newRequest = oldRequest.newBuilder()
                .header(USER_AGENT, userAgent)
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build()

        return chain.proceed(newRequest)
    }

    companion object {

        val USER_AGENT = "User-Agent"
    }

}
