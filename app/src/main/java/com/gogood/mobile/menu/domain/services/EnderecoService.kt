package com.gogood.mobile.menu.domain.services

import com.gogood.mobile.address.domain.model.AddressRequest
import com.gogood.mobile.address.domain.model.AddressResponse
import com.gogood.mobile.address.domain.services.AddressService
import com.gogood.mobile.menu.domain.models.EnderecoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService : AddressService {

    @GET("api/enderecos/{id}")
    suspend fun obter(@Path("id")id:Int):Response<List<EnderecoResponse>>
    override fun getUserAddresses(idUsuario: Int): Response<List<AddressResponse>> {
        TODO("Not yet implemented")
    }

    override fun saveAddress(address: AddressRequest): Response<AddressResponse> {
        TODO("Not yet implemented")
    }

    override fun updateAddress() {
        TODO("Not yet implemented")
    }

    override fun deleteAddress() {
        TODO("Not yet implemented")
    }
}