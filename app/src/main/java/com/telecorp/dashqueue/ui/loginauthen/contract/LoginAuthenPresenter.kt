package com.telecorp.dashqueue.ui.loginauthen.contract

import com.telecorp.dashqueue.api.TelecorpApiInterface
import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.api.model.LoginAuthenRequestModel
import com.telecorp.dashqueue.api.model.LoginAuthenResponseModel
import com.telecorp.dashqueue.utils.pref.MyPreferencesHolder
import com.telecorp.dashqueue.utils.schedulers.BaseSchedulerProvider

/**
 * Created by Saran on 14/10/2560.
 */

class LoginAuthenPresenter(private val mData: HospitalItem?, private val mApi: TelecorpApiInterface, private val mSchedulerProvider: BaseSchedulerProvider) : LoginAuthenContract.Presenter {

    private var mView: LoginAuthenContract.View? = null
    private var mAuthenResponse: LoginAuthenResponseModel? = null


    override fun subscribe(view: LoginAuthenContract.View) {
        mView = view
        mView?.bindData(mData)
    }

    override fun unsubscribe() {
        mView = null
    }

    override fun onRememberMeCheckChange(checked: Boolean) {
        MyPreferencesHolder.rememberMe = checked

    }

    override fun onSubmitClick(queueNumber: String, phoneNumber: String, deviceName: String, deviceMacAddress: String, hospitalId: Long?) {
        if (null != mView) {
            mView?.showLoading(true)
        }
        if(MyPreferencesHolder.rememberMe){
            MyPreferencesHolder.savedPhoneNumber = phoneNumber
        }else{
            MyPreferencesHolder.savedPhoneNumber = ""
        }

        mApi.postLoginAuthen(LoginAuthenRequestModel(queueNumber, phoneNumber, deviceName, deviceMacAddress, hospitalId))
                .subscribeOn(mSchedulerProvider.io())
                .observeOn(mSchedulerProvider.ui())
                .subscribe({ loginAuthenResponseModel ->
                    if (null != mView) {
                        mView?.showLoading(false)
                        mAuthenResponse = loginAuthenResponseModel
                    }
                }, { throwable ->
                    if (null != mView) {
                        mView?.showLoading(false)
                        mView?.showErrorNetwork(throwable.cause.toString())
                    }
                }) {
                    if (null != mView) {
                        mView?.showLoadingDataFinish()
                    }
                }
    }
}
