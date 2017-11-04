package com.page365.store365.generic.searchable

import android.support.v7.widget.RecyclerView
import com.telecorp.dashqueue.utils.SafeCollectionUtils
import java.util.*

/**
 * Created by SoftBaked on 5/16/2017 AD.
 */

abstract class SearchableRecyclerAdapter<E : RecyclerView.ViewHolder> : RecyclerView.Adapter<E>() {

    protected var mDatas: ArrayList<SearchableEntity> = ArrayList()
    protected var mFilteredDatas: ArrayList<SearchableEntity> = ArrayList()
    private val mFilter: SearchableRecyclerAdapterFilter
    var criteriaText: String? = null
        private set

    init {
        this.mFilter = SearchableRecyclerAdapterFilter(mDatas, this)
    }

    fun replaceData(datas: List<SearchableEntity>) {
        mDatas.clear()
        mFilteredDatas.clear()
        notifyDataSetChanged()
        mFilteredDatas.addAll(datas)
        mDatas.addAll(datas)
        notifyDataSetChanged()
    }

    fun setFilteredData(filteredData: List<SearchableEntity>) {
        this.mFilteredDatas.clear()
        this.mFilteredDatas.addAll(filteredData)
        notifyItemChanged(0, itemCount)
    }

    fun filterList(text: String) {
        criteriaText = text
        mFilter.filter(text)
    }

    override fun getItemCount(): Int {
        return SafeCollectionUtils.getSize(mFilteredDatas)
    }


}
