package com.orion.zenite.http.autenticacao

import com.orion.zenite.model.*
import com.orion.zenite.model.UserZenite
import com.orion.zenite.model.Token
import com.orion.zenite.model.Usuario
import retrofit2.Call
import retrofit2.http.*

interface LoginApi {

    //AUTENTICAÇÃO PARA AS OUTRAS ROTAS
    @POST("/autentica/login")
    fun postloginRequest(@Body usuario: Usuario): Call<Token>


    // DADOS DA CONTA
    @GET("/logado")
    fun getDadosConta(
        @Header("authorization") auth: String
    ): Call<UserZenite>

    //ROTA RECUPERAR SENHA
    @GET("/api/esqueci-senha/{email}")
    fun getEmailRecuperacao(
        @Path("email") email: String
        //@Header("authorization") auth: String
    ):Call<Void>
}