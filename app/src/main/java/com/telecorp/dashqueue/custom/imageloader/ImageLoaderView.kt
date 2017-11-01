package com.telecorp.dashqueue.custom.imageloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.custom.RatioImageView
import kotlinx.android.synthetic.main.customview_image_loader.view.*

/**
 * Created by Saran on 29/10/2560.
 */
class ImageLoaderView : FrameLayout {


    private val mImageView: RatioImageView by lazy {
        imageview_image_loader
    }

    private val mProgressBar: ProgressBar by lazy {
        progressbar_image_loader
    }


    constructor(context: Context) : super(context) {
        init(context, null, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr, 0)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }

    fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        inflate(context, R.layout.customview_image_loader, this)
        val array = context.obtainStyledAttributes(attrs, R.styleable.Ratio, defStyleAttr, defStyleRes)
        try {
            val widthRatio = array.getInteger(R.styleable.Ratio_ratioWidth, -1).toFloat()
            val heightRatio = array.getInteger(R.styleable.Ratio_ratioHeight, -1).toFloat()
            mImageView.setRatioHeight(heightRatio)
            mImageView.setRatioWidth(widthRatio)
        } finally {
            array.recycle()
        }
        invalidate()
    }

    fun loadImage(url: String?, @DrawableRes drawableRes: Int, crop: Boolean? = null) {
        loadImage(url, ContextCompat.getDrawable(context, drawableRes), crop)
    }

    fun loadImage(url: UrlWithRetry, @DrawableRes drawableRes: Int, crop: Boolean? = null) {
        loadImage(url, ContextCompat.getDrawable(context, drawableRes), crop)
    }

    @JvmOverloads
    fun loadImage(url: String?, drawable: Drawable? = null, crop: Boolean? = null) {
        loadImage(object : UrlWithRetry {
            override val url: String?
                get() = url
            override val retryUrl: String?
                get() = null

        }, drawable, crop)
    }

    @JvmOverloads
    fun loadImage(url: UrlWithRetry, drawable: Drawable? = null, crop: Boolean? = null) {
        //        showProgress(true);
        //we are using glide as an example here
        showProgress(true)

        val request = GlideApp.with(this).load(url.url?.trim()).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                return if (TextUtils.isEmpty(url.retryUrl)) {
                    showProgress(false)
                    false
                } else {
                    url.retryUrl?.let { loadImage(it.trim()) }
                    true
                }

            }


            override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                showProgress(false)
                return false
            }
        })



        drawable?.let {
            request.apply(RequestOptions.placeholderOf(it))
        }

        crop?.let {
            if (it) {
                request.apply(RequestOptions.centerCropTransform())
            } else {
                request.apply(RequestOptions.fitCenterTransform())
            }
        }


        request.transition(DrawableTransitionOptions.withCrossFade())
        request.into(mImageView)
    }


    fun showProgress(loading: Boolean) {
        if (loading) {
            mImageView.visibility = View.INVISIBLE
            mProgressBar.visibility = View.VISIBLE
        } else {
            mProgressBar.visibility = View.GONE
            mImageView.visibility = View.VISIBLE
        }
    }

    fun clearGlide() {
        Glide.with(this).clear(mImageView)
    }

    fun setImageResource(@DrawableRes drawableRes: Int) {
        mImageView.setImageResource(drawableRes)
    }

    fun getImageView(): ImageView {
        return mImageView
    }


}
