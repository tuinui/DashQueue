package com.telecorp.dashqueue.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.AppCompatRadioButton
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import butterknife.ButterKnife
import com.google.firebase.iid.FirebaseInstanceId
import com.telecorp.dashqueue.BuildConfig
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.base.BaseActivity
import com.telecorp.dashqueue.custom.GenericFragmentPagerAdapter
import com.telecorp.dashqueue.di.Injectable
import com.telecorp.dashqueue.ui.main.hospitallist.HospitalListFragment
import com.telecorp.dashqueue.ui.main.yourprofile.YourProfileFragment
import com.telecorp.dashqueue.utils.copyTextToClipboard
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import info.hoang8f.android.segmented.SegmentedGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_main_appbar.*
import javax.inject.Inject

class MainActivity : BaseActivity(), Injectable, HasSupportFragmentInjector {

    private val mToolbar: Toolbar by lazy {
        toolbar_main
    }
    private val mSegmentedGroup: SegmentedGroup by lazy {
        segmentedgroup_main
    }
    private val mRadioHospital: AppCompatRadioButton by lazy {
        radiobutton_main_hospital
    }
    private val mRadioYourProfile: AppCompatRadioButton by lazy {
        radiobutton_main_yourprofile
    }
    private val mTextViewFcmToken: TextView by lazy {
        textview_main_fcm_token
    }
    @Inject
    lateinit var mDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    val mViewPager: ViewPager by lazy { viewpager_main }
    private val mContentFragments = ArrayList<Fragment>()
    private val mHospitalListFragment = HospitalListFragment.newInstance()
    private val mYourProfileFragment = YourProfileFragment.newInstance("https://marketdata.set.or.th/mkt/stockquotation.do?symbol=D")

    private val mToggleListener = RadioGroup.OnCheckedChangeListener { group, checkedId ->
        if (checkedId == mRadioHospital.id) {
            mViewPager.setCurrentItem(0, true)
        } else if (checkedId == mRadioYourProfile.id) {
            mViewPager.setCurrentItem(1, true)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        if (savedInstanceState == null) {
            initContentFragments()
        }

        initToolbar()
        initView()
        initViewPager()
    }

    private fun initContentFragments() {
        mContentFragments.add(mHospitalListFragment)
        mContentFragments.add(mYourProfileFragment)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mDispatchingAndroidInjector
    }

    private fun initViewPager() {
        mViewPager.adapter = GenericFragmentPagerAdapter(supportFragmentManager, mContentFragments)
    }

    private fun initView() {
        mSegmentedGroup.setOnCheckedChangeListener(mToggleListener)
        if (BuildConfig.DEBUG) {
            mTextViewFcmToken.text = "Firebase tokoen is " + FirebaseInstanceId.getInstance().token
            mTextViewFcmToken.setOnClickListener { FirebaseInstanceId.getInstance().token?.let { it1 -> copyTextToClipboard("firebasetoken", it1) } }
            mTextViewFcmToken.visibility = View.GONE
        } else {
            mTextViewFcmToken.visibility = View.GONE
        }

    }

    private fun initToolbar() {
        mToolbar.setTitle(R.string.app_name_dashqueue)
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        Log.i("MainActivity", "Firebase token : " + FirebaseInstanceId.getInstance().token)
    }

}
