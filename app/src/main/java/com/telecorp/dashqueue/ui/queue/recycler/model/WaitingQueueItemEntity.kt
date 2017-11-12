package com.telecorp.dashqueue.ui.queue.recycler.model

import com.telecorp.dashqueue.api.model.Waiting

/**
 * Created by Saran on 12/11/2560.
 */

class WaitingQueueItemEntity( val waiting: Waiting?) : QueueItemEntity {

    override val itemViewType: Long
        get() = QueueItemEntity.ITEM
}
