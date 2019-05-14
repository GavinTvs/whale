package com.gavin.common.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import butterknife.ButterKnife
import butterknife.Unbinder
import com.alibaba.android.arouter.launcher.ARouter
import com.gavin.common.constants.ActivityPath
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * @author : com.gavin
 * @date 2018/9/5.
 *
 *
 * 基础Activity
 * 功能概要:
 * Butterknife 初始化
 */

fun Disposable.addTo(c:CompositeDisposable){
    c.add(this)
}

abstract class BaseActivity : RxAppCompatActivity() {

    lateinit var mContext: Context
    lateinit var mBaseCompositeDisposable:CompositeDisposable
    var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mBaseCompositeDisposable= CompositeDisposable()
        onBaseInitBefore(savedInstanceState)
        setContentView(initContentViewId())
        //ButterKnife 注入
        unbinder = ButterKnife.bind(this)
        //初始化ARouter参数注入
        ARouter.getInstance().inject(this)
        onBaseInitAfter(savedInstanceState)

    }

    /**
     * View 已注入完成
     */
    protected abstract fun onBaseInitAfter(savedInstanceState: Bundle?)

    protected fun onBaseInitBefore(savedInstanceState: Bundle?){

    }


    @LayoutRes
    protected abstract fun initContentViewId(): Int


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        unbinder?.unbind()
        mBaseCompositeDisposable.clear()
        super.onDestroy()
    }
}
