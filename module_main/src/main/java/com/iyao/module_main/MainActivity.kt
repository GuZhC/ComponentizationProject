package com.iyao.module_main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.main_activity_main.*
import com.alibaba.android.arouter.launcher.ARouter
import com.iyao.lib_common.RouterPath
import com.iyao.module_main.adapter.MainViewPagerAdapter


class MainActivity : AppCompatActivity() {
    companion object {
        const val PAGE_NEWS = 0
        const val PAGE_TV = 1
        const val PAGE_TVNOW = 2
        const val PAGE_ME = 3
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_main)
//        btTest.setOnClickListener {
//            Toast.makeText(this,"click",Toast.LENGTH_SHORT)
//            ARouter.getInstance().build(RouterPath.news.NEWS_ACTIVITY).navigation(this)
//        }
        initView()
    }

    private fun initView() {
        val fragments = mutableListOf<Fragment>()
        for (i in 1..4) {
            // 获取Fragment
            val fragment = ARouter.getInstance().build(RouterPath.news.NEWS_FRAGMENT)
                .withInt("pager",i)
                .navigation() as Fragment
            fragments.add(fragment)
        }
        mainVp2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val mainViewPagerAdapter = MainViewPagerAdapter(this, fragments)
        mainVp2.adapter = mainViewPagerAdapter
        mainVp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                mainRgBottom.check(position)
                changeBtmTvColor(position)
            }
        })

        mainRgBottom.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.mainRbNews -> {
                    mainVp2.currentItem = PAGE_NEWS
                    changeBtmTvColor(PAGE_NEWS)
                }
                R.id.mainRbTv -> {
                    mainVp2.currentItem = PAGE_TV
                    changeBtmTvColor(PAGE_TV)
                }
                R.id.mainRbTvNow -> {
                    mainVp2.currentItem = PAGE_TVNOW
                    changeBtmTvColor(PAGE_TVNOW)
                }
                R.id.mainRbMe -> {
                    mainVp2.currentItem = PAGE_ME
                    changeBtmTvColor(PAGE_ME)
                }
            }
        }
    }

    /**
     * 改变底部文字颜色
     */
    private var lastPager = PAGE_NEWS

    private fun changeBtmTvColor(nowPager: Int) {
        if (lastPager == nowPager) return
        when(nowPager){
            PAGE_NEWS -> mainRbNews.setTextColor(ContextCompat.getColor(this,R.color.color_map_grid_selected))
            PAGE_TV ->mainRbTv.setTextColor(ContextCompat.getColor(this,R.color.color_map_grid_selected))
            PAGE_TVNOW ->mainRbTvNow.setTextColor(ContextCompat.getColor(this,R.color.color_map_grid_selected))
            PAGE_ME ->mainRbMe.setTextColor(ContextCompat.getColor(this,R.color.color_map_grid_selected))
        }
        when(lastPager){
            PAGE_NEWS -> mainRbNews.setTextColor(ContextCompat.getColor(this,R.color.common_text))
            PAGE_TV ->mainRbTv.setTextColor(ContextCompat.getColor(this,R.color.common_text))
            PAGE_TVNOW ->mainRbTvNow.setTextColor(ContextCompat.getColor(this,R.color.common_text))
            PAGE_ME ->mainRbMe.setTextColor(ContextCompat.getColor(this,R.color.common_text))
        }
        lastPager = nowPager
    }
}
