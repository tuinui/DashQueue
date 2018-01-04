package com.telecorp.dashqueue.ui.main.hospitallist.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.page365.store365.generic.searchable.SearchableRecyclerAdapter
import com.telecorp.dashqueue.R
import com.telecorp.dashqueue.api.model.HospitalItem
import com.telecorp.dashqueue.custom.GenericOnItemClickListener
import com.telecorp.dashqueue.utils.SafeCollectionUtils
import kotlinx.android.synthetic.main.view_hospital_item.view.*

/**
 * Created by Saran on 13/10/2560.
 */

class HospitalRecyclerAdapter(val onItemClickListener: GenericOnItemClickListener<HospitalItem>?) : SearchableRecyclerAdapter<HospitalRecyclerAdapter.HospitalItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalItemViewHolder {
        return HospitalItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_hospital_item, parent, false))
    }

    override fun onBindViewHolder(holder: HospitalItemViewHolder, position: Int) {
        if (SafeCollectionUtils.isAvailableData(mFilteredDatas, position)) {
            holder.bind(mFilteredDatas[position] as HospitalItem)
        }
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
                if (it == itemView.id && null != onItemClickListener && SafeCollectionUtils.isAvailableData(mFilteredDatas, adapterPosition)) {
                    onItemClickListener.onItemClick(v.context, mFilteredDatas[adapterPosition] as HospitalItem)
                }
            }
        }
    }
}
