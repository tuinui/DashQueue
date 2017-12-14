package com.telecorp.dashqueue.ui.waitingqueue.contract

import com.telecorp.dashqueue.base.BaseView
import com.telecorp.dashqueue.ui.queue.recycler.model.QueueItemEntity

/**
 * Created by Saran on 12/11/2560.
 */

class WatingQueueActivityContract {

    interface View : BaseView {
        fun showLoading(loading: Boolean)
        fun bindData(items: List<QueueItemEntity>)
        fun showErrorNetwork(error: String)
        fun showLoadingDataFinish()
    }


}


