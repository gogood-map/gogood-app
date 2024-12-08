package com.gogood.mobile.address.data.repository

import com.gogood.mobile.address.domain.model.AddressRequest
import com.gogood.mobile.address.domain.model.AddressResponse
import retrofit2.Response

interface IAddressRepository {
    suspend fun getUserAddresses(userId: Int): Response<List<AddressResponse>>
    suspend fun saveAddress(address: AddressRequest): Response<AddressResponse>
}