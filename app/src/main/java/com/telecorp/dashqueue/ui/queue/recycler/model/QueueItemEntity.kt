package com.telecorp.dashqueue.ui.queue.recycler.model

import android.support.annotation.IntDef

/**
 * Created by Saran on 12/11/2560.
 */

interface QueueItemEntity {

    @ViewType
    val itemViewType: Long


    companion object {
        @Retention(AnnotationRetention.SOURCE)
        @IntDef(HEADER, ITEM)
        annotation class ViewType

        const val HEADER = 1L
        const val ITEM = 2L
    }
}
