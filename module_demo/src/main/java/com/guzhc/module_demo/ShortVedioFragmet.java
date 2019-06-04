package com.guzhc.module_demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iyao.lib_common.RouterPath;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : GuZhC
 * @date :  2019/6/1 9:26
 * @description : ShortVedioFragmet
 */
@Route(path = RouterPath.demo.SHORT_VEDIO_FRAGMET)
public class ShortVedioFragmet extends Fragment implements ViewPager.OnPageChangeListener {
    private RecyclerView recyclerShortVideo;
    private List<String> mData = new ArrayList<>();
    private ShortVideoRecyclerAdapter shortVideoRecyclerAdapter;
    private LayoutInflater inflater;
    public ImageHandler handler = new ImageHandler(new WeakReference<ShortVedioFragmet>(this));
    public ViewPager viewPagerBanner;
    private ShortViewoViewPagerAdapter shortViewoViewPagerAdapter;
    private LinearLayoutManager mLayoutManager;
    private int playPosition = 1;
    private int stopPosition = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        View view = inflater.inflate(R.layout.demo_fragment_short_video, container,false);
        recyclerShortVideo = view.findViewById(R.id.demo_recycler_short_video);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDate();
        initView();
    }

    private void initDate() {
        mData.clear();
        mData.add("播1放");
        for (int i = 0; i < 15; i++) {
            mData.add("item" + i);
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        if (mData.size()==0) return;
        //设置banner
        shortViewoViewPagerAdapter = new ShortViewoViewPagerAdapter(mData, getContext());
        View bannerView = LayoutInflater.from(getContext()).inflate(R.layout.demo_layout_short_video_banner, null);
        viewPagerBanner = bannerView.findViewById(R.id.demo_vp_short_video_top);
        LinearLayout mViewPagerContainer = bannerView.findViewById(R.id.demo_ll_short_video_vp_root);
        viewPagerBanner.setAdapter(shortViewoViewPagerAdapter);
        viewPagerBanner.addOnPageChangeListener(this);
        //将容器的触摸事件反馈给ViewPager
        mViewPagerContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPagerBanner.dispatchTouchEvent(event);
            }
        });

    //默认在中间，使用户看不到边界
        int p = Integer.MAX_VALUE / 3;
        viewPagerBanner.setCurrentItem(p - p%mData.size());
        viewPagerBanner.setOffscreenPageLimit(mData.size());

        //设置recyclerView
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerShortVideo.setLayoutManager(mLayoutManager);
        shortVideoRecyclerAdapter = new ShortVideoRecyclerAdapter(mData);
        shortVideoRecyclerAdapter.addHeaderView(bannerView);
        recyclerShortVideo.setAdapter(shortVideoRecyclerAdapter);
        initClick();
    }

    private void initClick() {
        shortVideoRecyclerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Log.e("clickRecycler","click"+position);
            }
        });
        shortViewoViewPagerAdapter.setPagerClick(new ShortViewoViewPagerAdapter.PagerClick() {
            @Override
            public void onPagerClik(String data) {
                Log.e("clickPager","clickPager"+data);
            }
        });
        recyclerShortVideo.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //静止的时候
                if (newState == RecyclerView.SCROLL_STATE_IDLE ){
                    if (playPosition == 0) {
                        playPosition =1;
                    }
                    if (stopPosition!=playPosition){
                        mData.set(playPosition-1,"播放");
                        shortVideoRecyclerAdapter.startPlay(playPosition);
                        stopPosition = playPosition;
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (mLayoutManager == null) {
                    return;
                }
                //得到显示屏内的第一个list的位置数position
                int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                View firstView = mLayoutManager.findViewByPosition(firstVisibleItem);
                //数量小于二
                if (null == firstView||mLayoutManager.getChildCount() < 2) {
                    return;
                }
                //向上滑动
                if (dy > 0) {
                    if (firstView.getHeight() + firstView.getTop() <= firstView.getHeight() / 2) {
                        playPosition = firstVisibleItem + 1;
                        if (stopPosition!=-1){
                            if (playPosition == 1&&stopPosition==1){
                                return;
                            }
                            mData.set(stopPosition-1,"停止");
                            shortVideoRecyclerAdapter.stopPlay(stopPosition);
                            stopPosition =-1;
                        }
                    }
//                    else {
//                        playPosition = firstVisibleItem;
//                    }
                }
                //向下滑动
                if (dy < 0) {
                    if (firstView.getHeight() + firstView.getTop() >= firstView.getHeight() / 2) {
                        playPosition = firstVisibleItem;
                        if (stopPosition!=-1){
                            if (playPosition!=0&&stopPosition ==1){
                                return;
                            }
                            mData.set(stopPosition=1,"停止");
                            shortVideoRecyclerAdapter.stopPlay(stopPosition);
                            stopPosition =-1;
                        }
                    }
//                    else {
//                        playPosition = firstVisibleItem + 1;
//                    }
                }

            }
        });
    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser) {
//            //相当于OnResume(),可以做相关逻辑
//            //开始轮播效果
//            handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
//        }else {
//            //相当于OnPause()
//            //暂停轮播效果
//            handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
//        }
//    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//        handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, position, 0));
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        switch (state) {
//            //滑动中
//            case ViewPager.SCROLL_STATE_DRAGGING:
//                handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
//                break;
//            //未滑动
//            case ViewPager.SCROLL_STATE_IDLE:
//                handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
//                break;
//            default:
//                break;
//        }
    }
}




















