package com.iyao.module_main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.iyao.module_main.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.main_activity_main.*


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
        val mainViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        mainVp.adapter = mainViewPagerAdapter
        mainVp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageSelected(position: Int) {
                mainRgBottom.check(position)
                changeBtmTvColor(position)
            }


        })

        mainRgBottom.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.mainRbNews -> {
                    mainVp.currentItem = PAGE_NEWS
                    changeBtmTvColor(PAGE_NEWS)
                }
                R.id.mainRbTv -> {
                    mainVp.currentItem = PAGE_TV
                    changeBtmTvColor(PAGE_TV)
                }
                R.id.mainRbTvNow -> {
                    mainVp.currentItem = PAGE_TVNOW
                    changeBtmTvColor(PAGE_TVNOW)
                }
                R.id.mainRbMe -> {
                    mainVp.currentItem = PAGE_ME
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
