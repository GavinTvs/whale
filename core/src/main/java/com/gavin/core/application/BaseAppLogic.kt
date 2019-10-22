package com.gavin.core.application

import android.content.res.Configuration

/**
 * @author : com.gavin
 * @date 2018/8/28.
 */
open class BaseAppLogic {

    companion object {
        @JvmField
        var mApplication: BaseApplication? =null

        @JvmStatic
        fun getApplication():BaseApplication{
            return mApplication?:throw NullPointerException("must setApplication before")
        }
    }

    open fun onCreate() {}
    open fun onTerminate() {}
    open fun onLowMemory() {}
    open fun onTrimMemory(level: Int) {}
    open fun onConfigurationChanged(newConfig: Configuration) {}

    fun setApplication(application:BaseApplication){
        mApplication = application
    }

}
