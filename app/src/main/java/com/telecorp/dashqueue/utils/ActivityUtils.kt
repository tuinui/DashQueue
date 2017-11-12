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
    var deviceId = ""
    try {
        val mTelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        deviceId = mTelephonyManager.deviceId ?: ""
    } catch (ignored: Exception) {

    }

    return deviceId
}

fun getDeviceModelName(): String {
    var deviceName = ""

    try {
        deviceName = (Build.MANUFACTURER
                + " " + Build.MODEL + " " + Build.VERSION.RELEASE
                + " " + Build.VERSION_CODES::class.java.fields[android.os.Build.VERSION.SDK_INT].name)
    } catch (ignored: Exception) {

    }
    return deviceName
}

fun Activity.copyTextToClipboard(label: String, text: String) {
    if (!isFinishing) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        clipboard.primaryClip = clip
    }
}
