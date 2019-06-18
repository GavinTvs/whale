package com.gavin.tabi;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author : com.gavin
 * @date 2018/11/6.
 * 首页Item的适配器
 */

public class TabIItemAdapter extends BaseQuickAdapter<TabIItemEntity.ResultBean.DataBean,BaseViewHolder>{

    public TabIItemAdapter(@Nullable List<TabIItemEntity.ResultBean.DataBean> data) {
        super(R.layout.item_main,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TabIItemEntity.ResultBean.DataBean item) {
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
