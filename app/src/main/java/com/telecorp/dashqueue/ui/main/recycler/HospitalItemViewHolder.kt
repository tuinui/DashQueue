package com.telecorp.dashqueue.ui.main.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import com.telecorp.dashqueue.api.model.HospitalItem
import kotlinx.android.synthetic.main.view_hospital_item.view.*

/**
 * Created by Saran on 13/10/2560.
 */

class HospitalItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val imgIcon = itemView.imageloaderview_hospital_item_image
    private val tvTitle = itemView.textview_hospital_item_title


    fun bind(data: HospitalItem?) {
        if (null != data) {
            imgIcon.loadImage(data.imageLogoPath, null, false)
            tvTitle.text = data.title
        }
    }
}
