package com.orion.zenite.model


data class CronogramaGeral (
    val idLinha: Int,

    val nomeLinha: String,

    val cronograma: ArrayList<Cronograma>
)