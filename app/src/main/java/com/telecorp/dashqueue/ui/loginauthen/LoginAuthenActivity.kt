package com.telecorp.dashqueue.ui.loginauthen

import activitystarter.Arg
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.marcinmoskala.activitystarter.argExtra
import com.rengwuxian.materialedittext.MaterialEditText
import com.telecorp.dashqueue.BuildConfig
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.api.TelecorpApiInterface
import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.api.model.LoginAuthenResponseModel
import com.telecorp.dashqueue.base.BaseActivity
import com.telecorp.dashqueue.custom.SafeSwitch
import com.telecorp.dashqueue.custom.imageloader.ImageLoaderView
import com.telecorp.dashqueue.di.Injectable
import com.telecorp.dashqueue.ui.loginauthen.contract.LoginAuthenContract
import com.telecorp.dashqueue.ui.loginauthen.contract.LoginAuthenPresenter
import com.telecorp.dashqueue.ui.queue.QueueActivityStarter
import com.telecorp.dashqueue.utils.extractString
import com.telecorp.dashqueue.utils.getDeviceImei
import com.telecorp.dashqueue.utils.getDeviceModelName
import com.telecorp.dashqueue.utils.pref.MyPreferencesHolder
import com.telecorp.dashqueue.utils.schedulers.BaseSchedulerProvider
import kotlinx.android.synthetic.main.activity_login_authen.*
import kotlinx.android.synthetic.main.view_main_appbar.*
import javax.inject.Inject

/**
 * Created by Saran on 30/10/2560.
 */
class LoginAuthenActivity : BaseActivity(), LoginAuthenContract.View, Injectable, View.OnClickListener {


    @get:Arg
    var mData: HospitalItem? by argExtra()

    @Inject
    lateinit var mApi: TelecorpApiInterface
    @Inject
    lateinit var mSchedulerProvider: BaseSchedulerProvider


    private val mImageLoaderView: ImageLoaderView by lazy { imageloaderview_login_authen_hospital_image }
    private val mEditTextPhoneNo: MaterialEditText by lazy { edittext_login_authen_phone_no }
    private val mEditTextQueue: MaterialEditText by lazy { edittext_login_authen_phone_queue }
    private val mSwitchRememberMe: SafeSwitch by lazy { switch_login_authen_remember_me }
    private val mButtonSubmit: Button by lazy { button_login_authen_submit }
    private val mToolbar: Toolbar by lazy { toolbar_main }
    private lateinit var mPresenter: LoginAuthenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_authen)
        mPresenter = LoginAuthenPresenter(mData, mApi, mSchedulerProvider)
        initView()
        initToolbar()
    }

    private fun initToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        mToolbar.setNavigationOnClickListener { onBackPressed() }

    }


    override fun showLoading(loading: Boolean) {
        if (loading) {
            showLoading()
        } else {
            dismissLoading()
        }
    }

    override fun bindData(data: HospitalItem?) {
        data?.apply {
            imageLogoPath?.let { mImageLoaderView.loadImage(it) }
            if (!TextUtils.isEmpty(MyPreferencesHolder.savedPhoneNumber)) {
                mEditTextPhoneNo.setText(MyPreferencesHolder.savedPhoneNumber)
            }
            mSwitchRememberMe.setSafeCheck(MyPreferencesHolder.rememberMe,SafeSwitch.IGNORE)
            mToolbar.title = title
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        mPresenter.unsubscribe()
    }

    override fun showErrorNetwork(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoadingDataFinish() {
        Toast.makeText(this, "Finish!!!", Toast.LENGTH_LONG).show()
    }

    override fun showQueueActivity(data: HospitalItem?, response: LoginAuthenResponseModel?) {
        QueueActivityStarter.start(this, data, response)
        ActivityCompat.finishAffinity(this)
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        if (BuildConfig.DEBUG) {
            mEditTextQueue.setText("A001")
            mEditTextPhoneNo.setText("08283762563")
        }
        mButtonSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.id?.let {
            if (it == mButtonSubmit.id) {
                onSubmitClick()
            }
        }
    }

    private fun onSubmitClick() {
        /*
        {
            "QueueNumber": "A001",
            "PhoneNumber": "08283762563",
            "DeviceName": "BOMZ16",
            "DeviceMacAddress": "ทดสอบ",
            "HospitalID": "5"
         }
         */
        val queueNumber = mEditTextQueue.extractString()
        val phoneNumber = mEditTextPhoneNo.extractString()
        val deviceName = getDeviceImei()
        val deviceMacAddress = getDeviceModelName()
//        val hospitalID = mData?.uid

        mPresenter.onSubmitClick(queueNumber, phoneNumber, deviceName, deviceMacAddress)
    }

}
