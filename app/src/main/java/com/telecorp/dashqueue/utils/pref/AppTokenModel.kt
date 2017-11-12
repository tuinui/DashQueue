package com.telecorp.dashqueue.utils.pref

import android.os.Parcel
import android.os.Parcelable
import com.telecorp.dashqueue.api.model.HospitalItem

/**
 * Created by Saran on 12/11/2560.
 */

class AppTokenModel(
        val queueNumber: String?,
        val phoneNumber: String?,
        val hospitalItem: HospitalItem?) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readParcelable<HospitalItem>(HospitalItem::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(queueNumber)
        writeString(phoneNumber)
        writeParcelable(hospitalItem, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AppTokenModel> = object : Parcelable.Creator<AppTokenModel> {
            override fun createFromParcel(source: Parcel): AppTokenModel = AppTokenModel(source)
            override fun newArray(size: Int): Array<AppTokenModel?> = arrayOfNulls(size)
        }
    }
}
