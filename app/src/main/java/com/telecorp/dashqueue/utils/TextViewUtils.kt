package com.telecorp.dashqueue.utils

import android.text.TextUtils
import android.widget.TextView

/**
 * Created by Saran on 1/11/2560.
 */
fun TextView.extractString(): String {
    return if (!TextUtils.isEmpty(text)) {
        text.toString()
    } else {
        ""
    }
}



