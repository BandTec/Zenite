package com.orion.zenite.http.fiscal

import com.orion.zenite.model.*
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.*

interface FiscalApi {

    // TODAS AS LINHAS DO FISCAL X
    @GET("/api/fiscal/{id}/linhas")
    fun getFiscalLinhas(@Path("id") id: Int, @Header("authorization") auth: String): Call<List<Linha>>

    // MOTORISTAS E ONIBUS DA LINHA Y
    @GET("/api/linha/{id}/onibus")
    fun getLinhaMotoristaOnibus(@Path("id") id: Int, @Header("authorization") auth: String): Call<List<Onibus>>

    // iniciar viagem
    @POST("/api/viagem")
    fun iniciarViagem(@Body info: IniciarViagem,
                      @Header("authorization"
                      ) auth: String) : Call<Void>

    // finalizar viagem
    @PUT("/api/viagem/{idViagem}/{idFiscal}")
    fun finalizarViagem(@Path("idViagem") idViagem: Int,
                        @Path("idFiscal") idFiscal: Int,
                        @Header("authorization"
                    ) auth: String): Call<Void>

    // ADICIONAR QTD PASSAGEIROS
    @PUT("/api/viagem/{idViagem}/qtdPassageiros")
    fun adicionarPassageiros(@Path("idViagem") idViagem: Int,
                             @Body qtdPassageiros: QtdPassageiros,
                             @Header("authorization") auth: String
                            ) : Call<Void>

    @GET("/api/horarios/linha/{id}")
    fun getLinhaCronograma(@Path("id") id: Int, @Header("authorization") auth: String): Call<List<Cronograma>>

    @GET("/api/horarios/fiscal/{id}/cronograma/proximahora")
    fun getCronogramaGeral(@Path("id") id: Int, @Header("authorization") auth: String): Call<List<CronogramaGeral>>
}