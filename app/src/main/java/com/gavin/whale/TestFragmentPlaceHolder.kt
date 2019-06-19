package com.gavin.whale

import com.alibaba.android.arouter.facade.annotation.Route
import com.gavin.common.base.BaseFragment
import com.gavin.common.constants.FragmentPath

/**
 * @author : gavin
 * @date 2019-06-18.
 */
@Route(path = FragmentPath.PLACEHOLDER)
class TestFragmentPlaceHolder : BaseFragment() {

    override fun initFragmentViewId(): Int {
        return R.layout.fragment_placeholder_test
    }

    override fun onBaseViewCreateAfter() {
    }
}