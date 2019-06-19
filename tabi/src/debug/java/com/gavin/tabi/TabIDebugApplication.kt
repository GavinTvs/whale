package com.gavin.tabi

import com.gavin.core.CoreInitLogic
import com.gavin.core.application.BaseApplication

/**
 * @author : gavin
 * @date 2019-06-18.
 */
class TabIDebugApplication : BaseApplication(){

    override fun initLogic() {
        registerApplicationLogic(CoreInitLogic::class.java)
        registerApplicationLogic(TabIInitLogic::class.java)
    }

}