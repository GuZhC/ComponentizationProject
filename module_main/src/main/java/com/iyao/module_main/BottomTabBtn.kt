package com.iyao.module_main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.iyao.lib_common.utils.L
import kotlinx.android.synthetic.main.modle_main_layout_botom_btn.view.*


/**
 * @author : GuZhC
 * @date : 2019/6/20 10:12
 * @description : BottomTabBtn
 */
class BottomTabBtn : LinearLayout {
    private var iconChecked: Int = R.mipmap.ic_launcher
    private var iconNoChecked: Int = R.mipmap.ic_launcher
    private var textBtm: Int = R.string.app_name

    internal var alphaNum: Float = 0f
        set(value) {
            field = value
            setBtnAlphaNum(alphaNum)
        }

    internal var checked: Boolean = false
        set(value) {
            field = value
            alphaNum = if (value) 1f
            else 0f
            setBtnAlphaNum(alphaNum)
        }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.modle_main_layout_botom_btn, this, true)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.MainBtmTabBtn)
            checked = typedArray.getBoolean(R.styleable.MainBtmTabBtn_checked, false)
            alphaNum = typedArray.getFloat(R.styleable.MainBtmTabBtn_alphaNum, 0f)
            iconChecked = typedArray.getResourceId(R.styleable.MainBtmTabBtn_iconChecked, 0)
            iconNoChecked = typedArray.getResourceId(R.styleable.MainBtmTabBtn_iconNoChecked, 0)
            textBtm = typedArray.getResourceId(R.styleable.MainBtmTabBtn_textBtm, 0)
            //回收资源，这一句必须调用
            typedArray.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        //不能setBackgroundResource
        mainTabBottomIconG.setImageResource(iconChecked)
        mainTabBottomIconB.setImageResource(iconNoChecked)
        mainTabBottomText.text = resources.getString(textBtm)
    }

    private fun setBtnAlphaNum(alphaNum: Float) {
//        L.e("$alphaNum")
        when (alphaNum) {
            1f -> {
                mainTabBottomIconG.visibility = View.INVISIBLE
                mainTabBottomIconB.visibility = View.VISIBLE
            }
            0f -> {
                mainTabBottomIconG.visibility = View.VISIBLE
                mainTabBottomIconB.visibility = View.INVISIBLE
            }
            else -> {
                mainTabBottomIconG.visibility = View.VISIBLE
                mainTabBottomIconB.visibility = View.VISIBLE
            }
        }
        mainTabBottomIconG.imageAlpha = (alphaNum * 255).toInt()
        mainTabBottomIconB.imageAlpha = ((1 - alphaNum) * 255).toInt()
    }

}


