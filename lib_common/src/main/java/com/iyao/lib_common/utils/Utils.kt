package com.iyao.lib_common.utils


import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.text.TextUtils
import android.view.View

/**
 * @author : GuZhC
 * @date : 2019/5/23 17:13
 * @description : 初始化相关 Utils
 */

class Utils private constructor() {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {

        private var context: Context? = null

        /**
         * 初始化工具类
         *
         * @param context 上下文
         */
        fun init(context: Context) {
            Utils.context = context.applicationContext
        }

        /**
         * 获取ApplicationContext
         *
         * @return ApplicationContext
         */
        fun getContext(): Context {
            if (context != null) {
                return context as Context
            }
            throw NullPointerException("u should init first")
        }

        /**
         * View获取Activity的工具
         *
         * @param view view
         * @return Activity
         */
        fun getActivity(view: View): Activity {
            var context = view.context

            while (context is ContextWrapper) {
                if (context is Activity) {
                    return context
                }
                context = context.baseContext
            }

            throw IllegalStateException("View $view is not attached to an Activity")
        }

        /**
         * 全局获取String的方法
         *
         * @param id 资源Id
         * @return String
         */
        fun getString(@StringRes id: Int): String {
            return context!!.resources.getString(id)
        }

        /**
         * 判断App是否是Debug版本
         *
         * @return `true`: 是<br></br>`false`: 否
         */
        val isAppDebug: Boolean
            get() {
                if (TextUtils.isEmpty(context!!.packageName.trim { it <= ' ' })) return false
                return try {
                    val pm = context!!.packageManager
                    val ai = pm.getApplicationInfo(context!!.packageName, 0)
                    ai != null && ai.flags and ApplicationInfo.FLAG_DEBUGGABLE != 0
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                    false
                }

            }

        fun <T> checkNotNull(obj: T?): T {
            if (obj == null) {
                throw NullPointerException()
            }
            return obj
        }
    }

}