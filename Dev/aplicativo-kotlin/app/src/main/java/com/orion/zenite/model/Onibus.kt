package com.orion.zenite.model

data class Onibus(
    val id: Int,
    val numero: String,
    val placa: String,
    val modelo: String,
    val fabricante: String,
    val acessivel: Boolean,
    val qtdPassageirosSentados: Int,
    val qtdPassageirosEmPe: Int,
    val gerente: Gerente,
    val conta: Conta,
    val linha: String,
    val motorista: String,
    val motoristaTelefone: String,
    val linhaId: Int
)
