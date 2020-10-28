package com.orion.zenite.model

import com.google.gson.annotations.SerializedName

data class Linha (

    val id: Int,
    val numero: String,
    val pontoIda: PontoFinal,
    val pontoVolta: PontoFinal,
    val fiscal: String,
    val fiscalNumero: String,
    val fiscalId: Int,
    val carrosId: List<Int>,
    val carros: List<String>
)