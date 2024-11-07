package com.gogood.mobile.home.domain.services

import com.gogood.mobile.BuildConfig
import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesService {
    @GET("api/place/findplacefromtext/json")
    suspend fun buscarEndereco(
        @Query("input") entrada: String,
        @Query("inputtype") tipoEntrada: String = "textquery",
        @Query("fields") campos: String = "geometry",
        @Query("key") chave:String = BuildConfig.GOOGLE_KEY
    ): Response<BuscaEnderecoResponse>
}