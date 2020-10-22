package com.orion.zenite.http.fiscal

import com.orion.zenite.model.Linha
import com.orion.zenite.model.Onibus
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FiscalApi {

    // TODAS AS LINHAS DO FISCAL X
    @GET("/api/fiscal/{id}/linhas")
    fun getFiscalLinhas(@Path("id") id: Int, @Header("authorization") auth: String): Call<List<Linha>>

    // MOTORISTAS E ONIBUS DA LINHA Y
    @GET("/api/linha/{id}/onibus")
    fun getLinhaMotoristaOnibus(@Path("id") id: Int, @Header("authorization") auth: String): Call<List<Onibus>>


    // TODO => CAMERA => ABRIR E FECHAR VIAGEM


}