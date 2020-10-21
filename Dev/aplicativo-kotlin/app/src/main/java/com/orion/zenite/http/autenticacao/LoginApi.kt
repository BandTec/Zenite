package com.orion.zenite.http.autenticacao

import com.orion.zenite.model.Conta
import com.orion.zenite.model.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface LoginApi {

    //AUTENTICAÇÃO PARA AS OUTRAS ROTAS
    @POST("/autentica/login")
    fun loginRequest(@Body usuario: Conta): Call<LoginResponse>


    // BUSCA USUARIO
    @GET("/autentica/login")
    fun getUsuario(
        @Path("id") id: Int,
        @Header("authorization") auth: String
    ): Call<LoginResponse>

}