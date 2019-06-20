package com.iyao.module_main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.iyao.lib_common.utils.L
import com.iyao.module_main.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.main_activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {


    companion object {
        const val PAGE_NEWS = 0
        const val PAGE_TV = 1
        const val PAGE_TVNOW = 2
        const val PAGE_ME = 3
    }

    val btmBtns: List<BottomTabBtn> by lazy {
        arrayListOf(mainRbNews, mainRbTv, mainRbTvNow, mainRbMe)
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
        mainRbNews.setOnClickListener(this)
        mainRbTv.setOnClickListener(this)
        mainRbTvNow.setOnClickListener(this)
        mainRbMe.setOnClickListener(this)

        val mainViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        mainVp.adapter = mainViewPagerAdapter
        mainVp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                L.e("position", position.toString())
                L.e("Scrolled", positionOffset.toString()+"Scroll")
//                L.e("positionOffsetPixels", positionOffsetPixels.toString())
                //第一个页面 -> 第二个页面  position 一直0最后突变1   positionOffset 0 渐变 1 突变 0
                //第二个页面 -> 第一个页面  position 一直0           positionOffset 1 渐变 0

                //处理第一个页面到第二个最后变为1时不做处理
                if (positionOffset == 0.0f){
                    changeBtmChecked(position)
                    return
                }
                btmBtns[position].alphaNum = 1 - positionOffset
                btmBtns[position + 1].alphaNum = positionOffset
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageSelected(position: Int) {
//                changeBtmChecked(position)
//                L.e("Scrolled","Selected")
            }


        })
        //默认选中第一个
        changeBtmChecked(0)
        btmBtns[0].checked = true
    }

    override fun onClick(view: View?) {
        when (view) {
            mainRbNews -> {
                changeBtmChecked(0)
            }
            mainRbTv -> {
                changeBtmChecked(1)
            }
            mainRbTvNow -> {
                changeBtmChecked(2)
            }
            mainRbMe -> {
                changeBtmChecked(3)
            }
        }
    }

    /**
     *  改变底部选中状态
     *  p 选中位置
     */
    private fun changeBtmChecked(p: Int) {
//        btmBtns.forEach {
//            if (it.checked) it.checked = false
//        }
        mainVp.setCurrentItem(p, true)
//        btmBtns[p].checked = true
    }
}
