package com.guzhc.module_demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : GuZhC
 * @date :  2019/6/1 9:46
 * @description : 顶部banner viewPager 适配器
 */
public class ShortViewoViewPagerAdapter extends PagerAdapter {
    private final Context context;
    private final List<String> mData;
    private PagerClick pagerClick;

    public ShortViewoViewPagerAdapter(List<String> mData, Context context) {
        this.mData = mData;
        this.context = context;
    }



    @Override
    public int getCount() {
        if (mData.size()<2){
            return mData.size();
        }else {
            return Integer.MAX_VALUE;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        //Warning：不要在这里调用removeView
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= mData.size();
        final String data = mData.get(position);
        View view =  View.inflate(container.getContext(),R.layout.demo_item_short_video_viewpager, null);
        ImageView img = view.findViewById(R.id.demo_img_short_video_item);
        TextView tvContent = view.findViewById(R.id.demo_tv_short_video_item);
        tvContent.setText(data);
        Glide.with(context)
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1559367407524&di=2a7d6a1a12707287e908d61922a637c2&imgtype=0&src=http%3A%2F%2Fpic72.nipic.com%2Ffile%2F20150715%2F9448607_192612583000_2.jpg")
                .into(img);
        //对ViewPager页号求模取出View列表中要显示的项
        if (position < 0) {
            position = mData.size() + position;
        }
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        final int finalPosition = position;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pagerClick.onPagerClik(mData.get(finalPosition));
            }
        });
        return view;
    }
    public  void setPagerClick(PagerClick pagerClick){
        this.pagerClick =pagerClick;
    }

    public  interface  PagerClick{
        void onPagerClik(String data);
    }
}
