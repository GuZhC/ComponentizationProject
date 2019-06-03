package com.guzhc.module_demo

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class DemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.demo_activity_demo)
        initView()
    }

    private fun initView() {
        supportFragmentManager.beginTransaction().add(R.id.demo_fl_demo, ShortVedioFragmet()).commit()

    }
}
