package com.gavin.whale;

import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.gavin.common.base.BaseActivity;
import com.gavin.lib_link.constant.ActivityPath;

import butterknife.BindView;

/**
 * @author com.gavin
 */
public class LaunchActivity extends BaseActivity {

    @BindView(R.id.iv_start)
    ImageView ivStart;
    @BindView(R.id.tv_start)
    TextView tvStart;

    @Override
    protected void onViewInjected() {
        tvStart.postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance()
                        .build(ActivityPath.MAIN_MAIN)
                        .navigation();
            }
        },2000);
    }

    @Override
    protected int initContentViewId() {
        return R.layout.activity_start;
    }

}
