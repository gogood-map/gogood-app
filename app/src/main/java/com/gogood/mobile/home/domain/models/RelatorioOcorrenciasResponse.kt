package com.gogood.mobile.home.domain.models

data class RelatorioOcorrenciasResponse(
    val top5Ocorrencias: List<CrimeQtd>,
    val qtdMes: QtdMes,
    val qtdOcorrencias: Int
)


data class CrimeQtd(
    val crime: String,
    val qtdOcorrido: Int
)

data class QtdMes(
    var janeiro: Int = 0,
    var fevereiro: Int = 0,
    var marco: Int = 0,
    var abril: Int = 0,
    var maio: Int = 0,
    var junho: Int = 0,
    var julho: Int = 0,
    var agosto: Int = 0
)
