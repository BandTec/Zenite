package com.orion.zenite.http.autenticacao

import com.orion.zenite.model.*
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
    ): Call<Fiscal>

    //ROTA RECUPERAR SENHA
    @GET("/esqueci-senha/{email}")
    fun getEmailRecuperacao(
        @Path("email") email: EmailRecuperacaoSenha
        //@Header("authorization") auth: String
    ):Call<Void>
}