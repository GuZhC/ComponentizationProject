package com.guzhc.module_news.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @author : GuZhC
 * @date : 2019/5/24 15:26
 * @description : MainViewPagerAdapter
 */
class MainViewPagerAdapter(fragmentActivity: FragmentActivity, fragments: List<Fragment>) :
    FragmentStateAdapter(fragmentActivity) {
    private val fragments: List<Fragment> = fragments
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getItemCount(): Int {
        return fragments.size
    }
}