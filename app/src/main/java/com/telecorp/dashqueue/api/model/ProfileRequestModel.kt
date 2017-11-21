package com.telecorp.dashqueue.api.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Saran on 12/11/2560.
 */

class ProfileRequestModel(@SerializedName("QueueNumber")
                                 val queueNumber: String?,
                          @SerializedName("Tel")
                                 val phoneNumber: String?,
                          @SerializedName("HospitalID")
                                 val hospitalId: Long?)