package com.example.widget_ad;

import android.content.Context;

/**
 * @author : gavin
 * @date 2018/11/9.
 * 广告管理类
 *
 * 广告：缓存
 */

public class AdLoader {
    private static final String TAG = "KubikAdLoader";
    private static AdLoader adLoader;

    /**
     * 广告来源标识
     * ADSOURCE_CACHE 缓存
     * ADSOURCE_NETWORK 网络
     */
    public static final int ADSOURCE_CACHE = 100;
    public static final int ADSOURCE_NETWORK = 200;

    private Byte[] lock = new Byte[0];

    public static AdLoader getInstance() {
        if (adLoader == null) {
            synchronized (AdLoader.class) {
                if (adLoader == null) {
                    adLoader = new AdLoader();
                }
            }
        }
        return adLoader;
    }

    /**
     * 获取Google广告
     */
    public void getGoogleAd(AdLoadListener adLoadListener){

    }

    /**
     * 获取Facebook广告
     */
    public void getFacebookAd(AdLoadListener adLoadListener){

    }


    public interface AdLoadListener{

        /**
         * Ad加载成功
         */
        void onAdLoadSuccess();

        /**
         * Ad加载失败
         * @param errorMsg 错误信息
         */
        void onAdLoadFailed(String errorMsg);

    }





}
