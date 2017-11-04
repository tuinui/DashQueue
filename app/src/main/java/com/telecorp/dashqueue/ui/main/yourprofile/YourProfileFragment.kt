package com.telecorp.dashqueue.ui.main.yourprofile

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.telecorp.dashqueue.base.BaseFragment


/**
 * Created by Saran on 4/11/2560.
 */

class YourProfileFragment : BaseFragment() {
    private var mWebView: WebView? = null
    private var mIsWebViewAvailable: Boolean = false

    companion object {
        const val KEY_YOUR_PROFILE_URL = "KEY_YOUR_PROFILE_URL"

        fun newInstance(url: String): Fragment {
            val args = Bundle()
            args.putString(KEY_YOUR_PROFILE_URL, url)
            val fragment = YourProfileFragment()
            fragment.arguments = args
            return fragment;
        }
    }


    private var mUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mUrl = it.getString(KEY_YOUR_PROFILE_URL, "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mWebView?.destroy()

        mWebView = WebView(context)
        mIsWebViewAvailable = true
        return mWebView
    }

    /**
     * Called when the fragment is visible to the user and actively running. Resumes the WebView.
     */
    override fun onPause() {
        super.onPause()
        mWebView?.onPause()
    }

    /**
     * Called when the fragment is no longer resumed. Pauses the WebView.
     */
    override fun onResume() {
        mWebView?.onResume()
        super.onResume()
    }

    /**
     * Called when the WebView has been detached from the fragment.
     * The WebView is no longer available after this time.
     */
    override fun onDestroyView() {
        mIsWebViewAvailable = false
        super.onDestroyView()
    }

    /**
     * Called when the fragment is no longer in use. Destroys the internal state of the WebView.
     */
    override fun onDestroy() {
        if (mWebView != null) {
            mWebView?.destroy()
            mWebView = null
        }
        super.onDestroy()
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mWebView?.webViewClient = WebViewClient() // forces it to open in app
        mWebView?.loadUrl(mUrl)
        val settings = mWebView?.settings
        settings?.javaScriptEnabled = true
    }

}
