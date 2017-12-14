package com.telecorp.dashqueue.api

import com.telecorp.dashqueue.api.model.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

interface TelecorpApiInterface {
    @GET("hospital")
    fun getHospital() : Observable<ArrayList<HospitalItem>>

    /*
    Method: POST
URL:  http://telecorp.co.th/QCore/api/LoginAuthen/
Data POST:
{
    "QueueNumber": "A001",
    "PhoneNumber": "08283762563",
    "DeviceName": "BOMZ16",
    "DeviceMacAddress": "ทดสอบ",
    "HospitalID": "5"
 }

     */
    @POST("LoginAuthen")
    fun postLoginAuthen(@Body data: LoginAuthenRequestModel): Observable<LoginAuthenResponseModel>



    @POST("Registerdservice")
    fun postRegisterToken(@Body data: TokenRequestModel):Observable<SuccessResponseModel>

    @POST("Logout")
    fun postLogoutAuthen(@Body tokenRequestModel: TokenRequestModel):Observable<SuccessResponseModel>

    /*
    POST=> http://telecorp.co.th/QCore/api/PageOther/

{
    "QueueNumber": "A511",
    "Tel": "0827551713",
    "HospitalID": "5"
 }
     */
    @POST("PageOther")
    fun postLandingPage(@Body profileRequestModel: ProfileRequestModel):Observable<PageUrlModel>

}