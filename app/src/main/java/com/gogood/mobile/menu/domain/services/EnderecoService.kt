package com.gogood.mobile.menu.domain.services

import com.gogood.mobile.menu.domain.models.EnderecoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService {

    @GET("api/enderecos/{id}")
    suspend fun obter(@Path("id")id:Int):Response<List<EnderecoResponse>>
}