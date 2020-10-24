package com.orion.zenite.model

data class UserZenite(
    val id: Int,
    val nome: String,
    val cpf: String,
    val dataNascimento: String,
    val numeroTelefone: String,
    val endereco: Endereco,
    val registroFiscal: String,
    val conta: Conta,
    val linhas: List<String>,
    val cnh: String?,
    val carro: Carro?
)


data class Carro(
    val id: Int,
    val numero: String,
    val placa: String,
    val modelo: String,
    val fabricante: String,
    val acessivel: Boolean,
    val qtdPassageirosSentados: Int,
    val qtdPassageirosEmPe: Int,
    val gerente: UserZenite?,
    val motoristaTelefone: String,
    val linhaId: Int,
    val linha: String,
    val motorista: String
)