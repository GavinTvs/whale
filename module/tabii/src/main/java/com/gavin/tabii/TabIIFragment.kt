package com.gavin.tabii

import android.annotation.SuppressLint
import android.text.TextUtils

import com.alibaba.android.arouter.facade.annotation.Route
import com.gavin.common.base.BaseFragment
import com.gavin.common.constants.DataKey
import com.gavin.common.constants.FragmentPath
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_tab_ii.*


/**
 * TabFragment
 */
@Route(path = FragmentPath.TAB_II)
class TabIIFragment : BaseFragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null

    @SuppressLint("SetTextI18n")
    override fun onBaseViewCreateAfter() {
        if (arguments != null) {
            mParam1 = arguments!!.getString(DataKey.KEY_TEST_ONE)
            mParam2 = arguments!!.getString(DataKey.KEY_TEST_TWO)
        }

        if (!TextUtils.isEmpty(mParam1) && !TextUtils.isEmpty(mParam2)) {

            tv_text.text = "mParam1:$mParam1 mParam2:$mParam2"
            Logger.d("mParam1:$mParam1 mParam2:$mParam2")
        } else {
            tv_text.text = "No Params"
        }
    }

    override fun initFragmentViewId(): Int {
        return R.layout.fragment_tab_ii
    }

}
