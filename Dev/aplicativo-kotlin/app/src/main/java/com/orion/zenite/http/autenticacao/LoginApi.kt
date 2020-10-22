package com.orion.zenite.http.autenticacao

import com.orion.zenite.model.Token
import com.orion.zenite.model.Usuario
import retrofit2.Call
import retrofit2.http.*

interface LoginApi {

    //AUTENTICAÇÃO PARA AS OUTRAS ROTAS
    @POST("/autentica/login")
    fun postloginRequest(@Body usuario: Usuario): Call<Token>


    // BUSCA USUARIO
    @GET("/autentica/login")
    fun getUsuario(
        @Path("id") id: Int,
        @Header("authorization") auth: String
    ): Call<Token>

}