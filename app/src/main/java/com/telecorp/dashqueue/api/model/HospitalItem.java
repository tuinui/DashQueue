package com.telecorp.dashqueue.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Saran on 13/10/2560.
 */

public class HospitalItem {

    @SerializedName("UID")
    private long uid;
    @SerializedName("code")
    private String code;
    @SerializedName("title")
    private String title;
    @SerializedName("active")
    private String active;
    @SerializedName("urlapl")
    private String urlapl;
    @SerializedName("ImageLogo")
    private String imageLogo;
    @SerializedName("PathImageLogo")
    private String imageLogoPath;

    public HospitalItem() {
    }

    private HospitalItem(long uid, String code, String title, String active, String urlapl, String imageLogo, String imageLogoPath) {
        this.uid = uid;
        this.code = code;
        this.title = title;
        this.active = active;
        this.urlapl = urlapl;
        this.imageLogo = imageLogo;
        this.imageLogoPath = imageLogoPath;
    }

    public long getUid() {
        return uid;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getActive() {
        return active;
    }

    public String getUrlapl() {
        return urlapl;
    }

    public String getImageLogo() {
        return imageLogo;
    }

    public String getImageLogoPath() {
        return imageLogoPath;
    }


    public static HospitalItem getMock(String hello){
        return new HospitalItem(1L,hello+"1",hello,hello,hello,hello,hello);
    }
}
