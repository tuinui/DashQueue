package com.telecorp.dashqueue.ui.main.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.telecorp.dashqueue.R;
import com.telecorp.dashqueue.api.model.HospitalItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saran on 13/10/2560.
 */

public class HospitalItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.sdv_hospital_item_image)
    SimpleDraweeView imgIcon;
    @BindView(R.id.textview_hospital_item_title)
    TextView tvTitle;
    public HospitalItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(HospitalItem data) {
        if(null != data){
            imgIcon.setImageURI(data.getImageLogoPath());
            tvTitle.setText(data.getTitle());
        }
    }
}
