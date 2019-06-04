package com.guzhc.module_demo;

import android.util.Log;
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
    protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv_short_video_test, item);
    }

    public void startPlay(int playPosition) {
//            getData().set(playPosition,"播放");
            notifyItemChanged(playPosition);
            Log.e(TAG, "startPlay: 播放：" + playPosition);
    }
    public void stopPlay(int stopPosition){
//            getData().set(stopPosition,"停止");
            notifyItemChanged(stopPosition);
            Log.e(TAG, "startPlay: 停止：" + stopPosition);
    }
}
