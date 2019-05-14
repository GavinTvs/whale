package com.gavin.whale

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.FrameLayout
import butterknife.BindView

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.FragmentUtils
import com.gavin.common.base.BaseActivity
import com.gavin.common.constants.ActivityPath
import com.gavin.common.constants.DataKey
import com.gavin.common.constants.FragmentPath

import java.util.LinkedList

import butterknife.OnClick


/**
 * @author com.gavin
 * 首页
 */
@Route(path = ActivityPath.APP_MAIN)
class MainActivity : BaseActivity() {

    @BindView(R.id.fl_main)
    lateinit var flMain: FrameLayout

    private val mainTabFragments = LinkedList<Fragment>()

    override fun onBaseInitAfter(savedInstanceState: Bundle?) {

        initFragments()
        FragmentUtils.add(supportFragmentManager, mainTabFragments, R.id.fl_main, 0)

    }

    /**
     * 初始化首页可加载的Fragment列表
     */
    private fun initFragments() {
        for (i in 0..2) {
            val bundle = Bundle()
            bundle.putString(DataKey.KEY_TEST_ONE, "第" + i + "页")
            bundle.putString(DataKey.KEY_TEST_TWO, "第" + i + "页")

            val fragmentPath = if (i == 0) FragmentPath.MODULE_TAB_MAIN else FragmentPath.TEXT_ONE
            val tab: Fragment? = ARouter.getInstance()
                    .build(fragmentPath)
                    .navigation() as Fragment?
            if (tab != null) {
                tab.arguments = bundle
                mainTabFragments.add(tab)
            }
        }
    }

    @OnClick(R.id.rv_tab_one, R.id.rv_tab_center, R.id.rv_tab_three)
    fun onViewClicked(view: View) {
        when(view.id){
            R.id.rv_tab_one ->{
                FragmentUtils.showHide(mainTabFragments[0], mainTabFragments)
            }
            R.id.rv_tab_center -> {
                FragmentUtils.showHide(mainTabFragments[1], mainTabFragments)
            }
            R.id.rv_tab_three -> {
                FragmentUtils.showHide(mainTabFragments[2], mainTabFragments)
            }
        }
    }


    override fun initContentViewId(): Int {
        return R.layout.activity_main
    }
}
