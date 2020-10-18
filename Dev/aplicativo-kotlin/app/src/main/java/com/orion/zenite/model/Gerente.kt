package com.orion.zenite.model

data class Gerente (
    val id: Int,
    val nome: String,
    val cpf: String,
    val dataNascimento: String,
    val numeroTelefone: String,
    val endereco: Endereco
)


