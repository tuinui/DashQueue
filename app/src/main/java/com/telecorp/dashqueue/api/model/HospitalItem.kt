package com.telecorp.dashqueue.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * Created by Saran on 13/10/2560.
 */

class HospitalItem : Parcelable {
    @SerializedName("UID")
    var uid: Long? = null

    @SerializedName("code")
    var code: String? = null

    @SerializedName("title")
    var title: String? = ""

    @SerializedName("active")
    var active: String? = ""

    @SerializedName("urlapl")
    var urlapl: String? = ""

    @SerializedName("ImageLogo")
    var imageLogo: String? = ""

    @SerializedName("PathImageLogo")
    var imageLogoPath: String? = ""

    constructor()

    private constructor (uid: Long, code: String, title: String, active: String, urlapl: String, imageLogo: String, imageLogoPath: String) {
        this.uid = uid
        this.code = code
        this.title = title
        this.active = active
        this.urlapl = urlapl
        this.imageLogo = imageLogo
        this.imageLogoPath = imageLogoPath
    }

    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        fun getMock(hello: String): HospitalItem {
            return HospitalItem(1L, hello + "1", hello, hello, hello, hello, hello)
        }

        @JvmField
        val CREATOR: Parcelable.Creator<HospitalItem> = object : Parcelable.Creator<HospitalItem> {
            override fun createFromParcel(source: Parcel): HospitalItem = HospitalItem(source)
            override fun newArray(size: Int): Array<HospitalItem?> = arrayOfNulls(size)
        }
    }
}
