package com.telecorp.dashqueue.ui.queue.recycler

import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.api.model.Waiting
import kotlinx.android.synthetic.main.itemview_queue_item.view.*

/**
 * Created by Saran on 12/11/2560.
 */

class QueueItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    private val tvStatus: TextView by lazy {
        v.textview_queue_item_status
    }
    private val tvNumber: TextView by lazy {
        v.textview_queue_item_number
    }
    private val cardViewRoot: CardView by lazy {
        v.cardview_queue_item
    }
    private val tvQueueNumber: TextView by lazy {
        v.textview_queue_item_queue_number
    }
    private val colorBlue: Int by lazy {
        ContextCompat.getColor(v.context, R.color.blue_queue)
    }
    private val colorOrange: Int by lazy {
        ContextCompat.getColor(v.context, R.color.orange_queue)
    }


    fun bindData(data: Waiting?) {
        data?.apply {

            val isMine = currentQueue?.equals("Y", true)
            if (isMine == true) {
                cardViewRoot.setBackgroundColor(colorOrange)
            } else {
                cardViewRoot.setBackgroundColor(colorBlue)
            }
            tvStatus.text = data.processName
            tvQueueNumber.text = data.queueNumber
            tvNumber.text = data.number.toString()
        }
    }


}
