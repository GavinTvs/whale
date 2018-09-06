package com.example.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.common.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : gavin
 * @date 2018/9/5.
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mContext;
    protected Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(initFragmentViewId(), container, false);
        onViewUnInject();
        unbinder = ButterKnife.bind(this,view);
        onViewInjected();
        return view;
    }

    @LayoutRes
    public abstract int initFragmentViewId();

    /**
     * View 未注入完成
     */
    protected void onViewUnInject() {

    }
    /**
     * View 已注入完成
     */
    protected abstract void onViewInjected();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
