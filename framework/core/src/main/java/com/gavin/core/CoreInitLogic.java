package com.gavin.core;

import androidx.annotation.Nullable;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gavin.core.application.BaseAppLogic;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @author : com.gavin
 * @date 2018/8/28.
 * Module Application
 * Core组件的Application
 */
public class CoreInitLogic extends BaseAppLogic {
    private static final String TAG = "CoreInitLogic";

    @Override
    public void onCreate() {
        super.onCreate();

        initARouter();
        initLogger();
        Log.i(TAG,"onCreate");
    }

    /**
     * 初始化ARouter
     */
    private void initARouter(){
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // Print log
            ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        }
        ARouter.init(mApplication);
    }

    /**
     * 初始化Logger
     */
    private void initLogger(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(2)
                .methodOffset(7)
//                .logStrategy(customLog)
                .tag("PRETTY_LOGGER")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });
        //保存Logs到磁盘中
//        Logger.addLogAdapter(new DiskLogAdapter());
    }
}
