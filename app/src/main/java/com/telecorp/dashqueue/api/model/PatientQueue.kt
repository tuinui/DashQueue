package com.telecorp.dashqueue.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PatientQueue() : Parcelable {
    /*
     {
            "UID": 1,
            "MSOrganizationUID": 5,
            "MemberUID": 1,
            "QueueNumber": "A001",
            "HN": "18-11-007603",
            "EN": "O18-17-285601",
            "PhoneNumber": "08283762563",
            "VisitDate": "2017-10-09T09:39:16",
            "QueueDate": "2017-10-09T09:45:16",
            "Prename": "นาย",
            "FirstName": "ทดสอบ",
            "LastName": "ทดสอบ",
            "DOB": "1986-03-07T00:00:00",
            "PatientTypeName": "Appiontment",
            "CWhen": "2017-10-09T09:39:16"
        }
     */
    @SerializedName("UID")
    var uid: Long? = null
    @SerializedName("MSOrganizationUID")
    var organizationUID: Long? = null
    @SerializedName("MemberUID")
    var memberUID: Long? = null
    @SerializedName("QueueNumber")
    var queueNo: String? = null
    @SerializedName("HN")
    var hn: String? = null
    @SerializedName("EN")
    var en: String? = null
    @SerializedName("PhoneNumber")
    var phoneNumber: String? = null
    @SerializedName("VisitDate")
    var visitDate: String? = null
    @SerializedName("QueueDate")
    var queueDate: String? = null
    @SerializedName("Prename")
    var preName: String? = null
    @SerializedName("FirstName")
    var firstName: String? = null
    @SerializedName("LastName")
    var lastName: String? = null
    @SerializedName("DOB")
    var dob: String? = null
    @SerializedName("PatientTypeName")
    var patientTypeName: String? = null
    @SerializedName("CWhen")
    var cWhen: String? = null

    constructor(parcel: Parcel) : this() {
        uid = parcel.readValue(Long::class.java.classLoader) as? Long
        organizationUID = parcel.readValue(Long::class.java.classLoader) as? Long
        memberUID = parcel.readValue(Long::class.java.classLoader) as? Long
        queueNo = parcel.readString()
        hn = parcel.readString()
        en = parcel.readString()
        phoneNumber = parcel.readString()
        visitDate = parcel.readString()
        queueDate = parcel.readString()
        preName = parcel.readString()
        firstName = parcel.readString()
        lastName = parcel.readString()
        dob = parcel.readString()
        patientTypeName = parcel.readString()
        cWhen = parcel.readString()
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(uid)
        parcel.writeValue(organizationUID)
        parcel.writeValue(memberUID)
        parcel.writeString(queueNo)
        parcel.writeString(hn)
        parcel.writeString(en)
        parcel.writeString(phoneNumber)
        parcel.writeString(visitDate)
        parcel.writeString(queueDate)
        parcel.writeString(preName)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(dob)
        parcel.writeString(patientTypeName)
        parcel.writeString(cWhen)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PatientQueue> {
        override fun createFromParcel(parcel: Parcel): PatientQueue {
            return PatientQueue(parcel)
        }

        override fun newArray(size: Int): Array<PatientQueue?> {
            return arrayOfNulls(size)
        }
    }
}

