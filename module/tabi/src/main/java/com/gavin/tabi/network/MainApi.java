package com.gavin.tabi.network;

import com.gavin.common.network.NetManager;
import com.gavin.tabi.TabIItemEntity;

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
//    @Headers({"AssignHost:"+ NetManager.HOST_JUHE})
    @Headers({"AssignHost:"+NetManager.HOST_JUHE})
    @GET("/toutiao/index")
    Observable<TabIItemEntity> getNews(@Query("key") String appKey, @Query("type") String type);
}
