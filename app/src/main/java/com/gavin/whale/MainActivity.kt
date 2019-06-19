package com.gavin.whale

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import butterknife.OnClick
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.FragmentUtils
import com.gavin.common.base.BaseActivity
import com.gavin.common.constants.ActivityPath
import com.gavin.common.constants.DataKey
import com.gavin.common.constants.FragmentPath
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * @author com.gavin
 * 首页
 */
@Route(path = ActivityPath.APP_MAIN)
class MainActivity : BaseActivity() {

    private val mainTabFragments = LinkedHashMap<String,Fragment>()
    private var mShowFragmentPath = FragmentPath.TAB_I

    override fun onBaseInitAfter(savedInstanceState: Bundle?) {

        initFragments()
        FragmentUtils.add(supportFragmentManager, ArrayList(mainTabFragments.values), R.id.fl_main, 0)
        BarUtils.setStatusBarVisibility(this, false)
    }

    /**
     * 初始化首页可加载的Fragment列表
     */
    private fun initFragments() {
        for (i in 0..2) {
            val bundle = Bundle()
            bundle.putString(DataKey.KEY_TEST_ONE, "第" + i + "页")
            bundle.putString(DataKey.KEY_TEST_TWO, "第" + i + "页")
            var fragmentPath = ""
            when (i) {
                0 -> {
                    fragmentPath = FragmentPath.TAB_I
                }
                1 -> {
                    fragmentPath = FragmentPath.TAB_II
                }
                2 -> {
                    fragmentPath = FragmentPath.TAB_III
                }
            }

            val tab: Fragment? = ARouter.getInstance()
                    .build(fragmentPath)
                    .navigation() as Fragment?

            if (tab != null) {
                tab.arguments = bundle
                mainTabFragments[fragmentPath] = tab
            }
        }
    }

    @OnClick(R.id.rv_tab_one, R.id.rv_tab_center, R.id.rv_tab_three)
    fun onViewClicked(view: View) {
        var showFragmentPath = FragmentPath.PLACEHOLDER

        when (view.id) {
            R.id.rv_tab_one -> {
                showFragmentPath = FragmentPath.TAB_I
            }
            R.id.rv_tab_center -> {
                showFragmentPath = FragmentPath.TAB_II
            }
            R.id.rv_tab_three -> {
                showFragmentPath = FragmentPath.TAB_III
            }
        }
        var showFragment = ARouter.getInstance()
                .build(showFragmentPath)
                .navigation() as Fragment?
        if(showFragment == null){
            showFragment = ARouter.getInstance()
                    .build(FragmentPath.PLACEHOLDER)
                    .navigation() as Fragment
        }
        mShowFragmentPath = showFragmentPath
        FragmentUtils.showHide(showFragment, ArrayList(mainTabFragments.values))

    }


    override fun initContentViewId(): Int {
        return R.layout.activity_main
    }
}
