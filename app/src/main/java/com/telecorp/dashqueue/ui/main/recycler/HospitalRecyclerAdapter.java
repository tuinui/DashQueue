package com.telecorp.dashqueue.ui.main.recycler;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.telecorp.dashqueue.R;
import com.telecorp.dashqueue.api.model.HospitalItem;
import com.telecorp.dashqueue.utils.SafeCollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saran on 13/10/2560.
 */

public class HospitalRecyclerAdapter extends RecyclerView.Adapter<HospitalItemViewHolder> {
    private List<HospitalItem> mItems = new ArrayList<>();

    public HospitalRecyclerAdapter() {
    }

    public void replaceDatas(List<HospitalItem> newDatas){
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(
                new HospitalItemDiffCallback(mItems, newDatas), false);
        mItems.clear();
        mItems.addAll(newDatas);
        result.dispatchUpdatesTo(this);
    }

    @Override
    public HospitalItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HospitalItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_hospital_item,parent,false));
    }

    @Override
    public void onBindViewHolder(HospitalItemViewHolder holder, int position) {
        if(SafeCollectionUtils.isAvailableData(mItems,position)){
            holder.bind(mItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return SafeCollectionUtils.getSize(mItems);
    }





}
