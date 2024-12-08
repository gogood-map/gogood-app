package com.gogood.mobile.menu.domain.services

import com.gogood.mobile.address.domain.model.AddressRequest
import com.gogood.mobile.address.domain.model.AddressResponse
import com.gogood.mobile.address.domain.services.AddressService
import com.gogood.mobile.home.domain.models.RotaHistoricoResponse
import com.gogood.mobile.menu.domain.models.EnderecoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoService : AddressService {

    @GET("enderecos/{id}")
    suspend fun obterEnderecosFavoritosUsuario(@Path("id")id:Int):Response<List<EnderecoResponse>>

    @GET("historico-rota/{id}")
    suspend fun obterHistoricoRotasUsuario(@Path("id")id:Int):Response<List<RotaHistoricoResponse>>

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