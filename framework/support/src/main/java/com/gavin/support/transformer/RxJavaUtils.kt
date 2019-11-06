package com.gavin.support.transformer

import com.gavin.support.LoadingInf
import com.gavin.support.network.BaseResponseAbs
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author lyg
 * @data  10:17
 */
object RxJavaUtils {

    @JvmStatic
    fun <T> ioMain(): ObservableTransformer<T, T> {
        return ObservableTransformer { upStream ->
            upStream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    @JvmStatic
    fun <T> followLoading(loading: LoadingInf): ObservableTransformer<T, T> {
        return ObservableTransformer { upStream ->
            upStream.doOnSubscribe {
                loading.showLoading()
            }.doOnNext {
                loading.dismissLoading()
            }.doOnError {
                loading.showError(it.message?:return@doOnError)
            }
        }
    }

    @JvmStatic
    fun <T> transResponse(loading: LoadingInf? = null): ObservableTransformer<BaseResponseAbs<T>, T> {
        return ObservableTransformer { observable ->
            loading?.let{
                observable.doOnSubscribe {
                    loading.showLoading()
                }.doOnNext {
                    loading.dismissLoading()
                }.doOnError {
                    loading.showError(it.message?:return@doOnError)
                }
            }
            observable.compose(ioMain())
                    .flatMap { t ->
                        when (t.bindCode()) {
                            //todo 业务层错误逻辑处理
                        }
                        if(t.bindContentData() != null){
                            Observable.just(t.bindContentData())
                        }else{
                            Observable.empty()
                        }
                    }
        }
    }

}