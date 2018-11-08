package com.example.core.network;


import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.example.core.CoreInitLogic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : gavin
 * @date 2018/8/29.
 */

public class BasicParamsInterceptor implements Interceptor {

    public static final String USER_AGENT = "User-Agent";

    private HashMap<String, String> mCommomParamsMap;

    public BasicParamsInterceptor() {
        mCommomParamsMap = getDefaultParams();
    }

    public BasicParamsInterceptor(HashMap<String, String> commomParamsMap) {
        this.mCommomParamsMap = commomParamsMap;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取到request
        Request oldRequest = chain.request();

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());

        Iterator<String> iterator = mCommomParamsMap.keySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            authorizedUrlBuilder.addQueryParameter(key, mCommomParamsMap.get(key));
        }

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .header(USER_AGENT,System.getProperty("http.agent")+ AppUtils.getAppName())
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .build();

        return chain.proceed(newRequest);
    }


    /**
     * 获取默认的参数
     *
     * @return
     */
    private HashMap<String, String> getDefaultParams() {
        HashMap<String, String> commomParamsMap = new HashMap<>();
        commomParamsMap.put("sdk_version_code", String.valueOf(DeviceUtils.getSDKVersionCode()));
        commomParamsMap.put("android_id", DeviceUtils.getAndroidID());
        commomParamsMap.put("mac_address", DeviceUtils.getMacAddress());
        commomParamsMap.put("device_manufacturer", DeviceUtils.getManufacturer());
        commomParamsMap.put("device_model", DeviceUtils.getModel());
        commomParamsMap.put("device_abis", DeviceUtils.getABIs().toString());

        commomParamsMap.put("app_version_code", String.valueOf(AppUtils.getAppVersionCode()));
        commomParamsMap.put("network_is_connected",String.valueOf(NetworkUtils.isConnected()));
        commomParamsMap.put("network_operator_name", NetworkUtils.getNetworkOperatorName());
        commomParamsMap.put("network_type", NetworkUtils.getNetworkType().toString());
        commomParamsMap.put("network_ip_address", NetworkUtils.getIPAddress(true));
        commomParamsMap.put("network_address_by_wifi", NetworkUtils.getIpAddressByWifi());

        commomParamsMap.put("is_phone", String.valueOf(PhoneUtils.isPhone()));
        commomParamsMap.put("phone_sim_operator_name", PhoneUtils.getSimOperatorName());
        commomParamsMap.put("phone_sim_operator_by_mnc", PhoneUtils.getSimOperatorByMnc());

        commomParamsMap.put("now_mills", String.valueOf(TimeUtils.getNowMills()));

        return commomParamsMap;
    }


}
