package com.orion.zenite.http

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class HttpHelper {

    // Definir a URL do servidor coloque o IP da sua maquina no lugar de localhost
    // AWS: "http://3.86.5.222:8080",
    // Azure: "https://zenitebackend.azurewebsites.net",
    val URL = "http://3.86.5.222:8080"

    // Definir o cabeçalho
    val headerHttp = MediaType.parse("application/json; charset=utf-8")

    // Criar um cliente que vai fazer a requisição a API
    val client = OkHttpClient()

    fun post (uri: String,json: String): String {
        // Criar corpo da requisição
        val body = RequestBody.create(headerHttp, json)

        // Construir a requisição POST http para o servidor
        var request = Request.Builder().url("$URL$uri").post(body).build()

        val response = client.newCall(request).execute()

        val responseBody = response.body()

        var json = "";

        if(responseBody != null) {
            json = responseBody.string()
            println("=========== POST - $uri ============")
            print(json)
            println("=========== POST - $uri ============")
            return json;
        }else {
            return json;
        }
    }

    fun get(uri: String): String{
        // Construindo requisição
        val request = Request.Builder().url("$URL$uri").get().build()

        val response = client.newCall(request).execute()

        val responseBody = response.body()

        var json = "";

        if(responseBody != null) {
            json = responseBody.string()
            println("=========== GET - $uri ============")
            print(json)
            println("=========== GET - $uri ============")
            return json;
        }else {
            return json;
        }
    }

    fun put (uri: String,json: String): String {
        // Criar corpo da requisição
        val body = RequestBody.create(headerHttp, json)

        // Construir a requisição POST http para o servidor
        var request = Request.Builder().url("$URL$uri").put(body).build()

        val response = client.newCall(request).execute()

        val responseBody = response.body()

        var json = "";

        if(responseBody != null) {
            json = responseBody.string()
            println("============ PUT - $uri ===========")
            print(json)
            println("============ PUT - $uri ===========")
            return json;
        }else {
            return json;
        }
    }

    fun delete(uri: String): String{
        // Construindo requisição
        val request = Request.Builder().url("$URL$uri").get().build()

        val response = client.newCall(request).execute()

        val responseBody = response.body()

        var json = "";

        if(responseBody != null) {
            json = responseBody.string()
            println("=========== DELETE - $uri ============")
            print(json)
            println("=========== DELETE - $uri ============")
            return json;
        }else {
            return json;
        }
    }
}