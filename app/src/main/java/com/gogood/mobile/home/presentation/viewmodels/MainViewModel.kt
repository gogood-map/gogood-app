package com.gogood.mobile.home.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.utils.ConexaoInternetObserver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel( private val conexaoObserver: ConexaoInternetObserver): ViewModel() {
    private val _conectado = MutableStateFlow(false)
    val contectado: StateFlow<Boolean> = _conectado

    init {
        viewModelScope.launch {
            conexaoObserver.isConnected.collect{
                _conectado.value = it
            }
        }
    }


}