package com.telecorp.dashqueue.ui.queue.recycler.model

import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.api.model.WaitingQueue

/**
 * Created by Saran on 12/11/2560.
 */

class HeaderQueueItemEntity (val hospitalData: HospitalItem?, val watingQueue: WaitingQueue?) : QueueItemEntity {


    override val itemViewType: Long
        get() = QueueItemEntity.HEADER
}
