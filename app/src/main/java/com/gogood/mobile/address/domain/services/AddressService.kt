package com.gogood.mobile.address.domain.services

import com.gogood.mobile.address.domain.model.AddressRequest
import com.gogood.mobile.address.domain.model.AddressResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AddressService {

    @GET("api/enderecos/{idUsuario}")
    fun getUserAddresses(
        @Path("idUsuario") idUsuario: Int
    ): Response<List<AddressResponse>>

    @POST("api/enderecos")
    fun saveAddress(
        @Body address: AddressRequest
    ): Response<AddressResponse>

    fun updateAddress()
    fun deleteAddress()
}

