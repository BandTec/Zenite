package com.orion.zenite.model

data class Cronograma (
    val horarioAntigo: String,

    val horarioPrevisto: String,

    val nomeMotorista: String,

    val horarioRealizado: String,

    val atrasado: Boolean
)