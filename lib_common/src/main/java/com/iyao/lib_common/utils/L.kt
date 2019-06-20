package com.iyao.lib_common.utils

import android.util.Log
import com.iyao.lib_common.BuildConfig

/**
 * @author : GuZhC
 * @date : 2019/6/20 14:07
 * @description : L logUtils
 */
object L {
    private const val TAG = "GuZhC"
    private val sDebug = BuildConfig.DEBUG

    fun d(msg: String) {
        d(TAG, msg)
    }

    fun d(tag: String, msg: String) {
        if (!sDebug) return
        Log.d(tag, msg)
    }

    fun e(msg: String) {
        e(TAG, msg)
    }

    fun e(tag: String, msg: String) {
        if (!sDebug) return
        Log.e(tag, msg)
    }

}