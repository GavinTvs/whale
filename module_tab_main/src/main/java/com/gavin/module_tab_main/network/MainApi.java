package com.gavin.module_tab_main.network;

import com.gavin.module_tab_main.MainItemEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author : com.gavin
 * @date 2018/11/6.
 */

public interface MainApi {

    /**
     * 获取头条新闻
     * @param appKey
     * @param type
     * @return
     */
    @Headers({"AssignHost:http://v.juhe.cn/"})
    @GET("/toutiao/index")
    Observable<MainItemEntity> getNews(@Query("key") String appKey, @Query("type") String type);
}
