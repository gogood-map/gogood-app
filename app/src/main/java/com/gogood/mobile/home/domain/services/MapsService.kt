package com.gogood.mobile.home.domain.services

import com.gogood.mobile.BuildConfig
import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse
import com.gogood.mobile.home.domain.models.LatLngOcorrencia
import com.gogood.mobile.home.domain.models.RelatorioOcorrenciasResponse
import com.gogood.mobile.home.domain.models.RotaHistoricoRequest
import com.gogood.mobile.home.domain.models.RotaHistoricoResponse
import com.gogood.mobile.home.domain.models.RotaResponse
import com.google.android.gms.maps.model.LatLng
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MapsService {
    @GET("consultar/localMobile/{lat}/{lng}")
    suspend fun obterOcorrenciasRaio(
        @Path("lat") lat: Double,
        @Path("lng") lng: Double,
        @Query("raio") raio: Double
    ):Response<List<LatLng>>

    @GET("consultar/buscaOcorrencia/{lat}/{lng}")
    suspend fun obterRelatorioRaio(
        @Path("lat") lat: Double,
        @Path("lng") lng: Double,
        @Query("raio") raio: Double
    ):Response<RelatorioOcorrenciasResponse>


    @GET("rotas/{meio}")
    suspend fun obterRota(
        @Path("meio") meio: String,
        @Query("origem") origem: String,
        @Query("destino") destino: String
    ):Response<List<RotaResponse>>

    @POST("historico-rota")
    suspend fun salvarHistoricoRota(
        @Body request: RotaHistoricoRequest
    ):Response<RotaHistoricoResponse>
}





