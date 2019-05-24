package com.guzhc.module_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.news_fragment_news.*

/**
 * @author : GuZhC
 * @date : 2019/5/24 16:10
 * @description : NewsFragment
 */
class NewsFragment(i: Int) : Fragment() {
    val i = i
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_fragment_news, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsTvPager.text = "Pager-$i"
    }
}