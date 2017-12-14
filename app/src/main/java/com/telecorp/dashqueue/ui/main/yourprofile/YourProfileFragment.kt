package com.telecorp.dashqueue.ui.main.yourprofile

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.api.TelecorpApiInterface
import com.telecorp.dashqueue.api.model.ProfileRequestModel
import com.telecorp.dashqueue.base.BaseFragment
import com.telecorp.dashqueue.di.Injectable
import com.telecorp.dashqueue.utils.UrlWrapperUtils
import com.telecorp.dashqueue.utils.pref.MyPreferencesHolder
import com.telecorp.dashqueue.utils.schedulers.BaseSchedulerProvider
import kotlinx.android.synthetic.main.fragment_your_profile.*
import javax.inject.Inject


/**
 * Created by Saran on 4/11/2560.
 */

class YourProfileFragment : BaseFragment(), Injectable {

    private val mSwipeRefreshLayout: SwipeRefreshLayout by lazy {
        swiperefreshlayout_your_profile
    }
    private var mWebView: WebView? = null
    private var mIsWebViewAvailable: Boolean = false
    @Inject
    lateinit var mApi: TelecorpApiInterface
    @Inject
    lateinit var mSchedulerProvider: BaseSchedulerProvider

    companion object {
        fun newInstance(): YourProfileFragment {
            return YourProfileFragment()
        }
    }


    private var mUrl: String? = null


    private fun refreshData() {
        mSwipeRefreshLayout.isRefreshing = true
        var queueNumber: String? = null
        var phoneNumber: String? = null
        var hospitalId: Long? = null
        MyPreferencesHolder.appTokenModel?.let {
            queueNumber = it.queueNumber
            phoneNumber = it.phoneNumber
            hospitalId = it.hospitalItem?.uid

        }

        mApi.postLandingPage(ProfileRequestModel(queueNumber, phoneNumber, hospitalId))
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe({ data ->
                    mSwipeRefreshLayout.isRefreshing = false
                    mUrl = UrlWrapperUtils.getAvailableUrl(data.pageUrl)
                    mWebView?.loadUrl(mUrl)
                }, { throwable ->
                    mSwipeRefreshLayout.isRefreshing = false
                    Toast.makeText(context, throwable.cause.toString(), Toast.LENGTH_LONG).show()
                })
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_your_profile, container, false)
        mWebView?.destroy()

        mWebView = v?.findViewById(R.id.webview_your_profile)
        mIsWebViewAvailable = true
        return v
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

    fun onConsumeBackPressed(): Boolean {
        return if (mWebView?.canGoBack() == true) {
            mWebView?.goBack()
            true
        } else {
            false
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
        initView()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {

        val settings = mWebView?.settings
        settings?.javaScriptEnabled = true
        settings?.useWideViewPort = true
        settings?.loadWithOverviewMode = true

        mWebView?.webViewClient = object : WebViewClient() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                mSwipeRefreshLayout.isRefreshing = true
                view?.loadUrl(request?.url?.toString())
                return true
            }

            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                mSwipeRefreshLayout.isRefreshing = true
                view?.loadUrl(url)

                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                mSwipeRefreshLayout.isRefreshing = false
            }
        } // forces it to open in app
        mWebView?.webChromeClient = WebChromeClient()

        refreshData()

    }

    private fun initView() {
        mSwipeRefreshLayout.setOnRefreshListener { refreshData() }
    }

}
