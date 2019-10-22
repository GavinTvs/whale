package com.gavin.tabiii

import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.FragmentUtils
import com.blankj.utilcode.util.ToastUtils
import com.gavin.common.base.BaseActivity
import com.gavin.common.constants.FragmentPath

class TabIIIDebugActivity : BaseActivity() {

    private lateinit var fragmentContainer:FrameLayout

    override fun onBaseInitAfter(savedInstanceState: Bundle?) {
        val fragment = ARouter.getInstance().build(FragmentPath.TAB_III).navigation() as Fragment?
        if (fragment != null) {
            fragmentContainer = findViewById(R.id.container_fragment)
            FragmentUtils.add(supportFragmentManager,fragment,R.id.container_fragment)
        }else{
            ToastUtils.showShort("没有找到TAB_III")
        }
    }

    override fun initContentViewId(): Int {
        return R.layout.activity_tab_iii_debug
    }
}
