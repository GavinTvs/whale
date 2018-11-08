package com.example.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : gavin
 * @date 2018/9/5.
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(initFragmentViewId(), container, false);

        if (getArguments() != null) {
            initFragmentArguments();
        }
        onViewUnInject();
        unbinder = ButterKnife.bind(this,view);
        onViewInjected();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @LayoutRes
    public abstract int initFragmentViewId();

    /**
     * View 未注入完成
     */
    protected void onViewUnInject() {

    }

    /**
     * 初始化Fragment的
     */
    protected void initFragmentArguments(){

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
