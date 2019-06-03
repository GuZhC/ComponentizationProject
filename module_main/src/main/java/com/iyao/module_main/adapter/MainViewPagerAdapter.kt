package com.iyao.module_main.adapter

import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alibaba.android.arouter.launcher.ARouter
import com.iyao.lib_common.RouterPath

/**
 * @author : GuZhC
 * @date : 2019/5/24 15:26
 * @description : MainViewPagerAdapter
 */
class MainViewPagerAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments:SparseArray<Fragment> = SparseArray<Fragment>()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ARouter.getInstance().build(RouterPath.demo.SHORT_VEDIO_FRAGMET).navigation() as Fragment
            1 -> ARouter.getInstance().build(RouterPath.news.NEWS_FRAGMENT)
                .withInt("pager", 1)
                .navigation() as Fragment
            2 ->  ARouter.getInstance().build(RouterPath.news.NEWS_FRAGMENT)
                .withInt("pager", 2)
                .navigation() as Fragment
            else -> ARouter.getInstance().build(RouterPath.news.NEWS_FRAGMENT)
                .withInt("pager", 3)
                .navigation() as Fragment
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment :Fragment= super.instantiateItem(container, position)as Fragment
        fragments.put(position,fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        fragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

}