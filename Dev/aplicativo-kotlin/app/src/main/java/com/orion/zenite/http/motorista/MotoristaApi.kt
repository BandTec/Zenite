package com.orion.zenite.http.motorista

import com.orion.zenite.model.CronogramaHorarioSimples
import com.orion.zenite.model.HistoricoViagens
import com.orion.zenite.model.ViagemDiaria
import com.orion.zenite.model.Viagens
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface MotoristaApi {
    //Gera QR
    @GET("/api/qrcode/{id}")
    fun getQrcode(@Path("id") id: Int, @Header("authorization") auth: String): Call<ResponseBody>

    //Consulta viagem atual ou proxima
    @GET("api/horarios/motorista/{id}/viagem/atual")
    fun consultarViagemAtualOuProxima(
        @Path("id") id: Int,
        @Header("authorization") auth: String
    ): Call<Viagens>

    //Consulta viagens do dia
    @GET("api/horarios/motorista/{id}/viagem/dia")
    fun consultarViagensDia(
        @Path("id") id: Int,
        @Header("authorization") auth: String
    ): Call<ViagemDiaria>

    //Consulta viagens
    @GET("api/viagem/app/motorista/{id}")
    fun consultarTodasViagens(
        @Path("id") id: Int,
        @Header("authorization") auth: String
    ): Call<List<HistoricoViagens>>
}