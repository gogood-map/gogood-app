package com.gogood.mobile.home.domain.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MapsService {
    @GET("api/consultar/local/{lat}/{lng}")
    suspend fun obterOcorrenciasRaio(
        @Path("lat") lat: Double,
        @Path("lng") lng: Double,
        @Query("raio") raio: Double
    ):Response<RaioOcorrenciasResponse>
}


data class RaioOcorrenciasResponse(
    val qtdOcorrencias: Int,
    val coordenadasOcorrencias: List<List<Double>>
)


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
