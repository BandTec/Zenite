package com.orion.zenite.model

data class Endereco (
    val id: Int,
    val cep: String,
    val logradouro: String,
    val numero: String,
    val complemento: String?,
    val cidade: String,
    val estado: String
)
