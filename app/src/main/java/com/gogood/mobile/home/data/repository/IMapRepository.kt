package com.gogood.mobile.home.data.repository

import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse
import com.gogood.mobile.home.domain.models.OcorrenciasRaioResponse
import com.gogood.mobile.home.domain.models.RelatorioOcorrenciasResponse
import com.gogood.mobile.home.domain.models.RotaResponse
import retrofit2.Response

interface IMapRepository {

    suspend fun obterOcorrenciasRaio(lat: Double, lng: Double, raio: Double):Response<OcorrenciasRaioResponse>
    suspend fun buscarEndereco(entrada: String): Response<BuscaEnderecoResponse>
    suspend fun buscarRota(meioTransporte:String,origem: String, destino: String): Response<List<RotaResponse>>
    suspend fun buscarRelatorioRaio(lat:Double, lng: Double, raio: Double):Response<RelatorioOcorrenciasResponse>
}