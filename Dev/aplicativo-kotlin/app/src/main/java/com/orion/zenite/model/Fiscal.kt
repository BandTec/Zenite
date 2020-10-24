package com.orion.zenite.model

data class Fiscal(
    val id: Int,
    val nome: String,
    val cpf: String,
    val dataNascimento: String,
    val numeroTelefone: String,
    val endereco: Endereco,
    val registroFiscal: String,
    val conta: Conta,
    val linhas: List<String>
)