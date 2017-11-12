package com.telecorp.dashqueue.ui.queue.contract

import com.telecorp.dashqueue.base.BasePresenter
import com.telecorp.dashqueue.base.BaseView
import com.telecorp.dashqueue.ui.queue.recycler.model.QueueItemEntity

/**
 * Created by Saran on 12/11/2560.
 */

class QueueActivityContract {

    interface View : BaseView {
        fun showLoading(loading: Boolean)

        fun bindData(items: ArrayList<QueueItemEntity>)

        fun showErrorNetwork(error: String)

        fun showLoadingDataFinish()
    }


    interface Presenter : BasePresenter<View> {
        fun refreshData(deviceName: String, deviceMacAddress: String)
    }
}


