package com.example.core;

import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.basic.application.BaseAppLogic;

/**
 * @author : gavin
 * @date 2018/8/28.
 * Module Application
 * 组件的Application
 */
public class CoreInitLogic extends BaseAppLogic{
    private static final String TAG = "CoreInitLogic";

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(mApplication);
        Log.i(TAG,"onCreate");
    }


}
