package com.orion.zenite.administrador

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AdminApi {
    @FormUrlEncoded
    @POST("/api/administrador")
    fun createAdmin (
        @Field("email") email:String,
        @Field("senha") senha : String
    ): Call<Void>
}