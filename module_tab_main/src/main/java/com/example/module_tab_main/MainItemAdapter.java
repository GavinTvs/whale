package com.example.module_tab_main;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author : gavin
 * @date 2018/11/6.
 * 首页Item的适配器
 */

public class MainItemAdapter extends BaseQuickAdapter<MainItemEntity.ResultBean.DataBean,BaseViewHolder>{

    public MainItemAdapter(@Nullable List<MainItemEntity.ResultBean.DataBean> data) {
        super(R.layout.item_main,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainItemEntity.ResultBean.DataBean item) {
        helper.setText(R.id.tv_item_main_title,item.getTitle());
        helper.setText(R.id.tv_item_main_content,item.getDate());
        helper.setText(R.id.tv_item_main_author,item.getAuthor_name());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(ContextCompat.getDrawable(mContext,R.mipmap.ic_launcher));
        Glide.with(mContext)
                .load(item.getThumbnail_pic_s())
                .transition(new DrawableTransitionOptions().crossFade(500))
                .apply(options)
                .into((ImageView) helper.getView(R.id.iv_item_main_pic));

    }

}
