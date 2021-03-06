package io.treehouses.remote.utils

import android.os.Message
import android.util.Log
import io.treehouses.remote.BuildConfig

object LogUtils {
    fun mOffline() {
        if (BuildConfig.DEBUG) Log.e("STATUS", "OFFLINE")
    }

    fun mIdle() {
        if (BuildConfig.DEBUG) Log.e("STATUS", "IDLE")
    }

    fun mConnect() {
        if (BuildConfig.DEBUG) Log.e("STATUS", "CONNECTED")
    }

    fun log(message: String?) {
        if (BuildConfig.DEBUG) {
            Log.d("TREEHOUSES ", message!!)
        }
    }

    fun writeMsg(msg: Message) {
        val write_msg = String((msg.obj as ByteArray))
        Log.d("WRITE", write_msg)
    }
}

fun Any.logD(msg: String) {
    logD(this.javaClass.simpleName, msg)
}

fun Any.logE(msg: String, e: Throwable? = null) {
    logE(this.javaClass.simpleName, msg, e)
}


private fun logD(tag: String, msg: String) {
    if (BuildConfig.DEBUG) Log.d(tag, msg)
}

private fun logE(tag: String, msg: String, e: Throwable? = null) {
    if (BuildConfig.DEBUG) Log.e(tag, msg)
}