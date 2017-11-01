package com.telecorp.dashqueue.ui.main.recycler

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.custom.GenericOnItemClickListener
import com.telecorp.dashqueue.utils.SafeCollectionUtils
import kotlinx.android.synthetic.main.view_hospital_item.view.*
import java.util.*

/**
 * Created by Saran on 13/10/2560.
 */

class HospitalRecyclerAdapter(val onItemClickListener: GenericOnItemClickListener<HospitalItem>?) : RecyclerView.Adapter<HospitalRecyclerAdapter.HospitalItemViewHolder>() {
    private val mItems = ArrayList<HospitalItem>()

    fun replaceDatas(newDatas: List<HospitalItem>) {
        val result = DiffUtil.calculateDiff(
                HospitalItemDiffCallback(mItems, newDatas), false)
        mItems.clear()
        mItems.addAll(newDatas)
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalItemViewHolder {
        return HospitalItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_hospital_item, parent, false))
    }

    override fun onBindViewHolder(holder: HospitalItemViewHolder, position: Int) {
        if (SafeCollectionUtils.isAvailableData(mItems, position)) {
            holder.bind(mItems[position])
        }
    }

    override fun getItemCount(): Int {
        return SafeCollectionUtils.getSize(mItems)
    }

    inner class HospitalItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        private val imgIcon = itemView.imageloaderview_hospital_item_image
        private val tvTitle = itemView.textview_hospital_item_title


        fun bind(data: HospitalItem?) {
            if (null != data) {
                imgIcon.loadImage(data.imageLogoPath)
                tvTitle.text = data.title
                itemView.setOnClickListener(this)
            }
        }

        override fun onClick(v: View?) {
            v?.id?.let {
                if (it == itemView.id && null != onItemClickListener && SafeCollectionUtils.isAvailableData(mItems, adapterPosition)) {
                    onItemClickListener.onItemClick(v.context, mItems[adapterPosition])
                }
            }
        }
    }
}
