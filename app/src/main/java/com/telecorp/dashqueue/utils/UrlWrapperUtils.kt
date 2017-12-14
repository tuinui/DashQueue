package com.telecorp.dashqueue.utils

import android.text.TextUtils

/**
 * Created by softbaked on 9/21/2016 AD.
 */

object UrlWrapperUtils {

    fun getAvailableUrl(url: String?): String? {
        return if (!TextUtils.isEmpty(url) && !url!!.startsWith("http")) {
            "http:" + url
        } else {
            url
        }
    }
}
