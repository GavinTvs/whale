package com.gavin.module_tab_main;

import com.gavin.core.application.BaseAppLogic;
import com.gavin.core.network.NetManager;

/**
 * @author : com.gavin
 * @date 2018/11/7.
 */

public class MainTabInitLogic extends BaseAppLogic {
    private static final String TAG = "MainTabInitLogic";

    @Override
    public void onCreate() {
        super.onCreate();
        NetManager.getInstance().initNetConfig("http://v.juhe.cn/");
    }
}
