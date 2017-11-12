package com.telecorp.dashqueue.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class WaitingQueue() : Parcelable {
    /*
          "UID": 2,
        "MSOrganizationUID": 5,
        "TrPatientQueueUID": 1,
        "QueueNumber": "A001",
        "LocationQueueName": "Medical Center",
        "DoctorName": "ทดสอบ",
        "WaitingNumber": "3",
        "ProcessName": "รอตรวจ",
        "CWhen": "0001-01-01T00:00:00",
        "CurrentTime": "2017-10-30T23:12:57.0186452+07:00"
         */
    @SerializedName("UID")
    var uid: Long? = null

    @SerializedName("MSOrganizationUID")
    var organizationUID: Long? = null

    @SerializedName("TrPatientQueueUID")
    var trPatientQueueUID: Long? = null

    @SerializedName("QueueNumber")
    var queueNo: String? = null

    @SerializedName("LocationQueueName")
    var locationQueueName: String? = null

    @SerializedName("DoctorName")
    var doctorName: String? = null

    @SerializedName("WaitingNumber")
    var waitingNumber: String? = null

    @SerializedName("ProcessName")
    var processName: String? = null

    @SerializedName("CWhen")
    var cWhen: String? = null

    @SerializedName("CurrentTime")
    var currentIime: String? = null

    constructor(parcel: Parcel) : this() {
        uid = parcel.readValue(Long::class.java.classLoader) as? Long
        organizationUID = parcel.readValue(Long::class.java.classLoader) as? Long
        trPatientQueueUID = parcel.readValue(Long::class.java.classLoader) as? Long
        queueNo = parcel.readString()
        locationQueueName = parcel.readString()
        doctorName = parcel.readString()
        waitingNumber = parcel.readString()
        processName = parcel.readString()
        cWhen = parcel.readString()
        currentIime = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(uid)
        parcel.writeValue(organizationUID)
        parcel.writeValue(trPatientQueueUID)
        parcel.writeString(queueNo)
        parcel.writeString(locationQueueName)
        parcel.writeString(doctorName)
        parcel.writeString(waitingNumber)
        parcel.writeString(processName)
        parcel.writeString(cWhen)
        parcel.writeString(currentIime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WaitingQueue> {
        override fun createFromParcel(parcel: Parcel): WaitingQueue {
            return WaitingQueue(parcel)
        }

        override fun newArray(size: Int): Array<WaitingQueue?> {
            return arrayOfNulls(size)
        }
    }

}

