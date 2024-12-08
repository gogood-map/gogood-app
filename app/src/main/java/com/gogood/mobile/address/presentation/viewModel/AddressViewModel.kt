package com.gogood.mobile.address.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.address.data.repository.IAddressRepository
import com.gogood.mobile.address.domain.model.AddressRequest
import kotlinx.coroutines.launch

class AddressViewModel(private val addressRepository: IAddressRepository) : ViewModel() {
    var addressRequest by mutableStateOf(AddressRequest())
    var isLoading by mutableStateOf(true)
    var isError by mutableStateOf(false)
    var isOk by mutableStateOf(false)

    fun cadastrarEndereco(addressRequest: AddressRequest) {
        viewModelScope.launch {
            val resposta = addressRepository.saveAddress(addressRequest)
            if (resposta.isSuccessful) {
                isLoading = false
                isOk = true
            } else {
                isLoading = false
                isError = true
            }
        }
    }

    fun getUserAddresses(userId: Int) {
        viewModelScope.launch {
            val resposta = addressRepository.getUserAddresses(userId)
            if (resposta.isSuccessful) {
                isLoading = false
                isOk = true
            } else {
                isLoading = false
                isError = true
            }
        }
    }
}
