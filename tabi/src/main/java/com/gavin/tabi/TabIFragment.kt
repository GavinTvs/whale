package com.gavin.tabi

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

import com.alibaba.android.arouter.facade.annotation.Route
import com.gavin.common.base.BaseFragment
import com.gavin.common.constants.FragmentPath
import com.google.android.material.tabs.TabLayout

import java.util.ArrayList

import butterknife.BindView
import com.blankj.utilcode.util.ScreenUtils

/**
 *
 * @author com.gavin
 */
@Route(path = FragmentPath.TAB_I)
class TabIFragment : BaseFragment() {

    @BindView(R2.id.tab_main)
    lateinit var tabMain: TabLayout
    @BindView(R2.id.view_pager_main)
    lateinit var viewPagerMain: ViewPager
    @BindView(R2.id.iv_main_top)
    lateinit var ivMainTop: AppCompatImageView

    private val mTabs = ArrayList<String>()

    override fun onBaseViewCreateAfter() {

        mTabs.add("头条")
        mTabs.add("社会")
        mTabs.add("国内")
        for (i in mTabs.indices) {
            tabMain.addTab(tabMain.newTab().setText(mTabs[i]))
        }

        viewPagerMain.adapter = MainPagerAdapter(fragmentManager!!)
        tabMain.setupWithViewPager(viewPagerMain)

        ivMainTop.post {
            val ivMainLayoutParams :ViewGroup.LayoutParams = ivMainTop.layoutParams
            ivMainLayoutParams.height = (ScreenUtils.getScreenHeight()*0.6).toInt()
            ivMainTop.layoutParams = ivMainLayoutParams
        }

    }

    override fun initFragmentViewId(): Int {
        return R.layout.fragment_tab_main
    }


    inner class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(i: Int): Fragment {
            return TabIItemFragment.newInstance("1", "2")
        }

        override fun getCount(): Int {
            return mTabs.size
        }

        override fun getPageTitle(position: Int): CharSequence? {

            return mTabs[position]
        }
    }

}
