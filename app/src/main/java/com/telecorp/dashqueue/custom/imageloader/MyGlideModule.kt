package com.telecorp.dashqueue.custom.imageloader

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.telecorp.dashqueue.R

/**
 * Created by Saran on 29/10/2560.
 */

@GlideModule
class MyGlideModule : AppGlideModule() {


    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        super.applyOptions(context, builder)
        builder?.setDefaultRequestOptions(RequestOptions().error(R.drawable.ic_broken_image_gray_24dp))
    }
}
