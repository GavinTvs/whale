package com.gavin.common.network

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.functions.Function

/**
 * @author lyg
 * @data  10:35
 */
class BaseResponseTransFormer<T> :ObservableTransformer<BaseResponse<T>, T>{

    override fun apply(upstream: Observable<BaseResponse<T>>): ObservableSource<T> {
        return upstream.flatMap { baseResponse ->
            Observable.empty()
        }
    }

}