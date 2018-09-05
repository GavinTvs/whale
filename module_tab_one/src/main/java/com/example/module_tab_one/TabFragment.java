package com.example.module_tab_one;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.basic.constant.DataKey;
import com.example.basic.constant.FragmentPath;
import com.example.common.base.BaseFragment;
import com.example.module_main.R;
import com.example.module_main.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TabFragment
 */
@Route(path = FragmentPath.TAB_ONE)
public class TabFragment extends BaseFragment {

    @BindView(R2.id.tv_text)
    TextView tvText;
    Unbinder unbinder;

    private String mParam1;
    private String mParam2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(DataKey.KEY_TEST_ONE);
            mParam2 = getArguments().getString(DataKey.KEY_TEST_TWO);
        }

        tvText.setText("mParam1:"+mParam1+" mParam2:"+mParam2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_one, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
