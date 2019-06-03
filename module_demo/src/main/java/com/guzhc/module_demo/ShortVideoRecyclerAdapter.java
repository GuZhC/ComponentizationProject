package com.guzhc.module_demo;

import androidx.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author : GuZhC
 * @date :  2019/6/1 9:32
 * @description : ShortVideoRecyclerAdapter
 */
public class ShortVideoRecyclerAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ShortVideoRecyclerAdapter(@Nullable List<String> data) {
        super(R.layout.demo_item_short_video_recycler,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String data) {
        helper.setText(R.id.demo_tv_recycler_item_content,data);
    }
}
