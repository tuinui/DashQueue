package com.telecorp.dashqueue.api;

import com.telecorp.dashqueue.api.model.HospitalItem;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface TelecorpApiInterface {
    @GET("hospital")
    Observable<ArrayList<HospitalItem>> getHospital();
}