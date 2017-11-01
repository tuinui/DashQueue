package com.telecorp.dashqueue.custom

import android.content.Context

/**
 * Created by SoftBaked on 5/8/2017 AD.
 */

interface GenericOnItemClickListener<T> {
    fun onItemClick(context: Context, data: T)
}
