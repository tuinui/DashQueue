package com.telecorp.dashqueue.api.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.page365.store365.generic.searchable.SearchableEntity

/**
 * Created by Saran on 13/10/2560.
 */

class HospitalItem() : SearchableEntity, Parcelable {
    @SerializedName("UID")
    var uid: Long? = null

    @SerializedName("code")
    var code: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("active")
    var active: String? = null

    @SerializedName("urlapl")
    var urlapl: String? = null

    @SerializedName("ImageLogo")
    var imageLogo: String? = null

    @SerializedName("PathImageLogo")
    var imageLogoPath: String? = null

    constructor(parcel: Parcel) : this() {
        uid = parcel.readValue(Long::class.java.classLoader) as? Long
        code = parcel.readString()
        title = parcel.readString()
        active = parcel.readString()
        urlapl = parcel.readString()
        imageLogo = parcel.readString()
        imageLogoPath = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(uid)
        parcel.writeString(code)
        parcel.writeString(title)
        parcel.writeString(active)
        parcel.writeString(urlapl)
        parcel.writeString(imageLogo)
        parcel.writeString(imageLogoPath)
    }

    override fun getCriteria(): String {
        return title.toString();
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HospitalItem> {
        override fun createFromParcel(parcel: Parcel): HospitalItem {
            return HospitalItem(parcel)
        }

        override fun newArray(size: Int): Array<HospitalItem?> {
            return arrayOfNulls(size)
        }
    }


}
