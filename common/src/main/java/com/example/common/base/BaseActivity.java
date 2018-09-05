package com.example.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * @author : gavin
 * @date 2018/9/5.
 * <p>
 * 基础Activity
 * 功能概要:
 * Butterknife 初始化
 */

public abstract class BaseActivity extends RxAppCompatActivity{

    public Context mContext;
    public Activity mActivity;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;

        //初始化ARouter参数注入
        ARouter.getInstance().inject(this);
        onViewUnInject();
        setContentView(initContentViewId());
        ButterKnife.bind(this);
        onViewInjected();
    }

    /**
     * View 未注入完成
     */
    protected void onViewUnInject() {

    }
    /**
     * View 已注入完成
     */
    protected abstract void onViewInjected();

    protected abstract int initContentViewId();


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
