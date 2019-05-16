package com.gavin.common.base

import android.content.Context
import android.os.Bundle

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import butterknife.ButterKnife
import butterknife.Unbinder

import com.gavin.common.base.addTo

/**
 * @author : com.gavin
 * @date 2018/9/5.
 */
abstract class BaseFragment : Fragment() {
    protected var mContext: Context? = null
    protected var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(initFragmentViewId(), container, false)

        if (arguments != null) {
            initFragmentArguments()
        }
        onViewUnInject()
        unbinder = ButterKnife.bind(this, view)
        onViewInjected()
        return view
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext = context
    }

    @LayoutRes
    abstract fun initFragmentViewId(): Int

    /**
     * View 未注入完成
     */
    protected fun onViewUnInject() {

    }

    /**
     * 初始化Fragment的
     */
    protected open fun initFragmentArguments() {

    }

    /**
     * View 已注入完成
     */
    protected abstract fun onViewInjected()

    override fun onDestroyView() {
        super.onDestroyView()
        unbinder?.unbind()
    }
}
