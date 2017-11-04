package com.telecorp.dashqueue.utils

/**
 * Created by saranakkarawiwat on 9/5/2017 AD.
 */

class SafeIndexCollectionsUtils {
    companion object {


        fun isAvailableData(list: Collection<*>?, holderAdapterPosition: Int): Boolean {
            return null != list && !list.isEmpty() && holderAdapterPosition >= 0 && list.size > holderAdapterPosition
        }

        fun getSize(list: Collection<*>?): Int {
            return list?.size ?: 0
        }


        fun isEmpty(list: Collection<*>?): Boolean {
            return list?.isEmpty() ?: true
        }
    }


}
