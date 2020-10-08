package com.orion.zenite.model

data class Cronograma (
    val horarioAntigo: String,

    val horario: String,

    val nomeMotorista: String,

    val horarioChegada: String,

    val atrasado: Boolean
)