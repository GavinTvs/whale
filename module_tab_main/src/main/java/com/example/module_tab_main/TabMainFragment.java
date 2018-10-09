package com.example.module_tab_main;

import android.text.TextUtils;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.common.base.BaseFragment;
import com.example.lib_link.constant.DataKey;
import com.example.lib_link.constant.FragmentPath;
import com.orhanobut.logger.Logger;

import butterknife.BindView;

/**
 * TabFragment
 */
@Route(path = FragmentPath.TAB_ONE)
public class TabMainFragment extends BaseFragment{

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
            Logger.d("mParam1:"+mParam1+" mParam2:"+mParam2);
        }else{
            tvText.setText("No Params");
        }
    }

    @Override
    public int initFragmentViewId() {
        return R.layout.fragment_tab_main;
    }

}
