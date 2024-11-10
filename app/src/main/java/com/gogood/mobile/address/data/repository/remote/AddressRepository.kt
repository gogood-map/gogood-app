package com.gogood.mobile.address.data.repository.remote

import com.gogood.mobile.address.data.repository.IAddressRepository
import com.gogood.mobile.address.domain.model.AddressRequest
import com.gogood.mobile.address.domain.model.AddressResponse
import com.gogood.mobile.address.domain.services.AddressService
import retrofit2.Response

class AddressRepository(private val addressService: AddressService) : IAddressRepository {
    override suspend fun getUserAddresses(userId: Int): Response<List<AddressResponse>> {
        return addressService.getUserAddresses(userId)
    }

    override suspend fun saveAddress(address: AddressRequest): Response<AddressResponse> {
        return addressService.saveAddress(address)
    }
}