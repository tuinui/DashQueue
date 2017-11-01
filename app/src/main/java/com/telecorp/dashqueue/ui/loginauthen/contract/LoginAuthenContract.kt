package com.telecorp.dashqueue.ui.loginauthen.contract

import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.base.BasePresenter
import com.telecorp.dashqueue.base.BaseView

/**
 * Created by Saran on 14/10/2560.
 */

class LoginAuthenContract {

    interface View : BaseView {
        fun showLoading(loading: Boolean)
        fun bindData(data: HospitalItem?)
        fun showErrorNetwork(error: String)
        fun showLoadingDataFinish()
    }


    interface Presenter : BasePresenter<View> {
        /*
                val queueNumber = mEditTextQueue.extractString()
        val phoneNumber = mEditTextPhoneNo.extractString()
        val deviceName = getDeviceImei()
        val deviceMacAddress = getDeviceModelName()
        val hospitalID = mData?.uid

         */
        fun onSubmitClick(queueNumber: String, phoneNumber: String, deviceName: String, deviceMacAddress: String, hospitalId: Long?)

        fun onRememberMeCheckChange(checked: Boolean)
    }
}
