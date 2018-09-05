package com.example.common.base;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;


/**
 * @author : gavin
 * @date 2018/9/5.
 */

public interface FragmentProvider extends IProvider {

    /**
     * 实例化Fragment
     * @param context 上下文
     * @param containerId 父容器的id
     * @param manager FragmentManager
     * @param bundle bundle
     * @param tag tag
     * @return
     */
    Fragment newInstance(Activity context, @IdRes int containerId, FragmentManager manager, Bundle bundle,String tag);


}
