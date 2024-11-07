package com.gogood.mobile.home.domain.models

data class RelatorioOcorrenciasResponse(
    val top5Ocorrencias: List<CrimeQtd>,
    val qtdMes: QtdMes,
    val qtdOcorrencias: Int
)


data class CrimeQtd(
    val crime: String,
    val qtd: Int
)

data class QtdMes(
    val janeiro: Int,
    val fevereiro: Int,
    val marco: Int,
    val abril: Int,
    val maio: Int,
    val junho: Int,
    val julho: Int,
    val agosto: Int
)
