package com.telecorp.dashqueue.ui.queue.contract

import com.google.firebase.iid.FirebaseInstanceId
import com.telecorp.dashqueue.api.TelecorpApiInterface
import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.api.model.LoginAuthenRequestModel
import com.telecorp.dashqueue.api.model.LoginAuthenResponseModel
import com.telecorp.dashqueue.api.model.TokenRequestModel
import com.telecorp.dashqueue.ui.queue.recycler.model.QueueDetailItemEntity
import com.telecorp.dashqueue.ui.queue.recycler.model.QueueItemEntity
import com.telecorp.dashqueue.ui.queue.recycler.model.WaitingQueueItemEntity
import com.telecorp.dashqueue.utils.pref.AppTokenModel
import com.telecorp.dashqueue.utils.pref.MyPreferencesHolder
import com.telecorp.dashqueue.utils.schedulers.BaseSchedulerProvider

/**
 * Created by Saran on 12/11/2560.
 */

class QueueActivityPresenter(val mHospitalData: HospitalItem?, var mLoginAuthenData: LoginAuthenResponseModel?, val mApi: TelecorpApiInterface, val mSchedulerProvider: BaseSchedulerProvider) : QueueActivityContract.Presenter {



    private var mView: QueueActivityContract.View? = null
    private val mDatas = ArrayList<QueueItemEntity>()
    private var mCurrentDeviceName: String? = ""
    private var mCurrentDeviceMacAddress: String? = ""


    override fun subscribe(view: QueueActivityContract.View) {
        mView = view
        if (mDatas.isEmpty()) {
            mDatas.clear()
            mDatas.addAll(parseToQueueEntity(mHospitalData, mLoginAuthenData))
        }
        if (mDatas.isNotEmpty() && mDatas.size == 1) {
            refreshData(mCurrentDeviceName.toString(), mCurrentDeviceMacAddress.toString())
        }
        mView?.bindData(mDatas)
    }

    override fun unsubscribe() {
        mView = null
    }

    override fun requestLogout() {
        mView?.showLoading(true)
        MyPreferencesHolder.appTokenModel?.apply {
            val token = FirebaseInstanceId.getInstance().token
            if (token?.isNotEmpty() == true) {
                mApi.postLogoutAuthen(TokenRequestModel(queueNumber, phoneNumber, token, hospitalItem?.uid)).subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .subscribe({ response ->
                            mView?.showLoading(false)
                            if (response.isSuccess == true) {
                                mView?.openHospitalListActivity()
                            }

                        }, { throwable ->
                            if (null != mView) {
                                mView?.showLoading(false)
                                mView?.showErrorNetwork(throwable.cause.toString())
                            }
                        })
            }

        }
    }

    override fun onQueueListClick() {
        mView?.showQueueListActivity(mDatas)
    }

    override fun onProfileClick() {
        mView?.showProfileActivity()
    }
    override fun refreshData(deviceName: String, deviceMacAddress: String) {
        mView?.showLoading(true)
        mCurrentDeviceName = deviceName
        mCurrentDeviceMacAddress = deviceMacAddress
        MyPreferencesHolder.appTokenModel?.apply {
            mApi.postLoginAuthen(LoginAuthenRequestModel(queueNumber, phoneNumber, deviceName, deviceMacAddress, mHospitalData?.uid)).subscribeOn(mSchedulerProvider.io())
                    .observeOn(mSchedulerProvider.ui())
                    .map { response ->
                        mLoginAuthenData = response
                        return@map parseToQueueEntity(mHospitalData, mLoginAuthenData)
                    }
                    .subscribe({ datas ->
                        mDatas.clear()
                        mDatas.addAll(datas)
                        mView?.showLoading(false)
                        mView?.bindData(mDatas)

                    }, { throwable ->
                        if (null != mView) {
                            mView?.showLoading(false)
                            mView?.showErrorNetwork(throwable.cause.toString())
                        }
                    })

            if (null != mView) {
                MyPreferencesHolder.appTokenModel = AppTokenModel(queueNumber, phoneNumber, hospitalItem)
                sendTokenToService()
            }

        }
    }


    private fun parseToQueueEntity(hospitalData: HospitalItem?, loginData: LoginAuthenResponseModel?): List<QueueItemEntity> {
        val datas = ArrayList<QueueItemEntity>()
        datas.add(QueueDetailItemEntity(hospitalData, loginData?.waitingQueue))
        loginData?.waitingList?.forEach { data -> datas.add(WaitingQueueItemEntity(data)) }
        return datas
    }


    private fun sendTokenToService() {
        if (null != FirebaseInstanceId.getInstance()?.id && null != MyPreferencesHolder.appTokenModel) {
            MyPreferencesHolder.appTokenModel?.apply {
                mApi.postRegisterToken(TokenRequestModel(queueNumber, phoneNumber, FirebaseInstanceId.getInstance().id, hospitalItem?.uid)).subscribeOn(mSchedulerProvider.io())
                        .observeOn(mSchedulerProvider.ui())
                        .subscribe({
                            if (null != mView) {
                                mView?.showLoading(false)
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

    }


}
