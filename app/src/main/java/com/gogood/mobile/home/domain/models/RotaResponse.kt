package com.gogood.mobile.home.domain.models

data class RotaResponse(
    val origem: String,
    val destino: String,
    val distancia: Double,
    val duracao: String,
    val horarioSaida: String,
    val horarioChegada: String,
    val qtdOcorrenciasTotais: Int,
    val polyline: String,
    val etapas: List<Etapa>,

)

data class Etapa(
    val instrucao: String
)


