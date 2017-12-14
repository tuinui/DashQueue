package com.telecorp.dashqueue.ui.queue.recycler

import android.support.v4.widget.Space
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.ui.queue.recycler.model.QueueDetailItemEntity
import com.telecorp.dashqueue.ui.queue.recycler.model.QueueItemEntity
import com.telecorp.dashqueue.ui.queue.recycler.model.WaitingQueueItemEntity
import com.telecorp.dashqueue.utils.SafeCollectionUtils

/**
 * Created by Saran on 12/11/2560.
 */

class QueueRecyclerAdapter(val mListener: QueueDetailClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mItems = ArrayList<QueueItemEntity>()

    fun replaceData(datas: List<QueueItemEntity>) {
        mItems.clear()
        notifyDataSetChanged()
        mItems.addAll(datas)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return when (viewType) {
            QueueItemEntity.HEADER.toInt() -> QueueHeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_queue_detail, parent, false),mListener)
            QueueItemEntity.ITEM.toInt() -> QueueItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemview_queue_item, parent, false))
            else -> object : RecyclerView.ViewHolder(Space(parent.context)) {}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (SafeCollectionUtils.isAvailableData(mItems, position)) {
            val viewType = getItemViewType(position)
            val data = mItems[position]
            when (viewType) {
                QueueItemEntity.HEADER.toInt() -> bindHeader((holder as QueueHeaderViewHolder), data as QueueDetailItemEntity)
                QueueItemEntity.ITEM.toInt() -> bindItem(holder as QueueItemViewHolder, data as WaitingQueueItemEntity)
            }
        }
    }

    private fun bindHeader(holder: QueueHeaderViewHolder, data: QueueDetailItemEntity?) {
        data?.let {
            holder.bindHospitalItem(it.hospitalData)
            holder.bindWaitingQueue(it.watingQueue)
        }
    }

    private fun bindItem(holder: QueueItemViewHolder, data: WaitingQueueItemEntity?) {
        data?.let {
            holder.bindData(data.waiting)
        }
    }

    override fun getItemViewType(position: Int): Int {
        mItems.elementAtOrNull(position)?.apply {
            return itemViewType.toInt()
        }
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return SafeCollectionUtils.getSize(mItems)
    }
}
