package com.telecorp.dashqueue.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Waiting() : Parcelable {
    @SerializedName("Number")
    var number: Long? = null
    @SerializedName("QueueNumber")
    var queueNumber: String? = null
    @SerializedName("ProcessName")
    var processName: String? = null
    @SerializedName("CurrentQueue")
    var currentQueue: String? = null
    @SerializedName("Currentdate")
    var currentdate: String? = null

    constructor(parcel: Parcel) : this() {
        number = parcel.readValue(Long::class.java.classLoader) as? Long
        queueNumber = parcel.readString()
        processName = parcel.readString()
        currentQueue = parcel.readString()
        currentdate = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(number)
        parcel.writeString(queueNumber)
        parcel.writeString(processName)
        parcel.writeString(currentQueue)
        parcel.writeString(currentdate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Waiting> {
        override fun createFromParcel(parcel: Parcel): Waiting {
            return Waiting(parcel)
        }

        override fun newArray(size: Int): Array<Waiting?> {
            return arrayOfNulls(size)
        }
    }


}

