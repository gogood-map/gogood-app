package com.gogood.mobile.home.data.repository.remote

import com.gogood.mobile.home.domain.services.MapsService
import com.gogood.mobile.home.domain.services.RaioOcorrenciasResponse
import com.gogood.mobile.home.data.repository.IMapRepository
import retrofit2.Response

class MapRepository(private val service: MapsService): IMapRepository {
    override suspend fun obterOcorrenciasRaio(
        lat: Double,
        lng: Double,
        raio: Double
    ): Response<RaioOcorrenciasResponse> {
       return service.obterOcorrenciasRaio(lat, lng, raio)
    }
}