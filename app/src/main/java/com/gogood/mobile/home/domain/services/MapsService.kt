package com.gogood.mobile.home.domain.services

import com.gogood.mobile.BuildConfig
import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse
import com.gogood.mobile.home.domain.models.OcorrenciasRaioResponse
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
    ):Response<OcorrenciasRaioResponse>



}





