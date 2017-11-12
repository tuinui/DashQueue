package com.telecorp.dashqueue.ui.queue.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.api.model.WaitingQueue
import kotlinx.android.synthetic.main.itemview_queue_header.view.*
import java.util.*

/**
 * Created by Saran on 12/11/2560.
 */

class QueueHeaderViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val imgCover = v.imageloaderview_queue_header_hospital
    private val tvDepartment = v.textview_queue_header_department
    private val tvDoctorName = v.textview_queue_header_doctor_name
    private val tvWaitingCount = v.textview_queue_header_wating
    private val tvQueueTitle = v.textview_queue_header_queue_title
    private val tvQueueNumber = v.textview_queue_header_queue_number
    private val tvLastUpdated = v.textview_queue_header_last_updated_at
    private val LLast_updated_: String  by lazy {
        itemView.context.getString(R.string.Last_updated_)
    }
    private val LWaiting_count: String by lazy {
        itemView.context.getString(R.string.Waiting_count)
    }

    fun bindWaitingQueue(data: WaitingQueue?) {
        data?.apply {
            tvLastUpdated.text = String.format(Locale.getDefault(), LLast_updated_, cWhen)
            tvDoctorName.text = doctorName
            tvQueueNumber.text = queueNo
            tvDepartment.text = locationQueueName
            tvWaitingCount.text = String.format(Locale.getDefault(), LWaiting_count, waitingNumber)
        }
    }

    fun bindHospitalItem(data: HospitalItem?) {
        data?.apply {
            imgCover.loadImage(data.imageLogoPath, R.drawable.ic_broken_image_gray_24dp, false)
        }
    }
}
