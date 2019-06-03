package com.guzhc.module_news.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.guzhc.module_news.R
import com.iyao.lib_common.RouterPath
import kotlinx.android.synthetic.main.news_fragment_news.*

/**
 * @author : GuZhC
 * @date : 2019/5/24 16:10
 * @description : NewsFragment
 */
@Route(path = RouterPath.news.NEWS_FRAGMENT)
class NewsFragment : Fragment() {
    @Autowired(name = "pager")
    @JvmField
    var pager = 1
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.news_fragment_news, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ARouter.getInstance().inject(this)
        newsTvPager.text = "Pager-$pager"
    }
}