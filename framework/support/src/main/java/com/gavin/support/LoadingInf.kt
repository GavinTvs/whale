package com.gavin.support

/**
 * @author lyg
 * @data  17:01
 */
interface LoadingInf {
    /**
     * 显示loading
     */
    fun showLoading()

    /**
     * 隐藏loading
     */
    fun dismissLoading()

    /**
     * 显示错误信息
     * @param errMes
     */
    fun showError(errMes: String)
}