package com.gavin.whale.application;

import android.util.Log;

import com.gavin.core.CoreInitLogic;
import com.gavin.core.application.BaseApplication;
import com.gavin.tabi.TabIInitLogic;

/**
 * @author : com.gavin
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
        registerApplicationLogic(TabIInitLogic.class);
    }
}
