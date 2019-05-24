package com.iyao.zujianhuatest

import com.alibaba.android.arouter.launcher.ARouter
import com.iyao.lib_common.base.BaseApplication
import com.iyao.lib_common.utils.Utils

/**
 * @author : GuZhC
 * @date : 2019/5/23 17:22
 * @description :  一些初始化的工作
 */

class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        if (Utils.isAppDebug) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }


}
