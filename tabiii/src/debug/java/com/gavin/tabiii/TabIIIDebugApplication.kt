package com.gavin.tabiii

import com.gavin.core.CoreInitLogic
import com.gavin.core.application.BaseApplication

/**
 * @author : gavin
 * @date 2019-06-18.
 */
class TabIIIDebugApplication : BaseApplication(){

    override fun initLogic() {
        registerApplicationLogic(CoreInitLogic::class.java)
        registerApplicationLogic(TabIIIInitLogic::class.java)
    }

}