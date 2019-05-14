package com.gavin.module_tab_main;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gavin.common.base.BaseFragment;
import com.gavin.core.network.NetManager;
import com.gavin.module_tab_main.network.MainApi;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页ItemFragment
 *
 * @author com.gavin
 * @date 2018年11月06日
 */
public class MainItemFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R2.id.main_item_recycler)
    RecyclerView mRecycler;

    private MainItemAdapter mAdapter;
    private MainItemEntity mData;

    private static final String JUHE_APP_KEY = "684f1124341c51c68b62f308014c2dc1";

    private String mParam1;
    private String mParam2;

    public MainItemFragment() {
    }

    public static MainItemFragment newInstance(String param1, String param2) {
        MainItemFragment fragment = new MainItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initFragmentViewId() {
        return R.layout.fragment_tab_main_item;
    }

    @Override
    protected void initFragmentArguments() {
        super.initFragmentArguments();
        mParam1 = getArguments().getString(ARG_PARAM1);
        mParam2 = getArguments().getString(ARG_PARAM2);
    }

    @Override
    protected void onViewInjected() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);

        mAdapter = new MainItemAdapter(null);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(mAdapter);

        netNewsDatas();
    }

    /**
     * 获取新闻消息
     */
    private void netNewsDatas() {
        NetManager.getInstance().getApi(MainApi.class).getNews(JUHE_APP_KEY,"top")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MainItemEntity>() {
                    @Override
                    public void accept(MainItemEntity mainItemEntities) throws Exception {
                        mData = mainItemEntities;
                        mAdapter.setNewData(mData.getResult().getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Logger.e(throwable,"getNews");
                    }
                });
    }
}
