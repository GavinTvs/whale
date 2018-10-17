package com.example.gavin.gavinsource;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.lib_link.constant.ActivityPath;
import com.example.lib_link.constant.DataKey;
import com.example.lib_link.constant.FragmentPath;
import com.example.common.base.BaseActivity;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author gavin
 *         首页
 */
@Route(path = ActivityPath.MAIN_MAIN)
public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.iv_tab_one)
    ImageView ivTabOne;
    @BindView(R.id.tv_tab_one)
    TextView tvTabOne;
    @BindView(R.id.rv_tab_one)
    RelativeLayout rvTabOne;
    @BindView(R.id.iv_tab_center)
    ImageView ivTabCenter;
    @BindView(R.id.tv_tab_center)
    TextView tvTabCenter;
    @BindView(R.id.rv_tab_center)
    RelativeLayout rvTabCenter;
    @BindView(R.id.iv_tab_three)
    ImageView ivTabThree;
    @BindView(R.id.tv_tab_three)
    TextView tvTabThree;
    @BindView(R.id.rv_tab_three)
    RelativeLayout rvTabThree;
    @BindView(R.id.ll_tab)
    LinearLayout llTab;

    private List<Fragment> mainTabFragments = new LinkedList<>();

    @Override
    protected void onViewInjected() {

        initFragmentList();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_main, mainTabFragments.get(0));
        transaction.addToBackStack(null);
        transaction.commit();

    }

    /**
     * 初始化首页可加载的Fragment列表
     */
    private void initFragmentList() {
        for (int i = 0; i < 3; i++) {
            Bundle bundle = new Bundle();
            bundle.putString(DataKey.KEY_TEST_ONE, "第" + i + "页");
            bundle.putString(DataKey.KEY_TEST_TWO, "第" + i + "页");

            String fragmentPath = i == 0 ? FragmentPath.MODULE_TAB_MAIN : FragmentPath.TEXT_ONE;
            Fragment tab = (Fragment) ARouter.getInstance()
                    .build(fragmentPath)
                    .navigation();
            if (tab != null) {
                tab.setArguments(bundle);
                mainTabFragments.add(tab);
            }
        }
    }

    @OnClick({R.id.rv_tab_one, R.id.rv_tab_center, R.id.rv_tab_three})
    public void onViewClicked(View view) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (view.getId() == R.id.rv_tab_one) {
            transaction.replace(R.id.fl_main, mainTabFragments.get(0));
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (view.getId() == R.id.rv_tab_center) {
            transaction.replace(R.id.fl_main, mainTabFragments.get(1));
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (view.getId() == R.id.rv_tab_three) {
            transaction.replace(R.id.fl_main, mainTabFragments.get(2));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


    @Override
    protected int initContentViewId() {
        return R.layout.activity_main;
    }
}
