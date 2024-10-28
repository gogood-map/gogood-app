package com.gogood.mobile.home.data.repository

import com.gogood.mobile.home.domain.services.RaioOcorrenciasResponse
import retrofit2.Response

interface IMapRepository {

    suspend fun obterOcorrenciasRaio(lat: Double, lng: Double, raio: Double):Response<RaioOcorrenciasResponse>
}