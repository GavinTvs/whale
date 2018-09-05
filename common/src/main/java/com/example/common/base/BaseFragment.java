package com.example.common.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.ButterKnife;

/**
 * @author : gavin
 * @date 2018/9/5.
 */
public abstract class BaseFragment extends Fragment {
    private Activity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        ButterKnife.bind(mContext);

    }

}
