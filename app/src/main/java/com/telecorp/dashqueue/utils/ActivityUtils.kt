package com.telecorp.dashqueue.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager


@SuppressLint("MissingPermission", "HardwareIds")
        /**
         * Created by Saran on 1/11/2560.
         */

fun Activity.getDeviceImei(): String {
    val mTelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return mTelephonyManager.deviceId ?: ""
}

fun getDeviceModelName(): String {
    return (Build.MANUFACTURER
            + " " + Build.MODEL + " " + Build.VERSION.RELEASE
            + " " + Build.VERSION_CODES::class.java.fields[android.os.Build.VERSION.SDK_INT].name)
}

fun Activity.copyTextToClipboard(label: String, text: String) {
    if (!isFinishing) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.primaryClip = clip
    }
}
