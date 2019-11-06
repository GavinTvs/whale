package com.gavin.support.network

/**
 * @author lyg
 * @data  17:04
 */
abstract class BaseResponseAbs<T> {

    abstract fun bindCode():Int

    abstract fun bindMessage():String?

    abstract fun bindContentData():T?


}