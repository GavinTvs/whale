package com.gavin.whale

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView

import com.alibaba.android.arouter.launcher.ARouter
import com.gavin.common.base.BaseActivity
import com.gavin.common.constants.ActivityPath

import java.util.concurrent.TimeUnit

import butterknife.BindView
import com.gavin.common.base.addTo
import io.reactivex.Observable
import io.reactivex.functions.Consumer

/**
 * @author com.gavin
 */
class LaunchActivity : BaseActivity() {


    lateinit var ivStart: AppCompatImageView

    lateinit var tvStart: TextView

    override fun onBaseInitAfter(savedInstanceState: Bundle?) {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe { _ ->
                    ARouter.getInstance()
                            .build(ActivityPath.APP_MAIN)
                            .navigation()
                    finish()
                }.addTo(mBaseCompositeDisposable)
    }

    override fun initContentViewId(): Int {
        return R.layout.activity_start
    }


}
