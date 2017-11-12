package com.telecorp.dashqueue.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Saran on 12/11/2560.
 */

class RegisterTokenRequestModel (@SerializedName("QueueNumber")
                                 val queueNumber: String?,
                                 @SerializedName("Tel")
                                 val phoneNumber: String?,
                                 @SerializedName("DeviceID")
                                 val deviceID : String?,
                                 @SerializedName("HospitalID")
                                 val hospitalId: Long?){
    @SerializedName("Platform")
    val deviceName = "Android"


}
