package com.telecorp.dashqueue.ui.main

import activitystarter.Arg
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import butterknife.ButterKnife
import com.google.firebase.iid.FirebaseInstanceId
import com.marcinmoskala.activitystarter.argExtra
import com.telecorp.dashqueue.BuildConfig
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.base.BaseActivity
import com.telecorp.dashqueue.custom.GenericFragmentPagerAdapter
import com.telecorp.dashqueue.di.Injectable
import com.telecorp.dashqueue.ui.main.hospitallist.HospitalListFragment
import com.telecorp.dashqueue.ui.queue.QueueActivityStarter
import com.telecorp.dashqueue.utils.copyTextToClipboard
import com.telecorp.dashqueue.utils.pref.MyPreferencesHolder
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_main_appbar.*
import javax.inject.Inject

class MainActivity : BaseActivity(), Injectable, HasSupportFragmentInjector {

    private val mToolbar: Toolbar by lazy {
        toolbar_main
    }
    private val mTextViewFcmToken: TextView by lazy {
        textview_main_fcm_token
    }
    @Inject
    lateinit var mDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @get:Arg(optional = true)
    var mFromNotification: Boolean? by argExtra()
    val mViewPager: ViewPager by lazy { viewpager_main }
    private val mContentFragments = ArrayList<Fragment>()
    private val mHospitalListFragment = HospitalListFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        if (checkIfAlreadyLogin()) {
            return
        }
        if (savedInstanceState == null) {
            initContentFragments()
        }

        initToolbar()
        initView()
        initViewPager()
    }

    private fun checkIfAlreadyLogin(): Boolean {

        MyPreferencesHolder.appTokenModel?.let {
            QueueActivityStarter.start(this, it.hospitalItem, mFromNotification)
            ActivityCompat.finishAffinity(this)
            return true
        }
        return false
    }

    private fun initContentFragments() {
        mContentFragments.add(mHospitalListFragment)
//        mContentFragments.add(mYourProfileFragment)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mDispatchingAndroidInjector
    }

    private fun initViewPager() {
        mViewPager.adapter = GenericFragmentPagerAdapter(supportFragmentManager, mContentFragments)
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
//        mSegmentedGroup.setOnCheckedChangeListener(mToggleListener)
        if (BuildConfig.DEBUG) {
            mTextViewFcmToken.text = "Firebase token is " + FirebaseInstanceId.getInstance().token
            mTextViewFcmToken.setOnClickListener { FirebaseInstanceId.getInstance().token?.let { it1 -> copyTextToClipboard("firebasetoken", it1) } }
            mTextViewFcmToken.visibility = View.VISIBLE
        } else {
            mTextViewFcmToken.visibility = View.GONE
        }

    }

    private fun initToolbar() {
        mToolbar.setTitle(R.string.app_name_dashqueue)
    }

}
