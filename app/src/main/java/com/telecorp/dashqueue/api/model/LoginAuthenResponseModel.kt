package com.telecorp.dashqueue.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Saran on 30/10/2560.
 */

class LoginAuthenResponseModel {
    /*
    {
    "PatientQueueList": [

    ],
    "WaitingQueue": {

    },
    "WaitingList": [
        {
            "Number": 2,
            "QueueNumber": "G929",
            "ProcessName": "รอตรวจ",
            "CurrentQueue": "N",
            "Currentdate": "2017-10-30T23:12:57.0186452+07:00"
        },
        {
            "Number": 3,
            "QueueNumber": "G930",
            "ProcessName": "ทำหัตถการ",
            "CurrentQueue": "N",
            "Currentdate": "2017-10-30T23:12:57.0186452+07:00"
        },
        {
            "Number": 4,
            "QueueNumber": "A001",
            "ProcessName": "รอตรวจ",
            "CurrentQueue": "Y",
            "Currentdate": "2017-10-30T23:12:57.0186452+07:00"
        }
    ]
}
     */
    @SerializedName("PatientQueueList")
    val patientQueueArrayList: ArrayList<PatientQueue>? = null
    @SerializedName("WaitingQueue")
    val waitingQueue: WaitingQueue? = null
    @SerializedName("WaitingList")
    val waitingList: ArrayList<Waiting>? = null


}

class Waiting {
    /*{
            "Number": 4,
            "QueueNumber": "A001",
            "ProcessName": "รอตรวจ",
            "CurrentQueue": "Y",
            "Currentdate": "2017-10-30T23:12:57.0186452+07:00"
        }*/
    @SerializedName("Number")
    val number: Long? = null
    @SerializedName("QueueNumber")
    val queueNumber: String? = null
    @SerializedName("ProcessName")
    val processName: String? = null
    @SerializedName("CurrentQueue")
    val currentQueue: String? = null
    @SerializedName("Currentdate")
    val currentdate: String? = null

}

class PatientQueue {
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
    val uid: Long? = null
    @SerializedName("MSOrganizationUID")
    val organizationUID: Long? = null
    @SerializedName("MemberUID")
    val memberUID: Long? = null
    @SerializedName("QueueNumber")
    val queueNo: String? = null
    @SerializedName("HN")
    val hn: String? = null
    @SerializedName("EN")
    val en: String? = null
    @SerializedName("PhoneNumber")
    val phoneNumber: String? = null
    @SerializedName("VisitDate")
    val visitDate: String? = null
    @SerializedName("QueueDate")
    val queueDate: String? = null
    @SerializedName("Prename")
    val preName: String? = null
    @SerializedName("FirstName")
    val firstName: String? = null
    @SerializedName("LastName")
    val lastName: String? = null
    @SerializedName("DOB")
    val dob: String? = null
    @SerializedName("PatientTypeName")
    val patientTypeName: String? = null
    @SerializedName("CWhen")
    val cWhen: String? = null
}

class WaitingQueue {
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
    val uid: Long? = null
    @SerializedName("MSOrganizationUID")
    val organizationUID: Long? = null
    @SerializedName("TrPatientQueueUID")
    val trPatientQueueUID: Long? = null
    @SerializedName("QueueNumber")
    val queueNo: String? = null
    @SerializedName("LocationQueueName")
    val locationQueueName: String? = null
    @SerializedName("DoctorName")
    val doctorName: String? = null
    @SerializedName("WaitingNumber")
    val waitingNumber: String? = null
    @SerializedName("ProcessName")
    val processName: String? = null
    @SerializedName("CWhen")
    val cWhen: String? = null
    @SerializedName("CurrentTime")
    val currentIime: String? = null


}
