package com.telecorp.dashqueue.ui.main.hospitallist.recycler;

import android.support.v7.util.DiffUtil;

import com.telecorp.dashqueue.api.model.HospitalItem;

import java.util.List;

/**
 * Created by Saran on 13/10/2560.
 */

public class HospitalItemDiffCallback extends DiffUtil.Callback {
    private List<HospitalItem> oldList;
    private List<HospitalItem> newList;

    public HospitalItemDiffCallback(List<HospitalItem> oldList, List<HospitalItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return null != oldList ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return null != newList ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
       if(oldList != null && newList != null){
           return oldList.get(oldItemPosition).getUid() == newList.get(newItemPosition).getUid();
       }
       return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
