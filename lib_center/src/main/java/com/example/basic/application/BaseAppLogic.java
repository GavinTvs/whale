package com.example.basic.application;

import android.content.res.Configuration;
import android.support.annotation.NonNull;

import com.example.basic.application.BaseApplication;

/**
 * @author : gavin
 * @date 2018/8/28.
 */
public class BaseAppLogic {
    protected static BaseApplication mApplication;
    public BaseAppLogic() {}

    public void setApplication(@NonNull BaseApplication application) {
        mApplication = application;
    }

    public static BaseApplication getApplication() {
        return mApplication;
    }

    public void onCreate(){}
    public void onTerminate(){}
    public void onLowMemory(){}
    public void onTrimMemory(int level){}
    public void onConfigurationChanged(Configuration newConfig){}
}
