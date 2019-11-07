package com.gavin.core.application;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : com.gavin
 * @date 2018/8/28.
 */

public abstract class BaseApplication extends Application{
    private List<Class<? extends BaseAppLogic>> logicList = new ArrayList<>();
    private List<BaseAppLogic> loginClassList = new ArrayList<>();

    private static BaseApplication mApplication;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        initLogic();
        logicCreate();
    }

    protected abstract void initLogic();

    protected void registerApplicationLogic(Class<? extends BaseAppLogic> loginClass){
        logicList.add(loginClass);
    }

    private void logicCreate(){
        for(Class<? extends BaseAppLogic> logicClass : logicList){
            try{
                BaseAppLogic appLogic = logicClass.newInstance();
                loginClassList.add(appLogic);
                appLogic.setApplication(this);
                appLogic.onCreate();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for(BaseAppLogic logic : loginClassList){
            logic.onTerminate();
        }
    }
}
