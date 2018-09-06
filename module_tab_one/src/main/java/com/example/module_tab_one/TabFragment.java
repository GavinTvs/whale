package com.example.module_tab_one;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class TabFragment extends BaseFragment{

    @BindView(R2.id.tv_text)
    TextView tvText;

    private String mParam1;
    private String mParam2;

    @Override
    protected void onViewInjected() {
        if (getArguments() != null) {
            mParam1 = getArguments().getString(DataKey.KEY_TEST_ONE);
            mParam2 = getArguments().getString(DataKey.KEY_TEST_TWO);
        }

        if(!TextUtils.isEmpty(mParam1) && !TextUtils.isEmpty(mParam2)){
            tvText.setText("mParam1:"+mParam1+" mParam2:"+mParam2);
        }else{
            tvText.setText("No Params");
        }
    }

    @Override
    public int initFragmentViewId() {
        return R.layout.fragment_tab_one;
    }



//    @Override
//    public TabFragment newInstance(String params1, String params2) {
//        TabFragment fragment = new TabFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(DataKey.KEY_TEST_ONE,params1);
//        bundle.putString(DataKey.KEY_TEST_TWO,params2);
//        fragment.setArguments(bundle);
//        return fragment;
//    }

//    @Override
//    public void init(Context context) {
//
//    }
}
