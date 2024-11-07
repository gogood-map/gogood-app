package com.gogood.mobile.home.data.repository.remote

import com.gogood.mobile.home.domain.services.MapsService
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse
import com.gogood.mobile.home.domain.models.OcorrenciasRaioResponse
import com.gogood.mobile.home.domain.services.GooglePlacesService
import retrofit2.Response

class MapRepository(private val service: MapsService,
                    private val googlePlacesService: GooglePlacesService): IMapRepository {
    override suspend fun obterOcorrenciasRaio(
        lat: Double,
        lng: Double,
        raio: Double
    ): Response<OcorrenciasRaioResponse> {
       return service.obterOcorrenciasRaio(lat, lng, raio)
    }

    override suspend fun buscarEndereco(entrada: String): Response<BuscaEnderecoResponse> {
        return googlePlacesService.buscarEndereco(entrada)
    }


}