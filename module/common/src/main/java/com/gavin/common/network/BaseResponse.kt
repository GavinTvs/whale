package com.gavin.common.network

import com.gavin.support.network.BaseResponseAbs

/**
 * @author lyg
 * @data  10:33
 */
class BaseResponse<T>(var code: Int, var message: String?, var data: T?):BaseResponseAbs<T>(){
    override fun bindCode(): Int {
        return code
    }

    override fun bindMessage(): String? {
        return message
    }

    override fun bindContentData(): T? {
        return data
    }
}