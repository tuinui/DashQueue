package com.page365.store365.generic.searchable

import android.text.TextUtils
import android.widget.Filter
import java.util.*

/**
 * Created by SoftBaked on 5/16/2017 AD.
 */

class SearchableRecyclerAdapterFilter(datas: ArrayList<SearchableEntity>, private val mAdapter: SearchableRecyclerAdapter<*>) : Filter() {

    private var mDatas = ArrayList<SearchableEntity>()
    private var mFilteredDatas = ArrayList<SearchableEntity>()

    init {
        this.mDatas = datas
        this.mFilteredDatas = ArrayList()
    }


    override fun performFiltering(constraint: CharSequence): Filter.FilterResults {
        mFilteredDatas.clear()
        val results = Filter.FilterResults()

        //here you need to add proper items do filteredContactList
        for (item in mDatas) {
            val name = item.getCriteria()
            if (!TextUtils.isEmpty(name) && name.trim { it <= ' ' }.contains(constraint.toString().toLowerCase())) {
                mFilteredDatas.add(item)
            }
        }

        results.values = mFilteredDatas
        results.count = mFilteredDatas.size
        return results
    }

    override fun publishResults(constraint: CharSequence, results: Filter.FilterResults) {
        mAdapter.setFilteredData(mFilteredDatas)
        mAdapter.notifyDataSetChanged()
    }

    //    public class UserServiceFilter extends Filter {
    //
    //        private List<ProviderUserListGson.Data.UserService> contactList;
    //        private List<ProviderUserListGson.Data.UserService> filteredContactList;
    //        private PloyerPersonListRecyclerAdapter mAdapter;
    //
    //        public UserServiceFilter(List<ProviderUserListGson.Data.UserService> contactList, PloyerPersonListRecyclerAdapter mAdapter) {
    //            this.mAdapter = mAdapter;
    //            this.contactList = contactList;
    //            this.filteredContactList = new ArrayList();
    //        }
    //
    //        @Override
    //        protected FilterResults performFiltering(CharSequence constraint) {
    //            filteredContactList.clear();
    //            final FilterResults results = new FilterResults();
    //
    //            //here you need to add proper items do filteredContactList
    //            for (final ProviderUserListGson.Data.UserService item : contactList) {
    //                String name = item.getFullName().toLowerCase();
    //                if (name.trim().contains(String.valueOf(constraint).toLowerCase())) {
    //                    filteredContactList.add(item);
    //                }
    //            }
    //
    //            results.values = filteredContactList;
    //            results.count = filteredContactList.size();
    //            return results;
    //        }
    //
    //        @Override
    //        protected void publishResults(CharSequence constraint, FilterResults results) {
    //            mAdapter.setFilteredData(filteredContactList);
    //            mAdapter.notifyDataSetChanged();
    //        }
    //    }


}
