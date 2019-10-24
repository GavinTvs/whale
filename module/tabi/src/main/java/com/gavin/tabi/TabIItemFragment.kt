package com.gavin.tabi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ToastUtils
import com.gavin.common.NetManager
import com.gavin.common.base.BaseFragment
import com.gavin.tabi.network.MainApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_tab_main_item.*

/**
 * 首页ItemFragment
 *
 * @author com.gavin
 * @date 2018年11月06日
 */
class TabIItemFragment : BaseFragment() {
    private lateinit var mAdapter: TabIItemAdapter
    private var mData: TabIItemEntity? = null

    private var mParam1: String? = null
    private var mParam2: String? = null

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        private val JUHE_APP_KEY = "684f1124341c51c68b62f308014c2dc1"

        fun newInstance(param1: String, param2: String): TabIItemFragment {
            val fragment = TabIItemFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

    override fun initFragmentViewId(): Int {
        return R.layout.fragment_tab_main_item
    }

    override fun initFragmentArguments() {
        super.initFragmentArguments()
        mParam1 = arguments?.getString(ARG_PARAM1)
        mParam2 = arguments?.getString(ARG_PARAM2)
    }

    override fun onBaseViewCreateAfter() {

        mAdapter = TabIItemAdapter(null)
        main_item_recycler.layoutManager = LinearLayoutManager(context)
        main_item_recycler.adapter = mAdapter
        netNewsDates {
            mData = it
            mAdapter.setNewData(mData!!.result.data)
        }
    }

    /**
     * 获取新闻消息
     */
    @SuppressLint("CheckResult")
    private fun netNewsDates(consumer: (success: TabIItemEntity)->Unit) {
        NetManager.mInstance.getApi(MainApi::class.java).getNews(JUHE_APP_KEY, "top")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer,{
                    ToastUtils.showShort("加载失败")
                })
    }

//    { mainItemEntities ->
//
//                }
}
