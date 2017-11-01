package com.telecorp.dashqueue.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Saran on 30/10/2560.
 */

data class LoginAuthenRequestModel(@SerializedName("QueueNumber")
                                   val queueNumber: String?,
                                   @SerializedName("PhoneNumber")
                                   val phoneNumber: String?,
                                   @SerializedName("DeviceName")
                                   val deviceName: String?,
                                   @SerializedName("DeviceMacAddress")
                                   val deviceMacAddress: String?,
                                   @SerializedName("HospitalID")
                                   val hospitalId: Long?) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readValue(Long::class.java.classLoader) as Long?
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(queueNumber)
        writeString(phoneNumber)
        writeString(deviceName)
        writeString(deviceMacAddress)
        writeValue(hospitalId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<LoginAuthenRequestModel> = object : Parcelable.Creator<LoginAuthenRequestModel> {
            override fun createFromParcel(source: Parcel): LoginAuthenRequestModel = LoginAuthenRequestModel(source)
            override fun newArray(size: Int): Array<LoginAuthenRequestModel?> = arrayOfNulls(size)
        }
    }
}
