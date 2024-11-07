package com.gogood.mobile.home.data.repository

import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse
import com.gogood.mobile.home.domain.models.OcorrenciasRaioResponse
import retrofit2.Response

interface IMapRepository {

    suspend fun obterOcorrenciasRaio(lat: Double, lng: Double, raio: Double):Response<OcorrenciasRaioResponse>
    suspend fun buscarEndereco(entrada: String): Response<BuscaEnderecoResponse>
}