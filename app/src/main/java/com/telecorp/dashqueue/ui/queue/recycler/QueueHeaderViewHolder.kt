package com.telecorp.dashqueue.ui.queue.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.api.model.WaitingQueue
import com.telecorp.dashqueue.utils.DateParseUtils
import kotlinx.android.synthetic.main.view_queue_detail.view.*
import java.util.*

/**
 * Created by Saran on 12/11/2560.
 */

class QueueHeaderViewHolder(v: View, val mListener: QueueDetailClickListener) : RecyclerView.ViewHolder(v), View.OnClickListener {

    private val imgCover = v.imageloaderview_queue_detail_hospital
    private val tvDepartment = v.textview_queue_detail_department
    private val tvDoctorName = v.textview_queue_detail_doctor_name
    private val tvWaitingCount = v.textview_queue_detail_wating
    private val tvQueueTitle = v.textview_queue_detail_queue_title
    private val tvQueueNumber = v.textview_queue_detail_queue_number
    private val tvLastUpdated = v.textview_queue_detail_last_updated_at
    private val btnQueueList = v.button_queue_detail_queue_list
    private val btnProfile = v.button_queue_detail_profile
    private val LLast_updated_: String  by lazy {
        itemView.context.getString(R.string.Last_updated_)
    }
    private val LWaiting_count: String by lazy {
        itemView.context.getString(R.string.Waiting_count)
    }

    init {
        btnQueueList.setOnClickListener(this)
        btnProfile.setOnClickListener(this)
    }

    fun bindWaitingQueue(data: WaitingQueue?) {
        data?.apply {
            tvLastUpdated.text = String.format(Locale.getDefault(), LLast_updated_, DateParseUtils.relativeParseDateTime(itemView.context, cWhen))
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

    override fun onClick(v: View?) {
        v?.id?.let {
            when (it) {
                btnQueueList.id -> mListener.onQueueListClick()
                btnProfile.id -> mListener.onProfileClick()
            }
        }
    }

}
