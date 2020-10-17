package com.orion.zenite.http.fiscal

import com.orion.zenite.model.Linha
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FiscalApi {

    @GET("/api/fiscal/{id}/linhas")
    fun getFiscalLinhas(@Path("id") id: Int, @Header("authorization") auth: String): Call<List<Linha>>

}