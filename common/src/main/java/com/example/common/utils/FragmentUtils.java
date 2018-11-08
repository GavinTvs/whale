package com.example.common.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;

/**
 * @author gavin
 * Fragment 工具类
 */
public class FragmentUtils {


    public static Fragment replaceFragment(Activity act, int containerId, FragmentManager manager, Bundle bundle, Class<? extends Fragment> cls, String tag) {
        if (act == null || act.isFinishing()) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 17 && act.isDestroyed()) {
            return null;
        }
        if (manager == null || cls == null) {
            return null;
        }
        Fragment fragment;
        FragmentTransaction ft = manager.beginTransaction();
        if (bundle != null) {
            fragment = Fragment.instantiate(act, cls.getCanonicalName(), bundle);
        } else {
            fragment = Fragment.instantiate(act, cls.getCanonicalName());
        }
        if (tag != null && !"".equals(tag)) {
            ft.replace(containerId, fragment, tag);
        } else {
            ft.replace(containerId, fragment);
        }
        ft.commitAllowingStateLoss();
        return fragment;
    }
}