package com.orion.zenite.model


data class Conta (
    val idConta: Int,
    val senha: String,
    val email: String,
    val nivel: Nivel
)

data class Nivel(
    val id: Int,
    val descricao: String
)

