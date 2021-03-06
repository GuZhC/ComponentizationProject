package com.iyao.lib_common.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.iyao.lib_common.utils.Utils

/**
 * 要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 * 组件中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 * 组件的Application需置于java/debug文件夹中，不得放于主代码；
 * 组件中获取Context的方法必须为:Utils.getContext()，不允许其他写法；
 *
 * @author : GuZhC
 * @date : 2019/5/23 17:10
 * @description : BaseApplication
 */

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        ARouter.init(this)
    }


}
