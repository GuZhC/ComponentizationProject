package com.guzhc.module_news.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.guzhc.module_news.R
import com.iyao.lib_common.RouterPath
import kotlinx.android.synthetic.main.activity_news.*

@Route(path = RouterPath.news.NEWS_ACTIVITY)
class NewsActivity : AppCompatActivity() {
    @JvmField
    @Autowired
    var test= ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        ARouter.getInstance().inject(this)
        tvTest.text = "content--$test"
    }
}
