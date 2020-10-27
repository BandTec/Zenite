package com.orion.zenite.model

data class ViagemDiaria (
    val viagensRealizadas : Int,
    val viagensRestantes : Int,
    val listaViagens: ArrayList<Viagens>
)