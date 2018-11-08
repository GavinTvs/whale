package com.example.module_tab_main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.base.BaseFragment;
import com.example.lib_link.constant.FragmentPath;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 * @author gavin
 */
@Route(path = FragmentPath.MODULE_TAB_MAIN)
public class MainFragment extends BaseFragment {

    @BindView(R2.id.tv_app_name)
    TextView tvAppName;
    @BindView(R2.id.tab_main)
    TabLayout tabMain;
    @BindView(R2.id.view_pager_main)
    ViewPager viewPagerMain;

    private String mParam1;
    private String mParam2;

    private List<String> mTabs = new ArrayList<>();

    @Override
    protected void onViewInjected() {
        mTabs.add("top");
        mTabs.add("shehui");
        mTabs.add("guonei");
        for (int i = 0; i < mTabs.size(); i++) {
            tabMain.addTab(tabMain.newTab().setText(mTabs.get(i)));
        }

        viewPagerMain.setAdapter(new MainPagerAdapter(getFragmentManager()));
        tabMain.setupWithViewPager(viewPagerMain);

    }

    @Override
    public int initFragmentViewId() {
        return R.layout.fragment_tab_main;
    }


    public class MainPagerAdapter extends FragmentPagerAdapter{

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            return MainItemFragment.newInstance("1","2");
        }

        @Override
        public int getCount() {
            return mTabs.size();
        }

    }

}
