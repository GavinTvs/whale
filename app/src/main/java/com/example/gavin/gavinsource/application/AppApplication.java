package com.example.gavin.gavinsource.application;

import android.util.Log;

import com.example.core.CoreInitLogic;
import com.example.core.application.BaseApplication;

/**
 * @author : gavin
 * @date 2018/8/28.
 */

public class AppApplication extends BaseApplication {
    private static final String TAG = "AppApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate");
    }

    @Override
    protected void initLogic() {
        registerApplicationLogic(CoreInitLogic.class);
    }
}
