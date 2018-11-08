package com.example.module_tab_main;

import android.util.Log;

import com.example.core.application.BaseAppLogic;
import com.example.core.network.NetManager;

/**
 * @author : gavin
 * @date 2018/11/7.
 */

public class MainTabInitLogic extends BaseAppLogic{
    private static final String TAG = "MainTabInitLogic";

    @Override
    public void onCreate() {
        super.onCreate();
        NetManager.getInstance().initNetConfig("http://v.juhe.cn/");
    }
}
